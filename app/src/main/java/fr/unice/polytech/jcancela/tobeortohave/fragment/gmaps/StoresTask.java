package fr.unice.polytech.jcancela.tobeortohave.fragment.gmaps;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.unice.polytech.jcancela.tobeortohave.database.JSONParser;

/**
 * Created by Joel CANCELA VAZ on 15/05/2017.
 */

public class StoresTask extends AsyncTask<Void, Void, JSONArray> {


    @Override
    protected JSONArray doInBackground(Void... params) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonStores = jsonParser.getJSONFromUrl("http://www.joelcancela.fr/share/ToBeOrToHave/stores.json");
        JSONArray stores = new JSONArray();
        try {
            stores = jsonStores.getJSONArray("stores");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stores;
    }
}
