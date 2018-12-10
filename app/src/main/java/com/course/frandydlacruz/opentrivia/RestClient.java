package com.course.frandydlacruz.opentrivia;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestClient {

    @GET("api_category.php")
    Call<Category> getCategories();

    @GET("api.php")
    Call<Trivia> getTrivia(@Query("amount") int amount, @Query("type") String type);
}
