package com.netguru.testmovies.di

import com.netguru.testmovies.BuildConfig
import com.netguru.testmovies.remote.ApiService
import com.netguru.testmovies.repo.NetworkRepo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val restModule = module {
    single<Moshi> {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
            })
            .build()
    }

    single<ApiService> {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .baseUrl(BuildConfig.BASE_URL)
            .build().create(ApiService::class.java)
    }

    single {
        NetworkRepo(get())
    }
}