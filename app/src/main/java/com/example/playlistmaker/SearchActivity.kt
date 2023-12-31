package com.example.playlistmaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar

class SearchActivity : AppCompatActivity() {

    private var savedString: CharSequence = STRING_DEF

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SAVED_STRING, savedString.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedString = savedInstanceState.getString(SAVED_STRING, STRING_DEF)
    }

    companion object {
        const val SAVED_STRING = "SAVED_STRING"
        const val STRING_DEF = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchToolbar = findViewById<Toolbar>(R.id.searchToolbar)
        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        val clearButton = findViewById<ImageView>(R.id.clearButton)

        searchToolbar.setOnClickListener {
            val searchToolbarIntent = Intent(this, MainActivity::class.java)
            startActivity(searchToolbarIntent)
        }

        clearButton.setOnClickListener {
            searchEditText.setText("")
        }

        val searchTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // empty
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                clearButton.visibility = clearButtonVisibility(s)
                saveString(s)
            }

            override fun afterTextChanged(s: Editable?) {
                // empty
            }
        }

        searchEditText.setText(savedString)
        searchEditText.addTextChangedListener(searchTextWatcher)

    }

    private fun saveString(s: CharSequence?){
        if (!s.isNullOrEmpty()){
            savedString = s
        }
    }

    private fun clearButtonVisibility(s: CharSequence?): Int{
        return if (s.isNullOrEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}