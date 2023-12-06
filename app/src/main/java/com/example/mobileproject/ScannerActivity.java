package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScannerActivity extends AppCompatActivity {

    private TextView txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        txtData = (TextView) findViewById(R.id.txtData);
    }

    public void escanearCodigoBarra(View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(ScannerActivity.this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);

        intentIntegrator.setPrompt("Lector - CDP");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setBarcodeImageEnabled(true);
        intentIntegrator.initiateScan();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(intentResult != null) {
            if(intentResult.getContents() == null) {
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_SHORT).show();
            } else {
                AdminSQLiteOpenHelper initDatabase = new AdminSQLiteOpenHelper(this, "database", null, 1);
                SQLiteDatabase database = initDatabase.getWritableDatabase();
                String codeRes = intentResult.getContents();
                Cursor cursor = database.rawQuery("select * from store where code = '" + codeRes + "'", null);
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                        String code = cursor.getString(cursor.getColumnIndexOrThrow("code"));
                        String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));

                        String rowData = "Code: " + code  + "\n" +
                                "Descripción: " + description + "\n";
                        txtData.setText(rowData);
                    } while (cursor.moveToNext());
                    Toast.makeText(this, "Producto encontrado", Toast.LENGTH_SHORT).show();
                } else {
                    txtData.setText("Error 404, producto no localizado...");
                    Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}