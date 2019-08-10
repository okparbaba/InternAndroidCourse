package com.example.yannainglynn.roompersistence

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class TasksAdapter(private val mCtx: Context, private val taskList: List<Task>) : androidx.recyclerview.widget.RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.recyclervew_task, parent, false)
        return TasksViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val t = taskList[position]
        holder.textViewTask.text = t.task
        holder.textViewDesc.text = t.desc
        holder.textViewFinishBy.text = t.finishBy

        if (t.isFinished)
            holder.textViewStatus.text = "Completed"
        else
            holder.textViewStatus.text = "Not Completed"
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TasksViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var textViewStatus: TextView
        var textViewTask: TextView
        var textViewDesc: TextView
        var textViewFinishBy: TextView

        init {

            textViewStatus = itemView.findViewById(R.id.textViewStatus)
            textViewTask = itemView.findViewById(R.id.textViewTask)
            textViewDesc = itemView.findViewById(R.id.textViewDesc)
            textViewFinishBy = itemView.findViewById(R.id.textViewFinishBy)


            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val task = taskList[adapterPosition]

            val intent = Intent(mCtx, UpdateTaskActivity::class.java)
            intent.putExtra("task", task)

            mCtx.startActivity(intent)
        }
    }
}