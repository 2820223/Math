package com.example.math

import com.example.math.databinding.ActivityPinCodeBinding

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PinCodeActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityPinCodeBinding
    private var listPin = mutableListOf<String>()
    private var index = 0
    private lateinit var getPreferences: SharedPreferences
    private lateinit var edit: SharedPreferences.Editor
    private lateinit var getPreferencesState: SharedPreferences
    private lateinit var editState: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPinCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gson = Gson()

        getPreferences = getSharedPreferences("pin_code", MODE_PRIVATE)
        getPreferencesState = getSharedPreferences("state", MODE_PRIVATE)
        edit = getPreferences.edit()
        editState = getPreferencesState.edit()

        loadNumbers()

        binding.save.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val s = gson.toJson(listPin)
            edit.putString("Users", s).apply()
            editState.putBoolean("State", true).apply()
            startActivity(intent)
        }

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
        if (listPin.size < 4) {
            listPin.add(it!!.tag.toString())
            index++
            when (index) {
                1 -> binding.pin1.setBackgroundResource(R.drawable.pin_code_oval_checked)
                2 -> binding.pin2.setBackgroundResource(R.drawable.pin_code_oval_checked)
                3 -> binding.pin3.setBackgroundResource(R.drawable.pin_code_oval_checked)
                4 -> binding.pin4.setBackgroundResource(R.drawable.pin_code_oval_checked)
            }
        }
        if (listPin.size == 4) {
            binding.save.visibility = View.VISIBLE
        }
    }
}