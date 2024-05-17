package com.example.futbolitopocket.ui.accelerometer

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ricknout.composesensors.accelerometer.isAccelerometerSensorAvailable
import dev.ricknout.composesensors.accelerometer.rememberAccelerometerSensorValueAsState

@Composable
fun AccelerometerDemo() {
    var scoreLeft by remember { mutableStateOf(0) }
    var scoreRight by remember { mutableStateOf(0) }

    val Context = LocalContext.current

    if (isAccelerometerSensorAvailable()) {
        val sensorValue by rememberAccelerometerSensorValueAsState()
        val (x, y, z) = sensorValue.value

        val orientation = LocalConfiguration.current.orientation
        val contentColor = LocalContentColor.current
        val radius = with(LocalDensity.current) { 10.dp.toPx() }
        val goalHeight = with(LocalDensity.current) { 50.dp.toPx() }
        val goalWidth = with(LocalDensity.current) { 100.dp.toPx() }

        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF004D00))) {
            var center by remember { mutableStateOf(Offset.Zero) }

            val screenSize = getScreenSize(Context)
            val screenWidth = screenSize.first.toFloat()
            val screenHeight = screenSize.second.toFloat()

            FootballFieldCanvas(goalWidth = screenWidth, goalHeight = screenHeight)

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures { offset ->
                            Log.d("TouchEvent", "${offset.x}f,${offset.y}f")
                        }
                    }
            ) {
                val width = size.width
                val height = size.height

                if (center == Offset.Zero) {
                    center = Offset(width / 2, height / 2)
                }

                var newCenter = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    Offset(
                        x = (center.x - x).coerceIn(radius, width - radius),
                        y = (center.y + y).coerceIn(radius, height - radius)
                    )
                } else {
                    Offset(
                        x = (center.x + y).coerceIn(radius, width - radius),
                        y = (center.y + x).coerceIn(radius, height - radius)
                    )
                }

                val topGoalTopLeft = Offset((width - goalWidth) / 2, 0f)
                val bottomGoalTopLeft = Offset((width - goalWidth) / 2, height - goalHeight)

                // Detect goal scoring
                if (newCenter.y - radius <= goalHeight && newCenter.x in topGoalTopLeft.x..(topGoalTopLeft.x + goalWidth)) {
                    scoreRight++
                    newCenter = Offset(width / 2, height / 2) // Reset position after scoring
                } else if (newCenter.y + radius >= height - goalHeight && newCenter.x in bottomGoalTopLeft.x..(bottomGoalTopLeft.x + goalWidth)) {
                    scoreLeft++
                    newCenter = Offset(width / 2, height / 2) // Reset position after scoring
                }

                // Check collision with lines
                var collisionDetected = false
                val lines = getLines(width, height, goalWidth, goalHeight)
                for (line in lines) {
                    val (start, end) = line
                    if (lineIntersectsCircle(start, end, newCenter, radius)) {
                        collisionDetected = true
                        break
                    }
                }


                    if (!collisionDetected) {
                        center = newCenter
                    }
                val goalStrokeWidth = 4.dp.toPx()

                drawGoal(
                    topLeft = topGoalTopLeft,
                    width = goalWidth,
                    height = goalHeight,
                    strokeWidth = goalStrokeWidth,
                    color = Color.Black,
                    isTopGoal = true
                )

                drawGoal(
                    topLeft = bottomGoalTopLeft,
                    width = goalWidth,
                    height = goalHeight,
                    strokeWidth = goalStrokeWidth,
                    color = Color.Black,
                    isTopGoal = false
                )


                drawCircle(
                    color = contentColor,
                    radius = radius,
                    center = center
                )
            }

            // Display scores
            Text(
                text = "Goles: $scoreLeft",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp),
                fontSize = 19.sp
            )
            Text(
                text = "Goles: $scoreRight",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                fontSize = 19.sp
            )
        }
    } else {
        NotAvailableDemo()
    }
}
