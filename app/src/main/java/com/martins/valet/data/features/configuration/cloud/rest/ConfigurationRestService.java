package com.martins.valet.data.features.configuration.cloud.rest;

import com.martins.valet.data.features.entity.rest.BrandVehycleRest;
import com.martins.valet.data.features.entity.rest.TypeVehycleRest;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by policante on 7/24/16.
 */
public interface ConfigurationRestService {

    @GET("marca")
    Observable<List<BrandVehycleRest>> getAllBrands(@Header("Authorization") String authHeader);

    @GET("tabela/tipo")
    Observable<List<TypeVehycleRest>> getAllTypes(@Header("Authorization") String authHeader);

    @POST("usuario/login")
    @FormUrlEncoded
    Observable<String> doLogin(@Field("login") String username, @Field("password") String password);
}
