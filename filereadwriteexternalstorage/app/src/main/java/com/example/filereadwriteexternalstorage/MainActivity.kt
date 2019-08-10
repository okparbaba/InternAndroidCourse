package com.example.filereadwriteexternalstorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnwrite->{
                val fileName = fname.text.toString()
                val fileContent = ftext.text.toString()
                writeData(fileName,fileContent)
            }
            R.id.btnread->readData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnwrite.setOnClickListener(this)
        btnread.setOnClickListener(this)
    }
    private fun writeData(fName:String,fContent:String){
        val fop = FileOperations()
        fop.write(fName, fContent)
        if(fop.write(fName, fContent)!!){
            Toast.makeText(applicationContext, "$fName.txt created", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(applicationContext, "I/O error", Toast.LENGTH_SHORT).show();

        }
    }
    private fun readData(){
        val readfilename = fnameread.text.toString()
        val fop = FileOperations()
        val text = fop.read(readfilename)
        if (text != null) {
            filecon.text = text
        } else {
            Toast.makeText(applicationContext, "File not Found", Toast.LENGTH_SHORT).show()
            filecon.text = null
        }
    }
}
