package com.proyecto.isamisa.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.isamisa.Entidades.Solicitud;
import com.proyecto.isamisa.R;
import com.proyecto.isamisa.ui.Solicitudes.visualizarSolicitud;

import java.util.List;

public class ListadoSolicitudesAdapter extends RecyclerView.Adapter<ListadoSolicitudesAdapter.SolicitudViewHolder> {

    private Context mContext;
    private List<Solicitud> carga;
    private View.OnClickListener listener;

public ListadoSolicitudesAdapter(Context context, List<Solicitud> listsolitud){
this.mContext=context;
this.carga=listsolitud;

}

    @NonNull
    @Override
    public SolicitudViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.item_listado_solicitudes, parent, false);
        return new SolicitudViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SolicitudViewHolder holder, int position) {
//Carga = el listado de solicitud;
    final Solicitud s = carga.get(position);
    holder.decripcion.setText(s.getDescripcion());
    if(s.getEstado().equals("P")){
        holder.estado.setText("Pendiente");
    } else if (s.getEstado().equals("A")){
        holder.estado.setText("Atendido");
    } else {
        holder.estado.setText("Anulado");
    }
    holder.fecha.setText(DateFormat.format("dd/MM/yyyy",s.getFecha()));
    holder.numtip.setText(String.valueOf(s.getNumtipo()));
    holder.nomtip.setText(s.getNomtipo());
    holder.per.setText(s.getPersona().getNombre1() +" "+ s.getPersona().getNombre2());
    holder.trans.setText(s.getTiposol().getNombre());
        holder.setOnClickListeners();



    }

    @Override
    public int getItemCount() {
        return carga.size();
    }


    public class SolicitudViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView decripcion,estado,fecha,numtip,nomtip,per,trans;
    private Button visualizar;

        public SolicitudViewHolder(@NonNull View itemView) {
            super(itemView);
            decripcion=itemView.findViewById(R.id.lbldescripsoli);
            estado=itemView.findViewById(R.id.lblestadosol);
            fecha=itemView.findViewById(R.id.lblfechasol);
            numtip=itemView.findViewById(R.id.lblnumtipsol);
            nomtip=itemView.findViewById(R.id.lblnomtipsol);
            visualizar=itemView.findViewById(R.id.btnvisualizarsol);
            per=itemView.findViewById(R.id.lblpersonasol);
            trans=itemView.findViewById(R.id.lbltiposoli);
        }


        public void setOnClickListeners() {
            visualizar.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case  R.id.btnvisualizarsol:
                    Intent i = new Intent(mContext.getApplicationContext(), visualizarSolicitud.class);
                    mContext.startActivity(i);
                    break;
            }


        }




    }





}
