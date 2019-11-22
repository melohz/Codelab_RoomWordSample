package com.example.roomwordsampleapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "training_contents_table")
class TrainingContents(
    @PrimaryKey
    var name: String,
    var rep: String?
)