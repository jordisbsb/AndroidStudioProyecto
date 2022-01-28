package com.proyecto.isamisa.ui.Persona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.proyecto.isamisa.Adaptadores.ListadoPersonaAdapter;
import com.proyecto.isamisa.Entidades.Persona;
import com.proyecto.isamisa.Interface.personaService;
import com.proyecto.isamisa.R;
import com.proyecto.isamisa.ui.Producto.ListadoProducto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoPersona extends AppCompatActivity {

    GridView grid;
    Button editarpersona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_persona);

    grid=findViewById(R.id.grvpersona);

    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/")
            .addConverterFactory(GsonConverterFactory.create()).build();

        personaService perservice = retrofit.create(personaService.class);
        final Call<List<Persona>> list = perservice.getpersona();

        list.enqueue(new Callback<List<Persona>>() {
            @Override
            public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {
                if(response.isSuccessful()){
                    List<Persona> per = response.body();
                    ListadoPersonaAdapter adapter = new ListadoPersonaAdapter(ListadoPersona.this,R.layout.item_listado_persona,per);
                    grid.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<List<Persona>> call, Throwable t) {

            }
        });




    }

}