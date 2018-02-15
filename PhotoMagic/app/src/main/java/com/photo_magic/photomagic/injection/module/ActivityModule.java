package com.photo_magic.photomagic.injection.module;

import android.app.Activity;
import android.content.Context;

import com.photo_magic.photomagic.injection.quialifier.ActivityContext;
import com.photo_magic.photomagic.ui.adapter.PhotoAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by luis- on 12/02/2018.
 */
@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    PhotoAdapter productAdapter() {
        return new PhotoAdapter(mActivity);
    }

}
