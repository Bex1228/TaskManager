package com.example.taskmanager.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanager.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var binding: FragmentDashboardBinding? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentDashboardBinding.inflate(inflater, container, false)
       return binding!!.root

    }


}