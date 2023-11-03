package com.example.mobileproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileproject.databinding.FragmentHomeBinding;
import com.example.mobileproject.databinding.FragmentSubjectsBinding;

import java.util.ArrayList;
import java.util.Collections;

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
    private TextView txtDebug;
    private EditText txtNameSub, txtTeachSub;
    private Subjects subjectsObj;
    private ArrayList<Subjects> subjects;

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

        txtDebug = rootView.findViewById(R.id.txtDebug);
        txtNameSub = rootView.findViewById(R.id.txtNameSub);
        txtTeachSub = rootView.findViewById(R.id.txtTeachSub);
        btnAddSubject = rootView.findViewById(R.id.btnAddSubject);
        btnRefreshSubjects = rootView.findViewById(R.id.btnRefresh);


        sesionUser = (SesionUser) getActivity().getIntent().getSerializableExtra("User");
        int verificador = sesionUser.users.verificador;
        subjects = sesionUser.users.user[verificador].getSubjects();





        btnAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(txtNameSub.getText().toString().equals("") || txtTeachSub.getText().toString().equals(""))) {
                    sesionUser = (SesionUser) getActivity().getIntent().getSerializableExtra("User");
                    int verificador = (sesionUser.users.returnUser());
                    int idSubjects = sesionUser.users.addSubjectCount();
                    String nameSub = txtNameSub.getText().toString();
                    String nameTeacher = txtTeachSub.getText().toString();
                    subjectsObj = new Subjects(idSubjects, nameSub, nameTeacher);
                    subjects.add(subjectsObj);
                    sesionUser.users.user[verificador].setSubjects(subjects);
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
                sesionUser = (SesionUser) getActivity().getIntent().getSerializableExtra("User");
                int size = sesionUser.users.user[verificador].getSubjects().size();
                int verificador = (sesionUser.users.returnUser());
                String infoPerfil = "";
                for (int i = 1; i < size; i++) {
                    infoPerfil += "Identificador: " + sesionUser.users.user[verificador].getSubjects().get(i).getId() + "\n" +
                            "Materia: " + sesionUser.users.user[verificador].getSubjects().get(i).getNameSub() + "\n" +
                            "Profesor: " + sesionUser.users.user[verificador].getSubjects().get(i).getNameTea()+ "\n\n";
                }

                txtDebug.setText(infoPerfil);
            }
        });
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_subjects, container, false);
        return rootView;
    }

}