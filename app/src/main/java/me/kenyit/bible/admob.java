package me.kenyit.bible;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
public class admob {
	

	public static int nbShowInterstitial = 2;
	public static int mCount = 0;
	
	public static void admobBannerCall(Activity acitivty , LinearLayout linerlayout){
		
        AdView adView = new AdView(acitivty);
        adView.setAdUnitId("ca-app-pub-4384625939770339/7070657036");
        adView.setAdSize(AdSize.BANNER);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        });
        linerlayout.addView(adView);

	}
	
	public static void admobBannerCall(Activity acitivty , RelativeLayout relativeLayout){
		
        AdView adView = new AdView(acitivty);
        adView.setAdUnitId("ca-app-pub-4384625939770339/7070657036");
        adView.setAdSize(AdSize.BANNER);
        AdRequest.Builder builder = new AdRequest.Builder();
        adView.loadAd(builder.build());
        relativeLayout.addView(adView);

	}
	
	public static String getBannerId(){
		return "ca-app-pub-4384625939770339/7070657036";
	}
	
}