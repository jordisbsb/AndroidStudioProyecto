package com.proyecto.isamisa.ui.Transacciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.isamisa.Adaptadores.ListadoSolicitudesAdapter;
import com.proyecto.isamisa.Entidades.Solicitud;
import com.proyecto.isamisa.Entidades.Whingreso;
import com.proyecto.isamisa.R;

import java.util.List;

public class TransaccionesShowFragment extends Fragment {



  private CardView card1,card2,card3;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){

View root = inflater.inflate(R.layout.fragment_transacciones,container,false);
card1=root.findViewById(R.id.cardingresotrans);
card2=root.findViewById(R.id.cardegresotrans);
card3=root.findViewById(R.id.cardtransftrans);

card1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(TransaccionesShowFragment.this.getContext(),listadoIngresoAlmacen.class);
        startActivity(i);
    }
});
card2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(TransaccionesShowFragment.this.getContext(),listadoEgresoAlmacen.class);
        startActivity(i);
    }
});
card3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});



return root;

    }



}
