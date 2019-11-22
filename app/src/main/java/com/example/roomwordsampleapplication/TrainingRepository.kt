package com.example.roomwordsampleapplication

import androidx.lifecycle.LiveData
import com.example.roomwordsampleapplication.data.TrainingContents

class TrainingRepository(private val trainingDao: TrainingDao) {

    val allTrainingContents: LiveData<List<TrainingContents>> = trainingDao.getTraining()

    suspend fun insert(trainingContents: TrainingContents) {
        trainingDao.insert(trainingContents)
    }
}