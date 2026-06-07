package com.owllearning.mobile.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.owllearning.mobile.R;
import com.owllearning.mobile.data.repository.CoursRepository;
import com.owllearning.mobile.data.repository.UtilisateurRepository;
import com.owllearning.mobile.ui.adapter.CoursAdapter;
import com.owllearning.mobile.viewmodel.CatalogueViewModel;
import com.owllearning.mobile.viewmodel.ProfilViewModel;

public class CatalogueFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private View layoutErreur;
    private CatalogueViewModel viewModel;

    public CatalogueFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_catalogue, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_cours);
        progressBar = view.findViewById(R.id.progress_bar_catalogue);
        layoutErreur = view.findViewById(R.id.layout_erreur);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new CatalogueViewModel(new CoursRepository(requireContext()));
            }
        };
        viewModel = new ViewModelProvider(this, factory).get(CatalogueViewModel.class);

        viewModel.getCatalogue().observe(getViewLifecycleOwner(), catalogue -> {
            CoursAdapter adapter = new CoursAdapter(catalogue, cours -> {
                Toast.makeText(requireContext(), "Click sur le cours", Toast.LENGTH_SHORT).show();
            });
            recyclerView.setAdapter(adapter);
        });

        viewModel.getErreur().observe(getViewLifecycleOwner(), messageErreur -> {
            if (messageErreur != null) {
                layoutErreur.setVisibility(View.VISIBLE);
            } else {
                layoutErreur.setVisibility(View.GONE);
            }
        });

        viewModel.getChargement().observe(getViewLifecycleOwner(), enChargement -> {
            if (enChargement) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        viewModel.chargerCatalogue();
    }
}