package com.proyecto.isamisa.ui.Transacciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.proyecto.isamisa.Adaptadores.ListadoEgresoAdapter;
import com.proyecto.isamisa.Adaptadores.ListadoIngresosAdapter;
import com.proyecto.isamisa.Entidades.Whingreso;
import com.proyecto.isamisa.Entidades.Whsalida;
import com.proyecto.isamisa.Interface.whsalidaService;
import com.proyecto.isamisa.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class listadoEgresoAlmacen extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ListadoEgresoAdapter adapter;
    private List<Whsalida> whsalida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_egreso_almacen);
        mRecyclerView=findViewById(R.id.recicleregreso);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        Retrofit retro = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        whsalidaService service = retro.create(whsalidaService.class);
        Call<ArrayList<Whsalida>> call = service.listarsalida();
        call.enqueue(new Callback<ArrayList<Whsalida>>() {
            @Override
            public void onResponse(Call<ArrayList<Whsalida>> call, Response<ArrayList<Whsalida>> response) {
                if(response.isSuccessful()){
                    whsalida=response.body();
                    adapter= new ListadoEgresoAdapter(listadoEgresoAlmacen.this,whsalida);
                    mRecyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Whsalida>> call, Throwable t) {

            }
        });

    }


}