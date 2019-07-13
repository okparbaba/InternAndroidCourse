package com.example.yannainglynn.roompersistence

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_update_task.*

class UpdateTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_task)

        val task = intent.getSerializableExtra("task") as Task

        loadTask(task)

        findViewById<View>(R.id.button_update).setOnClickListener {
            Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_LONG).show()
            updateTask(task)
        }

        findViewById<View>(R.id.button_delete).setOnClickListener {
            val builder = AlertDialog.Builder(this@UpdateTaskActivity)
            builder.setTitle("Are you sure?")
            builder.setPositiveButton("Yes") { _, _ -> deleteTask(task) }
            builder.setNegativeButton("No") { _, _ -> }

            val ad = builder.create()
            ad.show()
        }
    }

    private fun loadTask(task: Task) {
        editTextTask.setText(task.task)
        editTextDesc.setText(task.desc)
        editTextFinishBy.setText(task.finishBy)
        checkBoxFinished.isChecked = task.isFinished
    }

    private fun updateTask(task: Task) {
        val sTask = editTextTask!!.text.toString().trim { it <= ' ' }
        val sDesc = editTextDesc!!.text.toString().trim { it <= ' ' }
        val sFinishBy = editTextFinishBy!!.text.toString().trim { it <= ' ' }

        if (sTask.isEmpty()) {
            editTextTask!!.error = "Task required"
            editTextTask!!.requestFocus()
            return
        }

        if (sDesc.isEmpty()) {
            editTextDesc!!.error = "Desc required"
            editTextDesc!!.requestFocus()
            return
        }

        if (sFinishBy.isEmpty()) {
            editTextFinishBy!!.error = "Finish by required"
            editTextFinishBy!!.requestFocus()
            return
        }

        class UpdateTask : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {
                task.task = sTask
                task.desc = sDesc
                task.finishBy = sFinishBy
                task.isFinished = checkBoxFinished!!.isChecked
                DatabaseClient.getInstance(applicationContext).appDatabase
                        .taskDao()
                        .update(task)
                return null
            }

            override fun onPostExecute(aVoid: Void?) {
                super.onPostExecute(aVoid)
                Toast.makeText(applicationContext, "Updated", Toast.LENGTH_LONG).show()
                finish()
                startActivity(Intent(this@UpdateTaskActivity, MainActivity::class.java))
            }
        }

        val ut = UpdateTask()
        ut.execute()
    }

    private fun deleteTask(task: Task) {
        class DeleteTask : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {
                DatabaseClient.getInstance(applicationContext).appDatabase
                        .taskDao()
                        .delete(task)
                return null
            }

            override fun onPostExecute(aVoid: Void?) {
                super.onPostExecute(aVoid)
                Toast.makeText(applicationContext, "Deleted", Toast.LENGTH_LONG).show()
                finish()
                startActivity(Intent(this@UpdateTaskActivity, MainActivity::class.java))
            }
        }

        val dt = DeleteTask()
        dt.execute()

    }

}