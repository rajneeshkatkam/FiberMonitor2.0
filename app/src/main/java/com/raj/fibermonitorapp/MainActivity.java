package com.raj.fibermonitorapp;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final Handler mHandler = new Handler();
    private Runnable mTimer1;
    CustomView mumbaiDelhi,delhiKolkata,kolkataSecunderabad,secunderabadChennai,chennaiMumbai;
    Boolean flag=true;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.alarm);


        ///Intialization
        mumbaiDelhi=findViewById(R.id.mumbaiDelhi);
        delhiKolkata=findViewById(R.id.delhiKolkata);
        kolkataSecunderabad=findViewById(R.id.kolkataSecunderabad);
        secunderabadChennai=findViewById(R.id.secunderabadChennai);
        chennaiMumbai=findViewById(R.id.chennaiMumbai);

        ///Line Attributes
        mumbaiDelhi.setLineColor(Color.GREEN);
        delhiKolkata.setLineColor(Color.GREEN);
        kolkataSecunderabad.setLineColor(Color.GREEN);
        secunderabadChennai.setLineColor(Color.GREEN);
        chennaiMumbai.setLineColor(Color.GREEN);



        /////OnClickListeners for Each Line

        mumbaiDelhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LineActivityValues =new Intent(getApplicationContext(),LineActivity.class);
                LineActivityValues.putExtra("lineName","MUMBAI-DELHI");
                startActivity(LineActivityValues);

                if(mumbaiDelhi.getLineColor()==Color.RED) {
                    mumbaiDelhi.setLineColor(Color.GREEN);
                    mp.release();
                    mp=MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                }
                else
                {
                    mumbaiDelhi.setLineColor(Color.RED);
                    mp.start();
                }
            }
        });

        delhiKolkata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LineActivityValues =new Intent(getApplicationContext(),LineActivity.class);
                LineActivityValues.putExtra("lineName","DELHI-KOLKATA");
                startActivity(LineActivityValues);

                if(delhiKolkata.getLineColor()==Color.RED){
                    delhiKolkata.setLineColor(Color.GREEN);
                    mp.release();
                    mp=MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                }
                else {
                    delhiKolkata.setLineColor(Color.RED);
                    mp.start();

                }
            }
        });

        kolkataSecunderabad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LineActivityValues =new Intent(getApplicationContext(),LineActivity.class);
                LineActivityValues.putExtra("lineName","KOLKATA-SECUNDERABAD");
                startActivity(LineActivityValues);

                if(kolkataSecunderabad.getLineColor()==Color.RED) {
                    kolkataSecunderabad.setLineColor(Color.GREEN);
                    mp.release();
                    mp=MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                }
                else
                {
                    kolkataSecunderabad.setLineColor(Color.RED);
                    mp.start();
                }
            }
        });

        secunderabadChennai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LineActivityValues =new Intent(getApplicationContext(),LineActivity.class);
                LineActivityValues.putExtra("lineName","SECUNDERABAD-CHENNAI");
                startActivity(LineActivityValues);

                if(secunderabadChennai.getLineColor()==Color.RED)
                {
                    secunderabadChennai.setLineColor(Color.GREEN);
                    mp.release();
                    mp=MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                }
                else
                {
                    secunderabadChennai.setLineColor(Color.RED);
                    mp.start();
                }
            }
        });

        chennaiMumbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LineActivityValues =new Intent(getApplicationContext(),LineActivity.class);
                LineActivityValues.putExtra("lineName","CHENNAI-MUMBAI");
                startActivity(LineActivityValues);

                if(chennaiMumbai.getLineColor()==Color.RED)
                {
                    chennaiMumbai.setLineColor(Color.GREEN);
                    mp.release();
                    mp=MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                }
                else
                {
                    chennaiMumbai.setLineColor(Color.RED);
                    mp.start();
                }
            }
        });


        mTimer1 = new Runnable() {
            @Override
            public void run() {

                if(flag) {
                    mumbaiDelhi.animate().alpha(0).setDuration(1000);
                    delhiKolkata.animate().alpha(0).setDuration(1000);
                    kolkataSecunderabad.animate().alpha(0).setDuration(1000);
                    secunderabadChennai.animate().alpha(0).setDuration(1000);
                    chennaiMumbai.animate().alpha(0).setDuration(1000);
                    flag = false;
                    // Log.i("Line color",String.valueOf(hyderabadChennai.getLineColor()));
                }
                else
                {
                    mumbaiDelhi.animate().alpha(1).setDuration(100);
                    delhiKolkata.animate().alpha(1).setDuration(100);
                    kolkataSecunderabad.animate().alpha(1).setDuration(100);
                    secunderabadChennai.animate().alpha(1).setDuration(100);
                    chennaiMumbai.animate().alpha(1).setDuration(100);
                    flag=true;
                }

                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.postDelayed(mTimer1, 1000);


    }




}
