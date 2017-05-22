package fr.unice.polytech.jcancela.tobeortohave;

import android.*;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.connection_button).setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        requestPermissions( new String[]{
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION},
                1337);
    }

    public void onRegisterLinkClick(View view) {
        //TODO RegisterActivity
//        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
//        startActivity(intent);
    }
}
