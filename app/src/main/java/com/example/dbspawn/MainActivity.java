package com.example.dbspawn;

import android.app.Activity;
import android.app.ListActivity;


import android.database.Cursor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends Activity {





//    declarando variable del tipo cursor llamada lentes

    private Cursor lentes;

//    declarando variable llamado db del tipo myDataBase
    private myDataBase db;


//    Las variables para ver la imagen correcta y el cargador



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

//sacando del cursor lentes los textos, los numeros son las columnas del cursor

        String fecha = lentes.getString(1);
        String texto = lentes.getString(2);
        String coment = lentes.getString(3);
        String referen = lentes.getString(4);


//Le pasamos la clase creada llamada sintildes la cual tiene el metodo que hemos llamado coloquialmente
//liquidpaper pues quita los simbolos extra;os




            String textobueno = sintildes.liquidpaper(texto);
            String referenbuena = sintildes.liquidpaper(referen);
            String comentbueno = sintildes.liquidpaper(coment);

            final String[] eltextodehoy = {fecha, textobueno, comentbueno, referenbuena};

        Integer [] imageId = {R.drawable.test2};

        CustomList adapter = new CustomList(MainActivity.this, imageId);
        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

CustomList2 adaptador2 = new CustomList2(MainActivity.this,eltextodehoy);
        ListView list2 = (ListView)findViewById(R.id.list2);
        list2.setAdapter(adaptador2);



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
