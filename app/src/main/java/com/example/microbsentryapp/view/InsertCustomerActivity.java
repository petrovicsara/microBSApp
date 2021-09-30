package com.example.microbsentryapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.microbsentryapp.R;
import com.example.microbsentryapp.model.Kupac;
import com.example.microbsentryapp.model.Zaposleni;
import com.example.microbsentryapp.viewModel.InsertCustomerViewModel;

import java.util.ArrayList;

public class InsertCustomerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText etName, etPib, etID;
    Spinner spZaposleni;
    Button btnInsertCustomer;

    private InsertCustomerViewModel insertCustomerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_customer);

        etName = findViewById(R.id.etName);
        etPib = findViewById(R.id.etPib);
        etID = findViewById(R.id.etId);
        spZaposleni = findViewById(R.id.spZaposleni);
        btnInsertCustomer = findViewById(R.id.btnInsertCustomer);

        insertCustomerViewModel = new ViewModelProvider(this).get(InsertCustomerViewModel.class);
        insertCustomerViewModel.init(InsertCustomerActivity.this);

        insertCustomerViewModel.getZaposleniListMutablLiveData().observe(this, new Observer<ArrayList<Zaposleni>>() {
            @Override
            public void onChanged(ArrayList<Zaposleni> zaposleni) {
                ArrayAdapter<Zaposleni> adapter = new ArrayAdapter<Zaposleni>(InsertCustomerActivity.this, android.R.layout.simple_spinner_item, zaposleni);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spZaposleni.setAdapter(adapter);
                spZaposleni.setOnItemSelectedListener(InsertCustomerActivity.this);
            }
        });

        insertCustomerViewModel.getKupacMutableLiveData().observe(this, new Observer<Kupac>() {
            @Override
            public void onChanged(Kupac kupac) {
                Toast.makeText(InsertCustomerActivity.this, "Customer inserted", Toast.LENGTH_SHORT).show();
            }
        });

        btnInsertCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String pib = etPib.getText().toString().trim();
                String id = etID.getText().toString().trim();
                String zaposleni = "ana, 1";

                if (name.isEmpty()) {
                    etName.setError("Please enter name");
                    etName.requestFocus();
                } else if(pib.isEmpty()) {
                    etPib.setError("Please enter surname");
                    etPib.requestFocus();
                } else if (id.isEmpty()) {
                    etID.setError("Please enter id");
                    etID.requestFocus();
                } else insertCustomerViewModel.insertCustomer(name, pib, id, zaposleni);
            }
            });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Log.d("Izabrani zaposleni", text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}