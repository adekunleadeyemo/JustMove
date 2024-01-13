package com.logistics.justMove;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class locationSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_search);

        Intent intent = new Intent(locationSearch.this, Location.class);

        if(!Places.isInitialized()){
            Places.initialize(getApplicationContext(), "AIzaSyA3h6cKqEri6qxNLO9Gc1iv8J5-JudKcLY");
        }
        AutocompleteSupportFragment autocompleteSupportFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.google_places);
        List<Place.Field> placeFields = Arrays.asList(Place.Field.LAT_LNG, Place.Field.NAME);
        autocompleteSupportFragment.setPlaceFields(placeFields);

        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onError(@NonNull Status status) {

            }

            @Override
            public void onPlaceSelected(@NonNull Place place) {

                String location = place.getName();
                List<Address> addresses = null;

                if(location != null) {
                    Geocoder geocoder = new Geocoder(locationSearch.this);

                    try {
                        addresses = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if(addresses != null && addresses.size() > 0){

                        Address address = addresses.get(0);
                        @Nullable String changeType = getIntent().getExtras().getString("change");

                        if(changeType.equals("pickup")){
                           @Nullable String dl_addr = getIntent().getExtras().getString("delivery");
                            intent.putExtra("pickup",address.getAddressLine(0));
//                            intent.putExtra("address",address);
                            if(dl_addr != null && !dl_addr.isEmpty()){
                                intent.putExtra("delivery",dl_addr);
                            }
                            intent.putExtra("change","pickup");
                            startActivity(intent);
                        }
                        else {
                            intent.putExtra("delivery",address.getAddressLine(0));
                            intent.putExtra("pickup",getIntent().getExtras().getString("pickup"));
                            intent.putExtra("change","delivery");
                            startActivity(intent);
                        }

                    }
                    else {
                        Toast.makeText(locationSearch.this,"Cannot find Address",Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
    }
}