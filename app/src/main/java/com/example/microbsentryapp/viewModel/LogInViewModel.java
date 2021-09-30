package com.example.microbsentryapp.viewModel;

import android.app.Application;

import com.example.microbsentryapp.model.Korisnik;
import com.example.microbsentryapp.repository.LogInRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class LogInViewModel extends AndroidViewModel {
    private LogInRepository logInRepository;
    private MutableLiveData<Korisnik> korisnikMutableLiveData;

    public LogInViewModel(@NonNull Application application) {
        super(application);

        logInRepository = new LogInRepository(application);
        korisnikMutableLiveData = logInRepository.getKorisnikMutableLiveData();
    }

    public void logInUser(String username, String password) {
        logInRepository.logInUser(username, password);
    }

    public MutableLiveData<Korisnik> getKorisnikMutableLiveData() {
        return korisnikMutableLiveData;
    }
}
