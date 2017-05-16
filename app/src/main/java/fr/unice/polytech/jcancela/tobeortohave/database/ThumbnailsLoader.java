package fr.unice.polytech.jcancela.tobeortohave.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Joel CANCELA VAZ on 29/03/2017.
 */

public class ThumbnailsLoader extends AsyncTask<String, Void, Bitmap> {

    private ImageView currentImageView;

    public ThumbnailsLoader(ImageView imageView) {
        currentImageView=imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bmp = null;
        try {
            String url = "http://www.joelcancela.fr/share/ToBeOrToHave/products/"+params[0];
            InputStream inptStr = (InputStream) new URL(url).getContent();
            bmp = BitmapFactory.decodeStream(inptStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap!=null){
            currentImageView.setImageBitmap(bitmap);
        }
    }
}
