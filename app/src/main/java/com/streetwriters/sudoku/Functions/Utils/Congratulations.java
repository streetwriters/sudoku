package com.streetwriters.sudoku.Functions.Utils;

import android.app.Activity;
import android.view.View;

import com.streetwriters.sudoku.R;
import com.plattysoft.leonids.ParticleSystem;

public class Congratulations {

    public void startFireworks(Activity activity, View v){
        new ParticleSystem(activity, 2000, R.drawable.circle, 10000)
                .setSpeedRange(0.05f, 0.1f)
                .oneShot(v, 4000);
    }
}
