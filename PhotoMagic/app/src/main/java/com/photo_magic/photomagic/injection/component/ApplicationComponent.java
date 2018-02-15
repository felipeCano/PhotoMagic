package com.photo_magic.photomagic.injection.component;

import android.app.Application;
import android.content.Context;

import com.photo_magic.photomagic.data.DataManager;
import com.photo_magic.photomagic.injection.module.ApplicationModule;
import com.photo_magic.photomagic.injection.module.NetworkModule;
import com.photo_magic.photomagic.injection.quialifier.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by luis- on 12/02/2018.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    Application application();

    @ApplicationContext
    Context context();

    DataManager dataManager();

}