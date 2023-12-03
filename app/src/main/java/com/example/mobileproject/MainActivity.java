package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private SesionUser sesionUser = new SesionUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btnWelcome);
        AdminSQLiteOpenHelper initDatabase = new AdminSQLiteOpenHelper(this, "database", null, 1);
        SQLiteDatabase database = initDatabase.getWritableDatabase();
        ContentValues registry = new ContentValues();
        registry.put("name", "admin");
        registry.put("surname", "admin");
        registry.put("email", "admin@admin.com");
        registry.put("pass", "adminadmin");
        registry.put("register", "00000000");
        registry.put("grade", "6P");
        database.insert("users", null, registry);
        database.close();
    }

    public void main (View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("User", sesionUser);
        startActivity(intent);
        finish();
    }

}