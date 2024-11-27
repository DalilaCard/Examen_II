package com.example.examen_ii.repositories

import com.example.examen_ii.entities.CommentEntity
import com.example.examen_ii.network.ClienteRetrofit
import com.example.examen_ii.services.CommentService

class CommentRepository(private val commentService: CommentService = ClienteRetrofit.getInstanciaRetrofit2) {
    suspend fun getAllComments(id: Long) : List<CommentEntity> = commentService.getAllComments(id)
}