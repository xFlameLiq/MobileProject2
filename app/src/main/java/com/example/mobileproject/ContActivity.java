package com.example.mobileproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepSensor;
    private boolean running = false;
    private float totalSteps = 0;
    private float previousTotalSteps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cont);

        loadData();
        resetSteps();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (checkSelfPermission(android.Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACTIVITY_RECOGNITION}, 1);

        } else {
            stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

            if (stepSensor == null) {
                Toast.makeText(this, "No sensor detected on this device", Toast.LENGTH_SHORT).show();
            } else {
                sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        TextView tv_stepsTaken = findViewById(R.id.txtSteps);

        if (running) {
            totalSteps = event.values[0];
            int currentSteps = (int) (totalSteps - previousTotalSteps);
            tv_stepsTaken.setText(String.valueOf(currentSteps));
        }
    }

    public void resetSteps() {
        TextView tv_stepsTaken = findViewById(R.id.txtSteps);
        tv_stepsTaken.setOnClickListener(view -> Toast.makeText(this, "Long tap to reset steps", Toast.LENGTH_SHORT).show());

        tv_stepsTaken.setOnLongClickListener(view -> {
            previousTotalSteps = totalSteps;
            tv_stepsTaken.setText("0");
            saveData();
            return true;
        });
    }

    private void saveData() {
        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("key1", previousTotalSteps);
        editor.apply();
    }

    private void loadData() {
        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        float savedNumber = sharedPreferences.getFloat("key1", 0f);
        Log.d("ContActivity", String.valueOf(savedNumber));
        previousTotalSteps = savedNumber;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // We do not have to write anything in this function for this app
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
        saveData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }
}