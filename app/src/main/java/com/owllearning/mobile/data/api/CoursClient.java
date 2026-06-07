package com.owllearning.mobile.data.api;

import com.owllearning.mobile.data.model.response.CoursResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoursClient {
    @GET("api/cours")
    Call<List<CoursResponse>> getTousLesCoursPublies();
}
