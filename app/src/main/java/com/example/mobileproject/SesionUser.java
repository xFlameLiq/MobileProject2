package com.example.mobileproject;

import java.io.Serializable;
import java.util.ArrayList;

public class SesionUser implements Serializable {

    public UserList users;
    ArrayList<Subjects> subjects = new ArrayList();
    private Subjects subjectsObj = new Subjects(0, "pruebitaxd", "");


    public SesionUser() {
        this.users = new UserList();
        init();
    }

    public void init() {
        subjects.add(subjectsObj);
        User admin = new User(0, "Hot", "Taco", "admin", "admin", "21110130", 8, subjects);
        users.addUser(admin);

        User david = new User(0, "David", "Loera", "davidarturoloera@gmail.com", "1234", "21110121", 5, subjects);
        users.addUser(david);

        User benjamin = new User(0, "Benjamin", "Cortina", "benja@hotmail.com", "1234", "21110150", 7, subjects);
        users.addUser(benjamin);

        User alondra = new User(0, "Alondra", "Urzua", "alondra@gmail.com", "1234", "21110134", 3, subjects);
        users.addUser(alondra);

        User nahomy = new User(0, "Nahomy", "Hernández", "nahomy@hotmail.com", "1234", "21110142", 6, subjects);
        users.addUser(nahomy);
    }


}
