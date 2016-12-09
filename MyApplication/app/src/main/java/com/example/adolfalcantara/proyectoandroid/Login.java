package com.example.adolfalcantara.proyectoandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText user;
    EditText pass;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.etUsuarioInput);
        pass = (EditText) findViewById(R.id.etContrasenaInput);
    }

    public void login(View view) {
        String usuario = user.getText().toString();
        String contra = pass.getText().toString();

        sharedPref = getSharedPreferences("usuario"+usuario, Context.MODE_PRIVATE);

        String usGuardado = sharedPref.getString("usuario","");
        String psGuardado = sharedPref.getString("contra","");

        if(usuario.equals(usGuardado)&&contra.equals(psGuardado)){
            Intent i = new Intent(this, Main.class);
            startActivity(i);
        }else{
            Toast.makeText(this,"Usuario o contraseña incorrecta",Toast.LENGTH_LONG).show();
        }


    }

    public void register(View view) {
        String usuario = user.getText().toString();
        String contra = pass.getText().toString();


        sharedPref = getSharedPreferences("usuario"+usuario, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("usuario",usuario);
        editor.putString("contra",contra);
        editor.apply();

        Toast.makeText(this,"Usuario creado",Toast.LENGTH_SHORT).show();
    }
}
