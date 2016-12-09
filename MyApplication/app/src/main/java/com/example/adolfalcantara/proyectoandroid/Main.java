package com.example.adolfalcantara.proyectoandroid;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main extends AppCompatActivity {

    EditText texto;
    TextView nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = (EditText) findViewById(R.id.etNota);
        nota = (TextView) findViewById(R.id.tvNota);
    }

    public void guardar(View view) throws IOException {
        String contenido = texto.getText().toString();
        try{
            FileOutputStream fos = openFileOutput("notas.txt",MODE_PRIVATE );
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(contenido+'.');
            osw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        texto.setText("");
        Toast.makeText(this,"Se guardo la nota",Toast.LENGTH_SHORT).show();
    }

    public void ver(View view) {
        try {
            FileInputStream fis = openFileInput("notas.txt");
            InputStreamReader isr = new InputStreamReader(fis);

            String textoObt="";

            int i;
            char caracter;
            do{
                i = isr.read();
                caracter = (char)i;

                textoObt +=caracter;
                if(caracter=='.')
                    textoObt+='\n';


            }while(i>0);
            nota.setText(textoObt);
            isr.close();

            Toast.makeText(this,"Cargada",Toast.LENGTH_SHORT).show();

         /*   char buffer[] = new char[100];
            int charRead;
            while((charRead=isr.read(buffer))>0){
                //convertirmos los char a strings
                String readString = String.copyValueOf(buffer,0,charRead);
                textoObt+=readString;

                buffer = new char[100];
            }

            nota.setText(textoObt);
            isr.close();

            Toast.makeText(this,"Cargada",Toast.LENGTH_LONG).show();*/


        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public void limpiar(View view) {
        texto.setText("");
    }
}
