package com.example.appcovid.home_fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcovid.DeclarePersonalInfoActivity;
import com.example.appcovid.EpidemicPreventionActivity;
import com.example.appcovid.R;
import com.example.appcovid.network.NetworkModule;
import com.example.appcovid.network.StatisticalService;
import com.example.appcovid.network.dto.CreateAccDto;
import com.example.appcovid.network.dto.DiseaseInfo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.chip.Chip;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements OnMapReadyCallback, LocationListener {

    private ImageButton btnrf;
    private TextView cured;
    private TextView died;
    private TextView sick;
    private TextView detailText;
    private Button btnDeclare;
    private CreateAccDto acc;
    private StatisticalService statisticalService = NetworkModule.statisticalService;
    private DiseaseInfo diseaseInfo;
    private Date currentDateTime = Calendar.getInstance().getTime();

    private Chip chipVn;
    private Chip chipWorld;

    private TextView txtInfected;
    private TextView txtDeath;
    private TextView txtRecovered;

    private TextView txtInfectedInc;
    private TextView txtDeathInc;
    private TextView txtRecoveredInc;
    private Button btnHDPCD;
    private Button btnEpidemic;

    private GoogleMap map;

    private ScrollView scrollView;

    private ConstraintLayout constraintLayout;

    private LocationManager mLocationManager;
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    public HomeFragment() {

    }

    public static HomeFragment newInstance(CreateAccDto acc) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putSerializable("accinfo", acc);
       // Log.d("INFO LOGIN",acc.toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            acc = ((CreateAccDto) getArguments().getSerializable("accinfo"));
        }
        getStatistical();
    }
    private String convertDateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("hh:mm, dd/MM");
        return dateFormat.format(date);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        TextView date = (TextView) rootView.findViewById(R.id.textView12);

        date.setText("C???p nh???t :"+convertDateToString(currentDateTime));

        //Stactistical
         sick = (TextView) rootView.findViewById(R.id.txt_infected);
         died = (TextView) rootView.findViewById(R.id.txt_death);
         cured = (TextView) rootView.findViewById(R.id.txt_recovered);

        // Inflate the layout for this fragment

        return rootView;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnDeclare = view.findViewById(R.id.btn_declare_info);
        btnHDPCD = view.findViewById(R.id.button6);
        btnrf = view.findViewById(R.id.imageButton);

        btnrf.setOnClickListener(v -> {
            getStatistical();
            Toast.makeText(getActivity(), "Refesh Data", Toast.LENGTH_LONG).show();

        } );
        btnHDPCD.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "OK", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getActivity(), EpidemicPreventionActivity.class);
            startActivity(intent);
        } );

        btnDeclare.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DeclarePersonalInfoActivity.class);
            intent.putExtra("accinfo", acc);
            startActivity(intent);
        });

        chipVn = (Chip) view.findViewById(R.id.chip_vn);
        chipWorld = (Chip) view.findViewById(R.id.chip_world);

        txtInfected = (TextView) view.findViewById(R.id.txt_infected);
        txtDeath = (TextView) view.findViewById(R.id.txt_death);
        txtRecovered = (TextView) view.findViewById(R.id.txt_recovered);
        txtInfectedInc = (TextView) view.findViewById(R.id.txt_infected_inc);
        txtDeathInc = (TextView) view.findViewById(R.id.txt_death_inc);
        txtRecoveredInc = (TextView) view.findViewById(R.id.txt_recovered_inc);

        scrollView = (ScrollView) view.findViewById(R.id.scrollview);
        constraintLayout = (ConstraintLayout) view.findViewById(R.id.frameLayout);


        chipVn.setOnClickListener(v -> {
            getStatistical();
        });

        chipWorld.setOnClickListener(v -> {
            getStatisticalWorld();
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        try{
            mLocationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000,
                    100f, this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void getStatistical(){

        Call<DiseaseInfo> call = statisticalService.getDataNcov();

        call.enqueue(new Callback<DiseaseInfo>() {
            @Override
            public void onResponse(Call<DiseaseInfo> call, Response<DiseaseInfo> response) {
                if (response.isSuccessful()) {
                    diseaseInfo = response.body();
                    Log.d("INFO 1234", String.valueOf(diseaseInfo.getTotal().getInternal().getCases()));

                    //total.internal.death
                    //total.internal.recovered
                    //total.internal.cases
                    //total.world
                    //today
                    if (diseaseInfo != null) {
                        //VN
                        String money = formatter.format(diseaseInfo.getTotal().getInternal().getCases());
                        sick.setText(money);
                        txtInfectedInc.setText("+" + formatter.format(diseaseInfo.getToday().getInternal().getCases()));
                        cured.setText(formatter.format(diseaseInfo.getTotal().getInternal().getRecovered()));
                        txtRecoveredInc.setText("+" + formatter.format(diseaseInfo.getToday().getInternal().getRecovered()));
                        died.setText(formatter.format(diseaseInfo.getTotal().getInternal().getDeath()));
                        txtDeathInc.setText("+" + formatter.format(diseaseInfo.getToday().getInternal().getDeath()));

                    } else {
                        Toast.makeText(getActivity(), "Data null", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "L???i khi call api", Toast.LENGTH_LONG).show();

                }

            }
            @Override
            public void onFailure(Call<DiseaseInfo> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getActivity(), "L???i khi call api", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getStatisticalWorld(){

        Call<DiseaseInfo> call = statisticalService.getDataNcov();

        call.enqueue(new Callback<DiseaseInfo>() {
            @Override
            public void onResponse(Call<DiseaseInfo> call, Response<DiseaseInfo> response) {
                if (response.isSuccessful()) {
                    diseaseInfo = response.body();
                    if (diseaseInfo != null) {
                        //World
                        txtInfected.setText(formatter.format(diseaseInfo.getTotal().getWorld().getCases()));
                        txtDeath.setText(formatter.format(diseaseInfo.getTotal().getWorld().getDeath()));
                        txtRecovered.setText(formatter.format(diseaseInfo.getTotal().getWorld().getRecovered()));
                        txtInfectedInc.setText("+" + formatter.format(diseaseInfo.getToday().getWorld().getCases()));
                        txtDeathInc.setText( "+" + formatter.format(diseaseInfo.getToday().getWorld().getDeath()));
                        txtRecoveredInc.setText( "+" + formatter.format(diseaseInfo.getToday().getWorld().getRecovered()));

                    } else {
                        Toast.makeText(getActivity(), "Data null", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "L???i khi call api", Toast.LENGTH_LONG).show();

                }

            }
            @Override
            public void onFailure(Call<DiseaseInfo> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getActivity(), "L???i khi call api", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        try {
            LatLng curr = new LatLng(location.getLatitude(), location.getLongitude());
            map.addMarker(new MarkerOptions()
                    .position(curr));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(curr, (float) (map.getMaxZoomLevel()*0.73)));
        }
        catch (Exception e )
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.setMyLocationEnabled(true);
        map.setMaxZoomPreference(map.getMaxZoomLevel());

        LatLng curr = new LatLng(21.003239, 105.823505);
        map.addMarker(new MarkerOptions()
                .position(curr));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(curr, (float) (map.getMaxZoomLevel()*0.73)));
    }

}