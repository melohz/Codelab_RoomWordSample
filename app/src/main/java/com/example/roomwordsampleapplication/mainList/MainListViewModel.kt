package com.example.roomwordsampleapplication.mainList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomwordsampleapplication.TrainingRepository
import com.example.roomwordsampleapplication.TrainingRoomDatabase
import com.example.roomwordsampleapplication.data.TrainingContents
import kotlinx.coroutines.launch

class MainListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TrainingRepository

    val allTrainings: LiveData<List<TrainingContents>>

    init {
        val trainingDao = TrainingRoomDatabase.getDatabase(
            application,
            viewModelScope
        ).trainingDao()
        repository = TrainingRepository(trainingDao)
        allTrainings = repository.allTrainingContents
    }

    fun insert(trainingContents: TrainingContents) = viewModelScope.launch {
        repository.insert(trainingContents)
    }
}