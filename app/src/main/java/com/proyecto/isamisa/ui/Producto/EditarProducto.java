package com.proyecto.isamisa.ui.Producto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.proyecto.isamisa.Entidades.Producto;
import com.proyecto.isamisa.Entidades.SubFamilia;
import com.proyecto.isamisa.Entidades.UnMedida;
import com.proyecto.isamisa.Interface.productoService;
import com.proyecto.isamisa.Interface.subfamiliaService;
import com.proyecto.isamisa.Interface.undmedidaService;
import com.proyecto.isamisa.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditarProducto extends AppCompatActivity {

    EditText nombre,comentario;
    Spinner subfam,undmedida;
    Button agregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int product = getIntent().getIntExtra("idproducto",0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);

    nombre=findViewById(R.id.txteditarnombreproducto);
    comentario= findViewById(R.id.txteditarcomentario);
    subfam= findViewById(R.id.speditarsubfamilia);
        undmedida= findViewById(R.id.speditarunmedida);
        agregar=findViewById(R.id.btneditarproducto);

        consultaProducto( product);
        subfamilia();
        unidadMedida();

agregar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      editarlo(product);

    }
});

    }

    private void unidadMedida() {

        Retrofit retro = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        undmedidaService unmed = retro.create(undmedidaService.class);
        Call<ArrayList<UnMedida>> un = unmed.getUnMedida();
        un.enqueue(new Callback<ArrayList<UnMedida>>() {
            @Override
            public void onResponse(Call<ArrayList<UnMedida>> call, Response<ArrayList<UnMedida>> response) {
                if(response.isSuccessful()){
                    ArrayList<UnMedida> und= new ArrayList<>();

                    und=response.body();
                    Log.i("UNIIDAAAAAAAAAAAAAAAAAAAAAAAAAAAADDDD","DAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+und);
                    ArrayAdapter<UnMedida> adapter = new ArrayAdapter<UnMedida>(EditarProducto.this,R.layout.support_simple_spinner_dropdown_item,und);
                    undmedida.setAdapter(adapter);
                }
            }


            @Override
            public void onFailure(Call<ArrayList<UnMedida>> call, Throwable t) {

            }
        });
    }


    private void subfamilia() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        subfamiliaService service = retrofit.create(subfamiliaService.class);
        Call<ArrayList<SubFamilia>> familia = service.getsubfamilia();
        familia.enqueue(new Callback<ArrayList<SubFamilia>>() {
            @Override
            public void onResponse(Call<ArrayList<SubFamilia>> call, Response<ArrayList<SubFamilia>> response) {
                if(response.isSuccessful()){
                    ArrayList<SubFamilia> sub = new ArrayList<>();
                    sub=response.body();
                    ArrayAdapter<SubFamilia> adapter = new ArrayAdapter<SubFamilia>(EditarProducto.this,R.layout.support_simple_spinner_dropdown_item,sub);
                    subfam.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SubFamilia>> call, Throwable t) {

            }
        });
    }


    private void editarlo(int idee){
        Retrofit r = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        productoService servi = r.create(productoService.class);
        Call<Producto> llamar = servi.obtenerProducto(idee);
        llamar.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                Producto p = response.body();
                if(response.isSuccessful()){

                    p.setNombre(nombre.getText().toString());
                    p.setComentario(comentario.getText().toString());
                    p.setSubFamilia((SubFamilia) subfam.getSelectedItem());
                    p.setUnMedida((UnMedida) undmedida.getSelectedItem());

                    Retrofit ret = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/")
                            .addConverterFactory(GsonConverterFactory.create()).build();
                    productoService serv = ret.create(productoService.class);
                    Call<Producto> proedit =serv.actualizarProducto(p,p.getId());
                    proedit.enqueue(new Callback<Producto>() {
                        @Override
                        public void onResponse(Call<Producto> call, Response<Producto> response) {
                        Producto proeditado = response.body();
                            if(response.isSuccessful()){
                                AlertDialog.Builder eli = new AlertDialog.Builder(EditarProducto.this);
                                eli.setMessage("Se Actualizo el producto  :"  + "\n"
                                        + "\n"+"Producto : " +proeditado.getNombre()
                                        + "\n"+"Comentario : " +proeditado.getComentario()
                                        + "\n"+"SubFamilia : " +proeditado.getSubFamilia()
                                        + "\n"+"Unidad Medidad : " +proeditado.getUnMedida()
                                );

                                eli.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        Intent i = new Intent(EditarProducto.this,ProductoshowFragment.class);
                                        EditarProducto.this.startActivity(i);

                                    }
                                });
                                AlertDialog alert = eli.create();
                                alert.setTitle("Producto Actualizado con exito");
                                alert.show();


                            }

                        }

                        @Override
                        public void onFailure(Call<Producto> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {

            }
        });
    }



    private void consultaProducto(int idprodcut){
        Retrofit r = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        productoService service = r.create(productoService.class);
        Call<Producto> pro = service.obtenerProducto(idprodcut);

        pro.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                Producto  respuesta = response.body();
                if(response.isSuccessful()){
                    Toast.makeText(EditarProducto.this,"Consulta con exito ",Toast.LENGTH_SHORT).show();

                    nombre.setText(respuesta.getNombre());
                    comentario.setText(respuesta.getComentario());
                    subfam.setSelection(respuesta.getSubFamilia().getId());
                    undmedida.setSelection(respuesta.getUnMedida().getId());
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {

            }
        });




    }





}