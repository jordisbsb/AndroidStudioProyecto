package com.proyecto.isamisa.ui.Solicitudes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.isamisa.Adaptadores.ListadoProducAdapter;
import com.proyecto.isamisa.Adaptadores.ListadoSolicitudesAdapter;
import com.proyecto.isamisa.Entidades.Producto;
import com.proyecto.isamisa.Entidades.Solicitud;
import com.proyecto.isamisa.Interface.solicitudService;
import com.proyecto.isamisa.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SolicitudesShowFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ListadoSolicitudesAdapter adapter;
    private List<Solicitud> msolicitud;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){


        View root = inflater.inflate(R.layout.fragment_solicitudes,container,false);
        mRecyclerView = root.findViewById(R.id.recyclersolicitud);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        Retrofit retro = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        solicitudService service = retro.create(solicitudService.class);
        Call<ArrayList<Solicitud>> sol = service.getListaSolicitud();
        sol.enqueue(new Callback<ArrayList<Solicitud>>() {
            @Override
            public void onResponse(Call<ArrayList<Solicitud>> call, Response<ArrayList<Solicitud>> response) {
                if(response.isSuccessful()){
                    msolicitud=response.body();
                    Log.i("solicituuuuuuuuuuuuuuuuuuuuuuuuuuuuuddddddddddddddddddddddddddddddddddddddddd"
                    ,"responseeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                    adapter= new ListadoSolicitudesAdapter( getActivity(),msolicitud);
                    mRecyclerView.setAdapter(adapter);

                    Log.i("solicituuuuuuuuuuuuuuuuuuuuuuuuuuuuuddddddddddddddddddddddddddddddddddddddddd"
                            ,"recicleeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeerrrrrrrr");
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Solicitud>> call, Throwable t) {
                Log.i("faileeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
                        ,"faaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaiiiiiiiiiiiiiiiiileeeeeeeeeeeeeeeeeeeeeeeeeeee  "+t.getMessage());
                t.getMessage();
            }
        });



        return root;

    }
}
