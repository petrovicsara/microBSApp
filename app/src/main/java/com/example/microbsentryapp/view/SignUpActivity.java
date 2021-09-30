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
import com.example.microbsentryapp.viewModel.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity {

    EditText etName, etUsername, etPassword;
    Button btnSignUp;
    TextView tvRedirectToLogIn;

    private SignUpViewModel signUpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvRedirectToLogIn = findViewById(R.id.btnRedirectToLogin);

        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        signUpViewModel.getKorisnikMutableLiveData().observe(this, new Observer<Korisnik>() {
            @Override
            public void onChanged(Korisnik korisnik) {
                if (korisnik != null) {
                    Toast.makeText(SignUpActivity.this, "User created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, OptionsActivity.class));
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (name.isEmpty()){
                    etName.setError("Please enter your name!");
                    etName.requestFocus();
                } else if (username.isEmpty()){
                    etUsername.setError("Please enter your username!");
                    etUsername.requestFocus();
                } else if(password.isEmpty() || password.length() < 6){
                    etPassword.setError("Please enter your password! It must contaon minimum 6 characters");
                    etPassword.requestFocus();
                } else {
                    signUpViewModel.signUpUser(name, username, password);
                }
            }
        });

        tvRedirectToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
            }
        });
    }
}