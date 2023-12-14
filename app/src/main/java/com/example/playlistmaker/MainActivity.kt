package com.example.playlistmaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSearch = findViewById<Button>(R.id.buttonSearch)
        val buttonLibrary = findViewById<Button>(R.id.buttonLibrary)
        val buttonSettings = findViewById<Button>(R.id.buttonSettings)

        buttonSearch.setOnClickListener {
            val searchActivityIntent = Intent(this, SearchActivity::class.java)
            startActivity(searchActivityIntent)
        }
        buttonLibrary.setOnClickListener {
            val libraryActivityIntent = Intent(this, LibraryActivity::class.java)
            startActivity(libraryActivityIntent)
        }
        buttonSettings.setOnClickListener {
            val settingsActivityIntent = Intent(this, SettingsActivity::class.java)
            startActivity(settingsActivityIntent)
        }

    }
}