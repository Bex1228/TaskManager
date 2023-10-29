package com.example.taskmanager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.databinding.ItemOnboardingBinding
import com.example.taskmanager.model.OnBoarding
import com.example.taskmanager.utils.loadImage

class OnBoardingAdapter(
    private val list: ArrayList<OnBoarding>,
    private val onClick: () -> Unit
) : Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from
                    (parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoarding: OnBoarding) {
            binding.run {
                tvTitle.text = onBoarding.title
                tvDesc.text = onBoarding.desc
                ivBoard.loadImage(onBoarding.image.toString())
                tvSkip.isVisible = adapterPosition != list.lastIndex
                btnStart.isVisible = adapterPosition == list.lastIndex

                btnStart.setOnClickListener { onClick() }
                tvSkip.setOnClickListener { onClick() }
            }
        }
    }
}
