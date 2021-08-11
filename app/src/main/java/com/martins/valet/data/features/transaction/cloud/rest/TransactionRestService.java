package com.martins.valet.data.features.transaction.cloud.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.martins.valet.data.features.entity.disk.AgreementRest;
import com.martins.valet.data.features.entity.rest.TransactionOutRest;
import com.martins.valet.data.features.entity.rest.reports.ReportVehycleRest;
import com.martins.valet.data.features.entity.rest.reports.ReportsRevenuewRest;

import java.util.List;
import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by policante on 7/13/16.
 */
public interface TransactionRestService {

    @POST("veiculo/entrada")
    @FormUrlEncoded
    Observable<JsonObject> transactionIn(@Header("Authorization") String authHeader, @FieldMap Map<String, String> transactionInRest);

    @GET("dashboard/veiculo/consulta/entrada/{plate}")
    Observable<JsonElement> findVehycleFromPlate(@Header("Authorization") String authHeader, @Path("plate") String plate);

    @POST("veiculo/consulta")
    @FormUrlEncoded
    Observable<JsonElement> findTransactionFromPlate(@Header("Authorization") String authHeader, @FieldMap Map<String, String> plateOrId);

    @POST("veiculo/saida")
    @FormUrlEncoded
    Observable<TransactionOutRest> transactionOut(@Header("Authorization") String authHeader, @FieldMap Map<String, String> transactionOutRest);

    @GET("veiculo/aberto")
    Observable<List<TransactionOutRest>> getTransactionOpen(@Header("Authorization") String authHeader);

    @GET("convenio")
    Observable<List<AgreementRest>> getAgreement(@Header("Authorization") String authHeader);

    @POST("veiculo/reabrir")
    @FormUrlEncoded
    Observable<TransactionOutRest> reopenTransaction(@Header("Authorization") String authHeader, @Field("id") String identifier);

    @GET("dashboard/relatorio/fechamento/{dateIn}/{dateOut}")
    Observable<ReportsRevenuewRest> getRevenue(@Header("Authorization") String authHeader, @Path("dateIn") String dateIn, @Path("dateOut") String dateOut );

}
