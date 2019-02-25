package com.example.week7daily4homework

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.week7daily4homework.DatabaseConstants.Companion.DATABASE_NAME
import com.example.week7daily4homework.DatabaseConstants.Companion.DATABASE_VERSION
import com.example.week7daily4homework.DatabaseConstants.Companion.FIELD_CITY
import com.example.week7daily4homework.DatabaseConstants.Companion.FIELD_DOB
import com.example.week7daily4homework.DatabaseConstants.Companion.FIELD_GPA
import com.example.week7daily4homework.DatabaseConstants.Companion.FIELD_MAJOR
import com.example.week7daily4homework.DatabaseConstants.Companion.FIELD_MINOR
import com.example.week7daily4homework.DatabaseConstants.Companion.FIELD_NAME
import com.example.week7daily4homework.DatabaseConstants.Companion.FIELD_SSN
import com.example.week7daily4homework.DatabaseConstants.Companion.FIELD_STATE
import com.example.week7daily4homework.DatabaseConstants.Companion.TABLE_NAME
import java.util.ArrayList

class MySqlDatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    val allStudents: ArrayList<Students>?
        get() {
            val sqLiteDatabase = this.readableDatabase
            val query = "SELECT * FROM $TABLE_NAME"
            val cursor = sqLiteDatabase.rawQuery(query, null)

            if (cursor.moveToFirst()) {
                val arrayList = ArrayList<Students>()
                do {
                    val name = cursor.getString(cursor.getColumnIndex(FIELD_NAME))
                    val major = cursor.getString(cursor.getColumnIndex(FIELD_MAJOR))
                    val minor = cursor.getString(cursor.getColumnIndex(FIELD_MINOR))
                    val gpa = cursor.getString(cursor.getColumnIndex(FIELD_GPA))
                    val dob = cursor.getString(cursor.getColumnIndex(FIELD_DOB))
                    val city = cursor.getString(cursor.getColumnIndex(FIELD_CITY))
                    val state = cursor.getString(cursor.getColumnIndex(FIELD_STATE))
                    val ssn = cursor.getString(cursor.getColumnIndex(FIELD_SSN))
                    arrayList.add(Students(name, major, minor, gpa, dob, city, state, ssn))
                } while (cursor.moveToNext())
                return arrayList
            } else {
                return null
            }
        }

    override fun onCreate(db: SQLiteDatabase) {
        val createQuery = ("CREATE TABLE " + TABLE_NAME + " ("
                + FIELD_NAME + " TEXT, "
                + FIELD_MAJOR + " TEXT, "
                + FIELD_MINOR + " TEXT, "
                + FIELD_GPA + " TEXT, "
                + FIELD_DOB + " TEXT, "
                + FIELD_CITY + " TEXT, "
                + FIELD_STATE + " TEXT, "
                + FIELD_SSN + " TEXT PRIMARY KEY);")
        db.execSQL(createQuery)

        var student = Students("Josh", "Computer Science", "Religion", "3.8", "12/27/1995", "LaGrange", "GA", "555555555")
        var content = ContentValues()
        content.put(FIELD_NAME, student.name)
        content.put(FIELD_MAJOR, student.major)
        content.put(FIELD_MINOR, student.minor)
        content.put(FIELD_GPA, student.gpa)
        content.put(FIELD_DOB, student.dob)
        content.put(FIELD_CITY, student.homeCity)
        content.put(FIELD_STATE, student.homeState)
        content.put(FIELD_SSN, student.ssn)
        db.insert(TABLE_NAME, null, content)

        student = Students("Michelle", "Psychology", "Communication", "4.0", "10/08/1976", "Manhattan", "NY", "666666666")
        content = ContentValues()
        content.put(FIELD_NAME, student.name)
        content.put(FIELD_MAJOR, student.major)
        content.put(FIELD_MINOR, student.minor)
        content.put(FIELD_GPA, student.gpa)
        content.put(FIELD_DOB, student.dob)
        content.put(FIELD_CITY, student.homeCity)
        content.put(FIELD_STATE, student.homeState)
        content.put(FIELD_SSN, student.ssn)
        db.insert(TABLE_NAME, null, content)

        student = Students("Herman", "Physical Training", "Education", "2.7", "08/15/1968", "Bronx", "NY", "777777777")
        content = ContentValues()
        content.put(FIELD_NAME, student.name)
        content.put(FIELD_MAJOR, student.major)
        content.put(FIELD_MINOR, student.minor)
        content.put(FIELD_GPA, student.gpa)
        content.put(FIELD_DOB, student.dob)
        content.put(FIELD_CITY, student.homeCity)
        content.put(FIELD_STATE, student.homeState)
        content.put(FIELD_SSN, student.ssn)
        db.insert(TABLE_NAME, null, content)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }

    fun insertStudent(student: Students?) {
        val database = this.writableDatabase
        val contentValues = ContentValues()

        if (student != null) {
            val name = student.name
            val major = student.major
            val minor = student.minor
            val gpa = student.gpa
            val dob = student.dob
            val city = student.homeCity
            val state = student.homeState
            val ssn = student.ssn

            contentValues.put(FIELD_NAME, name)
            contentValues.put(FIELD_MAJOR, major)
            contentValues.put(FIELD_MINOR, minor)
            contentValues.put(FIELD_GPA, gpa)
            contentValues.put(FIELD_DOB, dob)
            contentValues.put(FIELD_CITY, city)
            contentValues.put(FIELD_STATE, state)
            contentValues.put(FIELD_SSN, ssn)

            database.insert(TABLE_NAME, null, contentValues)
        }

    }

    fun getStudent(passedSsn: String?): Students? {
        var returnStudent: Students? = null
        if (passedSsn != null && !passedSsn.isEmpty()) {
            val sqLiteDatabase = this.readableDatabase
            val query = ("SELECT * FROM " + TABLE_NAME
                    + " WHERE " + FIELD_SSN + " = \"" + passedSsn + "\"")
            val cursor = sqLiteDatabase.rawQuery(query, null)
            if (cursor.moveToFirst()) {
                val name = cursor.getString(cursor.getColumnIndex(FIELD_NAME))
                val major = cursor.getString(cursor.getColumnIndex(FIELD_MAJOR))
                val minor = cursor.getString(cursor.getColumnIndex(FIELD_MINOR))
                val gpa = cursor.getString(cursor.getColumnIndex(FIELD_GPA))
                val dob = cursor.getString(cursor.getColumnIndex(FIELD_DOB))
                val city = cursor.getString(cursor.getColumnIndex(FIELD_CITY))
                val state = cursor.getString(cursor.getColumnIndex(FIELD_STATE))
                val ssn = cursor.getString(cursor.getColumnIndex(FIELD_SSN))
                returnStudent = Students(name, major, minor, gpa, dob, city, state, ssn)
            }
            cursor.close()
        }
        return returnStudent
    }

    fun deleteStudent(passedSSN: String): Int {
        val whereClause = FIELD_SSN + " = \"" + passedSSN + "\""
        val sqLiteDatabase = this.writableDatabase
        return sqLiteDatabase.delete(TABLE_NAME, whereClause, null)
    }

    fun updatePerson(passedStudent: Students?): Int {
        if (passedStudent != null) {
            val whereClause = FIELD_SSN + " = \"" + passedStudent.ssn + "\""
            val sqLiteDatabase = writableDatabase
            val contentValues = ContentValues()
            contentValues.put(FIELD_NAME, passedStudent.name)
            contentValues.put(FIELD_MAJOR, passedStudent.major)
            contentValues.put(FIELD_MINOR, passedStudent.minor)
            contentValues.put(FIELD_GPA, passedStudent.gpa)
            contentValues.put(FIELD_DOB, passedStudent.dob)
            contentValues.put(FIELD_CITY, passedStudent.homeCity)
            contentValues.put(FIELD_STATE, passedStudent.homeState)
            contentValues.put(FIELD_SSN, passedStudent.ssn)
            return sqLiteDatabase.update(TABLE_NAME, contentValues, whereClause, null)
        }
        return 0
    }

}
