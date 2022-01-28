

package com.proyecto.isamisa.ui.Transacciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.proyecto.isamisa.Adaptadores.ListadoItemsAdapter;
import com.proyecto.isamisa.Adaptadores.ListadoSolicitudesAdapter;
import com.proyecto.isamisa.Entidades.SolicitudItem;
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

public class vistaIngresoAlmacen extends AppCompatActivity {

TextView nombres,apellidos,tel,dni,correo,direc,estado,distrito,
        razonsocial,ruc,contacto,puestocontac,telcontac,dircontac,corcontac,actividad,estacontac,discontac
        ,nomsubalm,estasubalm,almsubalm
        ,nomalm,estadoalm,diralm
        ,tipotrans,nomtrans
        ,linea,cantidad,item;

 private RecyclerView recyclerView;
 private ListadoItemsAdapter adapter;
 private ArrayList<SolicitudItem> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int encontrado = getIntent().getIntExtra("idingreso",0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_ingreso_almacen);
        recyclerView=findViewById(R.id.idrecicleritem);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        nombres=findViewById(R.id.idnombres);
        apellidos=findViewById(R.id.idapellidos);
        tel=findViewById(R.id.idtelefono);
        dni=findViewById(R.id.iddni);
        correo=findViewById(R.id.idcorreo);
        direc=findViewById(R.id.iddireccion);
        estado=findViewById(R.id.idestado);
        distrito=findViewById(R.id.iddistrito);

        razonsocial=findViewById(R.id.idrazonsocial);
        ruc=findViewById(R.id.idruc);
        contacto=findViewById(R.id.idcontacto);
        puestocontac=findViewById(R.id.idpuesto);
        telcontac=findViewById(R.id.idtelcontac);
        dircontac=findViewById(R.id.iddirempresa);
        corcontac=findViewById(R.id.idcorempre);
        actividad=findViewById(R.id.idactividad);
        estacontac=findViewById(R.id.idestempresa);
        discontac=findViewById(R.id.iddisempresa);

        nomsubalm=findViewById(R.id.idnomsub);
        estasubalm=findViewById(R.id.idestadosub);
        almsubalm=findViewById(R.id.idalmnom);

        nomalm=findViewById(R.id.idnomalm);
        estadoalm=findViewById(R.id.idestadoalm);
        diralm=findViewById(R.id.iddiralm);

        tipotrans=findViewById(R.id.idtipotrans);
        nomtrans=findViewById(R.id.idnomtrans);

        linea=findViewById(R.id.lbllineaitems);
        cantidad=findViewById(R.id.lblprecioitems);
        item=findViewById(R.id.lblprecioitems);

        buscar(encontrado);

    }


    private void buscar(int id){
        Retrofit retro = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        whingresoService servie = retro.create(whingresoService.class);
        Call<Whingreso> call = servie.getingreso(id);
        call.enqueue(new Callback<Whingreso>() {
            @Override
            public void onResponse(Call<Whingreso> call, Response<Whingreso> response) {
                Whingreso nuevo = response.body();
                lista= nuevo.getSolicitudItems();
                if(response.isSuccessful()){
                  nombres.setText(nuevo.getPersona().getNombre1()+" "+nuevo.getPersona().getNombre2());
                  apellidos.setText(nuevo.getPersona().getApepat()+" "+nuevo.getPersona().getApemat());
                  dni.setText(nuevo.getPersona().getDni());
                  tel.setText(nuevo.getPersona().getTelefono());
                  direc.setText(nuevo.getPersona().getDireccioon());
                  correo.setText(nuevo.getPersona().getCorreo());
                  estado.setText(nuevo.getPersona().getEstado());
                  distrito.setText(nuevo.getPersona().getDistrito().getNombre());

                  razonsocial.setText(nuevo.getEmpresa().getRazonsocial());
                  ruc.setText(nuevo.getEmpresa().getRuc());
                  contacto.setText(nuevo.getEmpresa().getContacto());
                  puestocontac.setText(nuevo.getEmpresa().getPuestocontacto());
                  telcontac.setText(nuevo.getEmpresa().getTelefono());
                  dircontac.setText(nuevo.getEmpresa().getDireccion());
                  corcontac.setText(nuevo.getEmpresa().getCorreo());
                  actividad.setText(nuevo.getEmpresa().getActividad());
                  estacontac.setText(nuevo.getEmpresa().getEstado());
                  discontac.setText(nuevo.getEmpresa().getDistrito().getNombre());

                    nomsubalm.setText(nuevo.getSubalmacen().getNombre());
                    estasubalm.setText(nuevo.getSubalmacen().getEstado());
                    almsubalm.setText(nuevo.getSubalmacen().getAlmacen().getNombre());

                    nomalm.setText(nuevo.getSubalmacen().getAlmacen().getNombre());
                    estadoalm.setText(nuevo.getSubalmacen().getAlmacen().getEstado());
                    diralm.setText(nuevo.getSubalmacen().getAlmacen().getDireccion());

                    tipotrans.setText(nuevo.getTipotransaccion().getTipo());
                    nomtrans.setText(nuevo.getTipotransaccion().getNombre());

                 
                    adapter=new ListadoItemsAdapter(vistaIngresoAlmacen.this,lista);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<Whingreso> call, Throwable t) {
                Log.d("ERRROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOORRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR"
                ,"FUEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE   "+t.getMessage());
            }
        });

    }

}