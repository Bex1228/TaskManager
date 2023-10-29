package com.example.taskmanager.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val adapter = TaskAdapter(this::onLongClick, this::onClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        setData()
        binding.fab.setOnClickListener { findNavController().navigate(R.id.taskFragment) }
    }

    private fun onLongClick(task: Task) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder
            .setMessage("Вы  действительно хотите удалить?")
            .setTitle("Удаление")
            .setPositiveButton("Да") { dialog, which ->
                App.db.taskDao().delete(task)
                setData()
            }
            .setNegativeButton("Нет") { dialog, which ->
                dialog.dismiss()
            }
        builder.create().show()
    }

    private fun onClick(task: Task) {
        findNavController().navigate(R.id.taskFragment, bundleOf("TASK_KEY" to task))
    }

    private fun setData() {
        val data = App.db.taskDao().getAll()
        adapter.addTasks(data)
    }
}