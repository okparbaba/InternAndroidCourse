package com.ynl.spinnerandhorizontalscrollview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
        tvMsg.text = "Nothing Selected"
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        tvMsg.text = "Selected Language : ${lan[position]}"
    }

    val lan = arrayOf("English","French","Spanish","Thailand","Malaysia","Cambodia","Hindi","Chinese"
    ,"Japanese","Korean")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner_sample.onItemSelectedListener = this
        val spinnerAdapter = ArrayAdapter(this,R.layout.spinner_row,R.id.tvCust,lan)
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_row)
        spinner_sample.adapter = spinnerAdapter
    }
}