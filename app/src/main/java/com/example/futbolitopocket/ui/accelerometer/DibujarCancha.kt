package com.example.futbolitopocket.ui.accelerometer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp


fun DrawScope.drawGoal(
    topLeft: Offset,
    width: Float,
    height: Float,
    strokeWidth: Float,
    color: Color,
    isTopGoal: Boolean
) {
    // Draw the left bar
    drawLine(
        color = color,
        start = topLeft,
        end = Offset(topLeft.x, topLeft.y + height),
        strokeWidth = strokeWidth
    )
    // Draw the right bar
    drawLine(
        color = color,
        start = Offset(topLeft.x + width, topLeft.y),
        end = Offset(topLeft.x + width, topLeft.y + height),
        strokeWidth = strokeWidth
    )
    // Draw the top bar for the top goal or bottom bar for the bottom goal
    if (isTopGoal) {
        drawLine(
            color = color,
            start = topLeft,
            end = Offset(topLeft.x + width, topLeft.y),
            strokeWidth = strokeWidth
        )
    } else {
        drawLine(
            color = color,
            start = Offset(topLeft.x, topLeft.y + height),
            end = Offset(topLeft.x + width, topLeft.y + height),
            strokeWidth = strokeWidth
        )
    }
}


@Composable
fun FootballFieldCanvas(goalWidth: Float, goalHeight: Float) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        val lines = listOf(
            // Portería de arriba
            Pair(Offset((width - goalWidth) / 2, 0f), Offset((width + goalWidth) / 2, 0f)),
            Pair(Offset((width - goalWidth) / 2, 0f), Offset((width - goalWidth) / 2, goalHeight)),
            Pair(Offset((width + goalWidth) / 2, 0f), Offset((width + goalWidth) / 2, goalHeight)),

            // Portería de abajo
            Pair(Offset((width - goalWidth) / 2, height), Offset((width + goalWidth) / 2, height)),
            Pair(Offset((width - goalWidth) / 2, height - goalHeight), Offset((width - goalWidth) / 2, height)),
            Pair(Offset((width + goalWidth) / 2, height - goalHeight), Offset((width + goalWidth) / 2, height)),

            // Líneas del lado izquierdo
            Pair(Offset(165.0f, 310.0f), Offset(165.0f, 567.0f)),
            Pair(Offset(165.0f, 667.0f), Offset(165.0f, 867.0f)),
            Pair(Offset(165.0f, 967.0f), Offset(165.0f, 1067.0f)),
            Pair(Offset(165.0f, 1167.0f), Offset(165.0f, 1667.0f)),
            Pair(Offset(165.0f, 1767.0f), Offset(165.0f, 1867.0f)),

            // Líneas del lado derecho
            Pair(Offset(919.0f, 310.0f), Offset(919.0f, 567.0f)),
            Pair(Offset(919.0f, 667.0f), Offset(919.0f, 867.0f)),
            Pair(Offset(919.0f, 967.0f), Offset(919.0f, 1067.0f)),
            Pair(Offset(919.0f, 1167.0f), Offset(919.0f, 1667.0f)),
            Pair(Offset(919.0f, 1767.0f), Offset(919.0f, 1867.0f)),

            // Líneas superiores en el centro vertical
            Pair(Offset(302.0f,407.0f), Offset(302.0f,507.0f)),
            Pair(Offset(402.0f,407.0f), Offset(402.0f,507.0f)),
            Pair(Offset(502.0f,407.0f), Offset(502.0f,507.0f)),
            Pair(Offset(602.0f,407.0f), Offset(602.0f,507.0f)),
            Pair(Offset(702.0f,407.0f), Offset(702.0f,507.0f)),
            Pair(Offset(802.0f,407.0f), Offset(802.0f,507.0f)),

            // Líneas inferiores en el centro horizontal
            Pair(Offset(300.0f,714.0f), Offset(400.0f,714.0f)),
            Pair(Offset(500.0f,714.0f), Offset(600.0f,714.0f)),
            Pair(Offset(700.0f,714.0f), Offset(800.0f,714.0f)),

            // Líneas en el centro vertical 2
            Pair(Offset(302.0f,820.0f), Offset(302.0f,920.0f)),
            Pair(Offset(402.0f,820.0f), Offset(402.0f,920.0f)),
            Pair(Offset(502.0f,820.0f), Offset(502.0f,920.0f)),
            Pair(Offset(602.0f,820.0f), Offset(602.0f,920.0f)),
            Pair(Offset(702.0f,820.0f), Offset(702.0f,920.0f)),
            Pair(Offset(802.0f,820.0f), Offset(802.0f,920.0f)),

            // Líneas en el centro horizontal 2
            Pair(Offset(300.0f,1020.0f), Offset(400.0f,1020.0f)),
            Pair(Offset(500.0f,1020.0f), Offset(600.0f,1020.0f)),
            Pair(Offset(700.0f,1020.0f), Offset(800.0f,1020.0f)),

            // Líneas al lado de la pelota 1
            Pair(Offset(300.0f, 1100.0f), Offset(300.0f, 1200.0f)),
            Pair(Offset(400.0f, 1100.0f), Offset(400.0f, 1200.0f)),
            Pair(Offset(700.0f, 1100.0f), Offset(700.0f, 1200.0f)),
            Pair(Offset(800.0f, 1100.0f), Offset(800.0f, 1200.0f)),

            // Líneas en el centro horizontal 3
            Pair(Offset(300.0f,1300.0f), Offset(400.0f,1300.0f)),
            Pair(Offset(500.0f,1300.0f), Offset(600.0f,1300.0f)),
            Pair(Offset(700.0f,1300.0f), Offset(800.0f,1300.0f)),

            // Líneas en el centro vertical 3
            Pair(Offset(302.0f,1407.0f), Offset(302.0f,1507.0f)),
            Pair(Offset(402.0f,1407.0f), Offset(402.0f,1507.0f)),
            Pair(Offset(502.0f,1407.0f), Offset(502.0f,1507.0f)),
            Pair(Offset(602.0f,1407.0f), Offset(602.0f,1507.0f)),
            Pair(Offset(702.0f,1407.0f), Offset(702.0f,1507.0f)),
            Pair(Offset(802.0f,1407.0f), Offset(802.0f,1507.0f)),

            // Líneas en el centro horizontal 4
            Pair(Offset(300.0f,1620.0f), Offset(400.0f,1620.0f)),
            Pair(Offset(500.0f,1620.0f), Offset(600.0f,1620.0f)),
            Pair(Offset(700.0f,1620.0f), Offset(800.0f,1620.0f)),

            // Líneas en el centro vertical 4
            Pair(Offset(302.0f,1727.0f), Offset(302.0f,1827.0f)),
            Pair(Offset(402.0f,1727.0f), Offset(402.0f,1827.0f)),
            Pair(Offset(502.0f,1727.0f), Offset(502.0f,1827.0f)),
            Pair(Offset(602.0f,1727.0f), Offset(602.0f,1827.0f)),
            Pair(Offset(702.0f,1727.0f), Offset(702.0f,1827.0f)),
            Pair(Offset(802.0f,1727.0f), Offset(802.0f,1827.0f)),

            // Líneas cerca de la portería inferior
            Pair(Offset(0.0f,1997.0f), Offset(100.0f,1997.0f)),
            Pair(Offset(183.0f,1997.0f), Offset(283.0f,1997.0f)),
            Pair(Offset(383.0f,1997.0f), Offset(483.0f,1997.0f)),
            Pair(Offset(583.0f,1997.0f), Offset(683.0f,1997.0f)),
            Pair(Offset(783.0f,1997.0f), Offset(883.0f,1997.0f)),
            Pair(Offset(983.0f,1997.0f), Offset(1083.0f,1997.0f)),

            // Líneas cerca de la portería superior
            Pair(Offset(0.0f,230.0f), Offset(100.0f,230.0f)),
            Pair(Offset(183.0f,230.0f), Offset(283.0f,230.0f)),
            Pair(Offset(383.0f,230.0f), Offset(483.0f,230.0f)),
            Pair(Offset(583.0f,230.0f), Offset(683.0f,230.0f)),
            Pair(Offset(783.0f,230.0f), Offset(883.0f,230.0f)),
            Pair(Offset(983.0f,230.0f), Offset(1083.0f,230.0f))
        )

        for (line in lines) {
            val (start, end) = line
            drawLine(
                color = Color.Black,
                start = start,
                end = end,
                strokeWidth = 4.dp.toPx()
            )
        }
    }
}
