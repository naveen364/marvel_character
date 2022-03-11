package com.bnp.marvel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface getDataService {
    @GET("characters")
    Call<List<modelapi>> getAllPhotos();
}
