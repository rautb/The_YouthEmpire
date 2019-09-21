package com.example.the_youthempire;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login extends AppCompatActivity {

    TextView click_sign;
    EditText userid,pass;
    String fetchedPass,fetchedPhone;
    Button login,guest;
    ProgressDialog progressDialog;
    Boolean LOGIN=false;
    String logUrl="http://youthempire.tech/youthempire/login.php";
    List<List_Data> list_data;
    String login_status="No";
    String login_phone="Null";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //fetch login data
        list_data=new ArrayList<>();
        //initialize progress dialog
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(true);

        click_sign=(TextView)findViewById(R.id.click_sign);
        login=(Button)findViewById(R.id.login);
        guest=(Button)findViewById(R.id.guest);

        userid=(EditText)findViewById(R.id.userid);
        pass=(EditText)findViewById(R.id.pass);
        guest=(Button)findViewById(R.id.guest);
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),YouthEmpire.class);
                startActivity(i);
            }
        });

        click_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(), signup.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userid.getText().toString().trim().equals("")){
                    userid.setError("Please Enter Your UserId");
                    userid.requestFocus();
                }else if (pass.getText().toString().trim().equals("")){
                    pass.setError("Please Enter Your password");
                    pass.requestFocus();
                }else {
                    loguse l=new loguse();
                    new Thread(l).start();
                    progressDialog.show();
                }
            }
        });


    }


    class loguse implements Runnable{

        @Override
        public void run() {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, logUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(final String response) {
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        JSONArray array=jsonObject.getJSONArray("data");
                        for (int i=0; i<array.length(); i++ ){
                            JSONObject ob=array.getJSONObject(i);
                            final List_Data listData=new List_Data(ob.getString("ph_no"),ob.getString("password"));
                            list_data.add(listData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                                    fetchedPhone=listData.getphone();
                                    fetchedPass=listData.getpassword();
                                    LOGIN = fetchedPass.equals(convertPassMD5(pass.getText().toString()));
                                    if (LOGIN){
                                        Toast.makeText(getApplicationContext(),"Login successful!",Toast.LENGTH_LONG).show();

                                        SharedPreferences sp = getSharedPreferences(login_status, Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sp.edit();
                                        editor.putString("loginStatus", "Yes");
                                        editor.apply();

                                        SharedPreferences sp2 = getSharedPreferences(login_phone, Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor2 = sp2.edit();
                                        editor2.putString("loginPhone", userid.getText().toString().trim());
                                        editor2.apply();
                                        progressDialog.dismiss();
                                        Intent i=new Intent(Login.this,YouthEmpire.class);
                                       startActivity(i);
                                    } else if (!LOGIN){
                                         Toast.makeText(getApplicationContext(),"Wrong Phone no. or Password",Toast.LENGTH_LONG).show();
                                        progressDialog.hide();
                                    }
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.hide();
                    Toast.makeText(getApplicationContext(),"Some error occurred!",Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String, String>();
                    params.put("ph_no",userid.getText().toString());  //name should be same as database
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

    }
    public String convertPassMD5(String pass){
        String password=null;
        MessageDigest mdEnc;
        try{
            mdEnc=MessageDigest.getInstance("MD5");
            mdEnc.update(pass.getBytes(),0,pass.length());
            pass=new BigInteger(1,mdEnc.digest()).toString(16);
            while (pass.length()<32){
                pass="0" + pass;
            }
            password=pass;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password;
    }
}