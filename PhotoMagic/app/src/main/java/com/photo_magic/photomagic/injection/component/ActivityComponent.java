package com.photo_magic.photomagic.injection.component;

import android.content.Context;

import com.photo_magic.photomagic.ui.activity.LoginActivity;
import com.photo_magic.photomagic.ui.activity.MainActivity;
import com.photo_magic.photomagic.injection.module.ActivityModule;
import com.photo_magic.photomagic.injection.quialifier.ActivityContext;
import com.photo_magic.photomagic.injection.scope.PerActivity;

import dagger.Component;

/**
 * Created by luis- on 12/02/2018.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(LoginActivity loginActivity);


    @ActivityContext
    Context context();

}
