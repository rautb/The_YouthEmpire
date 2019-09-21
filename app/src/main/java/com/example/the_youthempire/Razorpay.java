package com.example.the_youthempire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class Razorpay extends AppCompatActivity {
Button pay;
    String login_phone="Null";
    String p;
    String uploadPayment="http://youthempire.tech/youthempire/.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razorpay);

        SharedPreferences b=getApplicationContext().getSharedPreferences(login_phone, Context.MODE_PRIVATE);
        p=b.getString("loginPhone","No");


        pay=(Button)findViewById(R.id.btn_pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String appPackageName = "net.one97.paytm";
                PackageManager pm = getApplicationContext().getPackageManager();
                Intent appstart = pm.getLaunchIntentForPackage(appPackageName);
                if(null!=appstart)
                {
                    getApplicationContext().startActivity(appstart);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Install PayTm on your device", Toast.LENGTH_SHORT).show();
                }

                PaymentUp pu=new PaymentUp();
                new Thread(pu).start();
            }
        });
    }

    class PaymentUp implements Runnable{
        @Override
        public void run() {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, uploadPayment, new Response.Listener<String>() {
                @Override
                public void onResponse(final String response) {
                    Toast.makeText(getApplicationContext(),"Your Payment Succesfull",Toast.LENGTH_LONG).show();
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
                    //params.put("score",coinCountText.getText().toString());//name should be same as database
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
}
