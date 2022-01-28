package com.proyecto.isamisa.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.isamisa.Entidades.SolicitudItem;
import com.proyecto.isamisa.R;
import com.proyecto.isamisa.ui.Transacciones.vistaIngresoAlmacen;

import java.util.ArrayList;
import java.util.List;

public class ListadoItemsAdapter extends RecyclerView.Adapter<ListadoItemsAdapter.ListadoItem> {

    private Context context;
    private List<SolicitudItem> lista = new ArrayList<>();

    public ListadoItemsAdapter(Context context, List<SolicitudItem> listado) {
        this.context=context;
        this.lista=  listado;
    }

    @NonNull
    @Override
    public ListadoItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.activity_vista_ingreso_almacen,parent,false);
        return new ListadoItem(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ListadoItem holder, int position) {
    SolicitudItem s = lista.get(position);
    holder.linea.setText(String.valueOf(s.getLine()));
    holder.prodcuto.setText(s.getProducto().getNombre());
    holder.precio.setText(String.valueOf(s.getCantidad()));
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ListadoItem extends RecyclerView.ViewHolder {

        TextView linea,precio,prodcuto;

        public ListadoItem(@NonNull View itemView) {
            super(itemView);
            linea=itemView.findViewById(R.id.lbllineaitems);
            precio=itemView.findViewById(R.id.lblprecioitems);
            prodcuto=itemView.findViewById(R.id.lblproducitem);


            



        }
    }



}
