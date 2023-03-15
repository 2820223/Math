package com.example.math


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.example.math.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var continents: MutableMap<String, MutableList<Themes>>
    private lateinit var continentsTittle: ArrayList<String>
    private lateinit var continentsValues: ArrayList<Themes>
    private var isFav = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()


        val adapter = parentAdapter(this, continents, continentsTittle)
        binding.expandable.setAdapter(adapter)
        binding.expandable.setOnGroupClickListener { expandableListView, view, i, l ->
            Log.d("TAG", "Group name: " + continentsTittle.get(i))
            false
        }
        binding.expandable.setOnChildClickListener { _, _, i, i2, _ ->
            val country = continents.get(continentsTittle.get(i))!!.get(i2)
            val intent = Intent(this, MoreInfo::class.java)
            intent.putExtra("user", country)
            startActivity(intent)
            true
        }
        binding.expandable.setOnGroupExpandListener {

        }
        binding.expandable.setOnGroupCollapseListener {

        }
    }

    private fun loadData() {
        continents = mutableMapOf()
        val asia = arrayListOf<Themes>()
        asia.add(
            Themes("Natural sonlar ustida amallar",R.string.first_first.toString(),
                false,R.drawable.first_first)

        )

        asia.add(
            Themes("Natural sonlar ustida amallar",R.string.first_first.toString(),
                false,R.drawable.first_first)

        )

        continents.put("Natural sonlar", asia)


        val africa = arrayListOf<Themes>()
        africa.add(
            Themes("Oddiy kasrlar",R.string.second_one.toString(),
                false,R.drawable.first_first)

        )

        continents.put("Ratsional sonlar va irratsional sonlar", africa)


//        val europe = arrayListOf<Themes>()
//        europe.add(
//            Themes("Natural sonlar ustida amallar",R.string.first_first.toString(),
//                false,R.drawable.first_first)
//
//        )
//
//        continents.put("Europe", europe)
//
//
//        val nAmerica = arrayListOf<Themes>()
//        nAmerica.add(
//            Themes("Natural sonlar ustida amallar",R.string.first_first.toString(),
//                false,R.drawable.first_first)
//
//        )
//
//        continents.put("North America", nAmerica)
//
//
//        val sAmerica = arrayListOf<Themes>()
//        sAmerica.add(
//               Themes("Natural sonlar ustida amallar",R.string.first_first.toString(),
//            false,R.drawable.first_first)
//
//        )
//
//        sAmerica.add(
//            Themes("Natural sonlar ustida amallar",R.string.first_first.toString(),
//                false,R.drawable.first_first)
//
//        )
//        continents.put("South America", sAmerica)
//
//
//        val australia = arrayListOf<Themes>()
//        australia.add(
//            Themes("Natural sonlar ustida amallar",R.string.first_first.toString(),
//                false,R.drawable.first_first)
//
//        )
//        continents.put("Australia", australia)
        continentsTittle = ArrayList(continents.keys)
    }
}