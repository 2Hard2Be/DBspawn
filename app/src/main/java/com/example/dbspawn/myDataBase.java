package com.example.dbspawn;

/**
 * Created by Peto-1 on 1/8/2015.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

//Creating SQliteAssetHelper class (que es subclase de SQliteOpenHelper, llamada myDataBase)


public class myDataBase extends SQLiteAssetHelper {

//    String indicando el nombre de la base de datos del tipo string para ser usada en las
//    Sqliteopenhelper y assethelper dento de los parametros, lo mismo que la version de la
//    base de datos

    private static final String DATABASE_NAME = "EED15";
    private static final int DATABASE_VERSION = 1;

//    Adentro se crea el metodo llamado myDataBase que para este caso es el nombre de la clase
//    este lo invocamos en el mainactivity, debdio a que la clase myDataBase es subclae de SQLiteAssetHelper
//    se nota que el metodo tiene la forma de un SQLiteOpenHelper (administra las bases de datos)
//    aqui le damos identidad al myDataBase, indicandoles el nombre de la base de datos y la version

    public myDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        // you can use an alternate constructor to specify a database location
        // (such as a folder on the sd card)
        // you must ensure that this folder is available and you have permission
        // to write to it
        //super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);

    }

//    Declarando un metodo cursor llamado "robotillodebusqueda", este lo usaremos para el mes de enero QUE FALTA LA CREACION
//    DE LAS TABLAS DE LOS OTROS MESES

    public Cursor robotillodebusqueda() {

//       aqui estamos haciendo que la base de datos "dB"
//  que ha sido creada en el main este lista o abierta, clasico metodo del SQLiteDatabase


        SQLiteDatabase db = getReadableDatabase();

//        Creando un querybuilder este es el constructor del query builder llamado "qb"

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

//        Creando un vector con los nombres de las columnas de la tabla deseada de la base de datos
//        es importante que se use la columna "_id" puesto que este es el listado
//        de columnas que se pasara al query como parte del "projectionln" mantener en mente
//        que no se debe usar muchas columnas si no las vas a ocupar (para esta app todas se usan :))


        String [] sqlSelect = {"_id","TEXTO", "COMENTARIO", "REFERENCIA"};

 //        sqltables es una variable string donde va el nombre de la tabla a query este sera pasado
//        al QueryBuilder, UNA OPTIMIZACION AQUI PUDIERA SER OCUPAR UN SWITCH BOARD donde se asigne
//        el nombre del mes correcto

        String sqlTables = "enero";

//        AQUI TERMINARA EL SWITCHBOARD QUE LE COLOCARAS

//seteando que se busque en la tabla guardada en sqlTables

        qb.setTables(sqlTables);

// creando el cursor e indicando que al obejto querybuilder debe realizar el query
// en la base de datos antes seteada para estar lista "db" la cual fue declarada en MAIN
// el "projectionln" es la lista de columnas que sera leida, este se declaro antes como
// sqSelect,

// para los group selection(que es un where en sql language
//  el cual indicaria que filas devolver, MODIFICA ESTE PARA QUE DEVUELVA SOLO EL DIA DESEADO, para este caso es null osea todas


// , groupBy (es un groupby de language sql) null indica que no sea agrupen las filas
// , having es todos los grupos incluidos al ser null,
// sortOrder null es que no los ordene adicionalmente, osea tal como estan los datos
// , limit se ha dejado null osea sin limite, este es para limitar el numero de filas a mostrar


        Cursor c = qb.query(db, sqlSelect, null, null,
                null, null, null);

//        al terminar la busqueda retorna el cursor a la primera fila de nuevo
        c.moveToFirst();

//        Public method para que devuelva el valor que eligio el cursor, si notas el cursor traera un conjunto
//        de datos cuando lo necesite

        return c;

    }

}
