package com.harjot.notesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author: 017
 * @Date: 24/02/23
 * @Time: 5:19 PM
 */
@Entity()
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo()
    var Title: String?= null,
    @ColumnInfo()
    var Text: String?= null
)
