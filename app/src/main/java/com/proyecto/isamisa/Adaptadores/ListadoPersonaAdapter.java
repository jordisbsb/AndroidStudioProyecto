package com.proyecto.isamisa.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.proyecto.isamisa.Entidades.Persona;
import com.proyecto.isamisa.R;

import java.util.List;


public class ListadoPersonaAdapter extends ArrayAdapter<Persona> {
    public ListadoPersonaAdapter(@NonNull Context context, int resource, @NonNull List<Persona> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_listado_persona,parent,false);
        }

        TextView nom = convertView.findViewById(R.id.lblnombre);
        TextView tel = convertView.findViewById(R.id.lbltelefono);
        TextView cor = convertView.findViewById(R.id.lblcorreo);
        TextView dis = convertView.findViewById(R.id.lbldistrito);

        Persona per = getItem(position);
        nom.setText(per.getNombre1());
        tel.setText(per.getTelefono());
        cor.setText(per.getCorreo());
        dis.setText(per.getDistrito().getNombre());

        return convertView;

    }
}
