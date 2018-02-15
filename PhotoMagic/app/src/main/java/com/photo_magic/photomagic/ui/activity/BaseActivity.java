package com.photo_magic.photomagic.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.photo_magic.photomagic.AppAplication;
import com.photo_magic.photomagic.injection.component.ActivityComponent;
import com.photo_magic.photomagic.injection.component.DaggerActivityComponent;
import com.photo_magic.photomagic.injection.module.ActivityModule;

/**
 * Created by luis- on 12/02/2018.
 */

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(AppAplication.get(this).getComponent()).build();
        }
        return mActivityComponent;
    }

    protected void callActivity(Intent intent) {
        startActivity(intent);
    }

}