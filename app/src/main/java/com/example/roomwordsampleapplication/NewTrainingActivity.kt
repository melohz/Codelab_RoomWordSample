package com.example.roomwordsampleapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_new_training.*
import kotlinx.android.synthetic.main.activity_new_word.*

class NewTrainingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_training)

        training_button_save.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(training_item.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val trainingTitle = training_item.text.toString()
                val trainingRep = training_rep.text.toString()
                replyIntent.putExtra(EXTRA_REPLY_TITLE, trainingTitle)
                replyIntent.putExtra(EXTRA_REPLY_REP, trainingRep)

                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY_TITLE = "Title"
        const val EXTRA_REPLY_REP = "Rep"
    }
}
