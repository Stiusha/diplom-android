package com.example.diplom

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.diplom.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_category, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navView, navController)
        //КОСТЫЛЬ подсвечивает элемент меню
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_category ->
                    navView.menu.findItem(R.id.navigation_category).isChecked = true

                R.id.navigation_subcategory ->
                    navView.menu.findItem(R.id.navigation_category).isChecked = true

                R.id.navigation_product ->
                    navView.menu.findItem(R.id.navigation_category).isChecked = true

                R.id.navigation_dashboard ->
                    navView.menu.findItem(R.id.navigation_dashboard).isChecked = true

                R.id.navigation_notifications ->
                    navView.menu.findItem(R.id.navigation_notifications).isChecked = true
            }
        }

    }

    fun openFragment(@IdRes resId: Int, bundle: Bundle) {
        navController.navigate(resId, bundle)
    }

    fun openFragmentAction(directions: NavDirections) {
        navController.navigate(directions)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            appBarConfiguration
        ) || super.onSupportNavigateUp()
    }
}