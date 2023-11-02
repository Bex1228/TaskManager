package com.example.taskmanager.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ItemTaskBinding
import com.example.taskmanager.model.Task

class TaskAdapter(private var onLongCLick: (Task) -> Unit,
    private var onClick: (Task) -> Unit) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val list = arrayListOf<Task>()

    fun addTasks(tasks: List<Task>) {
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class TaskViewHolder(private var binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            val colors = intArrayOf(
                R.color.white,
                R.color.black
            )
            val textColors = intArrayOf(
                R.color.black,
                R.color.white
            )
            val backgroundColor = ContextCompat.getColor(itemView.context, colors[position % 2])
            val textColor = ContextCompat.getColor(itemView.context, textColors[position % 2])

            binding.run {
                itemView.setBackgroundColor(backgroundColor)
                tvTitle.text = task.title
                tvTitle.setTextColor(textColor)
                tvDesc.text = task.desc
                tvDesc.setTextColor(textColor)
                itemView.setOnLongClickListener {
                    onLongCLick(task)
                    false
                }
                itemView.setOnClickListener {
                    onClick(task)
                }
            }
        }
    }
}