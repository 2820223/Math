package com.example.math

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.math.databinding.ActivityLoginBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var userList = mutableListOf<User>()
        val type = object : TypeToken<List<User>>() {}.type
        val gson = Gson()
        val getPreferences = getSharedPreferences("login", MODE_PRIVATE)
        val str = getPreferences.getString("Users", "")
        if (str == "") {
        } else {
            userList = gson.fromJson(str, type)
        }
        binding.signIn.setOnClickListener {
            if (binding.email.text!!.isEmpty()) {
                Toast.makeText(applicationContext, "Enter email", Toast.LENGTH_LONG).show()
            } else {
                if (binding.password.text!!.isEmpty()) {
                    Toast.makeText(applicationContext, "Enter password", Toast.LENGTH_LONG).show()
                } else {
                    var a = 0
                    for (i in 0 until userList.size) {
                        if (userList[i].email == binding.email.text.toString() && userList[i].password == binding.password.text.toString()) {
                            a = 1
                        }
                    }
                    if (a == 1) {
                        val intent = Intent(this, PinCodeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Wrong email or password",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        binding.signUp.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        finish()
    }
}