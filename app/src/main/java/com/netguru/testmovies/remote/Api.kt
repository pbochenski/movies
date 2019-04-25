package com.netguru.testmovies.remote

import com.netguru.testmovies.data.MovieDiscoveryResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("{version}/discover/movie")
    fun getMovieDiscover(
        @Path("version") version: Int,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("year") year: Int? = null,
        @Query("include_adult") includeAdult: Boolean = false
    ): Single<MovieDiscoveryResponse>

}