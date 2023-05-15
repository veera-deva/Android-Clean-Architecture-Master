package com.demo.movies

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.demo.common_ui.base.BaseActivity
import com.demo.movies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity holding fragment container view with navHost fragment.
 * */

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val navController by lazy { binding.navHostFragmentContentMain.getFragment<NavHostFragment>().navController }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolBar)
        val config = AppBarConfiguration(navController.graph)
        binding.toolBar.setupWithNavController(navController, config)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        @DrawableRes val darkModeIcon: Int =
            if (isDarkModeEnabled()) R.drawable.ic_dark_mode_fill else R.drawable.ic_dark_mode
        menu?.findItem(R.id.action_dark_mode)?.icon = ContextCompat.getDrawable(this, darkModeIcon)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        enableDarkMode(!isDarkModeEnabled())
        recreate()
        return true
    }

}