package com.example.the_youthempire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Powerbank extends AppCompatActivity {

    Button buy5;
    TextView pb;
    String login_phone="Null";
    String p;
    String uploadPayment="http://youthempire.tech/youthempire/buy.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_powerbank);

        SharedPreferences b=getApplicationContext().getSharedPreferences(login_phone, Context.MODE_PRIVATE);
        p=b.getString("loginPhone","No");
       pb=(TextView)findViewById(R.id.pb);
        buy5=(Button)findViewById(R.id.buy5);
        buy5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent j=new Intent(getApplicationContext(),Razorpay.class);
//                 startActivity(j);


//                PaymentUp pu=new PaymentUp();
//                new Thread(pu).start();
//
//                Intent j=new Intent(getApplicationContext(),Payment.class);
//                startActivity(j);

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
                    params.put("product",pb.getText().toString());//name should be same as database
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
}
