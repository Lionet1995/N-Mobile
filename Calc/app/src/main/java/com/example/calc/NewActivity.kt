package com.example.calc

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class NewActivity : AppCompatActivity() {

    private val LOG_TAG = "myLog2"
   // private val LOG_TAG: String = NewActivity::class.java.getSimpleName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        Log.d(LOG_TAG, "-----")
        Log.d(LOG_TAG, "onCreate")
    }
    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(LOG_TAG, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "onStop")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume")
    }

    // "Send text back" button click
    fun buttonClick2(view: View) {

        // Get the text from the EditText
        val editText = findViewById(R.id.editText) as EditText
        val stringToPassBack = editText.text.toString()

        // Put the String to pass back into an Intent and close this activity
        val intent = Intent()
        intent.putExtra("keyName", stringToPassBack)
        setResult(Activity.RESULT_OK, intent)
        Log.d(LOG_TAG, "End NewActivity")
        finish()

    }


}
