package com.example.microbsentryapp.viewModel;

import android.app.Application;
import android.content.Context;

import com.example.microbsentryapp.model.Kupac;
import com.example.microbsentryapp.model.Zaposleni;
import com.example.microbsentryapp.repository.InsertCustomerRepository;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class InsertCustomerViewModel extends AndroidViewModel {
    private InsertCustomerRepository insertCustomerRepository;
    private MutableLiveData<Kupac> kupacMutableLiveData;
    MutableLiveData<ArrayList<Zaposleni>> zaposleniListMutablLiveData;

    public void init(Context context) {
        if (zaposleniListMutablLiveData != null) {
            return;
        }
        zaposleniListMutablLiveData = insertCustomerRepository.getZaposleniListMutablLiveData();
    }

    public InsertCustomerViewModel(@NonNull Application application) {
        super(application);

        insertCustomerRepository = new InsertCustomerRepository(application);
        kupacMutableLiveData = insertCustomerRepository.getKupacMutableLiveData();
    }

    public void insertCustomer(String name, String pib, String id, String zaposleni) {
        insertCustomerRepository.insertCustomer(name, pib, id, zaposleni);
    }

    public void getZaposleniList() {
        insertCustomerRepository.getZaposleniList();
    }

    public LiveData<ArrayList<Zaposleni>> getZaposleniListMutablLiveData() {
        return zaposleniListMutablLiveData;
    }

    public MutableLiveData<Kupac> getKupacMutableLiveData() {
        return kupacMutableLiveData;
    }
}
