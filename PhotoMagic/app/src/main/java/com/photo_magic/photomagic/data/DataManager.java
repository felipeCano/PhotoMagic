package com.photo_magic.photomagic.data;

import android.content.Context;

import com.photo_magic.photomagic.injection.quialifier.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by luis- on 12/02/2018.
 */

@Singleton //solo puede haber una instancia de esa clase
public class DataManager {
    private final ApiService mApiService;

    @Inject
    public DataManager(ApiService mApiService){
        this.mApiService = mApiService;
    }
    //public Observable<ClimateResponse> getClimate(){
    //    return ApiService.Factory.create().getSfClimate();
  //  }
}