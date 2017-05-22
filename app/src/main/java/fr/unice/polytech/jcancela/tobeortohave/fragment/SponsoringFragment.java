package fr.unice.polytech.jcancela.tobeortohave.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import fr.unice.polytech.jcancela.tobeortohave.R;

/**
 * Created by Joel CANCELA VAZ on 08/05/2017.
 */

public class SponsoringFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sponsorhip, container, false);
        Button shareButton = (Button) view.findViewById(R.id.share_button);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ToBeOrToHave",0);
        final String user = sharedPreferences.getString("username","anonymous");
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Profitez d'un gain de 100 points sur ToBeOrToHave avec mon code promotionnel: https://api.qrserver.com/v1/create-qr-code/?size=150x150&data="+user);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
