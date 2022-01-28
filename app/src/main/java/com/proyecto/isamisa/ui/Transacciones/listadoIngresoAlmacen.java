package com.proyecto.isamisa.ui.Transacciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.proyecto.isamisa.Adaptadores.ListadoIngresosAdapter;
import com.proyecto.isamisa.Adaptadores.ListadoSolicitudesAdapter;
import com.proyecto.isamisa.Entidades.Solicitud;
import com.proyecto.isamisa.Entidades.Whingreso;
import com.proyecto.isamisa.Interface.whingresoService;
import com.proyecto.isamisa.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class listadoIngresoAlmacen extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ListadoIngresosAdapter adapter;
    private List<Whingreso> mwhingreso;
    private TextView documento,ruc,empresa,contacto;
    private Button visual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_ingreso_almacen);

        mRecyclerView=findViewById(R.id.recicleringreso);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retro = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/")
        .addConverterFactory(GsonConverterFactory.create()).build();

        whingresoService service = retro.create(whingresoService.class);
        Call<ArrayList<Whingreso>> call = service.listaringreso();

        call.enqueue(new Callback<ArrayList<Whingreso>>() {
            @Override
            public void onResponse(Call<ArrayList<Whingreso>> call, Response<ArrayList<Whingreso>> response) {

                if(response.isSuccessful()){
                    mwhingreso=response.body();


                    adapter= new ListadoIngresosAdapter(listadoIngresoAlmacen.this,mwhingreso);
                    mRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Whingreso>> call, Throwable t) {

            }
        });




    }
}