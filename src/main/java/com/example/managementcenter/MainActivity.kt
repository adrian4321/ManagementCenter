package com.example.managementcenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var name = findViewById(R.id.nameTextbox) as EditText
        var password = findViewById(R.id.passwordTextbox) as EditText
        val button: Button = findViewById(R.id.loginButton) as Button

        button.setOnClickListener{
            var name1 = name.text.toString()
            var pass = password.text.toString()

            if(verify(name1,pass) == "0")
            {
                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show()
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
                finish()
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun verify( name: String, password: String): String
    {
        if(name == "Adrian Hurst" && password == "1234")
        {
            return "0"
        }
        else{
            return "1"
        }
    }
}