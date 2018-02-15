package com.photo_magic.photomagic.injection.module;

import android.app.Application;
import android.content.Context;

import com.photo_magic.photomagic.injection.quialifier.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by luis- on 12/02/2018.
 */

@Module
public class ApplicationModule {

    protected final Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }



}

