package com.example.the_youthempire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    String login_status="No";
    int time_out=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        SharedPreferences sharedPreferences=getSharedPreferences(login_status, Context.MODE_PRIVATE);
        String defaultValue="No";
        String a=sharedPreferences.getString("loginStatus",defaultValue);

        if (a.equals("No")) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                    finish();

                }
            }, time_out);
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), YouthEmpire.class);
                    startActivity(i);
                    finish();

                }
            }, time_out);
        }
    }
}
