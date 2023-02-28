package com.harjot.notesapp

import androidx.room.*

/**
 * @Author: 017
 * @Date: 24/02/23
 * @Time: 5:31 PM
 */
@Dao
interface UserDao {

    @Insert
    fun insertData(userEntity: UserEntity)

    @Update
    fun updateData(userEntity: UserEntity)

    @Delete
    fun deleteData(userEntity: UserEntity)

    @Query("select * from UserEntity")
    fun getData(): List<UserEntity>
}