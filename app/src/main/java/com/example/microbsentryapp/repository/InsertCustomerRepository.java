package com.example.microbsentryapp.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.microbsentryapp.model.Kupac;
import com.example.microbsentryapp.model.Zaposleni;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class InsertCustomerRepository {
    Application application;
    DatabaseReference databaseReference;
    MutableLiveData<Kupac> kupacMutableLiveData;
    MutableLiveData<ArrayList<Zaposleni>> zaposleniListMutablLiveData;
    private ArrayList<Zaposleni> zaposleniArrayList = new ArrayList<>();

    public InsertCustomerRepository(Application application) {
        this.application = application;

        kupacMutableLiveData = new MutableLiveData<>();
        zaposleniListMutablLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Zaposleni>> getZaposleniListMutablLiveData() {
        getZaposleniList();

        MutableLiveData<ArrayList<Zaposleni>> data = new MutableLiveData<>();
        data.setValue(zaposleniArrayList);
        return data;
    }

    public void getZaposleniList() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Zaposleni");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                zaposleniArrayList.clear();
                for (DataSnapshot zaposleniDataSnap : snapshot.getChildren()){
                    zaposleniArrayList.add(zaposleniDataSnap.getValue(Zaposleni.class));
                }
                Log.d("Zaposleni retrieve data", zaposleniArrayList + "");
                zaposleniListMutablLiveData.postValue(zaposleniArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void insertCustomer(String name, String pib, String id, String zaposleni) {
        Kupac kupac = new Kupac(name, pib, id, zaposleni.split("\\s*\\s")[1]);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Kupac");
        databaseReference.push().setValue(kupac);
        Toast.makeText(application, "Data inserted", Toast.LENGTH_SHORT).show();
    }

    public MutableLiveData<Kupac> getKupacMutableLiveData() {
        return kupacMutableLiveData;
    }
}
