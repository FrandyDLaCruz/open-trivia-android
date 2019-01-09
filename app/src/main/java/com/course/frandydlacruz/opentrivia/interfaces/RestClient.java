package com.course.frandydlacruz.opentrivia.interfaces;

import com.course.frandydlacruz.opentrivia.entities.Category;
import com.course.frandydlacruz.opentrivia.entities.Trivia;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestClient {

    @GET("api_category.php")
    Call<Category> getCategories();

    @GET("api.php")
    Call<Trivia> getTrivia(@Query("amount") int amount, @Query("type") String type, @Query("category") int category, @Query("encode") String encode);
}
