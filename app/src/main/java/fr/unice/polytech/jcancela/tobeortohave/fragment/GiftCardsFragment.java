package fr.unice.polytech.jcancela.tobeortohave.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
                SharedPreferences settings = getActivity().getApplicationContext().getSharedPreferences("ToBeOrToHave", 0);
                final int currentPoints = settings.getInt("user_points", 0);
                String username = settings.getString("username", "Une mysterieuse personne");
                showDialogAlertForQRCode(currentPoints,username);
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

    private void showDialogAlertForQRCode(final int currentPoints, final String username) {


        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle(R.string.qrcode_create_title);
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setRawInputType(Configuration.KEYBOARD_12KEY);
        input.setHint(currentPoints + " max");
        alert.setView(input);
        alert.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String inputText = input.getText().toString();
                if (inputText.matches("")) {
                    Toast.makeText(getContext(), "S'il vous plaît entrez une valeur !", Toast.LENGTH_SHORT).show();
                    return;
                }
                int value = Integer.valueOf(input.getText().toString());
                if (value > currentPoints) {
                    Toast.makeText(getContext(), "Vous ne pouvez pas donner plus de points que vous en avez.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Intent qrDroid = new Intent("la.droid.qr.encode");
                    qrDroid.putExtra("la.droid.qr.code", username + "_2BO2H_" + value);
                    qrDroid.putExtra("la.droid.qr.image" , true);
                    try {
                        startActivityForResult(qrDroid, 1);
                    } catch (Exception e) {
                        showDialogAlertForQRDroid();
                    }
                }
            }
        });
        alert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Put actions for CANCEL button here, or leave in blank
            }
        });
        alert.show();
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
        if (data == null) {
            return;
        }
        if (requestCode == 0) {
            String result = data.getExtras().getString("la.droid.qr.result");
            if (result == null) {
                result = "";
            }
            //Parse QRCode result
            String[] parse = result.split("_");
            if (parse.length != 3) {
                Toast.makeText(getActivity(), "QRCode incorrect !",
                        Toast.LENGTH_LONG).show();
            } else {
                String username = parse[0];
                String points = parse[2];
                Toast.makeText(getActivity(), username + " vous a envoyé " + points + " pts!",
                        Toast.LENGTH_LONG).show();
                SharedPreferences settings = getActivity().getApplicationContext().getSharedPreferences("ToBeOrToHave", 0);
                int currentPoints = settings.getInt("user_points", 0);
                SharedPreferences.Editor editor = settings.edit();
                int newPoints = currentPoints + Integer.valueOf(points);
                editor.putInt("user_points", newPoints);
                editor.apply();
            }
        }
        if (requestCode == 1) {
            String url = data.getExtras().getString("la.droid.qr.result");
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(Uri.parse("file://" + url), "image/*");
            startActivity(intent);
        }

    }
}
