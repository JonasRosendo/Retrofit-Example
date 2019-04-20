package com.jonasrosendo.retrofit.api;

import com.jonasrosendo.retrofit.model.CEP;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ICEPService {

    @GET("{cep}/json")
    Call<CEP> retrieveCEP(@Path("cep") String cep);
}
