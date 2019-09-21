package com.example.the_youthempire;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Task extends Activity implements RewardedVideoAdListener {
     private static final String AD_UNIT_ID = "ca-app-pub-7628175294447075/4021644524";
    //private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917";  //test id
    private static final long COUNTER_TIME = 10;
    private static final int GAME_OVER_REWARD = 1;
    int time=10000;
    ProgressDialog progressDialog;
    private int coinCount;
    private TextView coinCountText;
    private CountDownTimer countDownTimer;
    private boolean gameOver;
    private boolean gamePaused;
    private RewardedVideoAd rewardedVideoAd;
    private long timeRemaining;
    int i=1;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16,btn17,btn18,btn19,btn20;
    String uploadUrl="http://youthempire.tech/youthempire/score_update.php";
    String time_url="http://youthempire.tech/youthempire/server_time.php";
    String login_phone="Null";
    String p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);


        //initialize progress dialog
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Processing...");
        progressDialog.setCancelable(true);


        SharedPreferences b=getApplicationContext().getSharedPreferences(login_phone, Context.MODE_PRIVATE);
        p=b.getString("loginPhone","No");

        Time_fetch tf=new Time_fetch();
        new Thread(tf).start();


        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);
        btn5=(Button)findViewById(R.id.btn5);
        btn6=(Button)findViewById(R.id.btn6);
        btn7=(Button)findViewById(R.id.btn7);
        btn8=(Button)findViewById(R.id.btn8);
        btn9=(Button)findViewById(R.id.btn9);
        btn10=(Button)findViewById(R.id.btn10);
        btn11=(Button)findViewById(R.id.btn11);
        btn12=(Button)findViewById(R.id.btn12);
        btn13=(Button)findViewById(R.id.btn13);
        btn14=(Button)findViewById(R.id.btn14);
        btn15=(Button)findViewById(R.id.btn15);
        btn16=(Button)findViewById(R.id.btn16);
        btn17=(Button)findViewById(R.id.btn17);
        btn18=(Button)findViewById(R.id.btn18);
        btn19=(Button)findViewById(R.id.btn19);
        btn20=(Button)findViewById(R.id.btn20);



        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();


        btn1.setVisibility(View.INVISIBLE);
        progressDialog.show();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=2;
                showRewardedVideo();
                btn1.setVisibility(View.INVISIBLE);

            }
        });
        //btn2.setVisibility(View.VISIBLE);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=3;
                showRewardedVideo();
                btn2.setVisibility(View.INVISIBLE);
            }
        });
        //btn3.setVisibility(View.VISIBLE);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=4;
                showRewardedVideo();
                btn3.setVisibility(View.INVISIBLE);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=5;
                showRewardedVideo();
                btn4.setVisibility(View.INVISIBLE);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=6;
                showRewardedVideo();
                btn5.setVisibility(View.INVISIBLE);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=7;
                showRewardedVideo();
                btn6.setVisibility(View.INVISIBLE);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=8;
                showRewardedVideo();
                btn7.setVisibility(View.INVISIBLE);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i=9;
                showRewardedVideo();
                btn8.setVisibility(View.INVISIBLE);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=10;
                showRewardedVideo();
                btn9.setVisibility(View.INVISIBLE);
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=11;
                showRewardedVideo();
                btn10.setVisibility(View.INVISIBLE);
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=12;
                showRewardedVideo();
                btn11.setVisibility(View.INVISIBLE);
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=13;
                showRewardedVideo();
                btn12.setVisibility(View.INVISIBLE);
            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=14;
                showRewardedVideo();
                btn13.setVisibility(View.INVISIBLE);
            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=15;
                showRewardedVideo();
                btn14.setVisibility(View.INVISIBLE);
            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=16;
                showRewardedVideo();
                btn15.setVisibility(View.INVISIBLE);
            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=17;
                showRewardedVideo();
                btn16.setVisibility(View.INVISIBLE);
            }
        });
        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=18;
                showRewardedVideo();
                btn17.setVisibility(View.INVISIBLE);
            }
        });
        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=19;
                showRewardedVideo();
                btn18.setVisibility(View.INVISIBLE);
            }
        });
        btn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=20;
                showRewardedVideo();
                btn19.setVisibility(View.INVISIBLE);
            }
        });
        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRewardedVideo();
                //btn20.setVisibility(View.VISIBLE);
                btn20.setVisibility(View.INVISIBLE);
                i=21;
            }
        });




        // Display current coin count to user.
        coinCountText = findViewById(R.id.coin_count_text);
        coinCount = 0;
        coinCountText.setText("Coins: " + coinCount);

        startGame();
    }

    @Override
    public void onPause() {
        super.onPause();
        pauseGame();
        rewardedVideoAd.pause(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!gameOver && gamePaused) {
            resumeGame();
        }
        rewardedVideoAd.resume(this);
    }

    private void pauseGame() {
        countDownTimer.cancel();
        gamePaused = true;
    }

    private void resumeGame() {
        createTimer(timeRemaining);
        gamePaused = false;
    }

    private void loadRewardedVideoAd() {
        if (!rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.loadAd(AD_UNIT_ID, new AdRequest.Builder().build());
        }
    }



    private void addCoins(int coins) {
        coinCount += coins/5;
        coinCountText.setText(""+coinCount/2);
    }

    private void startGame() {
        // Hide the retry button, load the ad, and start the timer.
        //retryButton.setVisibility(View.INVISIBLE);
       // btn1.setVisibility(View.INVISIBLE);
        loadRewardedVideoAd();
        createTimer(COUNTER_TIME);
        gamePaused = false;
        gameOver = false;
    }



    // Create the game timer, which counts down to the end of the level
    // and shows the "retry" button.
    private void createTimer(long time) {
        final TextView textView = findViewById(R.id.timer);
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(time * 1000, 50) {
            @Override
            public void onTick(long millisUnitFinished) {
                timeRemaining = ((millisUnitFinished / 1000) + 1);
                textView.setText("seconds remaining: " + timeRemaining);
            }

            @Override
            public void onFinish() {
                if (rewardedVideoAd.isLoaded()) {
                    btn1.setVisibility(View.VISIBLE);
                }
                textView.setText("You Win");
                addCoins(GAME_OVER_REWARD);
               // retryButton.setVisibility(View.VISIBLE);
                gameOver = true;
            }
        };
        countDownTimer.start();
    }

    private void showRewardedVideo() {
        btn1.setVisibility(View.INVISIBLE);
        if (rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.show();
        }
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, "Your Video Left Application", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Toast.makeText(this, "Your Video Closed", Toast.LENGTH_SHORT).show();
        // Preload the next video ad.
        progressDialog.show();

        loadRewardedVideoAd();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Toast.makeText(this, "Your Video Failed To Load", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {

        if (i==1){
            btn1.setVisibility(View.VISIBLE);
            progressDialog.dismiss();
        }else if (i==2){
            btn2.setVisibility(View.VISIBLE);
            progressDialog.dismiss();
//            scoreUpload su=new scoreUpload();
//            new Thread(su).start();
        }else if (i==3){
            btn3.setVisibility(View.VISIBLE);
            progressDialog.dismiss();
        }
        else if (i==4){
            btn4.setVisibility(View.VISIBLE);
        }
        else if (i==5){
            btn5.setVisibility(View.VISIBLE);
        }else if (i==6){
            btn6.setVisibility(View.VISIBLE);
        }
        else if (i==7){
            btn7.setVisibility(View.VISIBLE);
        }
        else if (i==8){
            btn8.setVisibility(View.VISIBLE);
        }
        else if (i==9){
            btn9.setVisibility(View.VISIBLE);
        }
        else if (i==10){
            btn10.setVisibility(View.VISIBLE);
        }
        else if (i==11){
            btn11.setVisibility(View.VISIBLE);
        }
        else if (i==12){
            btn12.setVisibility(View.VISIBLE);
        }
        else if (i==13){
            btn13.setVisibility(View.VISIBLE);
        }
        else if (i==14){
            btn14.setVisibility(View.VISIBLE);
        }
        else if (i==15){
            btn15.setVisibility(View.VISIBLE);
        }
        else if (i==16){
            btn16.setVisibility(View.VISIBLE);
        }
        else if (i==17){
            btn17.setVisibility(View.VISIBLE);
        }
        else if (i==18){
            btn18.setVisibility(View.VISIBLE);
        }
        else if (i==19){
            btn19.setVisibility(View.VISIBLE);
        }
        else if (i==20){
            btn20.setVisibility(View.VISIBLE);
        }else if (i==21){
            btn20.setVisibility(View.INVISIBLE);
            // database .......................................
            scoreUpload su=new scoreUpload();
            new Thread(su).start();
        }
        progressDialog.dismiss();
        Toast.makeText(this, "Your Video Loaded", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRewardedVideoAdOpened() {
       // Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewarded(RewardItem reward) {
       // Toast.makeText(this,show()show()
               // String.format(" Your currency: %s amount: %d", reward.getType(),
                    //    reward.getAmount()),
              //  Toast.LENGTH_SHORT).show();
        addCoins(reward.getAmount());
    }

    @Override
    public void onRewardedVideoStarted() {
        //Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {


    }

    class scoreUpload implements Runnable{
        @Override
        public void run() {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, uploadUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(final String response) {
                    Toast.makeText(getApplicationContext(),"Your Score is updated",Toast.LENGTH_LONG).show();
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
                    params.put("ph_no",p);
                    params.put("score",coinCountText.getText().toString());//name should be same as database
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
    class Time_fetch implements Runnable{
        @Override
        public void run() {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, time_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String t=response.substring(0,11);
                    //String u=response.substring(12,20);
                    Toast.makeText(Task.this, t, Toast.LENGTH_SHORT).show();
                   // Toast.makeText(Task.this, u, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Some error occurred!",Toast.LENGTH_SHORT).show();

                }
            });
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);


        }
    }
}