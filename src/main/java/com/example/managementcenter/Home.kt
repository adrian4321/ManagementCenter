package com.example.managementcenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBar: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navController = findNavController(R.id.nav_fragment)
        drawerLayout = findViewById(R.id.drawer_layout)

        appBar = AppBarConfiguration(setOf(R.id.fragmentOne, R.id.fragmentTwo, R.id.fragmentThree), drawerLayout)

        navigationView.setupWithNavController(navController)


        setupActionBarWithNavController(navController, appBar)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_fragment)
        return  navController.navigateUp(appBar) || super.onSupportNavigateUp()
    }
}