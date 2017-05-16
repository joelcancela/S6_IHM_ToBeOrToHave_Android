package fr.unice.polytech.jcancela.tobeortohave.fragment.gmaps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import fr.unice.polytech.jcancela.tobeortohave.R;
import fr.unice.polytech.jcancela.tobeortohave.model.Store;

/**
 * Created by Joel CANCELA VAZ on 08/05/2017.
 */

public class StoresFragment extends android.support.v4.app.Fragment {

    private static final int TAG_CODE_PERMISSION_LOCATION = 1337;

    MapView mapView;
    private GoogleMap myMap;
    private TextView storeSelected;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Bundle bundle = getArguments().getBundle("gotostore");
//        if(bundle!=null){
//            bundle.getString("")
//        }
        //TODO Change actionbar title
        View view = inflater.inflate(R.layout.fragment_stores, container, false);
        storeSelected = (TextView) view.findViewById(R.id.store_selected);
        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                myMap = googleMap;
                myMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
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
            List<Store> stores = new StoresTask().execute().get();
            for (int i = 0; i < stores.size(); i++) {
                Store store = stores.get(i);
                LatLng coordinates = new LatLng(store.getLatitude(), store.getLongitude());
                myMap.addMarker(new MarkerOptions().position(coordinates).title(store.getName()));
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
