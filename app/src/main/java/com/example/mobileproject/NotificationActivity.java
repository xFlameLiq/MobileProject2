package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// En la clase NotificationActivity
public class NotificationActivity extends AppCompatActivity {

    private EditText editTextEventName;
    private EditText editTextEventDate;
    private Button btnRecordatorio;
    private AdminSQLiteOpenHelper adminSQLiteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "nombre_base_datos", null, 1);

        // Asignar vistas desde el XML a variables en Java
        editTextEventName = findViewById(R.id.editTextTextPersonName);
        editTextEventDate = findViewById(R.id.editTextTextFecha);
        btnRecordatorio = findViewById(R.id.btnVideo);

        // Asignar un OnClickListener al botón
        btnRecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener el nombre y la fecha ingresados por el usuario
                String eventName = editTextEventName.getText().toString().trim();
                String eventDate = editTextEventDate.getText().toString().trim();

                // Agregar el evento a la base de datos
                adminSQLiteOpenHelper.agregarEvento("usuario_prueba", eventName, eventDate);

                // Aquí deberías agregar la lógica para programar la notificación
                // Puedes usar las funciones que ya habíamos discutido, como obtenerEventosProximosDesdeBD y scheduleNotification

                // Mostrar un mensaje indicando que se agregó el evento y programó la notificación
                Toast.makeText(NotificationActivity.this, "Evento agregado y notificación programada", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
