package com.netguru.testmovies.features.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.netguru.testmovies.BuildConfig
import com.netguru.testmovies.R
import com.netguru.testmovies.data.Movie
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.movie_item.*

class MovieListAdapter: PagedListAdapter<Movie, RecyclerView.ViewHolder>(Movie.DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieVH(LayoutInflater.from(parent.context).inflate(
            R.layout.movie_item, parent, false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (holder is MovieVH) {
            holder.bind(item!!)
        }
    }

    class MovieVH(private val view: View): RecyclerView.ViewHolder(view), LayoutContainer{
        override val containerView: View?
            get() = view

        fun bind(movie: Movie){
            title.text = movie.title
            originalTitle.text = movie.originalTitle
            year.text = movie.releaseDate

            if(movie.posterPath != null){
                Glide.with(view).load("${BuildConfig.PHOTO_PATH}${movie.posterPath}").into(poster)
            }
        }
    }
}