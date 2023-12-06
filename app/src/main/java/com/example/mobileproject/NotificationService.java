package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.content.Intent;
import android.app.PendingIntent;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NotificationService extends Service {
    private static final String CHANNEL_ID = "NotificationChannel";
    private static final int NOTIFICATION_ID = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Aquí implementa la lógica para verificar las notificaciones pendientes y mostrarlas.
        // Puedes usar AlarmManager para programar las notificaciones.

        // Ejemplo de cómo programar una notificación después de 10 minutos.
        String eventName = "Posada Navideña"; // Reemplaza esto con el nombre real del evento
        String eventDate = "06/12/2023 06:14:00"; // Reemplaza esto con la fecha real del evento

        scheduleNotification(eventName, eventDate);

        return START_STICKY;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "NotificationChannel";
            String description = "Channel for Notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void scheduleNotification(String eventName, String eventDate) {
        // Convertir la fecha del evento de String a Date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        try {
            Date notificationDate = sdf.parse(eventDate);

            // Obtener el tiempo restante para la notificación
            long tiempoRestante = notificationDate.getTime() - System.currentTimeMillis();

            if (tiempoRestante > 0) {
                // Si la fecha del evento está en el futuro, programar la notificación

                // Crear un intent para la notificación
                Intent notificationIntent = new Intent(this, NotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

                // Programar la notificación para el tiempo restante
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + tiempoRestante, pendingIntent);

                // Mostrar Toast para verificar que se ha programado la notificación
                showToast("Notificación programada con éxito para el evento " + eventName);
            } else {
                // La fecha del evento ya pasó, puedes mostrar un mensaje o realizar otra acción si lo deseas
                showToast("La fecha del evento " + eventName + " ya ha pasado");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}