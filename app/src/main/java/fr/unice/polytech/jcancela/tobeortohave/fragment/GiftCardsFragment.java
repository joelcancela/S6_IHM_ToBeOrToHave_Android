package fr.unice.polytech.jcancela.tobeortohave.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import fr.unice.polytech.jcancela.tobeortohave.R;

/**
 * Created by Joel CANCELA VAZ on 08/05/2017.
 */

public class GiftCardsFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gift_cards, container, false);
        Button createQRCode = (Button) view.findViewById(R.id.create_qrcode);
        createQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO New fragment
            }
        });
        Button flashQRCode = (Button) view.findViewById(R.id.decode_qrcode);
        flashQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qrDroid = new Intent("la.droid.qr.scan");
                qrDroid.putExtra("la.droid.qr.complete", true);
                try {
                    startActivityForResult(qrDroid, 0);
                } catch (Exception e) {
                    showDialogAlertForQRDroid();
                }
            }
        });
        return view;
    }

    private void showDialogAlertForQRDroid() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.qrdroid_not_found).setTitle(R.string.qrdroid_alert);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Uri marketUri = Uri.parse("market://details?id=la.droid.qr");
                Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                startActivity(marketIntent);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data==null){
            return;
        }
        String result = data.getExtras().getString("la.droid.qr.result");
        if (result == null) {
            result = "";
        }
        //Parse QRCode result
        String[] parse = result.split("_");
        if(parse.length !=3){
            Toast.makeText(getActivity(), "QRCode incorrect !",
                    Toast.LENGTH_LONG).show();
        }
        else{
            String username = parse[0];
            String points = parse[2];
            Toast.makeText(getActivity(), username+" vous a envoyé "+points+" pts!",
                    Toast.LENGTH_LONG).show();
            SharedPreferences settings = getActivity().getApplicationContext().getSharedPreferences("ToBeOrToHave", 0);
            int currentPoints = settings.getInt("user_points",0);
            SharedPreferences.Editor editor = settings.edit();
            int newPoints = currentPoints+ Integer.valueOf(points);
            editor.putInt("user_points", newPoints);
            editor.apply();
        }

    }
}
