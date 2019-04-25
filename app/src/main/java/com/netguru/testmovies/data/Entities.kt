package com.netguru.testmovies.data

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(@Json(name = "id") val id: String = "",
                 @Json(name = "title") val title: String = "",
                 @Json(name = "original_title") val originalTitle: String = "",
                 @Json(name = "adult") val adult: Boolean = false,
                 @Json(name = "genre_ids") val genreIds: List<Int> = listOf(),
                 @Json(name = "video") val video: Boolean = false,
                 @Json(name = "vote_average") val voteAverage: Float = 0f,
                 @Json(name = "vote_count") val voteCount: Int = 0,
                 @Json(name = "backdrop_path") val backdropPath: String? = null,
                 @Json(name = "release_date") val releaseDate: String = "",
                 @Json(name = "original_language") val originalLanguage: String = "",
                 @Json(name = "overview") val overview: String = "",
                 @Json(name = "poster_path") val posterPath: String? = null,
                 @Json(name = "popularity") val popularity: Float = 0f
) : Parcelable {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}

