package com.example.dbspawn;

import android.app.ListActivity;

import android.database.Cursor;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.text.Normalizer;
import java.util.ArrayList;



public class MainActivity extends ListActivity {

//    declarando variable del tipo cursor llamada lentes

    private Cursor lentes;

//    declarando variable llamado db del tipo myDataBase
    private myDataBase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Inicializando objeto db del tip myDataBase, recordando que la superclase es SQLiteAssetHelper

        db = new myDataBase(this);

//        al cursor lentes se le esta pasando el objeto db y estamos ejecutando el metodo robotillodebusqueda
//        que no es otra cosa que un metodo de cursor con query builder, asi que lentes practicamente
//        es el SQLAssetHelper db donde ya ejecuto el robot de busqueda cargado y listo para mostrar

        lentes = db.robotillodebusqueda(); // you would not typically call this on the main thread


//preparando el widget adaptador del tipo ListAdapter, este widet sera SimpleCursorAdapter (subclase de listadapter),
// este paquete es para mostrar UI en la pantalla asi que preparados
//aqui aparecera el texto, este es el puente entre el ListView xml y la base de datos almacenada en el cursor
// este ya viene cargado y con ganas de soltarlo en el cursor lentes (este es el cursor)

//contexto del simplecursoradapter es aqui, el layour a usar este sera el listview,
// el cursor lentes, el string desde donde mostrar, para este caso la columna a mostrar,
//el textview a mostrar osea donde se pega la informacion que trae el cursor

        String texto = lentes.getString(1);
        String coment = lentes.getString(2);
        String referen = lentes.getString(3);




        // Decompose unicode characters
        String comentbueno = sintildes.chulon(coment);

String[] eltextodehoy = {texto,comentbueno,referen};

        setListAdapter(new ArrayAdapter<String>(this,R.layout.texto_listado,R.id.texto_listado,eltextodehoy));


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lentes.close();
        db.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
