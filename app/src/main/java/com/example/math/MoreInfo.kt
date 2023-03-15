package com.example.math

import com.example.math.databinding.ActivityMoreInfoBinding


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import coil.load
class MoreInfo : AppCompatActivity() {
    private lateinit var checked:CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        checked= findViewById(R.id.checkBox2)
        var binding = ActivityMoreInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var user: Themes = intent.getSerializableExtra("user") as Themes
        binding.img.load(user.img) {
            placeholder(R.drawable.ic_launcher_background)
            error(androidx.appcompat.R.drawable.abc_btn_radio_material_anim)
        }
        binding.name.text = user.name
        var stat = user.status
        if (stat){
            binding.status.text = "O'qilgan"
        }
        else{
            binding.status.text = "O'qilmagan"
        }
        binding.info.text = user.info
        binding.info.text = user.info

    }
}