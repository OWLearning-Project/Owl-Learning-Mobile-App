package com.owllearning.mobile.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owllearning.mobile.R;

public class MessagerieFragment extends Fragment {

    public MessagerieFragment() {
        super(R.layout.fragment_messagerie);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messagerie, container, false);
    }
}