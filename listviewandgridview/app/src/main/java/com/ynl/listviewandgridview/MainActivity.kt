package com.ynl.listviewandgridview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ynl.listviewandgridview.listview.Employee
import com.ynl.listviewandgridview.listview.MyListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter: MyListAdapter?
        val empList : ArrayList<Employee> = generateEmployeeData()
        adapter = MyListAdapter(this, empList)

        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, i, _ ->
            Toast.makeText(this, "Selected Emp is = "+ empList[i].emp_name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateEmployeeData(): ArrayList<Employee> {
        val result = ArrayList<Employee>()

        var emp = Employee()
        emp.emp_id = 1
        emp.emp_name = "John Clington"
        emp.emp_designation = "CEO"
        emp.emp_salary = "USD 21000$"
        emp.emp_photo = R.drawable.c1
        result.add(emp)

        emp = Employee()
        emp.emp_id = 2
        emp.emp_name = "Grey Jonathan"
        emp.emp_designation = "Senior Developer"
        emp.emp_salary = "USD 11000$"
        emp.emp_photo = R.drawable.c2
        result.add(emp)

        emp = Employee()
        emp.emp_id = 3
        emp.emp_name = "Barbara Young"
        emp.emp_designation = "Senior Developer"
        emp.emp_salary = "USD 10000$"
        emp.emp_photo = R.drawable.c3
        result.add(emp)

        emp = Employee()
        emp.emp_id = 3
        emp.emp_name = "Barbara Young"
        emp.emp_designation = "Senior Developer"
        emp.emp_salary = "USD 10000$"
        emp.emp_photo = R.drawable.c4
        result.add(emp)


        emp = Employee()
        emp.emp_id = 3
        emp.emp_name = "Barbara Young"
        emp.emp_designation = "Senior Developer"
        emp.emp_salary = "USD 10000$"
        emp.emp_photo = R.drawable.c5
        result.add(emp)


        emp = Employee()
        emp.emp_id = 3
        emp.emp_name = "Barbara Young"
        emp.emp_designation = "Senior Developer"
        emp.emp_salary = "USD 10000$"
        emp.emp_photo = R.drawable.c6
        result.add(emp)


        emp = Employee()
        emp.emp_id = 3
        emp.emp_name = "Barbara Young"
        emp.emp_designation = "Senior Developer"
        emp.emp_salary = "USD 10000$"
        emp.emp_photo = R.drawable.c7
        result.add(emp)

        emp = Employee()
        emp.emp_id = 3
        emp.emp_name = "Barbara Young"
        emp.emp_designation = "Senior Developer"
        emp.emp_salary = "USD 10000$"
        emp.emp_photo = R.drawable.c8
        result.add(emp)
        return result
    }
}
