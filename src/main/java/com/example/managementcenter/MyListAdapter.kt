package com.example.managementcenter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyListAdapter(private val context: Activity, private val id: Array<String>, private val customerName: Array<String>, private val customerPosition: Array<String>,
                    private val department: Array<String>,private val ministryName: Array<String>,private val address: Array<String>,private val phoneNumber: Array<String>,
                    private val subject: Array<String>, private val agentName: Array<String>, private val message: Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list, customerName) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val idText = rowView.findViewById(R.id.textViewId) as TextView
        val customerNameText = rowView.findViewById(R.id.textViewCustomerName) as TextView
        val customerPositionText = rowView.findViewById(R.id.textViewCustomerPosition) as TextView
        val departmentText = rowView.findViewById(R.id.textViewDepartment) as TextView
        val ministryNameText = rowView.findViewById(R.id.textViewMinistryName) as TextView
        val addressText = rowView.findViewById(R.id.textViewAddress) as TextView
        val phoneNumberText = rowView.findViewById(R.id.textViewPhoneNumber) as TextView
        val subjectText = rowView.findViewById(R.id.textViewSubject) as TextView
        val agentNameText = rowView.findViewById(R.id.textViewAgentName) as TextView
        val messageText = rowView.findViewById(R.id.textViewMessage) as TextView

        idText.text = "Id: ${id[position]}"
        customerNameText.text = "customerName: ${customerName[position]}"
        customerPositionText.text = "customerPosition: ${customerPosition[position]}"
        departmentText.text = "department: ${department[position]}"
        ministryNameText.text = "ministryName: ${ministryName[position]}"
        addressText.text = "address: ${address[position]}"
        phoneNumberText.text = "phoneNumber: ${phoneNumber[position]}"
        subjectText.text = "subject: ${subject[position]}"
        agentNameText.text = "agentName: ${agentName[position]}"
        messageText.text = "message: ${message[position]}"
        return rowView
    }
}