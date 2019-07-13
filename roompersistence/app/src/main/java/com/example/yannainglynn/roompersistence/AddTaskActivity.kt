package com.example.yannainglynn.roompersistence

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        findViewById<View>(R.id.button_save).setOnClickListener {
            saveTask()
        }
    }
    private fun saveTask() {
        val sTask = editTextTask?.text.toString()
        val sDesc = editTextDesc?.text.toString()
        val sFinishBy = editTextFinishBy?.text.toString()

        if (sTask.isEmpty()) {
            editTextTask.error = "Task required"
            editTextTask.requestFocus()
            return
        }

        if (sDesc.isEmpty()) {
            editTextDesc.error = "Desc required"
            editTextDesc.requestFocus()
            return
        }

        if (sFinishBy.isEmpty()) {
            editTextFinishBy.error = "Finish by required"
            editTextFinishBy.requestFocus()
            return
        }

        class SaveTask : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                //creating a task
                val task = Task()
                task.task = sTask
                task.desc = sDesc
                task.finishBy = sFinishBy
                task.isFinished = false
                //adding to database
                DatabaseClient.getInstance(applicationContext).appDatabase
                        .taskDao()
                        .insert(task)
                return null
            }

            override fun onPostExecute(aVoid: Void?) {
                super.onPostExecute(aVoid)
                finish()
                startActivity(Intent(applicationContext, MainActivity::class.java))
                Toast.makeText(applicationContext, "Saved", Toast.LENGTH_LONG).show()
            }
        }
        val st = SaveTask()
        st.execute()
    }

}