package com.example.mobileproject.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobileproject.R;
import com.example.mobileproject.databinding.FragmentGalleryBinding;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private ImageButton plantel, cultura, edificiof, auditorio, pasillos, plano, canchas;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        plantel = binding.imgBtnPlantel;
        cultura = binding.imgBtnCultura;
        edificiof = binding.imgBtnEdificioF;
        auditorio = binding.imgBtnAuditorio;
        pasillos = binding.imgBtnPasillos;
        plano = binding.imgBtnPlano;
        canchas = binding.imgBtnCanchas;

        plantel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Entrada del Ceti Colomos", Toast.LENGTH_SHORT).show();
            }
        });

        cultura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Espacios culturales del Ceti Colomos", Toast.LENGTH_SHORT).show();
            }
        });

        edificiof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Edificio F del Ceti Colomos", Toast.LENGTH_SHORT).show();
            }
        });

        auditorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Auditorio del Ceti Colomos", Toast.LENGTH_SHORT).show();
            }
        });

        pasillos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Espacios libres del Ceti Colomos", Toast.LENGTH_SHORT).show();
            }
        });

        plano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Distribución de edificios del Ceti Colomos", Toast.LENGTH_SHORT).show();
            }
        });

        canchas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Deportividad en el Ceti Colomos", Toast.LENGTH_SHORT).show();
            }
        });


        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}