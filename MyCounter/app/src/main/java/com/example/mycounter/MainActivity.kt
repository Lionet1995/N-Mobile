package com.example.mycounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       var count = 0

        btn_counter.setOnClickListener(){

            count = count+1
         countView.text = count.toString()
        }

        if (savedInstanceState != null) {
            val isVisible : Boolean = savedInstanceState.getBoolean("reply_visible")

            if (isVisible) {
                countView.setText(savedInstanceState
                    .getString("reply_text"));
                countView.setVisibility(View.VISIBLE);
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (countView.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text",countView.getText().toString())
        }
    }

}
