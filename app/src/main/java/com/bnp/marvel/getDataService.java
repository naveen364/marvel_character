package com.bnp.marvel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface getDataService {
    @GET("/v1/public/characters/")
    Call<List<modelapi>> getAllPhotos();
}
