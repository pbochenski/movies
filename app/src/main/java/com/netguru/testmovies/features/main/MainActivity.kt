package com.netguru.testmovies.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.netguru.testmovies.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MovieListAdapter()
        movies.adapter = adapter
        mainViewModel.pagedListData.observe(this, Observer { movies ->
            adapter.submitList(movies)
        })

    }
}
