package com.ynl.listviewandgridview.girdview
import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.GridView
import com.ynl.listviewandgridview.R

@SuppressLint("Registered")
class GirdViewAcitvity : AppCompatActivity() {
    private val itemList: Array<String>
        get() = arrayOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10", "Item 11", "Item 12", "Item 13", "Item 14", "Item 15", "Item 16", "Item 17", "Item 18", "Item 19", "Item 20", "Item 21", "Item 22")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gridview)
        val gridview = findViewById<GridView>(R.id.gridview)
        val adapter = ImageListAdapter(this, R.layout.gird_list_item, itemList)
        gridview.adapter = adapter
    }
}
