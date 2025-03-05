package com.example.eco.widget

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.updateAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EcoAppWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget = EcoAppWidget()

    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        CoroutineScope(Dispatchers.IO).launch {
            glanceAppWidget.updateAll(context)
        }
    }
}
