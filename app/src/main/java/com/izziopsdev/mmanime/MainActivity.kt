package com.izziopsdev.mmanime

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.izziopsdev.mmanime.databinding.ActivityMainBinding
import com.izziopsdev.mmanime.ui.home.HomeFragment
import com.izziopsdev.mmanime.util.Helping
import com.izziopsdev.mmanime.util.Helping.Companion.openUrl
import com.izziopsdev.mmanime.util.Helping.Companion.showMessage
import com.izziopsdev.mmanime.util.Helping.Companion.toModifString

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setSupportActionBar(binding.appBarMain.toolbar)

//        binding.appBarMain.toolbar.setTitle("ANIME MANGA-MANHWA")

        binding.appBarMain.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            binding.drawerLayout.openDrawer( GravityCompat.START, true)
        }
        binding.appBarMain.fab.setImageTintList(ColorStateList.valueOf(Color.WHITE));

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        val currentFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)
        // menu should be considered as top level destinations.

        // Set up the drawer menu item click listener
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Handle "Home" menu item click
//                    navController.navigate(R.id.nav_home)
                    if(currentFragment is HomeFragment){
                        currentFragment.getWebview()?.let{
                            it.loadUrl(Helping.urlMain)
                        }
                    }
                }
                R.id.nav_prev -> {
                    // Handle "Previous" menu item click
//                    navController.navigate(R.id.nav_prev)
                    if(currentFragment is HomeFragment){
                        currentFragment.getWebview()?.let{
                            if(it.canGoBack()){
                                it.goBack()
                            }
                        }
                    }
                }
                R.id.nav_refresh -> {
                    // Handle "Previous" menu item click
//                    navController.navigate(R.id.nav_prev)
                    if(currentFragment is HomeFragment){
                        currentFragment.getWebview()?.let{
                            it.reload()
                        }
                    }
                }
                R.id.nav_next -> {
                    // Handle "Previous" menu item click
//                    navController.navigate(R.id.nav_prev)
                    if(currentFragment is HomeFragment){
                        currentFragment.getWebview()?.let{
                            if(it.canGoForward()){
                                it.goForward()
                            }
                        }
                    }
                }
                R.id.nav_about -> {
                    // Handle "Previous" menu item click
//                    navController.navigate(R.id.nav_prev)
                    if(currentFragment is HomeFragment){
                        val activity = (currentFragment.context as MainActivity)

                        showMessage(
                            getString(R.string.about_us),
                            getString(R.string.about_message),
                            activity,
                            false,
                            {

                            },
                            {

                            },
                            getString(R.string.contact_btn),
                            false,
                            false
                        )
                    }
                }
                R.id.nav_contact -> {
                    // Handle "Previous" menu item click
//                    navController.navigate(R.id.nav_prev)
                    if(currentFragment is HomeFragment){
                        val activity = (currentFragment.context as MainActivity)

                        showMessage(
                            getString(R.string.contact_title),
                            getString(R.string.contact_message),
                            activity,
                            false,
                            {

                            },
                            {

                            },
                            getString(R.string.contact_btn),
                            false,
                            false
                        )
                    }
                }
                R.id.nav_exit -> {
                    // Handle "Previous" menu item click
//                    navController.navigate(R.id.nav_prev)
                    if(currentFragment is HomeFragment){
                        (currentFragment.context as MainActivity).finish()
                    }
                }
                // Add more cases for other menu items
            }
            // Close the drawer after selecting an item
            drawerLayout.closeDrawer(GravityCompat.START)

            true // Return true to display the item as selected
        }

//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
//            ), drawerLayout
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}