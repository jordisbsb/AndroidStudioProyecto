package com.proyecto.isamisa.Adaptadores;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.isamisa.ui.Producto.EditarProducto;
import com.proyecto.isamisa.Entidades.Producto;
import com.proyecto.isamisa.Interface.productoService;
import com.proyecto.isamisa.R;
import com.proyecto.isamisa.ui.Producto.ProductoshowFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoProducAdapter extends RecyclerView.Adapter<ListadoProducAdapter.ProductoViewHolder> {

    private Context mContext;
    private List<Producto> carga;
    private View.OnClickListener listener;

    public ListadoProducAdapter(Context con, List<Producto> carga){
        this.mContext = con;
        this.carga = carga;
    }


    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(mContext).inflate(R.layout.item_listado_producto, parent, false);
       return new ProductoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        final Producto p = carga.get(position);
        holder.id.setText(String.valueOf(p.getId()));
        holder.nom.setText(p.getNombre());
        holder.com.setText(p.getComentario());
        holder.subfam.setText(p.getSubFamilia().toString());
        holder.unmed.setText(p.getUnMedida().toString());
        holder.estado.setText(p.getEstado());
        holder.setOnClickListeners();

    }

    @Override
    public int getItemCount() {
        return carga.size();
    }





    public class ProductoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView nom, com, subfam, unmed, estado,id;
        public Button btneditar, btnelimnar;


        public ProductoViewHolder(View itemView){
            super(itemView);
            id=itemView.findViewById(R.id.lblidproducto);
            nom = itemView.findViewById(R.id.lblnombre);
            com = itemView.findViewById(R.id.lblcomentario);
            subfam= itemView.findViewById(R.id.lblsubfam);
            unmed= itemView.findViewById(R.id.lblunmedida);
            estado= itemView.findViewById(R.id.lblestado);
            btneditar=itemView.findViewById(R.id.btneditarproducto);
            btnelimnar=itemView.findViewById(R.id.btneliminarproducto);
        }

        void setOnClickListeners(){
            btneditar.setOnClickListener(this);
            btnelimnar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btneditarproducto:

                        final String nombrepro = nom.getText().toString();
                        //final String ide = id.getText().toString();
                        final int numero = Integer.parseInt(id.getText().toString());


                    Intent viewEdtProducto = new Intent(mContext, EditarProducto.class);
                    viewEdtProducto.putExtra("idproducto",numero);
                    mContext.startActivity(viewEdtProducto);



                        break;

          //***************************************************************************
           case R.id.btneliminarproducto:
                    final String idee = id.getText().toString();
                    final int num = Integer.parseInt(idee);
                    Toast.makeText(mContext,"ELIMINAR", Toast.LENGTH_SHORT).show();
                    //________________________________________________________________________________________________________
                    AlertDialog.Builder eli = new AlertDialog.Builder(mContext);
               eli.setMessage("Quieres eliminar el producto");
               eli.setCancelable(false);
               eli.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(mContext, "PRESIONASTE EL YESSSSSS", Toast.LENGTH_SHORT).show();

                       Retrofit retro = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/")
                               .addConverterFactory(GsonConverterFactory.create()).build();

                       productoService call = retro.create(productoService.class);
                       Call<Producto> prod = call.EliminarProducto(num);
                       prod.enqueue(new Callback<Producto>() {
                           @Override
                           public void onResponse(Call<Producto> call, Response<Producto> response) {
                               if (response.isSuccessful()) {
                                   //____________________________________________________________________________
                                   Intent i = new Intent(mContext, ProductoshowFragment.class);
                                   mContext.startActivity(i);
                                   //_______________________________________________________________________________-
                               }
                           }

                           @Override
                           public void onFailure(Call<Producto> call, Throwable t) {

                           }
                       });

                   }
               });
               eli.setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                       Toast.makeText(mContext, "PRESIONASTE EL NOOOOOOOOO", Toast.LENGTH_SHORT).show();

                   }
               });//***************************************************************************+

               AlertDialog alert = eli.create();
                    alert.setTitle("Eliminar Producto");
                    alert.show();

                    //_______________________________________________________________________________________________________
                    break;
            }
        }
    }
}
