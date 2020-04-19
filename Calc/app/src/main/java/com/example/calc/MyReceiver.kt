package com.example.calc

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            val myStarterIntent = Intent(context, MainActivity::class.java)
            myStarterIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(myStarterIntent)
        }

        if (intent.action.equals("my.android.BROADCAST")) {
            Toast.makeText(context, "My Custom Broadcast", Toast.LENGTH_SHORT).show()
        }
    }
}
