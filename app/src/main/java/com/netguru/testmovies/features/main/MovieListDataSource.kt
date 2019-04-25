package com.netguru.testmovies.features.main

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.netguru.testmovies.data.Movie

class MovieListDataSource(): PageKeyedDataSource<Int, Movie>() {


    val data = listOf( Movie(1, "Godfather"), Movie(2, "Shawshank redemption")) //stub

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        callback.onResult(data,null,3)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
    }

    companion object {
        fun createFactory(): DataSource.Factory<Int,Movie> {
            return object : DataSource.Factory<Int,Movie>(){
                override fun create(): DataSource<Int, Movie> {
                    return MovieListDataSource()
                }
            }
        }
    }
}