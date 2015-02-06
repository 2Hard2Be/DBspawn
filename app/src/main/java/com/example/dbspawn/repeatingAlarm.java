package com.example.dbspawn;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Peto-1 on 2/2/2015.
 */
public class repeatingAlarm extends IntentService {

    public repeatingAlarm(){

        super("repeatingAlarm");
    }

    @Override

//ATENCION REVISAR SI REALMENTE FUNCIONA: AL DAR ONCREATE CREAR LOS INTENTS Y LA ALARMA?? O SERA
//MEJOR REVISAR SI NO HA SIDO CREADA ANTES (PROBABLEMENTE ESTE CORRECTO SOLO HEADS UP


    protected void onHandleIntent (Intent intent){




//    Falta crear la clase notificacion (tendria que ser el intent que llame la notificacion de la barra)
        Intent intentNotification = new Intent(repeatingAlarm.this, notificacion.class);


//    Falta usar el pending intent, contexto repeatingAlarm y llamara al intentNotification que debera
//    invocar la notificacion en la barra de tareas
        PendingIntent pendingIntentNoti = PendingIntent.getActivity(repeatingAlarm.this,0,intentNotification,PendingIntent.FLAG_UPDATE_CURRENT);

//    Aqui crea el alarmManager, guiate segun la pagina web android-er y los ejemlos
//Inicializando objeto del tipo alarma...beeep....beeep

        Calendar now = Calendar.getInstance();
        Calendar alarma = Calendar.getInstance();

        alarma.set(Calendar.MILLISECOND, 0);
        alarma.set(Calendar.SECOND, 0);
        alarma.set(Calendar.MINUTE, 0);
        alarma.set(Calendar.HOUR_OF_DAY, 5);


        int alarmType = AlarmManager.RTC;

        AlarmManager alarmManager = (AlarmManager)

//            ATENCION AQUI MODIFIQUE Y QUITE EL REPEATING ALARM COMO CONTEXTO
                getSystemService(repeatingAlarm.ALARM_SERVICE);
        alarmManager.setRepeating(alarmType, alarma.getTimeInMillis(),
                AlarmManager.INTERVAL_HALF_DAY , pendingIntentNoti);



    }

}
