package com.example.mobileproject;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

public class fragment_location extends Fragment  {

    private StreetViewPanorama streetViewPanorama;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_location, container, false);

        // genera una referencia al contenedor del fragment en el activity del fragment
        ViewGroup fragmentContainer = rootView.findViewById(R.id.streetviewpanorama);

        // opciones para street View
        StreetViewPanoramaOptions options = new StreetViewPanoramaOptions()
                .position(new LatLng(20.7022620000, -103.3884300000));
        SupportStreetViewPanoramaFragment streetViewPanoramaFragment = SupportStreetViewPanoramaFragment.newInstance(options);

        // transacción de fragment
        getChildFragmentManager().beginTransaction()
                .replace(fragmentContainer.getId(), streetViewPanoramaFragment)
                .commit();

        streetViewPanoramaFragment.getStreetViewPanoramaAsync(new OnStreetViewPanoramaReadyCallback() {
            @Override
            public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
                streetViewPanorama = panorama;

            }

        });

        return rootView;
    }
}


