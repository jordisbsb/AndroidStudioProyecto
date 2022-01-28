package com.proyecto.isamisa.ui.Producto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.proyecto.isamisa.Adaptadores.ListadoProducAdapter;

import com.proyecto.isamisa.Entidades.Producto;
import com.proyecto.isamisa.Interface.productoService;
import com.proyecto.isamisa.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoProducto extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ListadoProducAdapter padapter;
    private List<Producto> mProducto;

    private EditText edtTermino;
    private Button btnTerminoProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_producto);
        mRecyclerView = findViewById(R.id.grvproducto);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        edtTermino =  findViewById(R.id.edtTermino);
        btnTerminoProducto = findViewById(R.id.btnBuscarProductp);
        btnTerminoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObtenerProductoPorTermino(edtTermino.getText().toString());
            }
        });

        ListadoProductos();
//**********************************************************************************************************************

    }

    private void ListadoProductos(){
        Retrofit retro = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        final productoService pro = retro.create(productoService.class);
        Call<ArrayList<Producto>> call = pro.getProducto();
        call.enqueue(new Callback<ArrayList<Producto>>() {
            @Override
            public void onResponse(Call<ArrayList<Producto>> call, Response<ArrayList<Producto>> response) {
                if(response.isSuccessful()){
                    mProducto = response.body();
                    padapter = new ListadoProducAdapter(ListadoProducto.this, mProducto);
                    mRecyclerView.setAdapter(padapter);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Producto>> call, Throwable t) {
                Log.i("errrrrrrrrrrrrrrrrrrrrrrorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr"
                        ,"acaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+t.getMessage());
            }
        });
    }



    private void ObtenerProductoPorTermino(String termino){
        Retrofit retro = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        final productoService pro2 = retro.create(productoService.class);
        Call<ArrayList<Producto>> call2 = pro2.obtenerProductoPorTermino(termino);
        call2.enqueue(new Callback<ArrayList<Producto>>() {
            @Override
            public void onResponse(Call<ArrayList<Producto>> call, Response<ArrayList<Producto>> response) {
                if(response.isSuccessful()){
                    mProducto = response.body();
                    padapter = new ListadoProducAdapter(ListadoProducto.this, mProducto);
                    mRecyclerView.setAdapter(padapter);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Producto>> call, Throwable t) {
            }
        });
    }
//**********************************************************************************************************************

    private void getProductoxId(int i){

        Retrofit r = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        productoService service = r.create(productoService.class);
        Call<Producto> pro = service.obtenerProducto(i);

        pro.enqueue(new Callback<Producto>() {
    @Override
    public void onResponse(Call<Producto> call, Response<Producto> response) {
       Producto  respuesta = response.body();
        if(response.isSuccessful()){
            Toast.makeText(ListadoProducto.this,"Producto Eliminado "+respuesta.getNombre(),Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onFailure(Call<Producto> call, Throwable t) {

    }
});
    }

//***********************************************************************************+

private void Eliminarproducto(){

    AlertDialog.Builder eli = new AlertDialog.Builder(ListadoProducto.this);
    eli.setMessage("Quieres eliminar el producto").setCancelable(false)
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ListadoProducto.this,"PRESIONASTE EL YESSSSSS",Toast.LENGTH_SHORT).show();

                    Retrofit retro = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/")
                            .addConverterFactory(GsonConverterFactory.create()).build();

                    productoService call = retro.create(productoService.class);
                    Call<Producto> prod = call.EliminarProducto(1);
                prod.enqueue(new Callback<Producto>() {
                    @Override
                    public void onResponse(Call<Producto> call, Response<Producto> response) {
                        if(response.isSuccessful()){

                        }
                    }

                    @Override
                    public void onFailure(Call<Producto> call, Throwable t) {

                    }
                });

                }
            })
            //***************************************************************************+
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {

                     Toast.makeText(ListadoProducto.this,"PRESIONASTE EL NOOOOOOOOO",Toast.LENGTH_SHORT).show();

                 }
            });

    AlertDialog alert = eli.create();
    alert.setTitle("Eliminar Producto");
    alert.show();
}




}