package com.example.examen_ii.services

import com.example.examen_ii.entities.PostEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {
    @GET("users/{id}/posts")
    suspend fun getAllPosts(@Path("id") id: Long): List<PostEntity>
}