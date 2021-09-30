package com.example.microbsentryapp.view;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.microbsentryapp.R;


public class OptionsActivity extends AppCompatActivity {
    Button btnInsertWorker, btnListWorkers, btnInsertCustomer, btnListCustomers, btnStartService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        btnInsertWorker = findViewById(R.id.btnInsertWorker);
        btnListWorkers = findViewById(R.id.btnListtWorkers);
        btnInsertCustomer = findViewById(R.id.btnInsertCustomer);
        btnListCustomers = findViewById(R.id.btnListCustomers);
        btnStartService = findViewById(R.id.btnStartAService);

        btnInsertWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OptionsActivity.this, InsertWorkerActivity.class));
            }
        });

        btnListWorkers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OptionsActivity.this, ListWorkersActivity.class));
            }
        });

        btnInsertCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OptionsActivity.this, InsertCustomerActivity.class));
            }
        });

        btnListCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OptionsActivity.this, OptionsActivity.class));
            }
        });

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OptionsActivity.this, StartServiceActivity.class));
            }
        });
    }
}