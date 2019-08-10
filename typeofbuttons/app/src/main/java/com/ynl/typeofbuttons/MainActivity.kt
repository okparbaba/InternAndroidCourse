package com.ynl.typeofbuttons

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text)


        //Checked Box
//        button.setOnClickListener {
//            val result = StringBuffer()
//            result.append("Thank : ").append(checkBox1.isChecked)
//            result.append("\nThank : ").append(checkBox2.isChecked)
//            Toast.makeText(this@MainActivity,result.toString(),Toast.LENGTH_LONG).show()
//        }

        //RadioGroup
//        button.setOnClickListener {
//            val selectedId = radioGroup.checkedRadioButtonId
//            val radioButton = findViewById<RadioButton>(selectedId)
//            Toast.makeText(this@MainActivity,radioButton.text,Toast.LENGTH_LONG).show()
//        }

        //ToggleButton
//        button2.setOnClickListener {
//            val result = StringBuffer()
//            result.append("You Click first Button to ").append(toggleButton.text)
//            result.append("\nYou Click first Button to ").append(toggleButton2.text)
//            Toast.makeText(this@MainActivity,result.toString(),Toast.LENGTH_SHORT).show()
//        }



    }
}
