package com.owllearning.mobile.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.owllearning.mobile.data.repository.AuthRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnexionViewModel extends ViewModel {
    private final AuthRepository repository;

    private final MutableLiveData<String> token = new MutableLiveData<>();
    private final MutableLiveData<String> erreur = new MutableLiveData<>();
    private final MutableLiveData<Boolean> chargement = new MutableLiveData<>(false);

    public ConnexionViewModel(AuthRepository authRepository) {
        this.repository = authRepository;
    }

    public void seConnecter(String email, String motDePasse) {
        chargement.setValue(true);
        erreur.setValue(null);

        repository.connexion(email, motDePasse, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                chargement.setValue(false);

                if(response.isSuccessful() && response.body() != null)
                {
                    token.setValue(response.body());
                }
                else if (response.code() == 401 || response.code() == 403) {
                    erreur.setValue("Email ou mot de passe incorrect");
                } else {
                    erreur.setValue("Un problème côté serveur est survenu");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                chargement.setValue(false);
                erreur.setValue("Impossible de joindre le serveur");
            }
        });
    }

    public LiveData<String> getToken() {
        return token;
    }

    public LiveData<String> getErreur() {
        return erreur;
    }

    public LiveData<Boolean> getChargement() {
        return chargement;
    }
}
