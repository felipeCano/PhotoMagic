package com.photo_magic.photomagic.injection.module;

import com.photo_magic.photomagic.data.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by luis- on 12/02/2018.
 */

@Module
public class NetworkModule {

    public NetworkModule() {
    }

    @Provides
    @Singleton
    ApiService provideApiService() {
        return ApiService.Factory.create();
    }

}
