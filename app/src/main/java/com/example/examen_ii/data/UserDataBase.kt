package com.example.examen_ii.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.examen_ii.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun UserDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempDB = INSTANCE
            if (tempDB != null) {
                return tempDB
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}