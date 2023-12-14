package com.example.playlistmaker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible

class SearchActivity : AppCompatActivity() {

    private var savedString: CharSequence = ""

    private lateinit var searchEditText: EditText

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SAVED_EDIT_TEXT, savedString.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedString = savedInstanceState.getString(SAVED_EDIT_TEXT).toString()
        searchEditText.setText(savedString)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchToolbar = findViewById<Toolbar>(R.id.searchToolbar)
        searchEditText = findViewById(R.id.searchEditText)
        val clearButton = findViewById<ImageView>(R.id.clearButton)

        searchToolbar.setOnClickListener {
            val searchToolbarIntent = Intent(this, MainActivity::class.java)
            startActivity(searchToolbarIntent)
        }

        clearButton.setOnClickListener {
            searchEditText.setText("")
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManager?.hideSoftInputFromWindow(searchEditText.windowToken, 0)
        }

        val searchTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // empty
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                clearButton.isVisible = clearButtonVisibility(s)
                saveString(s)
            }

            override fun afterTextChanged(s: Editable?) {
                // empty
            }
        }

        searchEditText.addTextChangedListener(searchTextWatcher)

    }

    private fun saveString(s: CharSequence?){
        if (!s.isNullOrEmpty()){
            savedString = s
        }
    }

    private fun clearButtonVisibility(s: CharSequence?): Boolean{
        return !s.isNullOrEmpty()
    }

    companion object {
        private const val SAVED_EDIT_TEXT = "SAVED_EDIT_TEXT"
    }
}