package com.example.math


import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.BaseExpandableListAdapter
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import coil.load
import coil.transform.CircleCropTransformation
import com.example.math.databinding.ChildLayoutBinding
import com.example.math.databinding.ParentItemBinding
import java.util.*
import kotlin.collections.ArrayList

class parentAdapter(
    var context: Context,
    var continents: MutableMap<String, MutableList<Themes>>,
    var continentsTitle: ArrayList<String>
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return continentsTitle.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return continents.get(continentsTitle.get(groupPosition))!!.size
    }

    override fun getGroup(groupPosition: Int): String {
        return continentsTitle.get(groupPosition)
    }

    override fun getChild(groupPosition: Int, childPosition: Int): String {
        return continents.get(continentsTitle.get(groupPosition))!!.get(childPosition).toString()
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        position: Int, p1: Boolean,
        convertView: View?, parent: ViewGroup?
    ): View {

        val binding: ParentItemBinding
        if (convertView == null) {
            binding =
                ParentItemBinding.inflate(LayoutInflater.from(parent!!.context), parent, false)
        } else {
            binding = ParentItemBinding.bind(convertView)
        }
//        if (p1) {
//            binding.arrow.setImageResource(android.R.drawable.arrow_up_float)
//            binding.search.setOnClickListener {
//                binding.search.isFocusableInTouchMode = true
//                binding.search.isFocusable = true
//            }
//        } else {
//            binding.arrow.setImageResource(android.R.drawable.arrow_down_float)
//            binding.search.isFocusableInTouchMode = false
//            binding.search.isFocusable = false
//        }
//        binding.continentsTittle.text = continentsTitle.get(position)
//        binding.search.addTextChangedListener {
//            val filter = mutableListOf<Themes>()
//            val list = continents.get(continentsTitle.get(position))
//            if (it != null) {
//                for (c in list!!) {
//                    if (c.name.lowercase().contains(it.toString().lowercase())) {
//                        filter.add(c)
//                    }
//                }
//                continents.get(continentsTitle.get(position))!!.clear()
//                continents.get(continentsTitle.get(position))!!.addAll(filter)
//                notifyDataSetChanged()
//            }
//        }
//
//        binding.search.addTextChangedListener {
//            filterData(it.toString())
//        }
//

        return binding.root
    }

    override fun getChildView(
        parentPostion: Int,
        childPosition: Int,
        p2: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val binding: ChildLayoutBinding
        if (convertView == null) {
            binding =
                ChildLayoutBinding.inflate(LayoutInflater.from(parent!!.context), parent, false)
        } else {
            binding = ChildLayoutBinding.bind(convertView)
        }

        val country = continents.get(continentsTitle.get(parentPostion))!!.get(childPosition)
        binding.img.load(country.img) {
            placeholder(R.drawable.ic_launcher_background)
            error(androidx.appcompat.R.drawable.abc_btn_radio_material_anim)
            transformations(CircleCropTransformation())
        }
        binding.delete.setOnClickListener {
            continents.get(continentsTitle.get(parentPostion))!!.remove(country)
            notifyDataSetChanged()
        }
        binding.edit.setOnClickListener {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.custom_dialog)
            val name = dialog.findViewById<TextView>(R.id.name)
            name.text = country.name
            val population = dialog.findViewById<TextView>(R.id.population)
            population.text = country.status.toString()
            val change = dialog.findViewById(R.id.change) as Button
            change.setOnClickListener {
                country.name = name.text.toString()
                country.name = population.text.toString()
                notifyDataSetChanged()
                dialog.dismiss()
            }
            dialog.show()
        }
        binding.name.text = country.name
        binding.population.text = country.status.toString()
        binding.area.text = country.name

        return binding.root
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
    var listDataHeader: ArrayList<String> = continentsTitle
    var backupHeader: ArrayList<String> = continentsTitle
    private val listHashMap
            : MutableMap<String, MutableList<Themes>> = continents
    private var backupHashMap: MutableMap<String, MutableList<Themes>> = continents

    private fun filterData(query: String) {
        var query = query
        query = query.lowercase(Locale.getDefault())
        listDataHeader.clear()
        listHashMap.clear()
        if (query.isEmpty()) {
            listDataHeader.addAll(backupHeader)
            listHashMap.putAll(backupHashMap)
        } else
        {
            for (header in backupHeader) {
                val tmpList = mutableListOf<Themes>()
                for (item in backupHashMap[header]!!) {
                    if (item.name.lowercase(Locale.getDefault()).contains(query)) tmpList.add(item)
                }
                if (tmpList.size > 0) {
                    listDataHeader.add(header)
                    listHashMap[header] = tmpList
                }
            }
        }
        notifyDataSetChanged()
    }

}
