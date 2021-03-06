package com.artemissoftware.arieswidget.ui.widget

import androidx.glance.appwidget.GlanceAppWidget
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.background
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.background
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.artemissoftware.arieswidget.R
import com.artemissoftware.arieswidget.ui.AriesWidgetScreen


class AriesWidget : GlanceAppWidget() {

    override var stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    @Composable
    override fun Content() {
        AriesWidgetScreen(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(imageProvider = ImageProvider(
                    resId = R.drawable.ic_launcher_background
                )
                )
//                .background(
//                    day = Color.Blue,
//                    night = Color.DarkGray
//                )
                .appWidgetBackground()
                .cornerRadius(16.dp)
                .padding(8.dp),
        )
    }

    companion object {
        const val SIGHTINGS_PER_DAY = 1

        const val WATER_WIDGET_PREFS_KEY = "WATER_WIDGET_PREFS_KEY"
        const val MAX_GLASSES = 999
    }

}

class AriesWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = AriesWidget()
}