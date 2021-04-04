package com.example.analyics_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.UUID;

public class ElctronicActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;

    Button labBtn  ;
    Button iphoneBtn  ;
    Button smartBtn  ;

    long timestart;
    long timeend;
    long timeresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elctronic);
        labBtn = findViewById(R.id.btn_hp_laptop);
        iphoneBtn = findViewById(R.id.btn_iphone_x);
        smartBtn = findViewById(R.id.btn_smart_tv);
        timestart=System.currentTimeMillis();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        labBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Detail.class);
                intent.putExtra("CollectionPath", "Elctronic");
                intent.putExtra("DocumentPath", "HP Laptop");
                startActivity(intent);
                SelectCunter(UUID.randomUUID().toString(),labBtn.getText().toString());

            }
        });
        iphoneBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Detail.class);
                intent.putExtra("CollectionPath", "Elctronic");
                intent.putExtra("DocumentPath", "Iphone");
                startActivity(intent);
                SelectCunter(UUID.randomUUID().toString(),iphoneBtn.getText().toString());


            }
        });
        smartBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Detail.class);
                intent.putExtra("CollectionPath", "Elctronic");
                intent.putExtra("DocumentPath", "Smart TV");
                startActivity(intent);
                SelectCunter(UUID.randomUUID().toString(),smartBtn.getText().toString());

            }
        });
        trackScreen("ElctronicActivity screen");
    }
    @Override
    protected void onDestroy() {
        timeend = System.currentTimeMillis();
        timeEvent(timestart, timeend);
        super.onDestroy();
    }
    public void timeEvent(long tim1,long time2){
        timeresult=(tim1 - time2);
        Bundle bundletime= new Bundle();
        bundletime.putString("ueerid", UUID.randomUUID().toString());
        bundletime.putLong("timespend",timeresult);
        mFirebaseAnalytics.logEvent("time",bundletime);

    }
    public void  SelectCunter (String id ,String name){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "prodectitem");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }
    void trackScreen(String screenName){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "ElctronicActivity");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }
}