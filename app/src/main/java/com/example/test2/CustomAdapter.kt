package com.example.test2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView

class CustomAdapter(private val context: Context, private val items: List<Pair<String, String>>) :
    ArrayAdapter<Pair<String, String>>(context, R.layout.custom_list_item, items) {

    private var checkedPositions = mutableListOf<Int>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val viewHolder: ViewHolder

        if (convertView == null) {
            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.custom_list_item, parent, false)
            viewHolder.textViewItem = convertView.findViewById(R.id.textViewItem)
            viewHolder.checkBox = convertView.findViewById(R.id.checkBoxItem)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }

        val currentItem = getItem(position)
        viewHolder.textViewItem?.text = "${currentItem?.first} : ${currentItem?.second}"

        viewHolder.checkBox?.setOnCheckedChangeListener(null)
        viewHolder.checkBox?.isChecked = checkedPositions.contains(position)

        viewHolder.checkBox?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (!checkedPositions.contains(position)) {
                    checkedPositions.add(position)
                }
            } else {
                checkedPositions.remove(position)
            }
        }

        return convertView!!
    }

    private class ViewHolder {
        var checkBox: CheckBox? = null
        var textViewItem: TextView? = null
    }

    fun getCheckedItems(): List<Pair<String, String>> {
        val checkedItems = mutableListOf<Pair<String, String>>()
        for (position in checkedPositions) {
            checkedItems.add(items[position])
        }
        return checkedItems
    }
}
