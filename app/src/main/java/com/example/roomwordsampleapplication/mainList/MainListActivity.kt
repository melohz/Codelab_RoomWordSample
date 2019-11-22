package com.example.roomwordsampleapplication.mainList

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsampleapplication.NewTrainingActivity
import com.example.roomwordsampleapplication.R
import com.example.roomwordsampleapplication.data.TrainingContents
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainListActivity : AppCompatActivity() {

    private val newMainListActivityRequestCode = 1
    private lateinit var mainListViewModel: MainListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)

        val recyclerView = findViewById<RecyclerView>(R.id.main_list_recyclerview)
        val adapter = MainListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mainListViewModel = ViewModelProvider(this).get(MainListViewModel::class.java)

        mainListViewModel.allTrainings.observe(this, Observer { trainings ->
            trainings?.let { adapter.setTrainingContents(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.main_list_fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainListActivity, NewTrainingActivity::class.java)
            startActivityForResult(intent, newMainListActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newMainListActivityRequestCode && resultCode == Activity.RESULT_OK) {

            val trainingTitle = data?.getStringExtra(NewTrainingActivity.EXTRA_REPLY_TITLE) ?: return
            val trainingRep = data.getStringExtra(NewTrainingActivity.EXTRA_REPLY_REP) ?: return

            val trainingContent = TrainingContents(trainingTitle, trainingRep)
            mainListViewModel.insert(trainingContent)

        } else {
            Toast.makeText(
                applicationContext,
                "Training not saved because it is empty.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
