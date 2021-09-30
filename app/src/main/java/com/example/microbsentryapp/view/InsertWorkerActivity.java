package com.example.microbsentryapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.microbsentryapp.R;
import com.example.microbsentryapp.model.Zaposleni;
import com.example.microbsentryapp.viewModel.InsertWorkerViewModel;

public class InsertWorkerActivity extends AppCompatActivity {
    private static final String[] STORAGES = new String[] {"1a", "2r", "3x", "4s", "5e"};

    EditText etName, etSurname, etCity, etID;
    MultiAutoCompleteTextView macStorageUnits;
    Button btnInsertWorker;

    private InsertWorkerViewModel insertWorkerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_worker);

        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etID = findViewById(R.id.etId);
        etCity = findViewById(R.id.etCity);
        macStorageUnits = findViewById(R.id.macStorageUnits);
        btnInsertWorker = findViewById(R.id.btnInsertWorker);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, STORAGES);
        macStorageUnits.setAdapter(adapter);
        macStorageUnits.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        insertWorkerViewModel = new ViewModelProvider(this).get(InsertWorkerViewModel.class);
        insertWorkerViewModel.getZaposleniMutableLiveData().observe(this, new Observer<Zaposleni>() {
            @Override
            public void onChanged(Zaposleni zaposleni) {
                Toast.makeText(InsertWorkerActivity.this, "Worker inserted", Toast.LENGTH_SHORT).show();
            }
        });

        btnInsertWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String surname = etSurname.getText().toString().trim();
                String id = etID.getText().toString().trim();
                String city = etCity.getText().toString().trim();
                String storageUnits = macStorageUnits.getText().toString().trim();
                //String[] storageUnits = macStorageUnits.getText().toString().split("\\s*,\\s*");

                if (name.isEmpty()) {
                    etName.setError("Please enter name");
                    etName.requestFocus();
                } else if(surname.isEmpty()) {
                    etSurname.setError("Please enter surname");
                    etSurname.requestFocus();
                } else if (id.isEmpty()) {
                    etID.setError("Please enter id");
                    etID.requestFocus();
                } else if (city.isEmpty()){
                    etCity.setError("Please enter city");
                    etCity.requestFocus();
                } else if (storageUnits.isEmpty()) {
                    macStorageUnits.setError("Please add storage unit");
                    macStorageUnits.requestFocus();
                } else insertWorkerViewModel.insertWorker(name, surname, id, city, storageUnits);
            }
        });

    }
}