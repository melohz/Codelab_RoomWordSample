package com.example.roomwordsampleapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomwordsampleapplication.data.TrainingContents

@Dao
interface TrainingDao {

    @Query("SELECT * FROM training_contents_table")
    fun getTraining(): LiveData<List<TrainingContents>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(trainingContents: TrainingContents)
}