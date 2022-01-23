package com.artemissoftware.arieswidget.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.Row
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider


import com.artemissoftware.arieswidget.R
import com.artemissoftware.arieswidget.ui.theme.AriesWidgetTheme
import com.artemissoftware.arieswidget.ui.widget.AddWaterClickAction
import com.artemissoftware.arieswidget.ui.widget.AriesWidget.Companion.SIGHTINGS_PER_DAY
import com.artemissoftware.arieswidget.ui.widget.AriesWidget.Companion.WATER_WIDGET_PREFS_KEY
import com.artemissoftware.arieswidget.ui.widget.ClearWaterClickAction


@Composable
fun AriesWidgetScreen(
    modifier: GlanceModifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        val prefs = currentState<Preferences>()
        val glassesOfWater = prefs[intPreferencesKey(WATER_WIDGET_PREFS_KEY)] ?: 0


        WaterWidgetCounter(
            context = context,
            glassesOfWater = glassesOfWater,
            modifier = GlanceModifier
                .fillMaxWidth()
                .defaultWeight()
        )
        SightingsGoal(
            context = context,
            numberOfSightings = glassesOfWater,
            modifier = GlanceModifier
                .fillMaxWidth()
                .defaultWeight()
        )
        WaterWidgetButtonLayout(
            modifier = GlanceModifier
                .fillMaxSize()
                .defaultWeight()
        )
    }
}



@SuppressLint("StringFormatInvalid")
@Composable
fun WaterWidgetCounter(
    context: Context,
    glassesOfWater: Int,
    modifier: GlanceModifier
) {
    Text(
        text = context.getString(R.string.aries_constellation, glassesOfWater),
        modifier = modifier,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = ColorProvider(
                color = Color.White
            )
        )
    )
}

@Composable
private fun SightingsGoal(
    context: Context,
    numberOfSightings: Int,
    modifier: GlanceModifier
) {
    Text(
        text =
        when {
            numberOfSightings >= SIGHTINGS_PER_DAY -> context.getString(
                R.string.good_job
            )
            else -> context.getString(
                R.string.keep_looking
            )
        },
        modifier = modifier,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = ColorProvider(
                color = Color.White
            )
        ),
    )
}


@Composable
fun WaterWidgetButtonLayout(
    modifier: GlanceModifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            provider = ImageProvider(
                resId = R.drawable.ic_restart
            ),
            contentDescription = null,
            modifier = GlanceModifier
                .clickable(
                    onClick = actionRunCallback<ClearWaterClickAction>()
                )
                .defaultWeight()
        )
        Image(
            provider = ImageProvider(
                resId = R.drawable.ic_add
            ),
            contentDescription = null,
            modifier = GlanceModifier
                .clickable(
                    onClick = actionRunCallback<AddWaterClickAction>()
                )
                .defaultWeight()
        )
    }
}



@Preview(showBackground = true)
@Composable
private fun WaterWidgetCounterPreview() {
    AriesWidgetTheme {
        //WaterWidgetCounter(glassesOfWater = 1, modifier = GlanceModifier)
    }
}


