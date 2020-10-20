package com.example.androidexamplesqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase bd = openOrCreateDatabase("app",MODE_PRIVATE, null);

            bd.execSQL("CREATE TABLE IF NOT EXISTS pessoas ( nome VARCHAR, idade INT(3), genero VARCHAR )");

            bd.execSQL("INSERT INTO pessoas(nome, idade, genero) VALUES ('Raquel', '22', 'F')");
            bd.execSQL("INSERT INTO pessoas(nome, idade, genero) VALUES ('Maria', '21', 'F')");
            bd.execSQL("INSERT INTO pessoas(nome, idade, genero) VALUES ('Douglas', '13', 'M')");
            bd.execSQL("INSERT INTO pessoas(nome, idade, genero) VALUES ('João', '12', 'M')");

            Cursor c = bd.rawQuery("SELECT nome, idade, genero FROM pessoas", null);
            int indiceName = c.getColumnIndex("nome");
            int indiceAge = c.getColumnIndex("idade");
            int indiceG = c.getColumnIndex("genero");

            while(c != null){
                Log.i("Resultado - nome = ", c.getString(indiceName));
                Log.i("Resultado - idade = ", c.getString(indiceAge));
                Log.i("Resultado - genero = ", c.getString(indiceG));
                c.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}