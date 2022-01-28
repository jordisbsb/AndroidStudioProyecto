package com.proyecto.isamisa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dmax.dialog.SpotsDialog;

public class Login extends AppCompatActivity {

    TextInputEditText correo;
    TextInputEditText contraseña;
    Button ingresar;
    FirebaseAuth auth;
   DatabaseReference databaseReference;

   SharedPreferences sharedPreferences;

   AlertDialog alert ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        sharedPreferences=getBaseContext().getSharedPreferences("user",MODE_PRIVATE);
        correo=findViewById(R.id.txtcorreo);
        contraseña=findViewById(R.id.txtcontraseña);
        ingresar=findViewById(R.id.btningresar);
        alert= new SpotsDialog.Builder().setContext(Login.this).setMessage("Esperen un momento .....").build();

    ingresar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            validar();
        }

    });

    }

    private void validar(){
    String cor = correo.getText().toString();
    String pas = contraseña.getText().toString();


    if(!cor.isEmpty() && !pas.isEmpty()){
       alert.show();
        auth.signInWithEmailAndPassword(cor,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
          alert.dismiss();
           if(task.isSuccessful()){
               final String condicion = sharedPreferences.getString("user","");
               if(condicion.equals("administrador")){
                   Toast.makeText(Login.this,"Login Con Exito Administrador",Toast.LENGTH_SHORT).show();
                   Intent i = new Intent(Login.this,navdrawer.class);
                   startActivity(i);
               } else if(condicion.equals("usuario")){
                   Toast.makeText(Login.this,"Login Con Exito Usuario",Toast.LENGTH_SHORT).show();
                   Intent i = new Intent(Login.this,navdrawer.class);
                   startActivity(i);
               }


           }else{
               Toast.makeText(Login.this,"Datos no Validos",Toast.LENGTH_SHORT).show();
           }

            }
        });

    }else {
        Toast.makeText(this,"Debe de llenar todos los campos",Toast.LENGTH_SHORT).show();
    }



    }


}