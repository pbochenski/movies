package com.netguru.testmovies.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.netguru.testmovies.R
import com.netguru.testmovies.common.ViewState
import com.netguru.testmovies.common.hide
import com.netguru.testmovies.common.show
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MovieListAdapter()
        movies.adapter = adapter

        mainViewModel.pagedListData.observe(this, Observer { movies ->
            adapter.submitList(movies)
        })
        mainViewModel.getState().observe(this, Observer { state ->
            when(state){
               is ViewState.LOADING -> showLoading(true)
               is ViewState.DONE -> showLoading(false)
               is ViewState.ERROR -> showError(state.message)
               is ViewState.EMPTY -> showEmpty()
            }
        })
    }

    private fun showEmpty() {
        progressBar.hide()
        message.show()
        message.text = getString(R.string.empty)
    }

    private fun showError(text: String?) {
        progressBar.hide()
        message.show()
        message.text = getString(R.string.error, text)
    }

    private fun showLoading(show: Boolean) {
        if (!show && progressBar.visibility == View.VISIBLE) progressBar.hide()
    }
}
