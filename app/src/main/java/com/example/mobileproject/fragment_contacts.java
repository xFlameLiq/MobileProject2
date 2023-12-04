package com.example.mobileproject;

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


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_contacts#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_contacts extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SesionUser sesionUser;
    private EditText txtRegistro;
    private Button btnFindStudent;
    private TextView txtInfoStudent;

    public fragment_contacts() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_contacts.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_contacts newInstance(String param1, String param2) {
        fragment_contacts fragment = new fragment_contacts();
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
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);

        txtRegistro = rootView.findViewById(R.id.txtRegistro);
        btnFindStudent = rootView.findViewById(R.id.btnFindStudent);
        txtInfoStudent = rootView.findViewById(R.id.txtInfoStudent);
        // sesionUser = (SesionUser) getActivity().getIntent().getSerializableExtra("User");

       /* btnFindStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sesionUser = (SesionUser) getActivity().getIntent().getSerializableExtra("User");
                String registroIngresado = txtRegistro.getText().toString();

                if(sesionUser.users.findUser(registroIngresado)) {
                    Toast.makeText(requireContext(), "Registro encontrado", Toast.LENGTH_SHORT).show();
                    lbShowInfoStudent.setText(sesionUser.users.showUser(registroIngresado));
                } else {
                    Toast.makeText(requireContext(), "El registro no fue encontrado", Toast.LENGTH_SHORT).show();
                    lbShowInfoStudent.setText("Registro incorrecto...");
                }
            }
        });*/


        btnFindStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper initDatabase = new AdminSQLiteOpenHelper(getActivity().getBaseContext(), "database", null, 1);
                SQLiteDatabase database = initDatabase.getWritableDatabase();
                String studentReg = txtRegistro.getText().toString();
                Cursor cursor = database.rawQuery("select * from users where register = '" + studentReg + "'", null);
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                        String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                        String surname = cursor.getString(cursor.getColumnIndexOrThrow("surname"));
                        String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                        String register = cursor.getString(cursor.getColumnIndexOrThrow("register"));
                        String grade = cursor.getString(cursor.getColumnIndexOrThrow("grade"));

                        String rowData = "Nombre: " + name + "\n" +
                                "Apellido: " + surname + "\n" +
                                "Registro: " + register + "\n" +
                                "Correo: " + email + "\n" +
                                "Gmail: a" + register + "@ceti.mx \n" +
                                "Hotmail: A" + register + "@live.ceti.mx \n" +
                                "Semestre: " + grade + " | AGO-DIC 2023\n";
                        txtInfoStudent.append(rowData);
                    } while (cursor.moveToNext());
                    Toast.makeText(getActivity(), "Usuario encontrado", Toast.LENGTH_SHORT).show();
                } else {
                    txtInfoStudent.setText("Error 404, usuario no localizado...");
                    Toast.makeText(getActivity(), "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                }

                cursor.close();
            }
        });

        return rootView;
    }

}