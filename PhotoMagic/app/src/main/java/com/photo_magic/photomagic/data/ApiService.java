package com.photo_magic.photomagic.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by luis- on 12/02/2018.
 */

public interface ApiService {

    //@GET("conditions/q/CA/San_Francisco.json")
   // Observable<ClimateResponse> getSfClimate();

    class Factory {

        public static ApiService create() {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            // add logging as last interceptor
            httpClient.addInterceptor(logging);

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.wunderground.com/api/e5690a1b1473460d/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(httpClient.build())
                    .build();

            return retrofit.create(ApiService.class);
        }
    }
}