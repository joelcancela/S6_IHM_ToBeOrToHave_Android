package fr.unice.polytech.jcancela.tobeortohave.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import fr.unice.polytech.jcancela.tobeortohave.R;

/**
 * Created by Joel CANCELA VAZ on 26/05/2017.
 */

public class DisplayQRCodeFragment extends android.support.v4.app.Fragment{

    String imageURL;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.wtf("Sky",imageURL);
        View view = inflater.inflate(R.layout.fragment_display_qrcode, container, false);
        ImageView qrCodeImageView = (ImageView) view.findViewById(R.id.qrcode);
        URL newurl = null;
        try {
            newurl = new URL(imageURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bitmap qrcodebmp = null;
        try {
            qrcodebmp = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        qrCodeImageView.setImageBitmap(qrcodebmp);
        return view;
    }

    public DisplayQRCodeFragment(String url) {
        super();
        imageURL=url;
    }
}
