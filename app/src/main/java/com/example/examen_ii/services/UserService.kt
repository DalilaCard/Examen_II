package com.example.examen_ii.services

import com.example.examen_ii.entities.UserEntity
import retrofit2.http.GET

interface UserService {

    @GET("users")
    suspend fun getAllUsers(): List<UserEntity>

}