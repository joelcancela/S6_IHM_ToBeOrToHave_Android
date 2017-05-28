package fr.unice.polytech.jcancela.tobeortohave.database;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.jcancela.tobeortohave.model.Reward;

/**
 * Created by Joel CANCELA VAZ on 28/05/2017.
 */

public class RewardsTask  extends AsyncTask<Void, Void, List<Reward>> {
    @Override
    protected List<Reward> doInBackground(Void... params) {
        List<Reward> rewards = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = jsonParser.getJSONFromUrl("http://www.joelcancela.fr/share/ToBeOrToHave/rewards.json");
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("rewards");
            for (int i = 0; i < jsonArray.length() ; i++) {
                JSONObject currentReward = jsonArray.getJSONObject(i);
                String name = currentReward.getString("name");
                String description = currentReward.getString("description");
                int points = Integer.valueOf(currentReward.getString("pointsToReach"));
                rewards.add(new Reward(points,name,description));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rewards;
    }
}