package com.example.taskmanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.home.adapter.TaskAdapter
import com.example.taskmanager.ui.task.TaskFragment.Companion.RESULT_KEY
import com.example.taskmanager.ui.task.TaskFragment.Companion.TASK_KEY
import com.example.taskmanager.utils.showToast

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val adapter = TaskAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        setFragmentResultListener(RESULT_KEY){_, bundle ->
        val data = bundle.getSerializable(TASK_KEY) as Task
        adapter.addTask(data)
        }

        binding.fab.setOnClickListener{
            findNavController().navigate(R.id.taskFragment)
        }
        showToast("Hello!")
    }


}