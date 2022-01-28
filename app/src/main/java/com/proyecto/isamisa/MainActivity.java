package com.proyecto.isamisa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button administrador;
    Button usuario;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
administrador=findViewById(R.id.btnadministrador);
        usuario=findViewById(R.id.btnusuario);

sharedPreferences=getBaseContext().getSharedPreferences("user",MODE_PRIVATE);

        final SharedPreferences.Editor editor =sharedPreferences.edit();
administrador.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        editor.putString("user","administrador");
        editor.apply();
        Intent i = new Intent(MainActivity.this,Login.class);
        startActivity(i);
    }
});


usuario.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        editor.putString("user","usuario");
        editor.apply();
        Intent i = new Intent(MainActivity.this,Login.class);
        startActivity(i);
    }
});




}


    @Override
    protected void onStart() {
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            String valor = sharedPreferences.getString("user","");
            if(valor.equals("administrador")){
                Intent i = new Intent(MainActivity.this,navdrawer.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }else if (valor.equals("usuario")){
                Intent i = new Intent(MainActivity.this,navdrawer.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        }

        super.onStart();
    }
}



