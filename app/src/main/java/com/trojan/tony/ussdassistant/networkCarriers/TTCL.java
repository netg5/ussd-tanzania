package com.trojan.tony.ussdassistant.networkCarriers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.trojan.tony.ussdassistant.R;

import static com.trojan.tony.ussdassistant.Constants.balanceMenu;
import static com.trojan.tony.ussdassistant.Constants.rail;
import static com.trojan.tony.ussdassistant.Constants.tPesaMenu;
import static com.trojan.tony.ussdassistant.Constants.ttclBundle1Menu;
import static com.trojan.tony.ussdassistant.Constants.ttclUniMenu;

public class TTCL extends AppCompatActivity {

    private View view;
    private AdView mAdView;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttcl);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        CardView balance = findViewById(R.id.ttclBalanceCardView);
        CardView tpesa = findViewById(R.id.tPesaCardView);
        CardView uni = findViewById(R.id.ttclUniCardView);
        CardView bundle1 = findViewById(R.id.ttclBundle1CardView);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle params = new Bundle();
                switch (v.getId()) {
                    case R.id.ttclBalanceCardView:
                        Intent ttclCheckBalanceIntent = new Intent(Intent.ACTION_CALL);
                        ttclCheckBalanceIntent.setData(Uri.parse(balanceMenu + rail));
                        startActivity(ttclCheckBalanceIntent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("ttclBalance_button", params);
                        break;
                    case R.id.tPesaCardView:
                        Intent tPesaIntent = new Intent(Intent.ACTION_CALL);
                        tPesaIntent.setData(Uri.parse(tPesaMenu + rail));
                        startActivity(tPesaIntent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("tPesa_button", params);
                        break;
                    case R.id.ttclUniCardView:
                        Intent ttclUniIntent = new Intent(Intent.ACTION_CALL);
                        ttclUniIntent.setData(Uri.parse(ttclUniMenu + rail));
                        startActivity(ttclUniIntent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("ttclUni_button", params);
                        break;
                    case R.id.ttclBundle1CardView:
                        Intent ttclBundle1Intent = new Intent(Intent.ACTION_CALL);
                        ttclBundle1Intent.setData(Uri.parse(ttclBundle1Menu + rail));
                        startActivity(ttclBundle1Intent);
                        params.putInt("ButtonID", v.getId());
                        mFirebaseAnalytics.logEvent("ttclBundle1_button", params);
                        break;
                    default:
                        break;
                }

            }
        };


        balance.setOnClickListener(listener);
        tpesa.setOnClickListener(listener);
        bundle1.setOnClickListener(listener);
        uni.setOnClickListener(listener);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    /**
     * Called when leaving the activity
     */
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
