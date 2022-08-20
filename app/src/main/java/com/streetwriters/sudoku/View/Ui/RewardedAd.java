package com.streetwriters.sudoku.View.Ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.streetwriters.sudoku.Activities.MainActivity;
import com.streetwriters.sudoku.BuildConfig;
import com.streetwriters.sudoku.Controller.OnClick.ButtonOnClick;
import com.streetwriters.sudoku.Controller.Stats;
import com.streetwriters.sudoku.Functions.FetchData.Data;
import com.streetwriters.sudoku.Functions.Utils.Digits;
import com.streetwriters.sudoku.Functions.Utils.Dimensions;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;
import com.streetwriters.sudoku.View.Layouts.CellLayout;
import com.streetwriters.sudoku.View.Layouts.EditPadLayout;
import com.streetwriters.sudoku.View.Layouts.TextLayout;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;

public class RewardedAd  extends ButtonOnClick implements IUnityAdsInitializationListener {
    private String unityGameID = "4855805";
    private Boolean testMode = true;
            //BuildConfig.DEBUG;
    private String adUnitId = "Rewarded_Android";
    private Boolean isRewardedLoaded = false;
    Activity activity;
    Dialog dialog;

    public RewardedAd(Activity activity) {
        super(activity);
        this.activity = activity;
        UnityAds.initialize(activity.getApplicationContext(), unityGameID, testMode, this);
    }

    private IUnityAdsLoadListener loadListener = new IUnityAdsLoadListener() {
        @Override
        public void onUnityAdsAdLoaded(String placementId) {
            //UnityAds.show(activity, adUnitId, new UnityAdsShowOptions(), showListener);
            if (GameState.getInstance().getHints() == 0)
                new EditPadLayout(activity).setHintIcon(-1);
        }

        @Override
        public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
            Log.e("UnityAdsExample", "Unity Ads failed to load ad for " + placementId + " with error: [" + error + "] " + message);
        }
    };

    private IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
        @Override
        public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
            Log.e("UnityAdsExample", "Unity Ads failed to show ad for " + placementId + " with error: [" + error + "] " + message);
            // Re-enable the button if the user should be allowed to watch another rewarded ad
                new EditPadLayout(activity).setHintIcon(-1);
        }

        @Override
        public void onUnityAdsShowStart(String placementId) {
            Log.v("UnityAdsExample", "onUnityAdsShowStart: " + placementId);
        }

        @Override
        public void onUnityAdsShowClick(String placementId) {
            Log.v("UnityAdsExample", "onUnityAdsShowClick: " + placementId);
        }

        @Override
        public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
            Log.v("UnityAdsExample", "onUnityAdsShowComplete: " + placementId);
            if (state.equals(UnityAds.UnityAdsShowCompletionState.COMPLETED)) {
                // Reward the user for watching the ad to completion
                if(gameState.isAdTypeHint()) {
                    Digits digits = new Dimensions().numberToDigits(gameState.getActiveCellId());
                    setButtonVisibility(new CellLayout(activity, gameState.getActiveCellId()));
                    cellClick(gameState.getSolvedPuzzle()[digits.first()][digits.second()]);
                    new EditPadLayout(activity).setHintIcon(-1);
                }else{
                    gameState.setGameFinished(false);
                    gameState.setMistakes(2);
                    new TextLayout(activity).setMistakes(2);
                    dialog.dismiss();
                }
            } else {
                // Do not reward the user for skipping the ad
                if (!gameState.isAdTypeHint()) {
                    dialog.dismiss();
                    Stats scoreBoard = new Stats(gameState.getMistakes(), activity, 0);
                    new Data().deleteGameFile();
                    activity.startActivity(new Intent(activity, MainActivity.class));
                }else{
                    new EditPadLayout(activity).setHintIcon(-1);
                }
                Toast.makeText(activity, "No Ads to Show", Toast.LENGTH_LONG).show();
            }

            loadRewardedAd();

            // Re-enable the button if the user should be allowed to watch another rewarded ad
            //rewardedButton.setEnabled(true);
        }
    };

    @Override
    public void onInitializationComplete() {
        loadRewardedAd();
    }

    @Override
    public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {
        Log.e("UnityAdsExample", "Unity Ads initialization failed with error: [" + error + "] " + message);
    }

    // Implement a function to load an rewarded ad. The ad will start to show after the ad has been loaded.
    public void loadRewardedAd() {
        UnityAds.load(adUnitId, loadListener);
    }

    public void displayRewardedAd(){
        UnityAds.show(activity, adUnitId, new UnityAdsShowOptions(), showListener);
    }

    public void displayRewardedAd(Dialog dialog){
        UnityAds.show(activity, adUnitId, new UnityAdsShowOptions(), showListener);
        this.dialog = dialog;
    }
}

