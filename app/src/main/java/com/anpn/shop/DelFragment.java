package com.anpn.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class DelFragment extends Fragment {
    public DelFragment() {
        //конструктор
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //макет фрагмента
        return inflater.inflate(R.layout.fragment_del, container, false);
    }

}
