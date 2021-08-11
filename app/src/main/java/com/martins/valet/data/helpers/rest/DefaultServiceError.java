package com.martins.valet.data.helpers.rest;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by policante on 7/26/16.
 */
public class DefaultServiceError {

    @Expose
    private String message;
    @Expose
    private String data;

    public static DefaultServiceError prepareError(Response response) throws IOException {
        ResponseBody error = response.errorBody();
        return new Gson().fromJson(error.string(), DefaultServiceError.class);
    }

    /**
     * Use DefaultServiceError.prepareError(Response)
     */
    private DefaultServiceError() {

    }

    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }

}
