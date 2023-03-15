package com.example.math


import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.math.databinding.ActivityEnterPinBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EnterPinActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityEnterPinBinding
    private var listPin = mutableListOf<String>()
    private var oldListPin = mutableListOf<String>()
    private lateinit var getPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterPinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = object : TypeToken<List<String>>() {}.type
        val gson = Gson()
        getPreferences = getSharedPreferences("pin_code", MODE_PRIVATE)
        val str = getPreferences.getString("Users", "")
        if (str == "") {
        } else {
            oldListPin = gson.fromJson(str, type)
        }

        loadNumbers()

    }

    private fun loadNumbers() {
        binding.one.setOnClickListener(this)
        binding.two.setOnClickListener(this)
        binding.three.setOnClickListener(this)
        binding.four.setOnClickListener(this)
        binding.five.setOnClickListener(this)
        binding.six.setOnClickListener(this)
        binding.seven.setOnClickListener(this)
        binding.eight.setOnClickListener(this)
        binding.nine.setOnClickListener(this)
        binding.zero.setOnClickListener(this)
    }

    override fun onClick(it: View?) {
        val animationOut = AnimationUtils.loadAnimation(this, R.anim.shake)
        if (listPin.size < 4) {
            listPin.add(it!!.tag.toString())
            when (listPin.size) {
                1 -> binding.pin1.setBackgroundResource(R.drawable.pin_code_oval_checked)
                2 -> binding.pin2.setBackgroundResource(R.drawable.pin_code_oval_checked)
                3 -> binding.pin3.setBackgroundResource(R.drawable.pin_code_oval_checked)
                4 -> binding.pin4.setBackgroundResource(R.drawable.pin_code_oval_checked)
            }
        }
        if (listPin.size == 4) {
            if (oldListPin == listPin) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                binding.listPin.startAnimation(animationOut)
                Toast.makeText(
                    this,
                    "Wrong pin-code",
                    Toast.LENGTH_SHORT
                ).show()
                listPin.clear()
                binding.pin1.setBackgroundResource(R.drawable.pin_code_oval)
                binding.pin2.setBackgroundResource(R.drawable.pin_code_oval)
                binding.pin3.setBackgroundResource(R.drawable.pin_code_oval)
                binding.pin4.setBackgroundResource(R.drawable.pin_code_oval)
            }
        }
    }
}