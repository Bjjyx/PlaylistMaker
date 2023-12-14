package com.example.playlistmaker

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val settingsToolbar = findViewById<Toolbar>(R.id.settingsToolbar)
        val buttonShare = findViewById<TextView>(R.id.share)
        val buttonSupport = findViewById<TextView>(R.id.support)
        val buttonAgreement = findViewById<TextView>(R.id.agreement)

        settingsToolbar.setOnClickListener {
            val settingsToolbarIntent = Intent(this, MainActivity::class.java)
            startActivity(settingsToolbarIntent)
        }
        buttonShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
//            shareIntent.data = Uri.parse("text/plain") данная строка не работает! Строка ниже работает не совсем корректно.
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_message))
            try {
                startActivity(shareIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this@SettingsActivity, "Не найдено подходящее приложение :(", Toast.LENGTH_SHORT).show()
            }
        }

        buttonSupport.setOnClickListener {
            val supportIntent = Intent(Intent.ACTION_SENDTO)
            supportIntent.data = Uri.parse("mailto:")
            supportIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(resources.getString(R.string.support_mail)))
            supportIntent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.support_title))
            supportIntent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.support_message))
            try {
                startActivity(supportIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this@SettingsActivity, "Не найдено подходящее приложение :(", Toast.LENGTH_SHORT).show()
            }
        }

        buttonAgreement.setOnClickListener {
            val agreementIntent = Intent(Intent.ACTION_VIEW)
            agreementIntent.data = Uri.parse(resources.getString(R.string.agreement_url))
            try {
                startActivity(agreementIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this@SettingsActivity, "Не найдено подходящее приложение :(", Toast.LENGTH_SHORT).show()
            }
        }

    }
}