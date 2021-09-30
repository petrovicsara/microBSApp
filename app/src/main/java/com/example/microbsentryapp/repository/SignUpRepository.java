package com.example.microbsentryapp.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.microbsentryapp.model.Korisnik;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class SignUpRepository {
    private Application application;
    private FirebaseAuth firebaseAuth;
    private MutableLiveData<Korisnik> korisnikMutableLiveData;

    public SignUpRepository(Application application) {
        this.application = application;

        firebaseAuth = FirebaseAuth.getInstance();
        korisnikMutableLiveData = new MutableLiveData<>();
    }

    public void signUpUser(String name, String username, String password) {

        firebaseAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Korisnik korisnik = new Korisnik(name, username);

                            FirebaseDatabase.getInstance().getReference("Korisnici").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                                    .setValue(korisnik).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Log.d("korisnik", "korisnik dodao i dodatna polja");
                                    }
                                }
                            });

                            korisnikMutableLiveData.postValue(korisnik);

                        } else {
                            Toast.makeText(application, "Sign up fail: " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public MutableLiveData<Korisnik> getKorisnikMutableLiveData() {
        return korisnikMutableLiveData;
    }
}
