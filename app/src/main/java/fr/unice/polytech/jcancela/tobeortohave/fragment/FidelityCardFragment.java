package fr.unice.polytech.jcancela.tobeortohave.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.unice.polytech.jcancela.tobeortohave.R;

/**
 * Created by Joel CANCELA VAZ on 08/05/2017.
 */

public class FidelityCardFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fidelity_card, container, false);
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("ToBeOrToHave", 0);
        int userPoints = sharedPreferences.getInt("user_points",0);
        TextView points = (TextView) view.findViewById(R.id.points);
        points.setText(String.valueOf(userPoints)+" points");

        TextView dateTextView = (TextView) view.findViewById(R.id.date);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        dateTextView.setText("au "+ dateFormat.format(date));

        Button handleButton = (Button) view.findViewById(R.id.handle_button);
        handleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new GiftCardsFragment());
            }
        });

        Button rewardsButton = (Button) view.findViewById(R.id.rewards_button);
        rewardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new RewardsFragment());
            }
        });




        return view;
    }

    public void changeFragment(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack("fidelity_card");
        ft.commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
