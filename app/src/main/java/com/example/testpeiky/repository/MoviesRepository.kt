package com.example.testpeiky.repository

import com.example.testpeiky.models.MoviesResponse
import io.reactivex.Observable

interface MoviesRepository {
    fun observableMovies(): Observable<MoviesResponse>
    fun observableMoviesError(): Observable<String>
    fun getPopularMovies(
        language: String,
        page: Int)
}