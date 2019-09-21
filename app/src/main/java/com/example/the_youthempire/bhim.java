package com.example.the_youthempire;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class bhim extends Fragment {

    EditText upi;
    Button bhim;
    String bhimurl="http://youthempire.tech/youthempire/bhim.php";
    String login_phone="Null";
    String p,wp,wa;
    String points1="No";
    String amount1="Null";
    ProgressDialog progressDialog;


    public bhim() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bhim, container, false);

        SharedPreferences b=getContext().getSharedPreferences(login_phone, Context.MODE_PRIVATE);
        p=b.getString("loginPhone","No");

        SharedPreferences c=getContext().getSharedPreferences(points1, Context.MODE_PRIVATE);
        wp=c.getString("points1","No");

        SharedPreferences d=getContext().getSharedPreferences(amount1, Context.MODE_PRIVATE);
        wa=d.getString("ammount1","No");

        //Toast.makeText(getContext(), wa+wp+"", Toast.LENGTH_SHORT).show();

        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Processing...");
        progressDialog.setCancelable(true);

        bhim=(Button)view.findViewById(R.id.bhim);
        upi=(EditText)view.findViewById(R.id.upi);
        bhim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (upi.getText().toString().trim().equals("")){
                    upi.setError("Please Enter Your UPI");
                    upi.requestFocus();
                }else {
                    progressDialog.show();
                    uploadBhim ub = new uploadBhim();
                    new Thread(ub).start();
                }
            }
        });

        return view;

    }
    class uploadBhim implements Runnable{
        @Override
        public void run() {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, bhimurl, new Response.Listener<String>() {
                @Override
                public void onResponse(final String response) {
                    Toast.makeText(getContext(),"Your Bhim Upi Update Complete",Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(),"Some error occurred!",Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String, String>();
                    params.put("ph_no",p);
                    params.put("upbank",upi.getText().toString());//name should be same as database
                    params.put("point_type",wp);
                    params.put("amount",wa);
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);
        }
    }

}
