package fr.unice.polytech.jcancela.tobeortohave.database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joel CANCELA VAZ on 08/05/2017.
 */

public class LoggingVerifier {

    private String emailToCheck;
    private String passwordToCheck;
    private String nickname;
    private int points;

    public LoggingVerifier(String mEmail, String mPassword) {
        emailToCheck=mEmail;
        passwordToCheck=mPassword;
    }


    public boolean tryLogging() {
        JSONParser jsonParser = new JSONParser();
        JSONObject usersJson = jsonParser.getJSONFromUrl("http://example.com/ToBeOrToHave/users.json");
        if(usersJson == null){
            return false;
        }
        else{
            try {
                JSONArray usersList = usersJson.getJSONArray("users");
                for (int i = 0; i < usersList.length() ; i++) {
                    JSONObject user_i = usersList.getJSONObject(i);
                    if(user_i.getString("email").equals(emailToCheck)){
                        if(user_i.getString("password").equals(passwordToCheck)){
                            nickname = user_i.getString("username");
                            points = user_i.getInt("points");
                            return true;
                        }
                    }
                }
            } catch (JSONException e) {
                return false;
            }
        }
        return false;
    }

    public String getAccountNickname() {
        return nickname;
    }

    public int getPoints(){
        return points;
    }
}
