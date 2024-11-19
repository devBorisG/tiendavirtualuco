package com.example.tiendavirtualuco.tracking.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.tracking.TrackingActivity

class DailyNotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // Ensure the intent action matches the expected value
        if (intent?.action == "com.example.tiendavirtualuco.DAILY_NOTIFICATION") {
            Log.d("DailyNotificationReceiver", "onReceive called: Notificación activada")

            val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notificationIntent = Intent(context, TrackingActivity::class.java)
            val pendingIntent: PendingIntent = TaskStackBuilder.create(context).run {
                addNextIntentWithParentStack(notificationIntent)
                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)!!
            }

            val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationCompat.Builder(context, "daily_notification_channel")
            } else {
                NotificationCompat.Builder(context)
            }.apply {
                setSmallIcon(R.drawable.ic_notification)
                setContentTitle("Hola! No olvides mirar los productos que tenemos para ti!")
                setContentText("Pasate por la pagina principal para ver las ofertas del día")
                setPriority(NotificationCompat.PRIORITY_DEFAULT)
                setContentIntent(pendingIntent)
                setAutoCancel(true)
            }.build()

            notificationManager.notify(0, notification)
        } else {
            Log.w("DailyNotificationReceiver", "Received unexpected intent action")
        }
    }
}