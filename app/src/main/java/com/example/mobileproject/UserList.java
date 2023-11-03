package com.example.mobileproject;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class UserList extends AppCompatActivity implements Serializable {

    public User[] user;
    private final int REGISTERS = 15;
    int count;
    int verificador;
    int subjectCount;

    public UserList() {
        this.user = new User[REGISTERS];
        this.count = 0;
        this.subjectCount = -1;
    }

    public int addUser(User user) {
        if(this.count < this.user.length) {
            this.user[this.count] = user;
            this.user[this.count].setId(count);
            this.count++;
            return 1;
        } else {
            System.out.println("Usuarios alcanzados");
            return -1;
        }
    }

    public int validateUser(String email, String pass) {
        this.verificador = 0;
        for (int i = 0; i < this.count; i++) {
            if( email.equals(this.user[i].getEmail()) && pass.equals(this.user[i].getPass()) ) {
                System.out.println("Encontrado");
                return 1;
            }
            this.verificador++;
        }
        return -1;
    }

    public String getUsers() {
        String mostrar = "";
        for (int i = 0; i < this.count; i++) {
            mostrar += "Email: " + this.user[i].getEmail() + "\n" +
                    "Contraseña: " + this.user[i].getPass() + "\n" +
                    "Id: " + this.user[i].getId() + "\n\n";
        }
        return mostrar;
    }

    public int returnUser() {
        return this.verificador;
    }

    public boolean findUser(String registro) {

        for (int i = 0; i < this.count; i++)
            if( registro.equals(this.user[i].getRegistro()) )  return true;

        return false;
    }

    public String showUser(String registro) {

        for (int i = 0; i < this.count; i++) {
            if (registro.equals(this.user[i].getRegistro())) {
                return  "Nombre: " + this.user[i].getNombre() + "\n" +
                        "Apellido: " + this.user[i].getApellido() + "\n" +
                        "Registro: " + this.user[i].getRegistro() + "\n" +
                        "Correo: " + this.user[i].getEmail() + "\n" +
                        "Gmail: a" + this.user[i].getRegistro() + "@ceti.mx \n" +
                        "Hotmail: A" + this.user[i].getRegistro() + "@live.ceti.mx \n" +
                        "Semestre: " + this.user[i].getGrado() + " | FEB-JUN 2023\n";
            }
        }

        return "Parece que hubo un error al mostrar los datos...";
    }

    public int addSubjectCount() {
        this.subjectCount = this.subjectCount+1;
        return this.subjectCount;
    }

    public int getSubjectCount() {
        return this.subjectCount;
    }

}
