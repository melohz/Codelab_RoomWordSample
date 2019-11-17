package com.example.roomwordsampleapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "world_table")
class Word(@PrimaryKey @ColumnInfo(name = "word") val word: String)