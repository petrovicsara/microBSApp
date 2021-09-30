package com.example.microbsentryapp.repository;

import android.app.Application;
import android.widget.Toast;

import com.example.microbsentryapp.model.Zaposleni;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.lifecycle.MutableLiveData;

public class InsertWorkerRepository {
    Application application;
    DatabaseReference databaseReference;
    MutableLiveData<Zaposleni> zaposleniMutableLiveData;

    public InsertWorkerRepository(Application application) {
        this.application = application;

        zaposleniMutableLiveData = new MutableLiveData<>();
    }

    public void insertWorker(String name, String surname, String id, String city, String storageUnits) {
        Zaposleni zaposlen = new Zaposleni(name,surname,id,city,storageUnits);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Zaposleni");
        databaseReference.push().setValue(zaposlen);
        Toast.makeText(application, "Data inserted", Toast.LENGTH_SHORT).show();
    }

    public MutableLiveData<Zaposleni> getZaposleniMutableLiveData() {
        return zaposleniMutableLiveData;
    }
}
