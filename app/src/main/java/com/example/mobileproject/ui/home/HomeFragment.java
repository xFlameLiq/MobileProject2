package com.example.mobileproject.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobileproject.R;
import com.example.mobileproject.SesionUser;
import com.example.mobileproject.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TextView txtDatos, txtBienvenida;
    private SharedPreferences sp;
    private String name, surname, email, pass, register, grade;

    @SuppressLint("ResourceType")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        //sesionUser = (SesionUser) getActivity().getIntent().getSerializableExtra("User");

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sp = requireActivity().getSharedPreferences("sesion", Context.MODE_PRIVATE);
        name = sp.getString("name", "");
        surname = sp.getString("surname", "");
        email = sp.getString("email", "");
        register = sp.getString("register", "");
        grade = sp.getString("grade", "");

        txtBienvenida = binding.txtBienvenida;
        txtDatos = binding.txtDatos;

        txtDatos.setText("");
        txtBienvenida.setText("Bienvenido " + name + " " + surname);

        TableLayout tableLayout = root.findViewById(R.id.tableLayout);

        // Crear una fila de encabezado
        TableRow headerRow = new TableRow(requireContext());
        headerRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        TextView header1 = createTableCell("Mis datos", true);
        headerRow.addView(header1);

        TextView header2 = createTableCell("Correos", true);
        headerRow.addView(header2);

        TextView header3 = createTableCell("Ciclo escolar", true);
        headerRow.addView(header3);

        tableLayout.addView(headerRow);

        // Crear filas de datos
        for (int i = 0; i < 3; i++) {
            TableRow dataRow = new TableRow(requireContext());
            dataRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            // Mis datos
            TextView data1;
            switch (i) {

                case 0:
                    data1 = createTableCell("Nombre: " + name, false);
                    dataRow.addView(data1);
                    break;

                case 1:
                    data1 = createTableCell("Apellido: " + surname, false);
                    dataRow.addView(data1);
                    break;

                case 2:
                    data1 = createTableCell("ID: " + register, false);
                    dataRow.addView(data1);
                    break;
            }

            // Correos
            TextView data2;
            switch (i) {

                case 0:
                    data2 = createTableCell("Personal: " + email, false);
                    dataRow.addView(data2);
                    break;

                case 1:
                    data2 = createTableCell("Gmail: a" +register+ "@ceti.mx", false);
                    dataRow.addView(data2);
                    break;

                case 2:
                    data2 = createTableCell("Hotmail: A" +register+ "@live.ceti.mx", false);
                    dataRow.addView(data2);
                    break;
            }

            // Ciclo escolar
            TextView data3;
            switch (i) {

                case 0:
                    data3 = createTableCell("Grado: " + grade, false);
                    dataRow.addView(data3);
                    break;

                case 1:
                    data3 = createTableCell("Ciclo: " + "2023-B", false);
                    dataRow.addView(data3);
                    break;

                case 2:
                    data3 = createTableCell("AGO-DIC 2023", false);
                    dataRow.addView(data3);
                    break;
            }

            tableLayout.addView(dataRow);

            // Aplicar color de fondo a las filas impares
            if (i % 2 != 0) {
                dataRow.setBackgroundColor(Color.parseColor("#ECECEC"));
            }
        }

        // Ajustar el ancho de las filas al 100% del ancho de pantalla
        tableLayout.setStretchAllColumns(true);
        tableLayout.setShrinkAllColumns(true);


        final TextView textView = binding.txtBienvenida;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    // Método para crear celdas de tabla personalizadas
    private TextView createTableCell(String text, boolean isHeader) {
        TextView textView = new TextView(requireContext());
        textView.setText(text);
        textView.setPadding(10, 10, 10, 10);

        // Aplicar bordes a las celdas
        textView.setBackgroundResource(R.drawable.table_cell_border);

        // Personalizar estilo de texto para el encabezado
        if (isHeader) {
            textView.setTextColor(Color.WHITE);
            textView.setTypeface(null, Typeface.BOLD);
            textView.setBackgroundColor(Color.parseColor("#333333"));
        }

        return textView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}