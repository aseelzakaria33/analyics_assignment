package com.example.analyics_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class FoodActivity extends AppCompatActivity {

    Button shwBtn  ;
    Button pizzaBtn  ;
    Button salmBtn  ;

    long timestart;
    long timeend;
    long timeresult;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        shwBtn = findViewById(R.id.btn_shawerma);
        pizzaBtn = findViewById(R.id.btn_pizaa);
        salmBtn = findViewById(R.id.btn_salmon);
        timestart=System.currentTimeMillis();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        shwBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Detail.class);
                intent.putExtra("CollectionPath", "Food");
                intent.putExtra("DocumentPath", "shawrma");
                startActivity(intent);
                SelectCunter(UUID.randomUUID().toString(),shwBtn.getText().toString());

            }
        });
        pizzaBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Detail.class);
                intent.putExtra("CollectionPath", "Food");
                intent.putExtra("DocumentPath", "Pizza");
                startActivity(intent);
                SelectCunter(UUID.randomUUID().toString(),pizzaBtn.getText().toString());

            }
        });
        salmBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Detail.class);
                intent.putExtra("CollectionPath", "Food");
                intent.putExtra("DocumentPath", "Salmon");
                startActivity(intent);
                SelectCunter(UUID.randomUUID().toString(),salmBtn.getText().toString());

            }
        });
        trackScreen("FoodActivity screen");
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
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "FoodActivity");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }

}