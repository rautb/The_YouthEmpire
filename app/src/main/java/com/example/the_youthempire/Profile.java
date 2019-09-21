package com.example.the_youthempire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profile extends AppCompatActivity {

    TextView active,namep,addrp,emailp,php,tp,rp,ep,rfid;
    String url="http://youthempire.tech/youthempire/profile.php";
   // String img_uo_url="http://youthempire.tech/youthempire/level_fetch.php ";
    List<List_Data_sl> list_data_sl;
    String login_phone="Null";
    String p;
    Button invite;
    String login_status="No";
    LinearLayout logout,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        active=(TextView)findViewById(R.id.active);
        namep=(TextView)findViewById(R.id.namep);
        addrp=(TextView)findViewById(R.id.addrp);
        emailp=(TextView)findViewById(R.id.emailp);
        php=(TextView)findViewById(R.id.php);
        tp=(TextView)findViewById(R.id.tp);
        rp=(TextView)findViewById(R.id.rp);
        ep=(TextView)findViewById(R.id.ep);
        invite=(Button)findViewById(R.id.invite);
        logout=(LinearLayout)findViewById(R.id.logout);
        exit=(LinearLayout)findViewById(R.id.exit);
        rfid=(TextView)findViewById(R.id.rfid);


        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String message = "Youth Empire:A Best Application Download our App from this link\n\"https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName()+" Use "+ rfid.getText().toString()+" reffer ID";

                i.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(i, "choose one"));
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sp = getSharedPreferences(login_status, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("loginStatus", "No");
                editor.apply();
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });

        SharedPreferences b=getApplicationContext().getSharedPreferences(login_phone, Context.MODE_PRIVATE);
        p=b.getString("loginPhone","No");
        //Toast.makeText(this, p+"", Toast.LENGTH_SHORT).show();

        list_data_sl=new ArrayList<>();

        loguse l=new loguse();
        new Thread(l).start();
    }

    class loguse implements Runnable{

        @Override
        public void run() {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(final String response) {
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        JSONArray array=jsonObject.getJSONArray("data");
                        for (int i=0; i<array.length(); i++ ){
                            JSONObject ob=array.getJSONObject(i);
                            final List_Data_sl listData=new List_Data_sl(ob.getString("name"),ob.getString("email"),ob.getString("address"),ob.getString("ph_no"),ob.getString("active"),
                                    ob.getString("task_point"),ob.getString("reffer_point"),
                                    ob.getString("extra_point"),ob.getString("r_id"));
                            list_data_sl.add(listData);
                            namep.setText(listData.getNamep());
                            emailp.setText(listData.getEmailp());
                            php.setText(listData.getPhp());
                            addrp.setText(listData.getAddrp());
                            active.setText(listData.getActive());
                            tp.setText(listData.getTask_point());
                            rp.setText(listData.getReffer_point());
                            ep.setText(listData.getExtra_point());
                            rfid.setText(listData.getR_id());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
                    params.put("php",p);  //name should be same as database
                    return params;

                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

    }


}
