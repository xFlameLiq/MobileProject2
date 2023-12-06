package com.example.mobileproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileproject.databinding.FragmentSubjectsBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_subjects#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_subjects extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SesionUser sesionUser;
    private TextView txtShowSubs;
    private EditText txtNameSub, txtTeachSub;
    private Subjects subjectsObj;
    private ArrayList<Subjects> subjects;
    SharedPreferences sp;
    private String name, surname, email, pass, register, grade;

    private Button btnAddSubject, btnRefreshSubjects;

    private FragmentSubjectsBinding binding;

    public fragment_subjects() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_subjects.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_subjects newInstance(String param1, String param2) {
        fragment_subjects fragment = new fragment_subjects();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_subjects, container, false);

        txtShowSubs = rootView.findViewById(R.id.txtShowSubs);
        txtNameSub = rootView.findViewById(R.id.txtNameSub);
        txtTeachSub = rootView.findViewById(R.id.txtTeachSub);
        btnAddSubject = rootView.findViewById(R.id.btnAddSubject);
        btnRefreshSubjects = rootView.findViewById(R.id.btnRefresh);
        sp = requireActivity().getSharedPreferences("sesion", Context.MODE_PRIVATE);
        name = sp.getString("name", "");
        surname = sp.getString("surname", "");
        email = sp.getString("email", "");
        register = sp.getString("register", "");
        grade = sp.getString("grade", "");


        // sesionUser = (SesionUser) getActivity().getIntent().getSerializableExtra("User");
        // int verificador = sesionUser.users.verificador;
        // subjects = sesionUser.users.user[verificador].getSubjects();





        btnAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(txtNameSub.getText().toString().equals("") || txtTeachSub.getText().toString().equals(""))) {
                    String nameSub = txtNameSub.getText().toString();
                    String nameTea = txtTeachSub.getText().toString();

                    AdminSQLiteOpenHelper initDatabase = new AdminSQLiteOpenHelper(getActivity().getBaseContext(), "database", null, 1);
                    SQLiteDatabase database = initDatabase.getWritableDatabase();
                    ContentValues registry = new ContentValues();
                    registry.put("nameSub", nameSub);
                    registry.put("nameTea", nameTea);
                    registry.put("register", register);
                    database.insert("subjects", null, registry);
                    database.close();
                    txtNameSub.setText("");
                    txtTeachSub.setText("");
                    Toast.makeText(getContext(), "Materia agregada correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnRefreshSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper initDatabase = new AdminSQLiteOpenHelper(getActivity().getBaseContext(), "database", null, 1);
                SQLiteDatabase database = initDatabase.getWritableDatabase();
                Cursor cursor = database.rawQuery("select * from subjects where register = '" + register + "'", null);
                txtShowSubs.setText("");
                if (cursor.moveToFirst()) {
                    do {

                        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                        String nameSub = cursor.getString(cursor.getColumnIndexOrThrow("nameSub"));
                        String nameTea = cursor.getString(cursor.getColumnIndexOrThrow("nameTea"));
                        String register = cursor.getString(cursor.getColumnIndexOrThrow("register"));

                        String rowData = "Nombre de la materia: " + nameSub + "\n" +
                                "Nombre del profesor: " + nameTea + "\n\n";
                        txtShowSubs.append(rowData);
                    } while (cursor.moveToNext());
                    Toast.makeText(getActivity(), "Mostrando materias", Toast.LENGTH_SHORT).show();
                } else {
                    txtShowSubs.setText("Error 404, materias no añadidas aún...");
                    Toast.makeText(getActivity(), "Materias no encontradas", Toast.LENGTH_SHORT).show();
                }

                cursor.close();
            }
        });
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_subjects, container, false);
        return rootView;
    }

}