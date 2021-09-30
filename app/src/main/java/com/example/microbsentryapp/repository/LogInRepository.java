package com.example.microbsentryapp.repository;

import android.app.Application;
import android.widget.Toast;

import com.example.microbsentryapp.model.Korisnik;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class LogInRepository {
    private Application application;
    private FirebaseAuth firebaseAuth;
    private MutableLiveData<Korisnik> korisnikMutableLiveData;

    public LogInRepository(Application application) {
        this.application = application;

        firebaseAuth = FirebaseAuth.getInstance();
        korisnikMutableLiveData = new MutableLiveData<>();
    }

    public void logInUser(String username, String password) {
        firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Korisnik korisnik = new Korisnik(username, password);
                            korisnikMutableLiveData.postValue(korisnik);
                        } else {
                            Toast.makeText(application, "Log in fail: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    /*public void logOutUser() {
        //gore definisemo loggedOutMutableData i napravimo getter
        firebaseAuth.signOut();
        loggedOutMutableData.postValue("true");

    }*/

    public MutableLiveData<Korisnik> getKorisnikMutableLiveData() {
        return korisnikMutableLiveData;
    }
}
