package com.example.dbspawn;

import android.app.Activity;
import android.app.ListActivity;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
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

import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
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
        Intent trigger = new Intent(MainActivity.this,repeatingAlarm.class);
        startService(trigger);


        Button botonsalida = (Button)findViewById(R.id.botonsalida);
        botonsalida.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();

            }
        });

        ListView lv = (ListView)findViewById(R.id.list2);
        lv.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:

                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:

                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }


                v.onTouchEvent(event);
                return true;
            }
        });

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

    if (lentes != null && lentes.moveToFirst()) {

        String fecha = lentes.getString(1);
        String texto = lentes.getString(2);
        String coment = lentes.getString(3);
        String referen = lentes.getString(4);


//Le pasamos la clase creada llamada sintildes la cual tiene el metodo que hemos llamado coloquialmente
//liquidpaper pues quita los simbolos extra;os


        String textobueno = sintildes.liquidpaper(texto);
        String referenbuena = sintildes.liquidpaper(referen);
        String comentbueno = sintildes.liquidpaper(coment);

        char ref1[] = new char[12];
        referenbuena.getChars(0, 11, ref1, 0);
        int i;
        for (i = 0; i < ref1.length; i++) {

            if (ref1[i] == ':') {
                ref1[i] = 'x';
            }
            if (ref1[i] == ' ') {
                ref1[i] = '_';
            }


        }


        StringBuilder referen1 = new StringBuilder();
        referen1.append(ref1[0]).append(ref1[1]).append(ref1[2]).append(ref1[3]).append(ref1[4]).append(ref1[5]).append(ref1[7])
                .append(ref1[8]).append(ref1[9]).append(ref1[10]).toString();

        String referen2 = referen1.toString();


        int imagen = getResources().getIdentifier(referen2, "drawable", getPackageName());

        final String[] eltextodehoy = {fecha, textobueno, comentbueno, referenbuena};

        Integer[] imageId = {imagen};

        CustomList adapter = new CustomList(MainActivity.this, imageId);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        CustomList2 adaptador2 = new CustomList2(MainActivity.this, eltextodehoy);
        ListView list2 = (ListView) findViewById(R.id.list2);
        list2.setAdapter(adaptador2);
lentes.moveToFirst();
    }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lentes.close();
        db.close();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
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
