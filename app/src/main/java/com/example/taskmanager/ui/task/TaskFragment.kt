package com.example.taskmanager.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.model.Task
import kotlin.concurrent.timerTask


class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        initListeners()
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            if (task == null && binding.etTitle.text.isNotEmpty()) {
                saveTask()
            } else if (binding.etTitle.text.isNotEmpty()) {
                updateTask()
            }
            findNavController().navigateUp()
        }
    }

    private fun updateTask() {
        val data = task?.copy(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        if (data != null && data.title?.isNotEmpty() == true) {
            App.db.taskDao().update(data)
        }
    }

    private fun saveTask() {
        val data = Task(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        App.db.taskDao().insert(data)
    }

    private fun getData() {
        task = arguments?.getSerializable(TASK_KEY) as? Task
        if (task != null) {
            binding.etTitle.setText(task?.title)
            binding.etDesc.setText(task?.desc)
            binding.btnSave.text = getString(R.string.udpate)
        }
    }
    companion object{
        const val TASK_KEY = "TASK_KEY"
    }

}
