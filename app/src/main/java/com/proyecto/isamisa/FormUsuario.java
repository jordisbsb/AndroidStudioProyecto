package com.proyecto.isamisa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.isamisa.Entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class FormUsuario extends AppCompatActivity {
    TextInputEditText nombre;
    TextInputEditText apellido;
    TextInputEditText area;
    TextInputEditText correo;
    TextInputEditText contraseña;


    Spinner spinner;

    Button registrar;

    Button listado;

    AlertDialog alert;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_usuario);

        registrar=(Button)findViewById(R.id.btnregistrar);

        nombre= findViewById(R.id.txtnombre);
        apellido=findViewById(R.id.txtapellido);
        //area=findViewById(R.id.txtarea);
        spinner=findViewById(R.id.sptipousuario);
        correo=findViewById(R.id.txtcorreo);
        contraseña=findViewById(R.id.txtcontraseña);


        alert = new SpotsDialog.Builder().setContext(FormUsuario.this).setMessage("Registrando al Usuario ....").build();
        //alert=new SpotsDialog.Builder().setContext(FormUsuario.this).setMessage("Rgistrando Usuario ...").build();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Usuarios");
        auth=FirebaseAuth.getInstance();

        sharedPreferences=getBaseContext().getSharedPreferences("user",MODE_PRIVATE);

        List<String> lista = new ArrayList<>();
        lista.add("Usuario");
        lista.add("Administrador");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lista);
        spinner.setAdapter(adapter);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarusuario();
            }
        });

    }

    private void validarusuario(){


            String nom = nombre.getText().toString();
            String ape= apellido.getText().toString();
            String are = spinner.getSelectedItem().toString().trim(); //area.getText().toString();
            String cor = correo.getText().toString();
            String contra=contraseña.getText().toString();

            if(!nom.isEmpty() && !ape.isEmpty() && !are.isEmpty() && !cor.isEmpty() && !contra.isEmpty() ){
                alert.show();
                registrarcolaborador(nom,ape,are,cor,contra);
            }else{
                Toast.makeText(FormUsuario.this,"Debe llenar todos los campos",Toast.LENGTH_SHORT).show();
            }


    }

    private void registrarcolaborador(final String nombre, final String apellido, final String area,final String correo, final String contraseña){
    auth.createUserWithEmailAndPassword(correo,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            alert.dismiss();
            String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
            Usuario usua= new Usuario(id,nombre,apellido,area,correo,contraseña);
            crearUsuarionuevo(usua);
        }
    });
    }

    private void crearUsuarionuevo(Usuario usua){
        if(spinner.getSelectedItem().toString().equals("Administrador")){
            databaseReference.child("Administradores").child(usua.getId()).setValue(usua).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    nombre.setText("");
                    apellido.setText("");
                    correo.setText("");
                    contraseña.setText("");
                    nombre.findFocus();
                    Toast.makeText(FormUsuario.this,"Administrador creado con exito",Toast.LENGTH_SHORT).show();
                }
            });

        }else if (spinner.getSelectedItem().toString().equals("Usuario")){

        }
        databaseReference.child("Colaboradores").child(usua.getId()).setValue(usua).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                nombre.setText("");
                apellido.setText("");
                correo.setText("");
                contraseña.setText("");
                nombre.findFocus();

                Toast.makeText(FormUsuario.this,"Colaborador creado con exito",Toast.LENGTH_SHORT).show();
            }
        });
    }


}