package com.owllearning.mobile.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.owllearning.mobile.data.model.response.UtilisateurResponse;
import com.owllearning.mobile.data.repository.UtilisateurRepository;

public class ProfilViewModel extends ViewModel {
    private final UtilisateurRepository utilisateurRepository;

    private final MutableLiveData<UtilisateurResponse> profil = new MutableLiveData<>();
    private final MutableLiveData<Boolean> chargement = new MutableLiveData<>(false);
    private final MutableLiveData<String> erreur = new MutableLiveData<>();

    public ProfilViewModel(UtilisateurRepository repository) {
        this.utilisateurRepository = repository;
    }

    public LiveData<UtilisateurResponse> getProfil() {
        return profil;
    }

    public LiveData<Boolean> getChargement() {
        return chargement;
    }

    public LiveData<String> getErreur() {
        return erreur;
    }

    public void chargerProfil(int idUtilisateur) {
        chargement.setValue(true);
        erreur.setValue(null);

        utilisateurRepository.getProfilUtilisateur(idUtilisateur, new UtilisateurRepository.ProfilCallback() {
            @Override
            public void onSuccess(UtilisateurResponse utilisateurResponse) {
                chargement.postValue(false);
                profil.postValue(utilisateurResponse);
            }

            @Override
            public void onError(String message) {
                chargement.postValue(false);
                erreur.postValue(message);
            }
        });
    }
}
