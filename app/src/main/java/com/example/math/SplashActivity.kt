package com.example.math

import com.example.math.databinding.ActivitySplashBinding


import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputBinding
import java.util.*
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var getSharedPreferences: SharedPreferences
    private lateinit var getSharedPreferences2: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSharedPreferences = getSharedPreferences("state", MODE_PRIVATE)
        val state = getSharedPreferences.getBoolean("State", false)
        getSharedPreferences2 = getSharedPreferences("lang", MODE_PRIVATE)
        val lang = getSharedPreferences2.getString("lang", "En")

        val animationOut = AnimationUtils.loadAnimation(this, R.anim.anim_out)

        var languageToLoad: String? = null

        if (lang == "En") {
            languageToLoad = "en"
        } else if (lang == "Ru") {
            languageToLoad = "ru"
        } else if (lang == "Uz") {
            languageToLoad = "uz"
        }

        if (languageToLoad != null) {
            val locale = Locale(languageToLoad)
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            baseContext.resources.updateConfiguration(
                config,
                baseContext.resources.displayMetrics
            )
        }

        binding.text.text = resources.getString(R.string.splash_text)
        binding.startBtn.text = resources.getString(R.string.get_started)


        binding.text.startAnimation(animationOut)
        binding.food.startAnimation(animationOut)

        binding.startBtn.setOnClickListener {
            if (!state) {
                val intent = Intent(this, OfferingPermission::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, EnterPinActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}