package com.example.week7daily4homework

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.*

class MainActivity : AppCompatActivity() {
    private lateinit var mySqlDatabaseHelper: MySqlDatabaseHelper
    private lateinit var name: String
    private lateinit var major: String
    private lateinit var minor: String
    private lateinit var dob: String
    private lateinit var gpa: String
    private lateinit var city: String
    private lateinit var state: String
    private lateinit var ssn: String
    private lateinit var student : Students

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mySqlDatabaseHelper = MySqlDatabaseHelper(this)
    }

    fun onClick(view : View){
        name = etName.text.toString()
        major = etMajor.text.toString()
        minor = etMinor.text.toString()
        dob = etDob.text.toString()
        gpa = etGpa.text.toString()
        city = etHomeCity.text.toString()
        state = etHomeState.text.toString()
        ssn = etSsn.text.toString()

        when{
            view.id == R.id.btnNext -> {
                val intent = Intent(this,DisplayActivity::class.java)
                startActivity(intent)
            }
            view.id == R.id.btnInsert -> {
                if (name != null && major != null && minor != null && gpa != null && dob != null && city != null && state != null && ssn != null &&
                    !name.isEmpty() && !major.isEmpty() && !minor.isEmpty() && !gpa.isEmpty() && !dob.isEmpty() && !city.isEmpty() && !state.isEmpty() && !ssn.isEmpty()
                ){
                student = Students(name, major, minor, gpa, dob, city, state, ssn)
                mySqlDatabaseHelper.insertStudent(student)
                Toast.makeText(this, "Student has been added.", Toast.LENGTH_SHORT).show()
                }
            }
            view.id == R.id.btnUpdate -> {
                if(student != null){
                    student.name = etName.text.toString()
                    student.major = etMajor.text.toString()
                    student.minor = etMinor.text.toString()
                    student.dob = etDob.text.toString()
                    student.gpa = etGpa.text.toString()
                    student.homeCity = etHomeCity.text.toString()
                    student.homeState = etHomeState.text.toString()
                    student.ssn = etSsn.text.toString()
                }
            }
            view.id == R.id.btnDelete -> {
                if(ssn != null && !ssn.isEmpty()) {
                    mySqlDatabaseHelper.deleteStudent(ssn)
                    Toast.makeText(this, "Delete operation carried out", Toast.LENGTH_SHORT).show()
                }
            }
            view.id == R.id.btnGet -> {
                if(ssn != null && !ssn.isEmpty()){
                    student = mySqlDatabaseHelper.getStudent(ssn)!!
                    etName.setText(student.name)
                    etMajor.setText(student.major)
                    etMinor.setText(student.minor)
                    etGpa.setText(student.gpa)
                    etDob.setText(student.dob)
                    etHomeCity.setText(student.homeCity)
                    etHomeState.setText(student.homeState)
                    etSsn.setText(student.ssn)
                }
            }
        }
    }
}
