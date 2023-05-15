package com.demo.common_ui.base

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.demo.common_ui.R
import com.demo.common_ui.di.AppSettingsSharedPreference
import javax.inject.Inject

abstract class BaseActivity<VB : ViewBinding>(private val inflater: (LayoutInflater) -> VB) :
    AppCompatActivity() {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    @Inject
    @AppSettingsSharedPreference
    lateinit var appSettings: SharedPreferences
    fun isDarkModeEnabled() = appSettings.getBoolean(DARK_MODE, false)
    fun enableDarkMode(enable: Boolean) = appSettings.edit().putBoolean(DARK_MODE, enable).commit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(if (isDarkModeEnabled()) R.style.DarkTheme else R.style.LightTheme)
        _binding = inflater(layoutInflater)
        setContentView(binding.root)
    }


    companion object {
        const val DARK_MODE = "dark_mode"
    }
}