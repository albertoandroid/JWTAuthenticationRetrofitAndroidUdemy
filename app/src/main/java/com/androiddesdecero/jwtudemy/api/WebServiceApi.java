package com.androiddesdecero.jwtudemy.api;

import com.androiddesdecero.jwtudemy.model.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by albertopalomarrobledo on 21/12/18.
 */

public interface WebServiceApi {

    @POST("/token")
    Call<List<String>> obtenerToken(@Body Login login);

}
