package com.martins.valet.data.helpers.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.martins.valet.Utils.Helpers.ItemDataTypeAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by policante on 7/13/16.
 */
public class ValetRestServiceRetrofit {

    protected Retrofit retrofit;

    public ValetRestServiceRetrofit() {
    }

    protected String getApiBaseUrl() {
        return "http://epavalet.com.br/api/";
    }

    protected Retrofit getRetrofit() {
        if (retrofit == null || !retrofit.baseUrl().url().toString().equals(getApiBaseUrl())) {
            retrofit = createRetrofit();
        }
        return retrofit;
    }

    protected Retrofit createRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemDataTypeAdapterFactory())
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd HH:mm:ss");
        Gson gson = gsonBuilder.create();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(getApiBaseUrl())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client);

        return retrofitBuilder.build();
    }

    public <T> T getService(Class<T> serviceClass) {
        return getRetrofit().create(serviceClass);
    }
}
