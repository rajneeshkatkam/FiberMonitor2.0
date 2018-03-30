package com.raj.fibermonitorapp;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class LineActivity extends AppCompatActivity {

    static int insertionLossRealTimeCount,otdrinitialCount;
    private final Handler mHandler = new Handler();
    private Runnable mTimer2;
    public DataPoint dataPoint,dataPointOTDR;

    public LineGraphSeries<DataPoint> mSeriesOTDR=new LineGraphSeries<>( new DataPoint[]{new DataPoint(0,0)});
    public LineGraphSeries<DataPoint> mSeries2=new LineGraphSeries<>( new DataPoint[]{new DataPoint(0,0)});
    GraphView graph1,graph2,graph3;
    TextView graphTextViewMD;
    DocumentReference mDocRef;
    public String lineName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);

        graphTextViewMD=findViewById(R.id.graphtextViewMD);
        graph1 =  findViewById(R.id.graph1);
        graph2 = findViewById(R.id.graph2);
        graph3 = findViewById(R.id.graph3);

        graph1.setTitle("Insertion Loss (Real-Time)");
        graph1.setTitleColor(Color.WHITE);
        graph1.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
        graph1.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
        graph1.getGridLabelRenderer().setHorizontalAxisTitle("Time");
        graph1.getGridLabelRenderer().setVerticalAxisTitle("Loss");
        graph1.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.WHITE);
        graph1.getGridLabelRenderer().setVerticalAxisTitleColor(Color.WHITE);
        graph1.getGridLabelRenderer().setGridColor(Color.WHITE);
        graph1.getViewport().setXAxisBoundsManual(true);
        graph1.getViewport().setMinX(0);
        graph1.getViewport().setMaxX(40);


        graph2.setTitle("Insertion Loss (Predicted)");
        graph2.setTitleColor(Color.WHITE);
        graph2.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
        graph2.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
        graph2.getGridLabelRenderer().setHorizontalAxisTitle("Time");
        graph2.getGridLabelRenderer().setVerticalAxisTitle("Loss");
        graph2.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.WHITE);
        graph2.getGridLabelRenderer().setVerticalAxisTitleColor(Color.WHITE);
        graph2.getGridLabelRenderer().setGridColor(Color.WHITE);
        graph2.getViewport().setXAxisBoundsManual(true);
        graph2.getViewport().setMinX(0);
        graph2.getViewport().setMaxX(40);


        graph3.setTitle("OTDR (Real Time)");
        graph3.setTitleColor(Color.WHITE);
        graph3.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
        graph3.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
        graph3.getGridLabelRenderer().setHorizontalAxisTitle("Distance");
        graph3.getGridLabelRenderer().setVerticalAxisTitle("Loss");
        graph3.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.WHITE);
        graph3.getGridLabelRenderer().setVerticalAxisTitleColor(Color.WHITE);
        graph3.getGridLabelRenderer().setGridColor(Color.WHITE);
        graph3.getViewport().setXAxisBoundsManual(true);
        graph3.getViewport().setMinX(0);
        graph3.getViewport().setMaxX(40);




        //fetchOTDRInitialData();
        fetchInsertionLossRealTimeData();




    }




    @Override
    public void onResume() {
        super.onResume();

        mTimer2 = new Runnable() {
            @Override
            public void run() {
                fetchInsertionLossRealTimeData();
                fetchOTDRInitialData();
                mHandler.postDelayed(this, 500);
            }
        };
        mHandler.postDelayed(mTimer2, 500);


    }


    /* @Override
     public void onPause() {
         mHandler.removeCallbacks(mTimer2);
         super.onPause();
     }
 */
    ///////Checking Internet Connectivity
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }






    ////Insertion Loss Real Time Data;
    public void fetchInsertionLossRealTimeData() {




        if(isNetworkAvailable()) {
            lineName=getIntent().getExtras().getString("lineName");
            graphTextViewMD.setText("Graphs (Connected)");
            Log.i("lineName",String.valueOf(lineName));
            mDocRef = FirebaseFirestore.getInstance().collection("LOSS INSERTION").document("TIER-1").collection(lineName).document("TIME "+String.valueOf(insertionLossRealTimeCount));

            mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        Log.i("Success", "Succesfully Fetch");

                        dataPoint =new DataPoint(documentSnapshot.getDouble("time"),Double.valueOf(documentSnapshot.getString("val")));
                        if(insertionLossRealTimeCount==0)
                            mSeries2.resetData(new DataPoint[] {dataPoint});
                        else
                            mSeries2.appendData(dataPoint,true,60);
                        mSeries2.setThickness(6);
                        mSeries2.setColor(Color.GREEN);
                        graph1.getViewport().setYAxisBoundsManual(true);
                        graph1.getViewport().setMinY(99);
                        graph1.getViewport().setMaxY(105);
                        graph1.addSeries(mSeries2);
                        insertionLossRealTimeCount = insertionLossRealTimeCount + 1;


                    }
                    else
                    {
                        insertionLossRealTimeCount=0;
                    }

                }

            });

        }
        else
        {
            graphTextViewMD.setText("Graphs (Not Connected)");
            // values[insertionLossRealTimeCount]=new DataPoint(insertionLossRealTimeCount,0);

        }

    }




    ////OTDR Initial Data;
    public void fetchOTDRInitialData() {


        if(isNetworkAvailable()) {

            graphTextViewMD.setText("Graphs (Connected)");
            lineName=getIntent().getExtras().getString("lineName");
            mDocRef = FirebaseFirestore.getInstance().collection("OTDR").document("TIER-1").collection(lineName).document("Distance "+String.valueOf(otdrinitialCount));

            mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        Log.i("Success", "Succesfully Fetch");

                        dataPointOTDR =new DataPoint(documentSnapshot.getDouble("distance"),Double.valueOf(documentSnapshot.getString("val")));
                        Log.i("DatapointXY",String.valueOf(dataPointOTDR.getX())+"  "+String.valueOf(dataPointOTDR.getY()));
                        Log.i("DatapointXOTDR",String.valueOf(dataPointOTDR.getX())+"  "+String.valueOf(otdrinitialCount));

                        if(otdrinitialCount==0)
                            mSeriesOTDR.resetData(new DataPoint[] {dataPointOTDR});
                        else
                            mSeriesOTDR.appendData(dataPointOTDR,true,60);
                        mSeriesOTDR.setThickness(6);
                        mSeriesOTDR.setColor(Color.GREEN);
                        graph3.getViewport().setYAxisBoundsManual(true);
                        graph3.getViewport().setMinY(-10);
                        graph3.getViewport().setMaxY(-6);
                        graph3.addSeries(mSeriesOTDR);
                        otdrinitialCount = otdrinitialCount + 1;


                    }
                    else
                    {
                        otdrinitialCount=0;
                    }

                }

            });

        }
        else
        {
            graphTextViewMD.setText("Graphs (Not Connected)");
        }

    }


















    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mHandler.removeCallbacks(mTimer2);
        otdrinitialCount=0;
        mSeriesOTDR.resetData(new DataPoint[] {new DataPoint(otdrinitialCount,0)});

        insertionLossRealTimeCount=0;
        mSeries2.resetData(new DataPoint[] {new DataPoint(insertionLossRealTimeCount,0)});




        finish();
    }


}
