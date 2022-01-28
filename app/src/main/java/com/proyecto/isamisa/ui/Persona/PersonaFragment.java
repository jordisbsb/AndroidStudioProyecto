package com.proyecto.isamisa.ui.Persona;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.proyecto.isamisa.R;

public class PersonaFragment extends Fragment {

    private PersonaViewModel galleryViewModel;
    private CardView car;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(PersonaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_persona, container, false);
        car=root.findViewById(R.id.cardlistado);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PersonaFragment.this.getContext(),ListadoPersona.class);
                startActivity(i);
            }
        });

        return root;
    }
}