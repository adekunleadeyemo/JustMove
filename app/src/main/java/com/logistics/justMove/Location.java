package com.logistics.justMove;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Location extends AppCompatActivity implements OnMapReadyCallback {
    TextView pk_addr_1;
   TextView pk_addr_2 ;
    TextView dl_addr_1 ;
    TextView dl_addr_2 ;
    ConstraintLayout pk_addr_box;
    ConstraintLayout dl_addr_box;

    LinearLayout dl_text_layout;
    @Nullable String pickup_addr;
    @Nullable String delivery_addr;
    @Nullable String [] dl_Arr;
    @Nullable String [] pk_Arr;
    @Nullable String changeType;

    TextView next_btn;

    Intent intent;

    Button locationContinue;

    ImageView locationBack;

    GoogleMap google_map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        init();
        locationBack.setOnClickListener(e -> startActivity(new Intent(Location.this, Introduction.class)));
        pk_addr_box.setOnClickListener(e -> setPickupLocation());
        dl_addr_box.setOnClickListener(e -> setDestinationLocation());
        next_btn.setOnClickListener( e -> proceedToNextActivity());
        locationContinue.setOnClickListener(e -> proceedToNextActivity());

//      mapFragment.getMapAsync(this);
    }

    private  void proceedToNextActivity(){
        if(delivery_addr != null && !delivery_addr.isEmpty()){

            Intent vehicleIntent = new Intent(Location.this, Vehicle.class);
            vehicleIntent.putExtra("pk_addr_1", pk_addr_1.getText().toString());
            vehicleIntent.putExtra("pk_addr_2", pk_addr_2.getText().toString());
            vehicleIntent.putExtra("dl_addr_1", dl_addr_1.getText().toString());
            vehicleIntent.putExtra("dl_addr_2", dl_addr_2.getText().toString());

            startActivity(vehicleIntent);
        }
        else {
            Toast.makeText(Location.this,"Enter  Location",Toast.LENGTH_LONG).show();
        }
    }

    private void setDestinationLocation(){
        if(pickup_addr !=null && !pickup_addr.isEmpty()) {
            intent.putExtra("pickup", pickup_addr);
            intent.putExtra("change", "delivery");
            startActivity(intent);
        }
        else{
            Toast.makeText(Location.this,"Enter Pickup Location",Toast.LENGTH_LONG).show();
        }
    }

    private  void setPickupLocation(){
        if(delivery_addr != null && !delivery_addr.isEmpty()){
            intent.putExtra("delivery", delivery_addr);
        }
        intent.putExtra("change", "pickup");
        startActivity(intent);
    }

    private void init(){
        intent = new Intent(Location.this, locationSearch.class);
        Bundle extras  = getIntent().getExtras();
        changeType = extras.getString("change");
        delivery_addr = extras.getString("delivery");
        pickup_addr = extras.getString("pickup");


        pk_addr_1 = findViewById(R.id.pickup_addr_1);
        pk_addr_2 = findViewById(R.id.pickup_addr_2);
        dl_addr_1 = findViewById(R.id.delivery_addr_1);
        dl_addr_2 = findViewById(R.id.delivery_addr_2);
        pk_addr_box = findViewById(R.id.pickup_addr_container);
        dl_addr_box = findViewById(R.id.delivery_addr_container);
        dl_text_layout = findViewById(R.id.delivery_addr);
        next_btn = findViewById(R.id.location_next);
        locationContinue = findViewById(R.id.location_continue);
        locationBack = findViewById(R.id.location_back_arrow);

        if(pickup_addr !=null && !pickup_addr.isEmpty()){
            pk_Arr = pickup_addr.split(",");
//            Address address = (Address) extras.get("address");
//            LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
//            google_map.addMarker(new MarkerOptions().position(latLng).title(pickup_addr));
//            google_map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
        }

        if(changeType.equals("delivery")) {
            dl_Arr = delivery_addr.split(",");
            pk_addr_1.setText(pk_Arr[0]);
            pk_addr_2.setText(pk_Arr[1]+ pk_Arr[2]);
            dl_addr_1.setText(dl_Arr[0]);
            dl_addr_2.setText(dl_Arr[1] + dl_Arr[2]);
            next_btn.setVisibility(View.VISIBLE);
        }
        else {
            if(pickup_addr !=null && !pickup_addr.isEmpty()){
                pk_addr_1.setText(pk_Arr[0]);
                pk_addr_2.setText(pk_Arr[1]+ pk_Arr[2]);

                if(delivery_addr != null && !delivery_addr.isEmpty()){
                    dl_Arr = delivery_addr.split(",");
                    dl_addr_1.setText(dl_Arr[0]);
                    dl_addr_2.setText(dl_Arr[1] + dl_Arr[2]);
                    next_btn.setVisibility(View.VISIBLE);
                }

            }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        google_map = googleMap;
    }
}