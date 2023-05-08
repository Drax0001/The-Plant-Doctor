package com.example.theplantdoctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat

class settings : AppCompatActivity() {
    private fun  chooseThemeDialog(){
        val  builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.choose_theme_text))
        val styles = arrayOf("Light","Dark","System")
        val checkedItem = 0

        builder.setSingleChoiceItems(styles, checkedItem){
            dialog, which ->
            when (which){
                0 ->{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
                1 ->{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
                2 ->{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
            }
        }
        val dialog = builder.create()
        dialog.show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val changeMode = findViewById<SwitchCompat>(R.id.switch1)
        changeMode.setOnClickListener { chooseThemeDialog()}

    }
}