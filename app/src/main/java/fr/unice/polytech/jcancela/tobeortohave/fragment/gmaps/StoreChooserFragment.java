package fr.unice.polytech.jcancela.tobeortohave.fragment.gmaps;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.unice.polytech.jcancela.tobeortohave.R;

/**
 * Created by Joel CANCELA VAZ on 08/05/2017.
 */

public class StoreChooserFragment extends android.support.v4.app.Fragment {

    private static final int TAG_CODE_PERMISSION_LOCATION = 1337;

    MapView mapView;
    private GoogleMap myMap;
    private TextView storeSelected;
    private Button selectStore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //TODO Change actionbar title
        View view = inflater.inflate(R.layout.fragment_choose_store, container, false);
        storeSelected = (TextView) view.findViewById(R.id.store_selected);
        selectStore = (Button) view.findViewById(R.id.select_store);
        selectStore.setEnabled(false);
        selectStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String store= storeSelected.getText().toString();
                new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Nouveau magasin favori")
                        .setMessage("Confirmer "+store+" comme magasin favori ?")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    if (getActivity() != null) {
                                        SharedPreferences settings = getActivity().getApplicationContext().getSharedPreferences("ToBeOrToHave", 0);
                                        SharedPreferences.Editor editor = settings.edit();
                                        editor.putString("fav_store", store);
                                        editor.apply();
                                    }
                            }

                        })
                        .setNegativeButton("Non", null)
                        .show();
            }
        });
        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                myMap = googleMap;
                myMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        selectStore.setEnabled(true);
                        storeSelected.setText(marker.getTitle());
                        return false;
                    }
                });
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    myMap.setMyLocationEnabled(true);
                    myMap.getUiSettings().setMyLocationButtonEnabled(true);
                } else {
                    requestPermissions( new String[]{
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION},
                            TAG_CODE_PERMISSION_LOCATION);
                }//TODO getting permissions make app go to login
                LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                LatLng coordinates;
                if (location == null) {
                    coordinates = new LatLng(43.6155793, 7.071874799999932);
                } else {
                    coordinates = new LatLng(location.getLatitude(), location.getLongitude());
                }
//                googleMap.addMarker(new MarkerOptions().position(coordinates).title("Vous"));
                addStores(myMap);
                myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 8));
                mapView.onResume();

            }
        });

        //Edit View
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    private void addStores(GoogleMap myMap) {
        try {
            JSONArray stores = new StoresTask().execute().get();
            for (int i = 0; i < stores.length(); i++) {
                JSONObject store = stores.getJSONObject(i);
                String name = store.getString("name");
                JSONObject position = store.getJSONObject("position");
                double lat = position.getDouble("latitude");
                double longi = position.getDouble("longitude");
                LatLng coordinates = new LatLng(lat, longi);
                myMap.addMarker(new MarkerOptions().position(coordinates).title(name));
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
