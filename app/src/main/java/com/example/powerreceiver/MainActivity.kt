package com.example.powerreceiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.powerreceiver.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    companion object {
        const val ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST"
        const val EXTRA_RANDOM_NUMBER = "extra_random_number"
    }

    private val mReceiver = CustomReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)

        registerReceiver(mReceiver, filter)

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(mReceiver, IntentFilter(ACTION_CUSTOM_BROADCAST))
    }

    override fun onDestroy() {
        unregisterReceiver(mReceiver)

        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(mReceiver)

        super.onDestroy()
    }

    fun sendCustomBroadcast(view: View) {
        val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)

        val num = randomNum().toString()
        binding.number.text = num

        customBroadcastIntent.putExtra(EXTRA_RANDOM_NUMBER, num)
        LocalBroadcastManager.getInstance(this)
            .sendBroadcast(customBroadcastIntent)
    }

    private fun randomNum(): Int {
        return Random().nextInt(20) + 1
    }
}