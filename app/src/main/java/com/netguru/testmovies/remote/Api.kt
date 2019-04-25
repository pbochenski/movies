package com.netguru.testmovies.remote

import com.netguru.testmovies.data.Movie
import com.netguru.testmovies.data.MovieDiscoveryResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("{version}/movie/{id}")
    fun getMovieDetailsById(@Path("version") version: Int, @Path("id") movieId: Int,
                            @Query("api_key") apiKey: String): Single<Movie>

    @GET("{version}/discover/movie")
    fun getMovieDiscover(@Path("version") version: Int,
                         @Query("api_key") apiKey: String,
                         @Query("page") page: Int,
                         @Query("include_adult") includeAdult: Boolean = false): Single<MovieDiscoveryResponse>
}