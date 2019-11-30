package com.example.testpeiky.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("total_results") val totalResults: Int = 0,
    @SerializedName("total_pages") val totalPages: Int = 0,
    @SerializedName("results") val results: List<Movies>? = null
): Parcelable

@Parcelize
data class Movies(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("vote_average") val voteAverage: String? = null,
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("vote_count") val voteCount: Int? = null,
    @SerializedName("adult") val adult: Boolean = false,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("popularity") val popularity: Double = 0.0,
    @SerializedName("genre_ids") val genreIds: List<Int>? = null,
    @SerializedName("release_date") val release_date: String? = null
): Parcelable
