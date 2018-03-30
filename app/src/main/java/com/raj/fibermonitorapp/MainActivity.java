package com.raj.fibermonitorapp;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.TimedMetaData;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerViewAdapter recyclerViewAdapter;


    private final Handler mHandler = new Handler();
    private Runnable mTimer1;
    CustomView mumbaiDelhi,delhiKolkata,kolkataSecunderabad,secunderabadChennai,chennaiMumbai;
    Boolean flag=true;
    MediaPlayer mp;

    public CollectionReference mColRefMD,mColRefDK,mColRefKS,mColRefSC,mColRefCM;
    RecyclerView recyclerViewMD,recyclerViewDK,recyclerViewKS,recyclerViewSC,recyclerViewCM;
    //public DocumentReference mDocRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.alarm);

        recyclerViewMD=findViewById(R.id.recyclerViewMD);
        recyclerViewMD.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerViewDK=findViewById(R.id.recyclerViewDK);
        recyclerViewDK.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerViewKS=findViewById(R.id.recyclerViewKS);
        recyclerViewKS.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerViewSC=findViewById(R.id.recyclerViewSC);
        recyclerViewSC.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerViewCM=findViewById(R.id.recyclerViewCM);
        recyclerViewCM.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


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


        ////Notification Status Checking Listners

        //mColRefMD=FirebaseFirestore.getInstance().collection("NOTIFICATION").document("WARNING").collection("LINE-MD");
        mColRefMD=FirebaseFirestore.getInstance().collection("NOTIFICATION").document("TIER-1").collection("MUMBAI-DELHI");
        mColRefMD.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {
                    Log.w("messageFail", "Listen failed.", e);
                    return;
                }

                //List<Boolean> messagesListStatus = new ArrayList<>();
                List<Notifications> notificationsList=new ArrayList<>();
                List<Notifications> cardNotification=new ArrayList<>();

                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    if (doc.get("status") != null) {
                        Notifications notifications=new Notifications();
                        notifications.predictedFailureTime=doc.getString("predictedFailureTime");
                        notifications.msg=doc.getString("msg");
                        notifications.linkName=doc.getString("linkName");
                        notifications.issueTime=doc.getString("issueTime");
                        notifications.repairTime=doc.getString("repairTime");
                        notifications.status=doc.getBoolean("status");
                        notifications.distance=doc.getDouble("distance");
                        notifications.adjustmentValue=doc.getDouble("adjustmentValue");
                        notifications.type=doc.getString("type");
                        notifications.messageNumber=doc.getDouble("messageNumber");
                        notificationsList.add(notifications);
                        //messagesListStatus.add(doc.getBoolean("status"));
                    }
                }
                Boolean play=false;
                Boolean playSkip=false;
                for(int i=0;i<notificationsList.size();i++)
                    if (!notificationsList.get(i).status)
                    {
                        cardNotification.add(notificationsList.get(i));
                        Log.d("StatusList", String.valueOf(notificationsList.get(i).status +" "+String.valueOf(i)));
                        mumbaiDelhi.setLineColor(Color.RED);
                        if(!playSkip) {
                            mp.release();
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                            mp.start();
                            playSkip=true;
                        }
                        play=true;
                    }
                    else
                    {
                        if(!play) {
                            mp.release();
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                            mumbaiDelhi.setLineColor(Color.GREEN);
                        }

                    }

                recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), cardNotification);
                recyclerViewMD.setAdapter(recyclerViewAdapter);
                Log.d("StatusListSS", String.valueOf(cardNotification.size()));
            }

        });




        mColRefDK=FirebaseFirestore.getInstance().collection("NOTIFICATION").document("TIER-1").collection("DELHI-KOLKATA");
        mColRefDK.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("messageFail", "Listen failed.", e);
                    return;
                }

                //List<Boolean> messagesListStatus = new ArrayList<>();
                List<Notifications> notificationsList=new ArrayList<>();
                List<Notifications> cardNotification=new ArrayList<>();

                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    if (doc.get("status") != null) {
                        Notifications notifications=new Notifications();
                        notifications.predictedFailureTime=doc.getString("predictedFailureTime");
                        notifications.msg=doc.getString("msg");
                        notifications.linkName=doc.getString("linkName");
                        notifications.issueTime=doc.getString("issueTime");
                        notifications.repairTime=doc.getString("repairTime");
                        notifications.status=doc.getBoolean("status");
                        notifications.distance=doc.getDouble("distance");
                        notifications.adjustmentValue=doc.getDouble("adjustmentValue");
                        notifications.type=doc.getString("type");
                        notifications.messageNumber=doc.getDouble("messageNumber");
                        notificationsList.add(notifications);
                        //messagesListStatus.add(doc.getBoolean("status"));
                    }
                }
                Boolean play=false;
                Boolean playSkip=false;
                for(int i=0;i<notificationsList.size();i++)
                    if (!notificationsList.get(i).status)
                    {
                        cardNotification.add(notificationsList.get(i));
                        Log.d("StatusList", String.valueOf(notificationsList.get(i).status +" "+String.valueOf(i)));
                        delhiKolkata.setLineColor(Color.RED);
                        if(!playSkip) {
                            mp.release();
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                            mp.start();
                            playSkip=true;
                        }
                        play=true;
                    }
                    else
                    {
                        if(!play) {
                            mp.release();
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                            delhiKolkata.setLineColor(Color.GREEN);
                        }

                    }

                recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), cardNotification);
                recyclerViewDK.setAdapter(recyclerViewAdapter);
                Log.d("StatusListSS", String.valueOf(cardNotification.size()));
            }

        });


        mColRefKS=FirebaseFirestore.getInstance().collection("NOTIFICATION").document("TIER-1").collection("KOLKATA-SECUNDERABAD");
        mColRefKS.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {
                    Log.w("messageFail", "Listen failed.", e);
                    return;
                }

                //List<Boolean> messagesListStatus = new ArrayList<>();
                List<Notifications> notificationsList=new ArrayList<>();
                List<Notifications> cardNotification=new ArrayList<>();

                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    if (doc.get("status") != null) {
                        Notifications notifications=new Notifications();
                        notifications.predictedFailureTime=doc.getString("predictedFailureTime");
                        notifications.msg=doc.getString("msg");
                        notifications.linkName=doc.getString("linkName");
                        notifications.issueTime=doc.getString("issueTime");
                        notifications.repairTime=doc.getString("repairTime");
                        notifications.status=doc.getBoolean("status");
                        notifications.distance=doc.getDouble("distance");
                        notifications.adjustmentValue=doc.getDouble("adjustmentValue");
                        notifications.type=doc.getString("type");
                        notifications.messageNumber=doc.getDouble("messageNumber");
                        notificationsList.add(notifications);
                        //messagesListStatus.add(doc.getBoolean("status"));
                    }
                }
                Boolean play=false;
                Boolean playSkip=false;
                for(int i=0;i<notificationsList.size();i++)
                    if (!notificationsList.get(i).status)
                    {
                        cardNotification.add(notificationsList.get(i));
                        Log.d("StatusList", String.valueOf(notificationsList.get(i).status +" "+String.valueOf(i)));
                        kolkataSecunderabad.setLineColor(Color.RED);
                        if(!playSkip) {
                            mp.release();
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                            mp.start();
                            playSkip=true;
                        }
                        play=true;
                    }
                    else
                    {
                        if(!play) {
                            mp.release();
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                            kolkataSecunderabad.setLineColor(Color.GREEN);
                        }

                    }

                recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), cardNotification);
                recyclerViewKS.setAdapter(recyclerViewAdapter);
                Log.d("StatusListSS", String.valueOf(cardNotification.size()));
            }

        });


        mColRefSC=FirebaseFirestore.getInstance().collection("NOTIFICATION").document("TIER-1").collection("SECUNDERABAD-CHENNAI");
        mColRefSC.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {
                    Log.w("messageFail", "Listen failed.", e);
                    return;
                }

                //List<Boolean> messagesListStatus = new ArrayList<>();
                List<Notifications> notificationsList=new ArrayList<>();
                List<Notifications> cardNotification=new ArrayList<>();

                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    if (doc.get("status") != null) {
                        Notifications notifications=new Notifications();
                        notifications.predictedFailureTime=doc.getString("predictedFailureTime");
                        notifications.msg=doc.getString("msg");
                        notifications.linkName=doc.getString("linkName");
                        notifications.issueTime=doc.getString("issueTime");
                        notifications.repairTime=doc.getString("repairTime");
                        notifications.status=doc.getBoolean("status");
                        notifications.distance=doc.getDouble("distance");
                        notifications.adjustmentValue=doc.getDouble("adjustmentValue");
                        notifications.type=doc.getString("type");
                        notifications.messageNumber=doc.getDouble("messageNumber");
                        notificationsList.add(notifications);
                        //messagesListStatus.add(doc.getBoolean("status"));
                    }
                }
                Boolean play=false;
                Boolean playSkip=false;
                for(int i=0;i<notificationsList.size();i++)
                    if (!notificationsList.get(i).status)
                    {
                        cardNotification.add(notificationsList.get(i));
                        Log.d("StatusList", String.valueOf(notificationsList.get(i).status +" "+String.valueOf(i)));
                        secunderabadChennai.setLineColor(Color.RED);
                        if(!playSkip) {
                            mp.release();
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                            mp.start();
                            playSkip=true;
                        }
                        play=true;
                    }
                    else
                    {
                        if(!play) {
                            mp.release();
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                            secunderabadChennai.setLineColor(Color.GREEN);
                        }

                    }

                recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), cardNotification);
                recyclerViewSC.setAdapter(recyclerViewAdapter);
                Log.d("StatusListSS", String.valueOf(cardNotification.size()));
            }

        });

        mColRefCM=FirebaseFirestore.getInstance().collection("NOTIFICATION").document("TIER-1").collection("CHENNAI-MUMBAI");
        mColRefCM.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {
                    Log.w("messageFail", "Listen failed.", e);
                    return;
                }

                //List<Boolean> messagesListStatus = new ArrayList<>();
                List<Notifications> notificationsList=new ArrayList<>();
                List<Notifications> cardNotification=new ArrayList<>();

                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    if (doc.get("status") != null) {
                        Notifications notifications=new Notifications();
                        notifications.predictedFailureTime=doc.getString("predictedFailureTime");
                        notifications.msg=doc.getString("msg");
                        notifications.linkName=doc.getString("linkName");
                        notifications.issueTime=doc.getString("issueTime");
                        notifications.repairTime=doc.getString("repairTime");
                        notifications.status=doc.getBoolean("status");
                        notifications.distance=doc.getDouble("distance");
                        notifications.adjustmentValue=doc.getDouble("adjustmentValue");
                        notifications.type=doc.getString("type");
                        notifications.messageNumber=doc.getDouble("messageNumber");
                        notificationsList.add(notifications);
                        //messagesListStatus.add(doc.getBoolean("status"));
                    }
                }
                Boolean play=false;
                Boolean playSkip=false;
                for(int i=0;i<notificationsList.size();i++)
                    if (!notificationsList.get(i).status)
                    {
                        cardNotification.add(notificationsList.get(i));
                        Log.d("StatusList", String.valueOf(notificationsList.get(i).status +" "+String.valueOf(i)));
                        chennaiMumbai.setLineColor(Color.RED);
                        if(!playSkip) {
                            mp.release();
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                            mp.start();
                            playSkip=true;
                        }
                        play=true;
                    }
                    else
                    {
                        if(!play) {
                            mp.release();
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                            chennaiMumbai.setLineColor(Color.GREEN);
                        }

                    }

                recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), cardNotification);
                recyclerViewCM.setAdapter(recyclerViewAdapter);
                Log.d("StatusListSS", String.valueOf(cardNotification.size()));
            }

        });










        /////OnClick Custom View Listeners for Each Line

        mumbaiDelhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LineActivityValues =new Intent(getApplicationContext(),LineActivity.class);
                LineActivityValues.putExtra("lineName","MUMBAI-DELHI");
                startActivity(LineActivityValues);
            }
        });

        delhiKolkata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LineActivityValues =new Intent(getApplicationContext(),LineActivity.class);
                LineActivityValues.putExtra("lineName","DELHI-KOLKATA");
                startActivity(LineActivityValues);
            }
        });

        kolkataSecunderabad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LineActivityValues =new Intent(getApplicationContext(),LineActivity.class);
                LineActivityValues.putExtra("lineName","KOLKATA-SECUNDERABAD");
                startActivity(LineActivityValues);
            }
        });

        secunderabadChennai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LineActivityValues =new Intent(getApplicationContext(),LineActivity.class);
                LineActivityValues.putExtra("lineName","SECUNDERABAD-CHENNAI");
                startActivity(LineActivityValues);
            }
        });

        chennaiMumbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LineActivityValues =new Intent(getApplicationContext(),LineActivity.class);
                LineActivityValues.putExtra("lineName","CHENNAI-MUMBAI");
                startActivity(LineActivityValues);
            }
        });


        //// Thread for Blinking effect of the lines
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



    static void getNotificationObject(Notifications notifications)
    {


       // Log.i("NotifUpdate",)
        DocumentReference mDocRef;
        notifications.status=true;
        mDocRef=FirebaseFirestore.getInstance().collection("NOTIFICATION").document("TIER-1").collection(notifications.linkName).document("MESSAGE-"+notifications.messageNumber.intValue());
        mDocRef.set(notifications);

    }









}
