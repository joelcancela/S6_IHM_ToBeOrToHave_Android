package fr.unice.polytech.jcancela.tobeortohave.database;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.jcancela.tobeortohave.model.Product;

/**
 * Created by Joel CANCELA VAZ on 16/05/2017.
 */

public class ProductsTask extends AsyncTask<Void, Void, List<Product>> {
    @Override
    protected List<Product> doInBackground(Void... params) {
        List<Product> products = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = jsonParser.getJSONFromUrl("http://www.joelcancela.fr/share/ToBeOrToHave/products.json");
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("products");
            for (int i = 0; i < jsonArray.length() ; i++) {
                JSONObject currentProduct = jsonArray.getJSONObject(i);
                String name = currentProduct.getString("name");
                float price = Float.parseFloat(currentProduct.getString("price"));
                String description = currentProduct.getString("description");
                String thumbnailName = currentProduct.getString("picture");
                products.add(new Product(name,price,description,thumbnailName));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return products;
    }
}
