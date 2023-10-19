package com.example.taskmanager.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentOnBoardingBinding
import com.example.taskmanager.model.OnBoarding
import com.example.taskmanager.ui.onboarding.adapter.OnBoardingAdapter


class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding

    private var list = arrayListOf<OnBoarding>(
        OnBoarding(
            "Добро пожаловать в мир рыбалки",
            "Откройте для себя увлекательный мир рыбалки, исследуйте" +
                    " потаенные места и ловите лосось, как настоящий профессионал.",
            "https://gas-kvas.com/uploads/posts/2023-01/1674058510_gas-kvas-com-p-risunok-na-temu-ribalka-2.png"
        ),
        OnBoarding(
            "Профессиональное снаряжение",
            "Наше приложение предоставляет доступ к лучшему рыболовному снаряжению," +
                    " чтобы ваши рыболовные походы были более успешными и удовольствие.",
            "https://images.prom.ua/1461467566_w640_h640_saharnaya-kartinka-rybalka.jpg"
        ),
        OnBoarding(
            "Создайте воспоминания",
            "Поймайте рыбу в компании друзей, создайте незабываемые моменты" +
                    " и делись историями с нашим рыбацким сообществом.",
            "https://www.factroom.ru/wp-content/uploads/2018/01/tofish6fdsr5-1.png"
        )
    )


    private val adapter = OnBoardingAdapter(list, this::onCLick)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewpager.adapter = adapter

        val indicatorSize = resources.getDimensionPixelSize(R.dimen.indicator_size)
        val indicatorMargin = resources.getDimensionPixelSize(R.dimen.indicator_margin)



        for (i in 0 until list.size) {
            val indicator = View(requireContext())
            indicator.setBackgroundResource(R.drawable.indicators)
            val layoutParams = LinearLayout.LayoutParams(indicatorSize, indicatorSize)
            layoutParams.setMargins(indicatorMargin, 0, indicatorMargin, 0)
            indicator.layoutParams = layoutParams
            binding.indicatorsLayout.addView(indicator)
        }
        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicators(position)
            }
        })

    }
    private fun updateIndicators(position: Int) {
        val indicatorsLayout = binding.indicatorsLayout

        for (i in 0 until indicatorsLayout.childCount) {
            val indicator = indicatorsLayout.getChildAt(i) as View


            if (i == position) {
                indicator.setBackgroundResource(R.drawable.on_boarding_indicator_active)
            } else {
                indicator.setBackgroundResource(R.drawable.on_boarding_indicator_inactive)
            }
        }
    }
    private fun onCLick() {
        findNavController().navigateUp()
    }
}