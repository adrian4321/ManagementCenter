package com.example.managementcenter

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_fragment_one.*
import kotlinx.android.synthetic.main.fragment_fragment_one.tableLayout
import kotlinx.android.synthetic.main.fragment_fragment_three.*
//import kotlinx.android.synthetic.main.fragment_fragment_one.u_id
import kotlinx.android.synthetic.main.fragment_fragment_two.*

class Home : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBar: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navController = findNavController(R.id.nav_fragment)
        drawerLayout = findViewById(R.id.drawer_layout)

        appBar = AppBarConfiguration(
            setOf(R.id.fragmentHomeScreen,R.id.fragmentOne, R.id.fragmentTwo, R.id.fragmentThree),
            drawerLayout
        )

        navigationView.setupWithNavController(navController)


        setupActionBarWithNavController(navController, appBar)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_fragment)
        return navController.navigateUp(appBar) || super.onSupportNavigateUp()
    }

    //method for saving records in database
    fun saveRecord(view: View) {
        val id = u_id.text.toString()
        val customerName1 = customerName.text.toString()
        val customerPosition1 = customerPosition.text.toString()
        val department1 = department.text.toString()
        val ministryName1 = ministryName.text.toString()
        val address1 = address.text.toString()
        val phoneNumber1 = phoneNumber.text.toString()
        val subject1 = subject.text.toString()
        val agentName1 = agentName.text.toString()
        val message1 = message.text.toString()

        val databaseHandler: DatabaseHandler = DatabaseHandler(this)

        if (id.trim() != "" && customerName1.trim() != "" && customerPosition1.trim() != "" && department1.trim() != "" && ministryName1.trim() != ""
            && address1.trim() != "" && phoneNumber1.trim() != "" && subject1.trim() != "" && agentName1.trim() != "" && message1.trim() != "") {
            val status =
                databaseHandler.addEmployee(EmpModelClass(Integer.parseInt(id), customerName1, customerPosition1, department1,
                    ministryName1, address1, phoneNumber1, subject1, agentName1,message1 ))
            if (status > -1) {
                Toast.makeText(applicationContext, "record save", Toast.LENGTH_LONG).show()
                u_id.text.clear()
                customerName.text.clear()
                customerPosition.text.clear()
                department.text.clear()
                ministryName.text.clear()
                address.text.clear()
                phoneNumber.text.clear()
                subject.text.clear()
                agentName.text.clear()
                message.text.clear()
            }
        } else {
            Toast.makeText(
                applicationContext,
                "id or name or email cannot be blank",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    //method for read records from database in ListView
    fun viewRecord(view: View) {
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val emp: List<EmpModelClass> = databaseHandler.viewEmployee()
        val empArrayId = Array<String>(emp.size) { "0" }
        val empArraycustomerName = Array<String>(emp.size) { "null" }
        val empArraycustomerPosition = Array<String>(emp.size) { "null" }
        val empArraydepartment = Array<String>(emp.size) { "null" }
        val empArrayministryName = Array<String>(emp.size) { "null" }
        val empArrayaddress = Array<String>(emp.size) { "null" }
        val empArrayphoneNumber = Array<String>(emp.size) { "null" }
        val empArraysubject = Array<String>(emp.size) { "null" }
        val empArrayagentName = Array<String>(emp.size) { "null" }
        val empArraymessage = Array<String>(emp.size) { "null" }
        var index = 0
        for (e in emp) {
            empArrayId[index] = e.userId.toString()
            empArraycustomerName[index] = e.customerName
            empArraycustomerPosition[index] = e.customerPosition
            empArraydepartment[index] = e.department
            empArrayministryName[index] = e.ministryName
            empArrayaddress[index] = e.address
            empArrayphoneNumber[index] = e.phoneNumber
            empArraysubject[index] = e.subject
            empArrayagentName[index] = e.agentName
            empArraymessage[index] = e.message
            index++
        }
        //creating custom ArrayAdapter
        val myListAdapter = MyListAdapter(this, empArrayId, empArraycustomerName, empArraycustomerPosition,empArraydepartment,empArrayministryName
                            ,empArrayaddress, empArrayphoneNumber, empArraysubject, empArrayagentName, empArraymessage)
        listView.adapter = myListAdapter
        
    }

    //method for updating records based on user id
    fun updateRecord(view: View) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setView(dialogView)

        val edtId = dialogView.findViewById(R.id.updateId) as EditText
        val edtCustomerName = dialogView.findViewById(R.id.updateCustomerName) as EditText
        val edtCustomerPosition = dialogView.findViewById(R.id.updateCustomerPosition) as EditText
        val edtDepartment = dialogView.findViewById(R.id.updateDepartment) as EditText
        val edtMinistryName = dialogView.findViewById(R.id.updateMinistryName) as EditText
        val edtAddress = dialogView.findViewById(R.id.updateAddress) as EditText
        val edtPhoneNumber = dialogView.findViewById(R.id.updatePhoneNumber) as EditText
        val edtSubject = dialogView.findViewById(R.id.updateSubject) as EditText
        val edtAgentName = dialogView.findViewById(R.id.updateAgentName) as EditText
        val edtMessage = dialogView.findViewById(R.id.updateMessage) as EditText

        dialogBuilder.setTitle("Update Record")
        dialogBuilder.setMessage("Enter data below")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { _, _ ->

            val updateId = edtId.text.toString()
            val updateCustomerName = edtCustomerName.text.toString()
            val updateCustomerPosition = edtCustomerPosition.text.toString()
            val updateDepartment = edtDepartment.text.toString()
            val updateMinistryName = edtMinistryName.text.toString()
            val updateAddress = edtAddress.text.toString()
            val updatePhoneNumber = edtPhoneNumber.text.toString()
            val updateSubject = edtSubject.text.toString()
            val updateAgentName = edtAgentName.text.toString()
            val updateMessage = edtMessage.text.toString()


            //creating the instance of DatabaseHandler class
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            if (updateId.trim() != "" && updateCustomerName.trim() != "" && updateCustomerPosition.trim() != "" && updateDepartment.trim() != "" && updateMinistryName.trim() != ""
                && updateAddress.trim() != "" && updatePhoneNumber.trim() != "" && updateSubject.trim() != "" && updateAgentName.trim() != "" && updateMessage.trim() != "") {
                //calling the updateEmployee method of DatabaseHandler class to update record
                val status = databaseHandler.updateEmployee(
                    EmpModelClass(
                        Integer.parseInt(updateId),
                        updateCustomerName,
                        updateCustomerPosition,
                        updateDepartment,
                        updateMinistryName,
                        updateAddress,
                        updatePhoneNumber,
                        updateSubject,
                        updateAgentName,
                        updateMessage
                    )
                )
                if (status > -1) {
                    Toast.makeText(applicationContext, "record update", Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "id or name or email cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }

        })
        dialogBuilder.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, which ->
                //pass
            })
        val b = dialogBuilder.create()
        b.show()
    }

    //method for deleting records based on id
    fun deleteRecord(view: View) {
        //creating AlertDialog for taking user id
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_dialog, null)
        dialogBuilder.setView(dialogView)

        val dltId = dialogView.findViewById(R.id.deleteId) as EditText
        dialogBuilder.setTitle("Delete Record")
        dialogBuilder.setMessage("Enter id below")
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->

            val deleteId = dltId.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            if (deleteId.trim() != "") {
                //calling the deleteEmployee method of DatabaseHandler class to delete record
                val status = databaseHandler.deleteEmployee(
                    EmpModelClass(
                        Integer.parseInt(deleteId),
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""

                    )
                )
                if (status > -1) {
                    Toast.makeText(applicationContext, "record deleted", Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "id or name or email cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }
}