package com.grinta.agecalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.util.*
import android.widget.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val year: TextView = findViewById(R.id.yearID)
        val yearValue: EditText = findViewById(R.id.yearVal)
        val month: TextView = findViewById(R.id.monthID)
        val monthValue: Spinner = findViewById(R.id.monthVal)
        val day: TextView = findViewById(R.id.dayID)
        val dayValue: Spinner = findViewById(R.id.dayVal)
        val AgeY: TextView = findViewById(R.id.Age_Years)
        val AgeM: TextView = findViewById(R.id.Age_Months)
        val AgeD: TextView = findViewById(R.id.Age_Days)
        val start: Button = findViewById(R.id.startID)

        val months = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
        var month_flag: Int = 1
        val days = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                           21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31)
        var day_flag: Int = 1
        var function_flag: Int = 0

        monthValue.adapter = ArrayAdapter<Int>(this, android.R.layout.simple_list_item_1, months)
        dayValue.adapter = ArrayAdapter<Int>(this, android.R.layout.simple_list_item_1, days)

        monthValue.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                month_flag = months.get(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        dayValue.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                day_flag = days.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        start.setOnClickListener { View ->
            var y: Int = yearValue.text.toString().toInt()
            var m: Int = monthValue.selectedItem.toString().toInt()
            var d: Int = dayValue.selectedItem.toString().toInt()

            AgeY.text = age_calculator(y, m, d, function_flag).toString() + " Years"
            function_flag += 1
            AgeM.text = age_calculator(y, m, d, function_flag).toString() + " Months"
            function_flag += 1
            AgeD.text = age_calculator(y, m, d, function_flag).toString() + " Days"
            function_flag = 0
        }
    }
}

public fun age_calculator(year: Int, month: Int, day: Int, flag_val: Int):Int {
    var calender: Calendar = Calendar.getInstance() //Get an instance of Calender
    var current_year: Int = calender.get(Calendar.YEAR) //Get current year
    var current_month: Int = calender.get(Calendar.MONTH) //Get current month
    var current_day: Int = calender.get(Calendar.DAY_OF_MONTH) //Get current day of month

    current_month += 1 //Add one to current month since Calender.MONTH starts counting months form zero.

    if (day > current_day) { //check if day of birth is greater than current day
        current_day += 30
        current_month -= 1
    }

    if (month > current_month) { //check if month of birth is greater than current month
        current_month += 12
        current_year -= 1
    }

    if (flag_val == 0) {
        return current_year - year
    }

    else if (flag_val == 1) {
        return current_month - month
    }

    else {
        return current_day - day
    }
}



