package com.proyecto.isamisa.ui.Producto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.proyecto.isamisa.Entidades.Persona;
import com.proyecto.isamisa.Entidades.Producto;
import com.proyecto.isamisa.Entidades.SubFamilia;
import com.proyecto.isamisa.Entidades.UnMedida;
import com.proyecto.isamisa.Interface.productoService;
import com.proyecto.isamisa.Interface.subfamiliaService;
import com.proyecto.isamisa.Interface.undmedidaService;
import com.proyecto.isamisa.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgregarProducto extends AppCompatActivity {

    EditText nombre,comentario,codigobarra;
    Spinner subfam,undmedida;
    Button agregar,scan,abricam;
    ImageView img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);
        nombre=findViewById(R.id.txtnombreproducto);
        comentario=findViewById(R.id.txtcomentario);
        subfam=findViewById(R.id.spsubfamilia);
        undmedida=findViewById(R.id.spunmedida);
        agregar=findViewById(R.id.btnregistrarproducto);
        scan=findViewById(R.id.btnscanner);
        codigobarra=findViewById(R.id.edtcodigo);
        img=findViewById(R.id.imgproducto);
        abricam=findViewById(R.id.btnimgproducto);
        subfamiliasp();
        unidadsp();
//__________________________________________________

   //___________________________________________________
agregar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        crearProducto();
    }
});

scan.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
scannear();
    }
});

abricam.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
abrircamara();
    }
});

    }



//___________CREATE______________________________________________________________
    private void crearProducto(){
        String nom = nombre.getText().toString();
        String comen = comentario.getText().toString();
        SubFamilia sub = (SubFamilia) subfam.getSelectedItem();
        sub.getId();
        SubFamilia idsub=sub;
        UnMedida un = (UnMedida) undmedida.getSelectedItem();
        un.getId();
        UnMedida u=un;
        if(!nom.isEmpty() && !comen.isEmpty() && !idsub.equals("") && !u.equals("")){

            final Producto p = new Producto(nom,comen,idsub,u);


            Retrofit retro = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/")
                    .addConverterFactory(GsonConverterFactory.create()).build();

            productoService pro = retro.create(productoService.class);
            Call<Producto> call = pro.CrearProducto(p);
            call.enqueue(new Callback<Producto>() {
                @Override
                public void onResponse(Call<Producto> call, Response<Producto> response) {
                    Producto pro = response.body();
                    if(response.isSuccessful()){
                        nombre.setText("");
                        comentario.setText("");



                        AlertDialog.Builder eli = new AlertDialog.Builder(AgregarProducto.this);
                        eli.setMessage("Se registro el producto  :"
                                + "\n"+"Producto : " +pro.getNombre()
                                + "\n"+"Comentario : " +pro.getComentario()
                                + "\n"+"SubFamilia : " +pro.getSubFamilia()
                                + "\n"+"Unidad Medidad : " +pro.getUnMedida()
                        );

                        eli.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent i = new Intent(AgregarProducto.this,ProductoshowFragment.class);
                                AgregarProducto.this.startActivity(i);

                            }
                        });
                        AlertDialog alert = eli.create();
                        alert.setTitle("Producto creado con exito");
                        alert.show();


                    }
                }

                @Override
                public void onFailure(Call<Producto> call, Throwable t) {

                }
            });
        } else {
            Toast.makeText(AgregarProducto.this,"Los campos NO pueden estar vacio",Toast.LENGTH_SHORT).show();
        }




    }



private void subfamiliasp(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        subfamiliaService service = retrofit.create(subfamiliaService.class);
        Call<ArrayList<SubFamilia>> familia = service.getsubfamilia();

        familia.enqueue(new Callback<ArrayList<SubFamilia>>() {
            @Override
            public void onResponse(Call<ArrayList<SubFamilia>> call, Response<ArrayList<SubFamilia>> response) {
                if(response.isSuccessful()){
                    ArrayList<SubFamilia> list = new ArrayList<SubFamilia>();
                    list=response.body();

                    Log.i("LISTADOOOOOOOOOOOOOOOOOOOOOOOO","AACAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+list);

                    ArrayAdapter<SubFamilia> adapter = new ArrayAdapter<SubFamilia>(AgregarProducto.this,android.R.layout.simple_spinner_dropdown_item,list);
                    subfam.setAdapter(adapter);
                    subfam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            SubFamilia sub = (SubFamilia) subfam.getSelectedItem();
                            Log.i("aacaaaaaaaaaaaaaaaaaaaaaaaaaaaa","aaacaaaaaaaaaaaaaa  "+sub.getId());
                            Log.i("SEEELEEEEEEECCCIIIOOOOOOOOONNNNNNNNN","AAAAAACAAAAAAAAAAAAAAAAAAA   "+sub.getId()+"  " + sub.toString());
                            Toast.makeText(AgregarProducto.this,"Seleccion "+sub.getId() + " " +sub.toString(),Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<ArrayList<SubFamilia>> call, Throwable t) {

            }
        });

}

private void unidadsp(){
    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/")
            .addConverterFactory(GsonConverterFactory.create()).build();

undmedidaService unmed = retrofit.create(undmedidaService.class);
Call<ArrayList<UnMedida>> call = unmed.getUnMedida();

call.enqueue(new Callback<ArrayList<UnMedida>>() {
    @Override
    public void onResponse(Call<ArrayList<UnMedida>> call, Response<ArrayList<UnMedida>> response) {

        if(response.isSuccessful()){
            ArrayList<UnMedida> und= new ArrayList<>();
            Log.i("UNIIDAAAAAAAAAAAAAAAAAAAAAAAAAAAADDDD","DAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+und);
            und=response.body();
            ArrayAdapter<UnMedida> adapter = new ArrayAdapter<UnMedida>(AgregarProducto.this,R.layout.support_simple_spinner_dropdown_item,und);
            undmedida.setAdapter(adapter);
            undmedida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    UnMedida un = (UnMedida) undmedida.getSelectedItem();
                    Log.i("ITEEEEEEEEEEEEEEEEEEEEEEEEMMMM","SELEEEEEEEEEEEEEEEEEEEEEEEEEEEEE "+un.getId()+" "+un.toString());
                    Toast.makeText(AgregarProducto.this,"Seleccion "+un.getId()+" "+un.toString(),Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }else{

        }

    }

    @Override
    public void onFailure(Call<ArrayList<UnMedida>> call, Throwable t) {

    }
});


}



    public void scannear(){
        IntentIntegrator vista = new IntentIntegrator(this);
        vista.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);

        vista.setPrompt("SCANEANDOOOOOOO");
        vista.setCameraId(0);
        vista.setBeepEnabled(false);
        vista.setBarcodeImageEnabled(false);
        vista.initiateScan();

        //scannerView= new ZXingScannerView(this);
        //setContentView(scannerView);
        //scannerView.setResultHandler(this);
        //scannerView.startCamera();
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult resultado = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(resultado!=null){
            if(resultado.getContents()==null){
                Toast.makeText(this,"Cancelaste el scanner",Toast.LENGTH_SHORT).show();
            }
            else{
                codigobarra.setText(resultado.getContents().toString());
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }

        if(requestCode ==1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imgbitmap = (Bitmap) extras.get("data");
            img.setImageBitmap(imgbitmap);
        }

    }


    private void abrircamara(){
        Intent vistacamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(vistacamara.resolveActivity(getPackageManager()) !=null){
            startActivityForResult(vistacamara,1);

        }
    }




}