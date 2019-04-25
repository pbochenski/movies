package com.netguru.testmovies.features.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.netguru.testmovies.R
import com.netguru.testmovies.common.ViewState
import com.netguru.testmovies.common.hide
import com.netguru.testmovies.common.show
import com.netguru.testmovies.features.detail.MovieFragment
import kotlinx.android.synthetic.main.movie_list_fragment.*
import org.koin.android.viewmodel.ext.android.getViewModel

class MovieListFragment : Fragment() {

    private val mainViewModel: MainViewModel by lazy { requireActivity().getViewModel<MainViewModel>() }
    private lateinit var adapter: MovieListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movies.adapter = adapter

        button.setOnClickListener {
            val fragment = DatePickerDialog()
            fragment.show(requireFragmentManager(), "DIALOG")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = MovieListAdapter { movie, imageView ->
            findNavController()
                .navigate(R.id.action_movieListFragment_to_movieFragment,
                    bundleOf(MovieFragment.movie_arg to movie),
                    null,
                    FragmentNavigatorExtras(
                        imageView to "imageView"
                    ))
        }

        mainViewModel.pagedListData.observe(this, Observer { movies ->
            adapter.submitList(movies)
        })
        mainViewModel.getState().observe(this, Observer { state ->
            when (state) {
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
        if( show && !adapter.hasData() ) progressBar.show() else progressBar.hide()
    }
}
