package com.proyecto.isamisa.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.isamisa.Entidades.Whsalida;
import com.proyecto.isamisa.R;

import java.util.List;

public class ListadoEgresoAdapter extends RecyclerView.Adapter<ListadoEgresoAdapter.EgresoViewHoler>{

    private Context context;
    private List<Whsalida> lista;

    public ListadoEgresoAdapter(Context context, List<Whsalida> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public EgresoViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_listado_egreso_almacen, parent, false);
        return new EgresoViewHoler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EgresoViewHoler holder, int position) {
        Whsalida s = lista.get(position);
        holder.id.setText(String.valueOf(s.getId()));
        holder.centrocosto.setText(s.getCentroCosto().getNombre());
        holder.nomtransaccion.setText(s.getTipotransaccion().getNombre());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class EgresoViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView id,centrocosto,nomtransaccion;
        private Button visual;
        public EgresoViewHoler(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.lblidegresoal);
            centrocosto=itemView.findViewById(R.id.lblcentcost);
            nomtransaccion=itemView.findViewById(R.id.lbltransnom);
            visual=itemView.findViewById(R.id.btnvisualidaregre);
        }
        void setOnClickListeners(){
            visual.setOnClickListener(this);

        }



        @Override
        public void onClick(View v) {


        }
    }


}
