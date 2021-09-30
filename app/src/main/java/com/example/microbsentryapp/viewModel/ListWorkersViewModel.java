package com.example.microbsentryapp.viewModel;

import android.app.Application;
import android.content.Context;

import com.example.microbsentryapp.model.Zaposleni;
import com.example.microbsentryapp.repository.ListWorkersRepository;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ListWorkersViewModel extends AndroidViewModel {
    private ListWorkersRepository listWorkersRepository;
    MutableLiveData<ArrayList<Zaposleni>> zaposleniListMutalbeLiveData;

    public void init(Context context) {
        if (zaposleniListMutalbeLiveData != null) {
            return;
        }
        zaposleniListMutalbeLiveData = listWorkersRepository.getZaposleniMutableLiveData();
    }

    public ListWorkersViewModel(@NonNull Application application) {
        super(application);

        listWorkersRepository = new ListWorkersRepository(application);
    }

    public LiveData<ArrayList<Zaposleni>> getZaposleniLiveData() {
        return zaposleniListMutalbeLiveData;
    }

    public void getZaposleniList() {
        listWorkersRepository.getZaposleniList();
    }

    public LiveData<ArrayList<Zaposleni>> getZaposleniListMutablLiveData() {
        return zaposleniListMutalbeLiveData;
    }
}
