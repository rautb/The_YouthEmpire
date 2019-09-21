package com.example.the_youthempire;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

public class YouthEmpire extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    AdView adView,mAdView;
    private InterstitialAd interstitial;
    TextView rfid1;
    String login_status="No";
    int time=40000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youth_empire);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        rfid1=(TextView)findViewById(R.id.rfid1);







        //for admob   test ads:adUnitId="ca-app-pub-3940256099942544/6300978111"
        adView = findViewById(R.id.adv);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        MobileAds.initialize(this,
                "ca-app-pub-7628175294447075~5547668559");
        MobileAds.initialize(this, getString(R.string.admob_app_id));
        //mAdView = findViewById(R.id.adView);
        AdRequest adIRequest = new AdRequest.Builder().build();
        //mAdView.loadAd(adIRequest);
        // Prepare the Interstitial Ad Activity
        interstitial = new InterstitialAd(YouthEmpire.this);

        // Insert the Ad Unit ID
        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));

        // Interstitial Ad load Request
        interstitial.loadAd(adIRequest);

        // Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener()
        {
            public void onAdLoaded()
            {
                // Call displayInterstitial() function when the Ad loads
                displayInterstitial();
            }
        });
    }

    public void displayInterstitial()
    {
        // If Interstitial Ads are loaded then show else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.youth_empire_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        }  else if (id == R.id.nav_task) {

            Intent i=new Intent(getApplicationContext(),Task.class);
            startActivity(i);


        } else if (id == R.id.nav_Extraearn) {

            Intent i=new Intent(getApplicationContext(),Extra_earn.class);
            startActivity(i);

        } else if (id == R.id.nav_profile) {

            Intent i=new Intent(getApplicationContext(),Profile.class);
            startActivity(i);

        } else if (id == R.id.nav_wallet) {
            Intent i=new Intent(getApplicationContext(),wallet.class);
            startActivity(i);

        }else if (id == R.id.nav_product) {

            Intent i=new Intent(getApplicationContext(),Product.class);
            startActivity(i);

        }else if (id == R.id.nav_order) {

            Intent i=new Intent(getApplicationContext(),Order.class);
            startActivity(i);

        }else if (id == R.id.nav_team) {

            Intent i=new Intent(getApplicationContext(),Team.class);
            startActivity(i);

        }else if (id == R.id.nav_logout) {
            SharedPreferences sp=getSharedPreferences(login_status, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("loginStatus","No");
            editor.apply();
            Intent i=new Intent(getApplicationContext(),Login.class);
            startActivity(i);


        }else if (id == R.id.nav_rate) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + this.getPackageName())));
        }/*else if (id == R.id.nav_share) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String message = "Youth Empire:A Best Application Download our App from this link\n\"https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName()+" Use "+ rfid1.getText().toString()+" reffer ID";
            i.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(Intent.createChooser(i, "choose one"));
        }*/else if (id == R.id.nav_aboutUs) {

            AlertDialog.Builder alert=new AlertDialog.Builder(YouthEmpire.this);
            alert.setTitle("Youth Empire");
            alert.setIcon(R.drawable.logo);
            alert.setMessage("Youth Empire"+"\n"+"version: 1.0"+"\n\n"+"Digital India কে সফল  করতে এক অভিনব যুগের সূচনা করতে চলেছে YOUTH EMPIRE আপনাদের সার্বিক এবং  স্বতঃস্ফূর্ত সহযোগিতা  প্রতিষ্ঠানের একমাত্র মূলধন। প্রতিষ্ঠানও আপনাদেরকে সর্বদা স্বতঃস্ফূর্ত ও উন্নত পরিষেবা এবং সার্বিক সহযোগিতা দেওয়ার জন্য বদ্ধপরিকর। প্রতিষ্ঠান দ্বারা প্রদত্ত পণ্য এবং ডিসকাউন্ট  ভ্যালু সঠিক সময় এবং সঠিকভাবে দেওয়ার জন্য প্রতিষ্ঠান 100% সচেষ্ট থাকবে। আপনাদের এবং আমাদের একে অপরের প্রতি বিশ্বাস ও ভালোবাসার প্রতি নির্ভর করে আমরা সচেষ্ট থাকব আপনাদের ভবিষ্যৎ উজ্জ্বল থেকে উজ্জ্বলতর করার। এই প্রতিষ্ঠানের সাথে আপনার এই সার্বিক মেলবন্ধন দেখাতে পারে আপনার এবং  আপনাদের পরিবারকে এক নতুন পথের দিশা।\n" +
                    "NB :    বিশেষ বিশেষ উৎসবে প্রতিষ্ঠান তরফ থেকে আপনার এবং আপনাদের জন্য থাকবে বিশেষ চমকপ্রদক উপহার"+"\n\n"+"Contact us: youthempire@gmail.com");
            alert.setPositiveButton("OK",null);
            // Showing Alert Message
            alert.show();
            return true;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
