package com.ynl.listviewandgridview.listview

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ynl.listviewandgridview.R

class MyListAdapter (private var activity: Activity, private var items: ArrayList<Employee>) :  BaseAdapter(){
    private class ViewHolder(row: View?) {
        var lblName: TextView? = null
        var lblDesignation: TextView? = null
        var imgEmp: ImageView? = null
        var lblSalary: TextView? = null
        init {
            this.lblName = row?.findViewById(R.id.lbl_name)
            this.lblDesignation = row?.findViewById(R.id.lbl_designation)
            this.imgEmp = row?.findViewById(R.id.img_emp)
            this.lblSalary = row?.findViewById(R.id.lbl_salary)
        }
    }
    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.single_emp, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val emp = items[position]
        viewHolder.lblName?.text = emp.emp_name
        viewHolder.lblDesignation?.text = emp.emp_designation
        viewHolder.lblSalary?.text = emp.emp_salary
        viewHolder.imgEmp?.setImageResource(emp.emp_photo!!)
        return view
    }
    override fun getItem(i: Int): Employee {
        return items[i]
    }
    override fun getItemId(i: Int): Long {
        return i.toLong()
    }
    override fun getCount(): Int {
        return items.size
    }
}