package com.harjot.notesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserEntity {
    @PrimaryKey(autoGenerate = true)
    var id :Int=0
    @ColumnInfo(name = "Title")var Title :String?= null
    @ColumnInfo(name = "Text")var Text:String?=null
}
