package com.example.somzzzzz.retrofitexample;

import java.util.List;

import Model.CheckSum;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    //define url

    //https://us-central1-easytowofficer.cloudfunctions.net/api/create-checksum

    //https://us-central1-easytowofficer.cloudfunctions.net/api/verify-checksum

    String BASE_URL="https://us-central1-easytowofficer.cloudfunctions.net/api/";

    @GET("create-checksum")
    Call<CheckSum> getCheckSum();

}
