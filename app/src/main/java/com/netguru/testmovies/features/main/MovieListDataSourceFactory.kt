package com.netguru.testmovies.features.main

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.netguru.testmovies.data.Movie
import com.netguru.testmovies.repo.NetworkRepo
import io.reactivex.disposables.CompositeDisposable

class MovieListDataSourceFactory(private val repo: NetworkRepo,
                                 private  val disposable: CompositeDisposable) : DataSource.Factory<Int, Movie>() {

    val createdDataSource = MutableLiveData<MovieListDataSource>()
    private var year: Int? = null

    override fun create(): DataSource<Int, Movie> {
             val dataSource = MovieListDataSource(repo, disposable, year)
             createdDataSource.postValue(dataSource)
            return dataSource
    }

    fun setYear(year: Int?){
        this.year = year
    }

}