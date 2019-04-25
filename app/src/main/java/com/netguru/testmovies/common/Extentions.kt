package com.netguru.testmovies.common

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

fun <T, O> LiveData<T>.switchMap(function: (T)-> LiveData<O>): LiveData<O> {
    return Transformations.switchMap(this, function)
}

fun View.show(){
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}