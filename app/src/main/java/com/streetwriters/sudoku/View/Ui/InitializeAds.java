package com.streetwriters.sudoku.View.Ui;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.streetwriters.sudoku.Functions.CheckErrors;
import com.streetwriters.sudoku.Functions.Objects.HistoryItem;
import com.streetwriters.sudoku.Functions.Utils.Digits;
import com.streetwriters.sudoku.Functions.Utils.Dimensions;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;
import com.streetwriters.sudoku.View.Buttons.Cell;
import com.streetwriters.sudoku.View.Layouts.CellLayout;
import com.streetwriters.sudoku.View.Layouts.TextLayout;

public class InitializeAds implements RewardedVideoAdListener {
    private AdView mAdView;
    private Context context;
    private int retry = 0;
    GameState gameState = GameState.getInstance();

    public InitializeAds(Context context) {
        this.context = context;
    }

    public void initializeMobileAds(Context context) {
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
    }

    public void initializeMobileAdswithId(Context context) {
        MobileAds.initialize(context, "ca-app-pub-3940256099942544/5224354917");
    }

    public void initializeAdView(Activity activity) {
        //when ads are used these should be unCommented
        //mAdView = activity.findViewById(R.id.adView);
        //AdRequest adRequest = new AdRequest.Builder().build();
        //mAdView.loadAd(adRequest);
    }

    public void initializeRewardedVideoAd(Context context) {
        // Use an activity context to get the rewarded video instance.
        gameState.setmRewardedVideoAd(MobileAds.getRewardedVideoAdInstance(context));
        gameState.getmRewardedVideoAd().setRewardedVideoAdListener(this);
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Log.d("test", "I am in Rewarded Loaded.");
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Log.d("test", "I am in Rewarded opened.");
    }

    @Override
    public void onRewardedVideoStarted() {
        Log.d("test", "I am in Rewarded started.");
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Log.d("test", "I am in Rewarded closed.");
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        Log.d("test", "I am in Rewarded.");
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Log.d("test", "I am in Rewarded left.");
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Log.d("test", "I am in Rewarded failed." + i);
        if (retry < 10) {
            loadRewardedVideoAd();
            retry++;
        }
    }

    @Override
    public void onRewardedVideoCompleted() {
        Log.d("test", "I am in Rewarded Completed.");

        if (gameState.getGameOverReward()) {
            giveGameOverReward();
        } else {
            giveHint();
        }

        retry = 0;
        loadRewardedVideoAd();
    }

    public void loadRewardedVideoAd() {
        gameState.getmRewardedVideoAd().loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());
    }

    public RewardedVideoAd getmRewardedVideoAd() {
        return gameState.getmRewardedVideoAd();
    }


    void giveGameOverReward() {
        new TextLayout(context).subtractMistake();
        new CellLayout(context, gameState.getActiveCellId()).setCellText("");
    }

    public void giveHint() {
        if (gameState.getActiveCellId() != -1) {
            if(gameState.getHintsUsed()<3) {
                Dimensions changeUnits = new Dimensions();
                Digits digits = changeUnits.numberToDigits(gameState.getActiveCellId());
                int i = digits.first();
                int j = digits.second();
                CellLayout cellLayout = new CellLayout(context, gameState.getActiveCellId());
                CheckErrors checkErrors = new CheckErrors();

                addHistoryItem(cellLayout.getCell());
                cellLayout.setCellText("" + gameState.getSolvedPuzzle()[i][j]);
                gameState.updateUserSolvedPuzzle(gameState.getSolvedPuzzle()[i][j]);
                checkErrors.check((Activity) context);
                gameState.setHintsUsed(gameState.getHintsUsed() + 1);
            }
        }
    }


    void addHistoryItem(Cell buttonView) {

        HistoryItem historyItem = new HistoryItem();

        historyItem.setCellText(buttonView.getText().toString());
        historyItem.setCellId(gameState.getActiveCellId());
        gameState.getUserHistory().add(historyItem);
    }
}
