package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RouteActivity extends AppCompatActivity {

    private EditText edtOrigin, edtDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        edtOrigin = (EditText) findViewById(R.id.edtOrigin);
        edtDestination = (EditText) findViewById(R.id.edtDestination);
    }

    public void openMaps(View view) {
        String origin = edtOrigin.getText().toString();
        String destination = edtDestination.getText().toString();
        Uri uri = Uri.parse("https://www.google.com/maps/dir/?api=1&origin=" + Uri.encode(origin) + "&destination=" + Uri.encode(destination));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }


}