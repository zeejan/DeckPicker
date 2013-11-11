
package com.ichi2.anki;

import com.ichi2.anki.R;

import com.ichi2.anim.ActivityTransitionAnimation;
import com.ichi2.anim.ViewAnimation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;

public class AnkiActivity extends Activity {

    public boolean animationDisabled() {
        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(this);
        boolean eInkDisplay = preferences.getBoolean("eInkDisplay", false);
        return eInkDisplay;
    }


    public boolean animationEnabled() {
        return !animationDisabled();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getWindow().setWindowAnimations(0);
    }


    @Override
    public void setContentView(View view) {
        if (animationDisabled()) {
            view.clearAnimation();
        }
        super.setContentView(view);
    }


    @Override
    public void setContentView(View view, LayoutParams params) {
        if (animationDisabled()) {
            view.clearAnimation();
        }
        super.setContentView(view, params);
    }


    @Override
    public void addContentView(View view, LayoutParams params) {
        if (animationDisabled()) {
            view.clearAnimation();
        }
        super.addContentView(view, params);
    }


    @Deprecated
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }


    public void startActivityWithoutAnimation(Intent intent) {
        disableIntentAnimation(intent);
        super.startActivity(intent);
        disableActivityAnimation();
    }


    public void startActivityWithAnimation(Intent intent, int animation) {
        enableIntentAnimation(intent);
        super.startActivity(intent);
        enableActivityAnimation(animation);
    }


    @Deprecated
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }


    public void startActivityForResultWithoutAnimation(Intent intent, int requestCode) {
        disableIntentAnimation(intent);
        super.startActivityForResult(intent, requestCode);
        disableActivityAnimation();
    }


    public void startActivityForResultWithAnimation(Intent intent, int requestCode, int animation) {
        enableIntentAnimation(intent);
        super.startActivityForResult(intent, requestCode);
        enableActivityAnimation(animation);
    }


    @Deprecated
    @Override
    public void finish() {
        super.finish();
    }


    public void finishWithoutAnimation() {
        super.finish();
        disableActivityAnimation();
    }


    public void finishWithAnimation(int animation) {
        super.finish();
        enableActivityAnimation(animation);
    }


    protected void disableViewAnimation(View view) {
        view.clearAnimation();
    }


    protected void enableViewAnimation(View view, Animation animation) {
        if (animationDisabled()) {
            disableViewAnimation(view);
        } else {
            view.setAnimation(animation);
        }
    }


    private void disableIntentAnimation(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    }


    private void disableActivityAnimation() {
        if (AnkiDroidApp.SDK_VERSION > 4) {
            ActivityTransitionAnimation.slide(this, ActivityTransitionAnimation.NONE);
        }
    }


    private void enableIntentAnimation(Intent intent) {
        if (animationDisabled()) {
            disableIntentAnimation(intent);
        } else {
            // Nothing for now
        }
    }


    private void enableActivityAnimation(int animation) {
        if (animationDisabled()) {
            disableActivityAnimation();
        } else {
            if (AnkiDroidApp.SDK_VERSION > 4) {
                ActivityTransitionAnimation.slide(this, animation);
            }
        }
    }

}
