package com.example.calc

import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {

   // private val LOG_TAG: String = MainActivity::class.java.simpleName на выбор в нижней строкой, хз, как лучше объявить переменную
    private val LOG_TAG = "myLog"
    private val SECOND_ACTIVITY_REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myReceiver : MyReceiver = MyReceiver()
        val intentFilter : IntentFilter = IntentFilter()
        intentFilter.addAction("my.android.BROADCAST")
        registerReceiver(myReceiver, intentFilter)

        btn_showBroadcast.setOnClickListener()
        {

            OnClick()

        }

        btn_plus.setOnClickListener()
        {
            add()
        }

        btn_minus.setOnClickListener()
        {
            subtract()
        }

        btn_multi.setOnClickListener()
        {
            multiply()
        }

        btn_div.setOnClickListener()
        {
            divide()
        }

        if (savedInstanceState != null) {
            val isVisible : Boolean = savedInstanceState.getBoolean("reply_visible")
            // Show both the header and the message views. If isVisible is
            // false or missing from the bundle, use the default layout.
            if (isVisible) {
                textView.setText(savedInstanceState
                    .getString("reply_text"));
                textView.setVisibility(View.VISIBLE);

                tv_result.setText(savedInstanceState
                    .getString("rep_text"));
                tv_result.setVisibility(View.VISIBLE);
            }
        }

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (textView.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text",textView.getText().toString())
            outState.putString("rep_text", tv_result.getText().toString())
        }
    }

    fun OnClick() { // спросить про view, как параметр передаваемый и про override
        val i : Intent = Intent()
        i.setAction("my.android.BROADCAST")
        sendBroadcast(i)}
        // "Go to Second Activity" button click
        fun buttonClick1(view: View) {

            // Start the SecondActivity
            val intent = Intent(this, NewActivity::class.java)
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
        }


        fun add() {
            if (inputIsNotEmpty()) {
                val input1 = edt1.text.toString().trim().toBigDecimal()
                val input2 = edt2.text.toString().trim().toBigDecimal()
                tv_result.text = input1.add(input2).toString()

            }
        }


        fun subtract() {
            if (inputIsNotEmpty()) {
                val input1 = edt1.text.toString().trim().toBigDecimal()
                val input2 = edt2.text.toString().trim().toBigDecimal()
                tv_result.text = input1.subtract(input2).toString()
            }
        }

        fun multiply() {
            if (inputIsNotEmpty()) {
                val input1 = edt1.text.toString().trim().toBigDecimal()
                val input2 = edt2.text.toString().trim().toBigDecimal()
                tv_result.text = input1.multiply(input2).toString()
            }
        }

        fun divide() {
            if (inputIsNotEmpty()) {
                val input1 = edt1.text.toString().trim().toBigDecimal()
                val input2 = edt2.text.toString().trim().toBigDecimal()
                if (input2.compareTo(BigDecimal.ZERO) == 0) {
                    edt2.error = "Invalid input"
                } else {
                    tv_result.text = input1.divide(input2, 2, RoundingMode.HALF_UP).toString()
                }
            }
        }

        fun inputIsNotEmpty(): Boolean {
            var b = true
            if (edt1.text.toString().trim().isEmpty()) {
                edt1.error = "Required"
                b = false
            }
            if (edt2.text.toString().trim().isEmpty()) {
                edt2.error = "Required"
                b = false
            }
            return b

        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            // Check that it is the SecondActivity with an OK result
            if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
                if (resultCode == Activity.RESULT_OK) {

                    // Get String data from Intent
                    val returnString = data!!.getStringExtra("keyName")

                    // Set text view with string
                    val textView = findViewById(R.id.textView) as TextView
                    textView.text = returnString
                }
            }
        }
    }

