package com.proyecto.isamisa.ui.Producto;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.proyecto.isamisa.R;

public class ProductoshowFragment extends Fragment {

    private CardView card,cardagregar;

    private ProductoshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(ProductoshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_producto, container, false);
        card=root.findViewById(R.id.cardlistadoproducto);
        cardagregar=root.findViewById(R.id.cardagregarproducto);


        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductoshowFragment.this.getContext(),ListadoProducto.class);
                startActivity(i);
            }
        });

        cardagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductoshowFragment.this.getContext(),AgregarProducto.class);
                startActivity(i);
            }
        });

        return root;
    }
}