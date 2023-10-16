package com.example.taskmanager.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.databinding.ItemTaskBinding
import com.example.taskmanager.model.Task

class TaskAdapter: RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val list = arrayListOf<Task>()

    fun addTask(task: Task){
        list.add(0,task)
        notifyItemChanged(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
       return TaskViewHolder(
           ItemTaskBinding.inflate(
           LayoutInflater.from(parent.context),
               parent,false
           )
       )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list[position])
    }


    inner class TaskViewHolder(private var binding: ItemTaskBinding) :ViewHolder(binding.root){

        fun bind(task: Task){
            binding.run {
                tvTitle.text = task.title
                tvDesc.text = task.desc
            }
        }

    }

}