package com.example.powerreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CustomReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // 이 메서드는 BroadcastReceiver가 인텐트 브로드캐스트를 수신할 때 호출됩니다.
        val intentAction = intent.action
        val stringNumber = intent.getStringExtra(MainActivity.EXTRA_RANDOM_NUMBER)
        val number = stringNumber!!.toInt()

        val toastMessage = when (intentAction) {
            Intent.ACTION_POWER_CONNECTED -> "Power connected!"
            Intent.ACTION_POWER_DISCONNECTED -> "Power disconnected"
            MainActivity.ACTION_CUSTOM_BROADCAST -> "Custom Broadcast Received"
            else -> "unknown intent action"
        }

        Toast.makeText(context, "$toastMessage\nSquare of the Random number: " + number * number, Toast.LENGTH_SHORT).show()
    }
}