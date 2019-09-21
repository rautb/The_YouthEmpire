package com.example.the_youthempire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Torch extends AppCompatActivity {

    Button buy1;
    TextView rl;
    String login_phone="Null";
    String p;
    String uploadPayment="http://youthempire.tech/youthempire/buy.php";
    //String uploadPayment="http://youthempire.tech/youthempire/paytm/pgRedirect.php";
    final String CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";
    final String websiteURL = "http://viralandroid.com/";
    final String googleURL = "http://google.com/";

    CustomTabsClient mCustomTabsClient;
    CustomTabsSession mCustomTabsSession;
    CustomTabsServiceConnection mCustomTabsServiceConnection;
    CustomTabsIntent mCustomTabsIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torch);

        SharedPreferences b=getApplicationContext().getSharedPreferences(login_phone, Context.MODE_PRIVATE);
        p=b.getString("loginPhone","No");
        rl=(TextView)findViewById(R.id.rl);

        buy1=(Button)findViewById(R.id.buy1);
        buy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i=new Intent(getApplicationContext(),Razorpay.class);
//               startActivity(i);

//                PaymentUp pu=new PaymentUp();
//                new Thread(pu).start();

//                Intent j=new Intent(getApplicationContext(),Payment.class);
//                startActivity(j);
//                openInCustomTab("http://youthempire.tech/youthempire/buy.php");
                String g="http://youthempire.tech/youthempire/buy.php";
                Uri w= Uri.parse(g);
                Intent d=new Intent(Intent.ACTION_VIEW,w);
                if(d.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(d);
                }

            }
        });
    }
    class PaymentUp implements Runnable{
        @Override
        public void run() {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, uploadPayment, new Response.Listener<String>() {
                @Override
                public void onResponse(final String response) {
                    Toast.makeText(getApplicationContext(),response+"Your Payment Succesfull",Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getApplicationContext(),"Some error occurred!",Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String, String>();
                    params.put("ph_no",p);
                    params.put("product",rl.getText().toString());//name should be same as database
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }

//    private void openInCustomTab(String url){
//        Uri websiteUri;
//        if (!url.contains("https://")&&!url.contains("http://")){
//            websiteUri=Uri.parse("http://"+url);
//        }else {
//            websiteUri=Uri.parse(url);
//        }
//        CustomTabsIntent.Builder customtabintent=new CustomTabsIntent.Builder();
//        customtabintent.setToolbarColor(Color.parseColor("#3f51b5"));
//        customtabintent.setShowTitle(true);
//        if (chromeInstalled()){
//            customtabintent.build().intent.setPackage("com.android.chrome");
//        }
//        customtabintent.build().launchUrl(getApplicationContext(),websiteUri);
//        //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//    }
//
//
//    private boolean chromeInstalled(){
//        try{
//            getPackageManager().getPackageInfo("com.android.chrome",0);
//            return true;
//        }catch (Exception e){
//            return false;
//        }
//    }
}
