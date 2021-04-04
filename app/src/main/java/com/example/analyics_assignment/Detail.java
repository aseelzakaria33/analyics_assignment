package com.example.analyics_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.UUID;

public class Detail extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;

    TextView  name ;
    TextView  price ;
    TextView  detail ;

    long timestart;
    long timeend;
    long timeresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        timestart=System.currentTimeMillis();
        name = findViewById(R.id.txtname);
        price = findViewById(R.id.txtprice);
        detail = findViewById(R.id.txtdetail);
        String path1 = getIntent().getStringExtra("CollectionPath");
        String path2 = getIntent().getStringExtra("DocumentPath");
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection(path1).document(path2)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                                System.out.println(document);

                                String Name = document.getString("name");
                                String price1 = document.getString( "Price");
                                String det = document.getString( "Gradians");
                                name.setText(Name);
                                 price.setText(price1);
                                detail.setText(det);




                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
            }
        }) ;




        trackScreen("Detail screen");
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
    void trackScreen(String screenName){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "Detail");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }
}