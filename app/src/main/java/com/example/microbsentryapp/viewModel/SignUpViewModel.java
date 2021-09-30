package com.example.microbsentryapp.viewModel;

import android.app.Application;

import com.example.microbsentryapp.model.Korisnik;
import com.example.microbsentryapp.repository.SignUpRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class SignUpViewModel extends AndroidViewModel {
    private SignUpRepository signUpRepository;
    private MutableLiveData<Korisnik> korisnikMutableLiveData;

    public SignUpViewModel(@NonNull Application application) {
        super(application);

        signUpRepository = new SignUpRepository(application);
        korisnikMutableLiveData = signUpRepository.getKorisnikMutableLiveData();
    }

    public void signUpUser(String name, String username, String password) {
        signUpRepository.signUpUser(name, username, password);
    }

    public MutableLiveData<Korisnik> getKorisnikMutableLiveData() {
        return korisnikMutableLiveData;
    }
}
