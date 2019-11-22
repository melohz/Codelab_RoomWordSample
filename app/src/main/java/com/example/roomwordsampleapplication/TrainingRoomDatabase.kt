package com.example.roomwordsampleapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomwordsampleapplication.data.TrainingContents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(TrainingContents::class), version = 2, exportSchema = false)
abstract class TrainingRoomDatabase : RoomDatabase() {

    abstract fun trainingDao(): TrainingDao

    companion object {
        @Volatile
        private var INSTANCE: TrainingRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): TrainingRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TrainingRoomDatabase::class.java,
                    "training_database"
                ).addCallback(TrainingDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class TrainingDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.trainingDao())
                }
            }
        }
        suspend fun populateDatabase(trainingDao: TrainingDao) {
            // Add sample
            var training = TrainingContents("Push up", "10")
            trainingDao.insert(training)
        }
    }
}