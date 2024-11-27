package com.example.examen_ii.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "name_user")
    val name: String,
    @ColumnInfo(name = "username_user")
    val username: String,
    @ColumnInfo(name = "email_user")
    val email: String,
)
