package com.example.examen_ii.network

import com.example.examen_ii.services.CommentService
import com.example.examen_ii.services.PostService
import com.example.examen_ii.services.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClienteRetrofit {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val getInstanciaRetrofit0: UserService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }

    val getInstanciaRetrofit1: PostService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostService::class.java)
    }

    val getInstanciaRetrofit2: CommentService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CommentService::class.java)
    }

}