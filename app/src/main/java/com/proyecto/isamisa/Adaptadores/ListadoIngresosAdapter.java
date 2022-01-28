package com.proyecto.isamisa.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.isamisa.Entidades.Whingreso;
import com.proyecto.isamisa.R;
import com.proyecto.isamisa.ui.Transacciones.vistaIngresoAlmacen;

import java.util.List;

public class ListadoIngresosAdapter extends RecyclerView.Adapter<ListadoIngresosAdapter.IngresoViewHolder> {

    private Context mContex;
    private List<Whingreso> mlista;
    private View.OnClickListener listener;

    public ListadoIngresosAdapter(Context contex, List<Whingreso> mlista) {
        this.mContex = contex;
        this.mlista = mlista;
    }

    @NonNull
    @Override
    public IngresoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContex).inflate(R.layout.item_listado_ingreso_almacen, parent, false);
        return new IngresoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull IngresoViewHolder holder, int position) {
        Whingreso ingreso = mlista.get(position);
        holder.id.setText(String.valueOf(ingreso.getId()));
        holder.documento.setText(ingreso.getDocreferencia());
        holder.empresa.setText(ingreso.getEmpresa().getRazonsocial());
        holder.ruc.setText(ingreso.getEmpresa().getRuc());
        holder.contacto.setText(ingreso.getEmpresa().getContacto());
        holder.subalm.setText(ingreso.getSubalmacen().getNombre());
        holder.setOnClickListeners();



    }

    @Override
    public int getItemCount() {
        return mlista.size();
    }



    public class IngresoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView id,documento,empresa,ruc,contacto,subalm;
            private Button visual;


        public IngresoViewHolder(@NonNull View itemView) {
            super(itemView);

            id=itemView.findViewById(R.id.lblidingresoal);
        documento=itemView.findViewById(R.id.lbldocing);
        empresa=itemView.findViewById(R.id.lblemping);
        ruc=itemView.findViewById(R.id.lblrucing);
        contacto=itemView.findViewById(R.id.lblcontacing);
        subalm=itemView.findViewById(R.id.lblsubalmacening);
        visual=itemView.findViewById(R.id.btnvisualizarsol);
        }

        void setOnClickListeners(){
            visual.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.btnvisualizarsol :

                  final  int numero = Integer.parseInt(id.getText().toString());

                    Intent ventana = new Intent(mContex, vistaIngresoAlmacen.class);
                    ventana.putExtra("idingreso",numero);
                    mContex.startActivity(ventana);

            }


        }
    }









}
