package fr.unice.polytech.jcancela.tobeortohave.fragment.gmaps;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.jcancela.tobeortohave.database.JSONParser;
import fr.unice.polytech.jcancela.tobeortohave.model.Store;

/**
 * Created by Joel CANCELA VAZ on 15/05/2017.
 */

public class StoresTask extends AsyncTask<Void, Void, List<Store>> {


    @Override
    protected List<Store> doInBackground(Void... params) {
        List<Store> stores = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonStores = jsonParser.getJSONFromUrl("http://www.joelcancela.fr/share/ToBeOrToHave/stores.json");
        JSONArray storesjson = new JSONArray();
        try {
            storesjson = jsonStores.getJSONArray("stores");
            for (int i = 0; i < storesjson.length(); i++) {
                JSONObject currentStore = storesjson.getJSONObject(i);
                String name = currentStore.getString("name");
                JSONObject postion = currentStore.getJSONObject("position");
                double latitude = postion.getDouble("latitude");
                double longitude = postion.getDouble("latitude");
                stores.add(new Store(name,latitude,longitude));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stores;
    }
}
