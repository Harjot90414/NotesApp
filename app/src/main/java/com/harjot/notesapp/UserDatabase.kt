package com.harjot.notesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @Author: 017
 * @Date: 24/02/23
 * @Time: 5:21 PM
 */
@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    //static
    companion object{
        var userDatabase : UserDatabase? = null

        @Synchronized
        fun getInstance( context: Context): UserDatabase{
            if(userDatabase == null){
                userDatabase = Room.databaseBuilder(
                    context,
                    UserDatabase::class.java, context.resources.getString(R.string.app_name)
                ).build()
            }
            return userDatabase!!
        }
    }
}