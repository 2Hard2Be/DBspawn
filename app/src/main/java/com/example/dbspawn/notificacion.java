package com.example.dbspawn;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Peto-1 on 2/2/2015.
 */
public class notificacion extends Activity {

    private Cursor lentes2;
    private myDataBase db2;

    public void sapito() {

        db2 = new myDataBase(this);
        lentes2 = db2.robotillodebusqueda();

        String texto1 = lentes2.getString(2);
        String texto2 = sintildes.liquidpaper(texto1);

        String referen2 = lentes2.getString(4);
        String referenbuena2 = sintildes.liquidpaper(referen2);


//    Creando Notification builder
            NotificationCompat.Builder constructorNotificacion = new NotificationCompat.Builder(this);

//    Creando Intent para llamar al Main

            Intent intentalMain = new Intent(notificacion.this, MainActivity.class);

//    Colocando flag para que pase al frente del history stack, no se ejecuta si ya esta corriendo
//    ALERTA ESTO TE PUEDE DAR PROBLEMAS PUES LO TRAES DEL EJEMPLO SIN TENER CLARO PORQUE

            intentalMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

//crando pendint intent para llamar al inentalMain, ATENTO QUE NO SE SABE SI FLAG_UPDATE_CURRENT
//    ES REALMENTE NECESARIO?????

            PendingIntent napoLeon = PendingIntent.getActivity(this, 0, intentalMain, 0);

//    colocando pendint intent napoLeon al Notificationcompat.Builder llamado constructorNotificacion, creado
//    anteiormente
            constructorNotificacion.setContentIntent(napoLeon);

//    agrengando ticker ESTE SOLO ES PLANTE LO PODEMOS QUITAR pero diversion va primero

            constructorNotificacion.setTicker(getResources().getString(R.string.ticker));

//    Creando la imagen de la barra de tareas

            constructorNotificacion.setSmallIcon(R.drawable.noti_ico);

//    Cancelando la notificacion al darle clic

            constructorNotificacion.setAutoCancel(true);

//    ATENCION AQUI SEGUI CREANDO LA ANIMACION DE ABRIR EL EXAMINANDO SEGUN EL EJEMPLO DE CUSTOM
//    NOTIFICATION, ANTES PROBA LA APP CREANDO EL MAIN, DE AQUI EN ADELANTE A LA PASARRAYA SOLO
//        PARA PROBAR

        //                Truco para que el texto tenga un solo color dependiendo de cual tenga el sistema
        TypedArray colorin = getTheme().obtainStyledAttributes(new int[]{android.R.attr.textColorPrimary});
        int colordeltexto = colorin.getColor(0, R.color.azulito);

//Fin del antiguo truco

            Notification notificarle = constructorNotificacion.build();

            RemoteViews vistasdeContenido = new RemoteViews(getPackageName(), R.layout.notif);

        vistasdeContenido.setTextColor(R.id.textView, colordeltexto);
            final String time = DateFormat.getTimeInstance().format(new Date()).toString();
            final String text = texto2;

            vistasdeContenido.setTextViewText(R.id.textView, text);
//        alerta con este contentview tal vez se refuere a vustas de contenido
            notificarle.contentView = vistasdeContenido;

            if (Build.VERSION.SDK_INT >= 16) {

                char ref1[] = new char[12];
                referenbuena2.getChars(0, 11, ref1, 0);
                int i;
                for (i = 0; i < ref1.length; i++) {

                    if (ref1[i] == ':') {
                        ref1[i] = 'x';
                    }
                    if (ref1[i] == ' ') {
                        ref1[i] = '_';
                    }
                }



                StringBuilder referen3 = new StringBuilder();


                referen3.append(ref1[0]).append(ref1[1]).append(ref1[2]).append(ref1[3]).append(ref1[4]).append(ref1[5]).append(ref1[7])
                        .append(ref1[8]).toString();
                String referen4 = referen3.toString();

                char ref2[] = new char[12];
                referenbuena2.getChars(0, 11, ref2, 0);
                int ii;
                for (ii = 0; ii < ref2.length; ii++) {
                    if (ref2[ii] == ':') {
                        ref2[ii] = ' ';
                    }

                }

        StringBuilder referen5 = new StringBuilder();
                referen5.append(ref2[0]).append(ref2[1]).append(ref2[2]).append(" ").append(ref2[4]).append(ref2[5]).append("/").append(ref2[7])
                        .append(ref2[8]).toString();

        StringBuilder estudionumero = new StringBuilder();
                estudionumero.append(ref2[9]).append(ref2[10]).toString();


                String referen6 = referen5.toString();
                String estudionumero2 = estudionumero.toString();

                int imagen2 = getResources().getIdentifier(referen4, "drawable", getPackageName());

                final String text2 = "El texto de ahora se basa en el estudio numero"+" "+estudionumero2+" "+"de la revista Atalaya"+" "+referen6;


                Bitmap bitimagen = BitmapFactory.decodeResource(getResources(), imagen2);


                Toast.makeText(notificacion.this, texto2, Toast.LENGTH_LONG).show();
                moveTaskToBack(true);



                // Inflate and set the layout for the expanded notification view
                RemoteViews expandedView =
                        new RemoteViews(getPackageName(), R.layout.notificacion_expandida);
                expandedView.setTextViewText(R.id.Textovisto, text2);
                expandedView.setTextColor(R.id.Textovisto, colordeltexto);
                expandedView.setImageViewBitmap(R.id.imageView, bitimagen);
                notificarle.bigContentView = expandedView;
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(0, notificarle);

            }

            lentes2.moveToFirst();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sapito();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lentes2.close();
        db2.close();
    }
}
