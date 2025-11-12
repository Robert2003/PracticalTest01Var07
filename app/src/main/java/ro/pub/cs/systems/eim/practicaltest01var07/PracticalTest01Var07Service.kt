package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import kotlin.random.Random

class PracticalTest01Var07Service : Service() {

    companion object {
        const val ACTION_UPDATE_VALUES = "ro.pub.cs.systems.eim.practicaltest01var07.ACTION_UPDATE_VALUES"
        const val EXTRA_VALUE_1 = "EXTRA_VALUE_1"
        const val EXTRA_VALUE_2 = "EXTRA_VALUE_2"
        const val EXTRA_VALUE_3 = "EXTRA_VALUE_3"
        const val EXTRA_VALUE_4 = "EXTRA_VALUE_4"
        const val BROADCAST_INTERVAL = 10000L
    }

    private val handler = Handler(Looper.getMainLooper())
    private val broadcastRunnable = object : Runnable {
        override fun run() {
            sendBroadcast()
            handler.postDelayed(this, BROADCAST_INTERVAL)
        }
    }

    override fun onCreate() {
        super.onCreate()
        handler.postDelayed(broadcastRunnable, BROADCAST_INTERVAL)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    private fun sendBroadcast() {
        val intent = Intent(ACTION_UPDATE_VALUES)
        intent.setPackage(packageName)
        intent.putExtra(EXTRA_VALUE_1, Random.nextInt(0, 100))
        intent.putExtra(EXTRA_VALUE_2, Random.nextInt(0, 100))
        intent.putExtra(EXTRA_VALUE_3, Random.nextInt(0, 100))
        intent.putExtra(EXTRA_VALUE_4, Random.nextInt(0, 100))
        sendBroadcast(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(broadcastRunnable)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}