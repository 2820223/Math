package com.example.math


import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.math.databinding.ActivityOfferingPermissionBinding
import java.util.*

class OfferingPermission : AppCompatActivity() {
    private lateinit var binding: ActivityOfferingPermissionBinding
    private var list = arrayListOf<String>()
    private lateinit var getPreferences: SharedPreferences
    private lateinit var edit: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOfferingPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPreferences = getSharedPreferences("lang", MODE_PRIVATE)
        edit = getPreferences.edit()



        list.add("En")
        list.add("Ru")
        list.add("Uz")


        binding.checkBox.setOnCheckedChangeListener { compoundButton, b ->
            binding.nextBtn.isEnabled = true
        }

        binding.nextBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.textView2.text = resources.getString(R.string.text_permission)
        binding.textView.text = resources.getString(R.string.permission)
        binding.checkBox.text = resources.getString(R.string.agreement)
        binding.nextBtn.text = resources.getString(R.string.next)

        binding.spinner.adapter = adapter
        binding.spinner.setSelection(0)
        binding.spinner.onItemSelectedListener = (object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                p1: View?,
                position: Int,
                p3: Long
            ) {
                val lang = parent!!.getItemAtPosition(position).toString()
                var languageToLoad: String? = null

                when (lang) {
                    "En" -> {
                        languageToLoad = "en"
                        edit.putString("lang", "En").apply()
                    }
                    "Ru" -> {
                        languageToLoad = "ru"
                        edit.putString("lang", "Ru").apply()
                    }
                    "Uz" -> {
                        languageToLoad = "uz"
                        edit.putString("lang", "Uz").apply()

                    }
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
                binding.textView2.text = resources.getString(R.string.text_permission)
                binding.textView.text = resources.getString(R.string.permission)
                binding.checkBox.text = resources.getString(R.string.agreement)
                binding.nextBtn.text = resources.getString(R.string.next)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        })
    }
}