package com.example.primaryfolder.cookbuddy.fragments;


import android.content.pm.PackageManager;
import android.location.Location;

import com.example.primaryfolder.cookbuddy.GetNearbyPlacesData;
import com.example.primaryfolder.cookbuddy.MapsActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.primaryfolder.cookbuddy.R;
import com.google.android.gms.maps.SupportMapFragment;

public class MappingFragment extends Fragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks ,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

  GoogleMap mGoogleMap;
  private GoogleApiClient client;
  private
  MapView mMapView;
  View mView;
  private static final int MY_PERMISSION_REQUEST_CODE=1;
  int PROXIMITY_RADIUS=10000;
  double latitude;
  double longitude;
  private Location lastlocation;
  private Marker currentLocationmMarker;
  private LocationRequest locationRequest;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
      super.onCreate(savedInstanceState);
  }



  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      mView = inflater.inflate(R.layout.fragment_nearby_stores, container, false);
      return mView;
  }


  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
      super.onViewCreated(view, savedInstanceState);

      mMapView = (MapView) mView.findViewById(R.id.map);
      if (mMapView != null) {
          mMapView.onCreate(null);
          mMapView.onResume();
          mMapView.getMapAsync(this);

//          setUpLocation();
      }

  }

//  private void setUpLocation(){
//      if(android.support.v4.app.ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//              && android.support.v4.app.ActivityCompat.checkSelfPermission(this,
//              android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//          requestRuntimePermission();
//      }
//  }
//
//    private void requestRuntimePermission() {
//      android.support.v4.app.ActivityCompat.requestPermissions(this, new String[]
//              {
//          android.Manifest.permission.ACCESS_COARSE_LOCATION,
//            android.Manifest.permission.ACCESS_FINE_LOCATION
//        },MY_PERMISSION_REQUEST_CODE);
//
//    }







    @Override
  public void onMapReady(GoogleMap googleMap) {
    MapsInitializer.initialize(getContext());

    mGoogleMap = googleMap;
    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    //googleMap.addMarker(new MarkerOptions().position(new LatLng(40.689247, -74.044502)).title("Statue of Liberty"));
    //requestPermissions(permissionsList, REQUEST_CODE);
    googleMap.setMyLocationEnabled(true);
    //CameraPosition Liberty = CameraPosition.builder().target(new LatLng(40.689247, -74.044502)).zoom(16).bearing(0).tilt(45).build();
    //googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));
        googleMap.clear();
        String hospital = "schools";
        String url = getURL(latitude, longitude, hospital);
        Object dataTransfer[] = new Object[2];
        dataTransfer[0]=mGoogleMap;
        dataTransfer[1]=url;
        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
        getNearbyPlacesData.execute(dataTransfer);




//      mGoogleMap.setMyLocationEnabled(true);
//      mGoogleMap.setOnMyLocationButtonClickListener((GoogleMap.OnMyLocationButtonClickListener) this);
//      mGoogleMap.setOnMyLocationClickListener((GoogleMap.OnMyLocationClickListener) this);
//    MapsActivity.buildGoogleApiClient();
//    mGoogleMap.setMyLocationEnabled(true);


  }


  private String getURL(double latitude, double longitude, String nearbyPlace){
      StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
      //googlePlaceUrl.append("location=42.034534,-93.620369"); //ames, IA
     // googlePlaceUrl.append("location="+latitude+","+longitude); //would use actual location on a real device(not emulator)
      googlePlaceUrl.append("location=37.4220,-122.0844308");
      googlePlaceUrl.append("&radius="+PROXIMITY_RADIUS);
      googlePlaceUrl.append("&type=supermarket");
      googlePlaceUrl.append("&sensor=true");
      googlePlaceUrl.append("&key=AIzaSyAYpmHoc7GWuethJ39agrMaSBWxmphIQNY");
              return googlePlaceUrl.toString();

  }

    //This method doesn't get updated in the emulator, meaning you can only manually set a location
    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude=location.getLongitude();
        lastlocation = location;
        if(currentLocationmMarker != null){
            currentLocationmMarker.remove();
        }
        Log.d("lat = ",""+latitude);
        LatLng latLng = new LatLng(location.getLatitude() , location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currentLocationmMarker = mGoogleMap.addMarker(markerOptions);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomBy(10));

        if(client != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(client,this);
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(100);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);


//        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED)
//        {
//            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
//        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
