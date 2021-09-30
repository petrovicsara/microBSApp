package com.example.microbsentryapp.viewModel;

import android.app.Application;

import com.example.microbsentryapp.model.Zaposleni;
import com.example.microbsentryapp.repository.InsertWorkerRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class InsertWorkerViewModel extends AndroidViewModel {
    private InsertWorkerRepository insertWorkerRepository;
    private MutableLiveData<Zaposleni> zaposleniMutableLiveData;

    public InsertWorkerViewModel(@NonNull Application application) {
        super(application);

        insertWorkerRepository = new InsertWorkerRepository(application);
        zaposleniMutableLiveData = insertWorkerRepository.getZaposleniMutableLiveData();
    }

    public void insertWorker(String name, String surname, String id, String city, String storageUnits) {
        insertWorkerRepository.insertWorker(name, surname, id, city, storageUnits);
    }

    public MutableLiveData<Zaposleni> getZaposleniMutableLiveData() {
        return zaposleniMutableLiveData;
    }
}
