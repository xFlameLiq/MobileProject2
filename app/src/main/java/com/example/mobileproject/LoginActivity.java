package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileproject.ui.home.HomeFragment;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmailLogin, txtPassLogin;
    private CheckBox cbSaveProfile;
    private SesionUser sesionUser;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sesionUser = (SesionUser) getIntent().getSerializableExtra("User");
        txtEmailLogin = (EditText) findViewById(R.id.txtEmailLogin);
        txtPassLogin = (EditText) findViewById(R.id.txtPassLogin);
        //cbSaveProfile = (CheckBox) findViewById(R.id.cbSaveProfile);
        sp = getSharedPreferences("sesion", Context.MODE_PRIVATE);
        String savedUser = sp.getString("userSesion", "");
        if(savedUser != "") {
            Intent home = new Intent(this, MenuLateralActivity.class);
            startActivity(home);
        }
    }

    public void validateLogin(View view) {
        if(!(txtEmailLogin.getText().toString().equals("") || txtPassLogin.getText().toString().equals(""))) {
            AdminSQLiteOpenHelper initDatabase = new AdminSQLiteOpenHelper(this, "database", null, 1);
            SQLiteDatabase database = initDatabase.getWritableDatabase();
            String email = txtEmailLogin.getText().toString();
            String pass = txtPassLogin.getText().toString();

            Cursor row = database.rawQuery
                    ("select id, name, surname, email, pass, register, grade from users where email = '" + email + "' and pass = '" + pass + "' ", null);
            if(row.moveToFirst()) {
                Toast.makeText(this, "Usuario encontrado", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("id", row.getString(0));
                editor.putString("name", row.getString(1));
                editor.putString("surname", row.getString(2));
                editor.putString("email", row.getString(3));
                editor.putString("pass", row.getString(4));
                editor.putString("register", row.getString(5));
                editor.putString("grade", row.getString(6));
                if(cbSaveProfile.isChecked()) {
                    editor.putString("userSesion", "active");
                }
                editor.commit();
                database.close();
                Intent intent = new Intent(this, MenuLateralActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "No ha sido posible encontrar al usuario.", Toast.LENGTH_SHORT).show();
                database.close();
            }
        }
        else {
            Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("User", sesionUser);
        startActivity(intent);
        finish();
    }
}