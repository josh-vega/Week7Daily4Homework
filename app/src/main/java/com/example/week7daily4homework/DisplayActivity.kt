package com.example.week7daily4homework

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_display.*
import java.util.ArrayList

class DisplayActivity : AppCompatActivity() {
    private lateinit var mySqlDatabaseHelper: MySqlDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        rvMainRecyclerView.layoutManager = LinearLayoutManager(this)
        rvMainRecyclerView.adapter = RecyclerViewAdapter(listOfStudents())
    }

    fun onClick(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun listOfStudents(): ArrayList<Students> {
        return mySqlDatabaseHelper.allStudents!!
    }

}
