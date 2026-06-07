package com.owllearning.mobile.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.owllearning.mobile.data.model.response.CoursResponse;
import com.owllearning.mobile.data.repository.CoursRepository;

import java.util.List;

public class CatalogueViewModel extends ViewModel {
    private final CoursRepository coursRepository;
    private final MutableLiveData<List<CoursResponse>> catalogue = new MutableLiveData<>();
    private final MutableLiveData<Boolean> chargement = new MutableLiveData<>(false);
    private final MutableLiveData<String> erreur = new MutableLiveData<>();

    public CatalogueViewModel(CoursRepository repository) {
        this.coursRepository = repository;
    }

    public LiveData<List<CoursResponse>> getCatalogue() {
        return this.catalogue;
    }

    public LiveData<Boolean> getChargement() {
        return this.chargement;
    }

    public LiveData<String> getErreur() {
        return this.erreur;
    }

    public void chargerCatalogue() {

        if (catalogue.getValue() != null && !catalogue.getValue().isEmpty())
            return;

        chargement.setValue(true);
        erreur.setValue(null);

        coursRepository.getListeCoursPublies(new CoursRepository.CoursCallback() {
            @Override
            public void onSuccess(List<CoursResponse> listeCours) {
                chargement.postValue(false);
                catalogue.postValue(listeCours);
            }

            @Override
            public void onError(String message) {
                chargement.postValue(false);
                erreur.postValue(message);
            }
        });
    }
}
