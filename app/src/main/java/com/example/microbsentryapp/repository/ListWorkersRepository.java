package com.example.microbsentryapp.repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.microbsentryapp.model.Zaposleni;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class ListWorkersRepository {
    Application application;
    DatabaseReference databaseReference;
    MutableLiveData<ArrayList<Zaposleni>> zaposleniMutableLiveData;
    private ArrayList<Zaposleni> zaposleniArrayList = new ArrayList<>();


    public ListWorkersRepository(Application application) {
        this.application = application;

        zaposleniMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Zaposleni>> getZaposleniMutableLiveData() {
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
                for (DataSnapshot snapshotData : snapshot.getChildren()) {
                    zaposleniArrayList.add(snapshotData.getValue(Zaposleni.class));
                }
                Log.d("Zaposleni lista nazad", zaposleniArrayList + "");
                zaposleniMutableLiveData.postValue(zaposleniArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
