package com.example.testpeiky.rest

import com.example.testpeiky.models.MoviesResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "0f6c06b1df72667cfd66e0b1b2108c37"

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es",
        @Query("page") page: Int): Single<Response<MoviesResponse>>
}
