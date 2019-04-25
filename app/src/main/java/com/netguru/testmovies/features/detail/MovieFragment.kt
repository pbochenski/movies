package com.netguru.testmovies.features.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.netguru.testmovies.BuildConfig
import com.netguru.testmovies.R
import com.netguru.testmovies.data.Movie
import kotlinx.android.synthetic.main.movie_detail.*

class MovieFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        sharedElementEnterTransition = ChangeBounds()
        sharedElementReturnTransition = ChangeBounds()
        return inflater.inflate(R.layout.movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = arguments?.get(movie_arg) as? Movie
        if(movie != null){
            if(movie.posterPath != null){
                Glide.with(view).load("${BuildConfig.PHOTO_PATH}${movie.posterPath}")
                    .into(poster)
            }
            title.text = movie.title
        }
    }

    companion object {
        const val movie_arg = "movie"
    }
}