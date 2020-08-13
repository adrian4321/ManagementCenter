package com.example.managementcenter

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException

//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "TicketDatabase"
        private val TABLE_CONTACTS = "TicketTable"
        private val KEY_ID = "id"
        private val KEY_CUSTOMERNAME = "customerName"
        private val KEY_CUSTOMERPOSITION = "customerPosition"
        private val KEY_DEPARTMENT = "department"
        private val KEY_MINISTRYNAME = "ministryName"
        private val KEY_ADDRESS = "address"
        private val KEY_PHONENUMBER = "phoneNumber"
        private val KEY_SUBJECT = "subject"
        private val KEY_AGENTNAME = "agentName"
        private val KEY_MESSAGE = "message"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //creating table with fields
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CUSTOMERNAME + " TEXT,"
                + KEY_CUSTOMERPOSITION + " TEXT," + KEY_DEPARTMENT + " TEXT,"
                + KEY_MINISTRYNAME + " TEXT," + KEY_ADDRESS + " TEXT,"
                + KEY_PHONENUMBER + " TEXT,"  + KEY_SUBJECT + " TEXT,"
                + KEY_AGENTNAME + " TEXT,"    + KEY_MESSAGE + " TEXT"
                +")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }


    //method to insert data
    fun addEmployee(emp: EmpModelClass):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, emp.userId)
        contentValues.put(KEY_CUSTOMERNAME, emp.customerName) // EmpModelClass Name
        contentValues.put(KEY_CUSTOMERPOSITION,emp.customerPosition ) // EmpModelClass Phone
        contentValues.put(KEY_DEPARTMENT, emp.department) // EmpModelClass Name
        contentValues.put(KEY_MINISTRYNAME,emp.ministryName ) // EmpModelClass Phone
        contentValues.put(KEY_ADDRESS, emp.address) // EmpModelClass Name
        contentValues.put(KEY_PHONENUMBER,emp.phoneNumber ) // EmpModelClass Phone
        contentValues.put(KEY_SUBJECT, emp.subject) // EmpModelClass Name
        contentValues.put(KEY_AGENTNAME,emp.agentName ) // EmpModelClass Phone
        contentValues.put(KEY_MESSAGE, emp.message) // EmpModelClass Name

        // Inserting Row
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to read data
    fun viewEmployee():List<EmpModelClass>{
        val empList:ArrayList<EmpModelClass> = ArrayList<EmpModelClass>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userId: Int
        var customerName: String
        var customerPosition: String
        var department: String
        var ministryName: String
        var address: String
        var phoneNumber: String
        var subject: String
        var agentName: String
        var message: String

        if (cursor.moveToFirst()) {
            do {
                userId = cursor.getInt(cursor.getColumnIndex("id"))
                customerName = cursor.getString(cursor.getColumnIndex("customerName"))
                customerPosition = cursor.getString(cursor.getColumnIndex("customerPosition"))
                department = cursor.getString(cursor.getColumnIndex("department"))
                ministryName = cursor.getString(cursor.getColumnIndex("ministryName"))
                address = cursor.getString(cursor.getColumnIndex("address"))
                phoneNumber = cursor.getString(cursor.getColumnIndex("phoneNumber"))
                subject = cursor.getString(cursor.getColumnIndex("subject"))
                agentName = cursor.getString(cursor.getColumnIndex("agentName"))
                message = cursor.getString(cursor.getColumnIndex("message"))

                val emp= EmpModelClass(userId = userId, customerName = customerName, customerPosition = customerPosition, department = department,
                        ministryName = ministryName, address = address, phoneNumber = phoneNumber,  subject = subject, agentName = agentName,
                    message = message)
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        return empList
    }
    //method to update data
    fun updateEmployee(emp: EmpModelClass):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, emp.userId)
        contentValues.put(KEY_CUSTOMERNAME, emp.customerName) // EmpModelClass Name
        contentValues.put(KEY_CUSTOMERPOSITION,emp.customerPosition ) // EmpModelClass Phone
        contentValues.put(KEY_DEPARTMENT, emp.department) // EmpModelClass Name
        contentValues.put(KEY_MINISTRYNAME,emp.ministryName ) // EmpModelClass Phone
        contentValues.put(KEY_ADDRESS, emp.address) // EmpModelClass Name
        contentValues.put(KEY_PHONENUMBER,emp.phoneNumber ) // EmpModelClass Phone
        contentValues.put(KEY_SUBJECT, emp.subject) // EmpModelClass Name
        contentValues.put(KEY_AGENTNAME,emp.agentName ) // EmpModelClass Phone
        contentValues.put(KEY_MESSAGE, emp.message) // EmpModelClass Name

        // Updating Row
        val success = db.update(TABLE_CONTACTS, contentValues,"id="+emp.userId,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to delete data
    fun deleteEmployee(emp: EmpModelClass):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, emp.userId) // EmpModelClass UserId
        // Deleting Row
        val success = db.delete(TABLE_CONTACTS,"id="+emp.userId,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
}