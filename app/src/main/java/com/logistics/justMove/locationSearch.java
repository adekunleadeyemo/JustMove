package com.logistics.justMove;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceTypes;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logistics.justMove.Models.GeocodingResult;
import com.logistics.justMove.Utils.LatLngAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class locationSearch extends AppCompatActivity {

     private static final String TAG = locationSearch.class.getSimpleName();
    private final Handler handler = new Handler();
    private final PlacePredictionAdapter adapter = new PlacePredictionAdapter();
    private final Gson gson = new GsonBuilder().registerTypeAdapter(LatLng.class, new LatLngAdapter())
            .create();
    private RequestQueue queue;
    private PlacesClient placesClient;
    private AutocompleteSessionToken sessionToken;
    androidx.appcompat.widget.SearchView searchPlace;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_google_autocomplete_search);

        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyAj0dpREmWBTwbyPMGYLMHolWXM8-hituk");
        }

//      Initialize members
        intent = new Intent(locationSearch.this, Location.class);
        searchPlace = findViewById(R.id.search_place);
        placesClient = Places.createClient(this);
        queue = Volley.newRequestQueue(this);

        searchPlace.setIconifiedByDefault(false);
        searchPlace.setFocusable(true);
        searchPlace.setIconified(false);
        searchPlace.requestFocusFromTouch();
        searchPlace.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //progressBar.setIndeterminate(true);

                // Cancel any previous place prediction requests
                handler.removeCallbacksAndMessages(null);

                // Start a new place prediction request in 300 ms
                handler.postDelayed(() -> getPlacePredictions(newText), 300);
                return true;
            }
        });

        initRecyclerView();
    }



    public void setAddress (AutocompletePrediction placePrediction) {

        String location = placePrediction.getFullText(null).toString();
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


    private void initRecyclerView() {
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView
                .addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        adapter.setPlaceClickListener(this::setAddress);
    }

    /**
     * This method demonstrates the programmatic approach to getting place predictions. The
     * parameters in this request are currently biased to Kolkata, India.
     *
     * @param query the plus code query string (e.g. "GCG2+3M K")
     */
    private void getPlacePredictions(String query) {

        // The value of 'bias' biases prediction results to the rectangular region provided
        // (currently Kolkata). Modify these values to get results for another area. Make sure to
        // pass in the appropriate value/s for .setCountries() in the
        // FindAutocompletePredictionsRequest.Builder object as well.
        final LocationBias bias = RectangularBounds.newInstance(
                new LatLng(24.3963, 124.5662), // SW lat, lng
                new LatLng(49.3458, 66.9346) // NE lat, lng
        );

        // Create a new programmatic Place Autocomplete request in Places SDK for Android
        final FindAutocompletePredictionsRequest newRequest = FindAutocompletePredictionsRequest
                .builder()
                .setSessionToken(sessionToken)
                .setLocationBias(bias)
                .setQuery(query)
                .setCountries(Arrays.asList("USA"))
                .setTypesFilter(Arrays.asList(PlaceTypes.ADDRESS))
                .build();

        // Perform autocomplete predictions request
        placesClient.findAutocompletePredictions(newRequest).addOnSuccessListener((response) -> {
            List<AutocompletePrediction> predictions = response.getAutocompletePredictions();
            adapter.setPredictions(predictions);

        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                Log.e(TAG, "Place not found: " + apiException.getStatusCode());
            }
        });
    }

    /**
     * Performs a Geocoding API request and displays the result in a dialog.
     *
     * @see <a href="https://developers.google.com/maps/documentation/geocoding/intro">documentation</a>
     */
    private void geocodePlaceAndDisplay(AutocompletePrediction placePrediction) {
        // Construct the request URL
        final String apiKey = "AIzaSyA3h6cKqEri6qxNLO9Gc1iv8J5-JudKcLY";
        final String url = "https://maps.googleapis.com/maps/api/geocode/json?place_id=%s&key=%s";
        final String requestURL = String.format(url, placePrediction.getPlaceId(), apiKey);

        // Use the HTTP request URL for Geocoding API to get geographic coordinates for the place
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, requestURL, null,
                response -> {
                    try {
                        // Inspect the value of "results" and make sure it's not empty
                        JSONArray results = response.getJSONArray("results");
                        if (results.length() == 0) {
                            Log.w(TAG, "No results from geocoding request.");
                            return;
                        }

                        // Use Gson to convert the response JSON object to a POJO
                        GeocodingResult result = gson.fromJson(
                                results.getString(0), GeocodingResult.class);
                        //use result
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Log.e(TAG, "Request failed"));

        // Add the request to the Request queue.
        queue.add(request);
//        setAddress(placePrediction);
    }


}