package com.example.taskmanager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.databinding.ItemOnboardingBinding
import com.example.taskmanager.model.OnBoarding
import com.example.taskmanager.utils.loadImage

class OnBoardingAdapter(private val onClick: ()-> Unit) : Adapter<OnBoardingAdapter.OnBoardingViewHolder>(){

    private val list = arrayListOf<OnBoarding>(
        OnBoarding("Добро пожаловать в мир рыбалки",
            "Откройте для себя увлекательный мир рыбалки, исследуйте" +
                    " потаенные места и ловите лосось, как настоящий профессионал.",
             "https://gas-kvas.com/uploads/posts/2023-01/1674058510_gas-kvas-com-p-risunok-na-temu-ribalka-2.png"),
        OnBoarding("Профессиональное снаряжение",
            "Наше приложение предоставляет доступ к лучшему рыболовному снаряжению," +
                    " чтобы ваши рыболовные походы были более успешными и удовольствие.",
            "https://images.prom.ua/1461467566_w640_h640_saharnaya-kartinka-rybalka.jpg"),
        OnBoarding("Создайте воспоминания",
            "Поймайте рыбу в компании друзей, создайте незабываемые моменты" +
                    " и делись историями с нашим рыбацким сообществом.",
            "https://www.factroom.ru/wp-content/uploads/2018/01/tofish6fdsr5-1.png")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
    return OnBoardingViewHolder(
        ItemOnboardingBinding.
        inflate(LayoutInflater.from
            (parent.context)
            ,parent, false ))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
    holder.bind(list[position])
    }

    inner class OnBoardingViewHolder(private val binding : ItemOnboardingBinding): ViewHolder(binding.root){


        fun bind(onBoarding: OnBoarding){
            binding.run {
                tvTitle.text = onBoarding.title
                tvDesc.text = onBoarding.desc
                ivBoard.loadImage(onBoarding.image.toString())
                tvSkip.isVisible = adapterPosition != list.lastIndex
                btnStart.isVisible = adapterPosition == list.lastIndex

                btnStart.setOnClickListener {  onClick() }
                tvSkip.setOnClickListener { onClick() }


            }
        }
    }

}
