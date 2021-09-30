package com.example.microbsentryapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.microbsentryapp.R;
import com.example.microbsentryapp.model.Korisnik;
import com.example.microbsentryapp.viewModel.LogInViewModel;

public class LogInActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogIn;
    TextView tvRedirectToSignUp;

    private LogInViewModel logInViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogIn = findViewById(R.id.btnLogIn);
        tvRedirectToSignUp = findViewById(R.id.btnRedirectToSignup);

        logInViewModel = new ViewModelProvider(this).get(LogInViewModel.class);
        logInViewModel.getKorisnikMutableLiveData().observe(this, new Observer<Korisnik>() {
            @Override
            public void onChanged(Korisnik korisnik) {
                if (korisnik != null) {
                    Toast.makeText(LogInActivity.this, "User logged in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LogInActivity.this, OptionsActivity.class));
                }
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (username.isEmpty()){
                    etUsername.setError("Please enter your username!");
                    etUsername.requestFocus();
                } else if(password.isEmpty() || password.length() < 6){
                    etPassword.setError("Please enter your password! It must contaon minimum 6 characters");
                    etPassword.requestFocus();
                } else {
                    logInViewModel.logInUser(username, password);
                }
            }
        });

        tvRedirectToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogInActivity.this, SignUpActivity.class));
            }
        });
    }
}