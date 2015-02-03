package com.example.dbspawn;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Peto-1 on 2/2/2015.
 */
public class notificacion extends Activity {


    public void sapito() {


//    Creando Notification builder
        NotificationCompat.Builder constructorNotificacion = new NotificationCompat.Builder(this);

//    Creando Intent para llamar al Main

        Intent intentalMain = new Intent(notificacion.this, MainActivity.class);

//    Colocando flag para que pase al frente del history stack, no se ejecuta si ya esta corriendo
//    ALERTA ESTO TE PUEDE DAR PROBLEMAS PUES LO TRAES DEL EJEMPLO SIN TENER CLARO PORQUE

        intentalMain.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

//crando pendint intent para llamar al inentalMain, ATENTO QUE NO SE SABE SI FLAG_UPDATE_CURRENT
//    ES REALMENTE NECESARIO?????

        PendingIntent napoLeon = PendingIntent.getActivity(this, 0, intentalMain, PendingIntent.FLAG_UPDATE_CURRENT);

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

        Notification notificarle = constructorNotificacion.build();

        RemoteViews vistasdeContenido = new RemoteViews(getPackageName(), R.layout.notif);
        final String time = DateFormat.getTimeInstance().format(new Date()).toString();
        final String text = getResources().getString(R.string.colapsado, time);
        vistasdeContenido.setTextViewText(R.id.textView, text);
//        alerta con este contentview tal vez se refuere a vustas de contenido
        notificarle.contentView = vistasdeContenido;

        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView =
                    new RemoteViews(getPackageName(), R.layout.notificacion_expandida);
            notificarle.bigContentView = expandedView;
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.notify(0, notificarle);

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sapito();
        Toast.makeText(notificacion.this, "'Den gracias a Jehová, porque él es bueno.' (Salmo 106: 1)", Toast.LENGTH_SHORT).show();
        moveTaskToBack(true);
    }

}
