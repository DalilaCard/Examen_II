package com.example.examen_ii.repositories

import com.example.examen_ii.entities.PostEntity
import com.example.examen_ii.network.ClienteRetrofit
import com.example.examen_ii.services.PostService

class PostRepository(private val postService: PostService = ClienteRetrofit.getInstanciaRetrofit1) {
    suspend fun getAllPosts(id: Long) : List<PostEntity> = postService.getAllPosts(id)
}