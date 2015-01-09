package com.example.dbspawn;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

//Problema repite el texto tantas veces se genere el codigo


public class MainActivity extends ListActivity {

    /** Called when the activity is first created. */
    private final String MY_DATABASE_NAME = "mysqliteDB";

    public class DefaultDBHelper extends SQLiteOpenHelper {

        public final static String MY_DATABASE_TABLE = "t_person";

        public DefaultDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE IF NOT EXISTS "
                    + MY_DATABASE_TABLE
                    + " (LastName VARCHAR, FirstName VARCHAR,"
                    + " Country VARCHAR, Age INT(3));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("My DB", "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList results = new ArrayList();
        // -- SQLiteOpenHelper dbHelper = new DefaultDBHelper(this, MY_DATABASE_NAME, null, 1);
        SQLiteDatabase myDB = this.openOrCreateDatabase(MY_DATABASE_NAME, SQLiteDatabase.OPEN_READWRITE, null);
        try {
             /* Create the Database (no Errors if it already exists) */
            // myDB = dbHelper.getWritableDatabase();
            // dbHelper.onCreate(myDB);
            myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                    + DefaultDBHelper.MY_DATABASE_TABLE
                    + " (LastName VARCHAR, FirstName VARCHAR,"
                    + " Country VARCHAR, Age INT(3));");

            myDB.execSQL("INSERT INTO "
                    + DefaultDBHelper.MY_DATABASE_TABLE
                    + " (LastName, FirstName, Country, Age)"
                    + " VALUES ('Gramlich', 'Nicolas', 'Germany', 15);");
            myDB.execSQL("INSERT INTO "
                    + DefaultDBHelper.MY_DATABASE_TABLE
                    + " (LastName, FirstName, Country, Age)"
                    + " VALUES ('Doe', 'John', 'US', 34);");

            // -- openOrCreateDatabase(name, mode, factory)
            // myDB = dbHelper.getReadableDatabase();
//            ATENCION AQUI LLEVA LA CONDICIONAL PARA BUSCAR EN LA BASE DE DATOS
//            PARA ESTE CASO LA CONDICONAL ES QUE SEA MAYOR A 10

            Cursor c = myDB.query(DefaultDBHelper.MY_DATABASE_TABLE, null, "Age > 10", null, null, null, null);

                /* Check if our result was valid. */
            if (c != null) {

                c.moveToFirst(); // it's very important to do this action otherwise your Cursor object did not get work
                int firstNameColumn = c.getColumnIndex("FirstName");
                int ageColumn = c.getColumnIndex("Age");
                 /* Check if at least one Result was returned. */
                if (c.isFirst()) {
                    int i = 0;
                      /* Loop through all Results */
                    do {
                        i++;
                        String firstName = c.getString(firstNameColumn);
                        int age = c.getInt(ageColumn);
                        String ageColumName = c.getColumnName(ageColumn);

                           /* Add current Entry to results. */
                        results.add("" + i + ": " + firstName + " (" + ageColumName + ": " + age + ")");
                    } while (c.moveToNext());
                }
            }

        } catch (SQLiteException e) {
        } finally {
            if (myDB != null)
                myDB.close();
        }

        // -- android.R.layout.simple_list_item_1 is object which belong to ListActivity itself
        // -- you only need to add list object in your main layout file

//        ANTIGUO TRUQUITO, LE ESTAMOS PASANDO LA VISTA TEXTO_LISTADO AL LISTENER PARA QUE EL TEXTO SEA
//        VERDE, MIENTRAS LA PRINCIPAL ACTIVIDAD ES ACTIVTY MAIN, APRENDE COMO HACER PARA CADA
//        LISTVIEW

        this.setListAdapter(new ArrayAdapter(this, R.layout.texto_listado,R.id.list_content, results));
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
