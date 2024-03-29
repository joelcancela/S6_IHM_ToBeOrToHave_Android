package fr.unice.polytech.jcancela.tobeortohave.database;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.jcancela.tobeortohave.model.Store;

/**
 * Created by Joel CANCELA VAZ on 15/05/2017.
 */

public class StoresTask extends AsyncTask<Void, Void, List<Store>> {


    @Override
    protected List<Store> doInBackground(Void... params) {
        List<Store> stores = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonStores = jsonParser.getJSONFromUrl("http://example.com/ToBeOrToHave/stores.json");
        try {
            JSONArray storesjson = jsonStores.getJSONArray("stores");
            for (int i = 0; i < storesjson.length(); i++) {
                JSONObject currentStore = storesjson.getJSONObject(i);
                String name = currentStore.getString("name");
                JSONObject postion = currentStore.getJSONObject("position");
                double latitude = postion.getDouble("latitude");
                double longitude = postion.getDouble("longitude");
                stores.add(new Store(name,latitude,longitude));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stores;
    }
}
