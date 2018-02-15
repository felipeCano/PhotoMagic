package com.photo_magic.photomagic;

import android.app.Application;
import android.content.Context;
import com.facebook.FacebookSdk;

import com.facebook.appevents.AppEventsLogger;
import com.photo_magic.photomagic.injection.component.ApplicationComponent;
import com.photo_magic.photomagic.injection.component.DaggerApplicationComponent;
import com.photo_magic.photomagic.injection.module.ApplicationModule;
import com.photo_magic.photomagic.injection.module.NetworkModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by luis- on 12/02/2018.
 */

public class AppAplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        initRealmConfig();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }

    private void initRealmConfig() {

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("photomagic.realm")
                .schemaVersion(2)
                .build();

        Realm.setDefaultConfiguration(config);

    }

    public static AppAplication get(Context context) {
        return (AppAplication) context.getApplicationContext();
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        this.mApplicationComponent = applicationComponent;
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .networkModule(new NetworkModule())
                    .build();
        }
        return mApplicationComponent;
    }

}
