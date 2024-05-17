package com.example.futbolitopocket.ui.accelerometer

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset

@Composable
fun NotAvailableDemo() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Sensor no disponible")
    }
}

fun lineIntersectsCircle(start: Offset, end: Offset, center: Offset, radius: Float): Boolean {
    val ac = Offset(center.x - start.x, center.y - start.y)
    val ab = Offset(end.x - start.x, end.y - start.y)
    val ab2 = ab.x * ab.x + ab.y * ab.y
    val acab = ac.x * ab.x + ac.y * ab.y
    val t = acab / ab2

    val h = when {
        t < 0 -> start
        t > 1 -> end
        else -> Offset(start.x + ab.x * t, start.y + ab.y * t)
    }

    val hDist = Offset(center.x - h.x, center.y - h.y)
    return (hDist.x * hDist.x + hDist.y * hDist.y) <= radius * radius
}

fun getLines(width: Float, height: Float, goalWidth: Float, goalHeight: Float): List<Pair<Offset, Offset>> {
    return listOf(
        Pair(Offset((width - goalWidth) / 2, 0f), Offset((width + goalWidth) / 2, 0f)),
        Pair(Offset((width - goalWidth) / 2, 0f), Offset((width - goalWidth) / 2, goalHeight)),
        Pair(Offset((width + goalWidth) / 2, 0f), Offset((width + goalWidth) / 2, goalHeight)),

        Pair(Offset((width - goalWidth) / 2, height), Offset((width + goalWidth) / 2, height)),
        Pair(Offset((width - goalWidth) / 2, height - goalHeight), Offset((width - goalWidth) / 2, height)),
        Pair(Offset((width + goalWidth) / 2, height - goalHeight), Offset((width + goalWidth) / 2, height)),

        //Lineas de lado izquierdo
        Pair(Offset(165.0f, 310.0f), Offset(165.0f, 567.0f)),
        Pair(Offset(165.0f, 667.0f), Offset(165.0f, 867.0f)),
        Pair(Offset(165.0f, 967.0f), Offset(165.0f, 1067.0f)),
        Pair(Offset(165.0f, 1167.0f), Offset(165.0f, 1667.0f)),
        Pair(Offset(165.0f, 1767.0f), Offset(165.0f, 1867.0f)),

        //lineas de lado derecho
        Pair(Offset(919.0f, 310.0f), Offset(919.0f, 567.0f)),
        Pair(Offset(919.0f, 667.0f), Offset(919.0f, 867.0f)),
        Pair(Offset(919.0f, 967.0f), Offset(919.0f, 1067.0f)),
        Pair(Offset(919.0f, 1167.0f), Offset(919.0f, 1667.0f)),
        Pair(Offset(919.0f, 1767.0f), Offset(919.0f, 1867.0f)),

        //lineas de arriba en el cenro vertical
        Pair(Offset(302.0f,407.0f), Offset(302.0f,507.0f)),
        Pair(Offset(402.0f,407.0f), Offset(402.0f,507.0f)),
        Pair(Offset(502.0f,407.0f), Offset(502.0f,507.0f)),
        Pair(Offset(602.0f,407.0f), Offset(602.0f,507.0f)),
        Pair(Offset(702.0f,407.0f), Offset(702.0f,507.0f)),
        Pair(Offset(802.0f,407.0f), Offset(802.0f,507.0f)),

        //lineas de abajo en el centro horizontal
        Pair(Offset(300.0f,714.0f), Offset(400.0f,714.0f)),
        Pair(Offset(500.0f,714.0f), Offset(600.0f,714.0f)),
        Pair(Offset(700.0f,714.0f), Offset(800.0f,714.0f)),

        // linea centro vertical 2
        Pair(Offset(302.0f,820.0f), Offset(302.0f,920.0f)),
        Pair(Offset(402.0f,820.0f), Offset(402.0f,920.0f)),
        Pair(Offset(502.0f,820.0f), Offset(502.0f,920.0f)),
        Pair(Offset(602.0f,820.0f), Offset(602.0f,920.0f)),
        Pair(Offset(702.0f,820.0f), Offset(702.0f,920.0f)),
        Pair(Offset(802.0f,820.0f), Offset(802.0f,920.0f)),

        //linea centro horizontal 2
        Pair(Offset(300.0f,1020.0f), Offset(400.0f,1020.0f)),
        Pair(Offset(500.0f,1020.0f), Offset(600.0f,1020.0f)),
        Pair(Offset(700.0f,1020.0f), Offset(800.0f,1020.0f)),

        //linea al lado de la pelota 1
        Pair(Offset(300.0f, 1100.0f), Offset(300.0f, 1200.0f)),
        Pair(Offset(400.0f, 1100.0f), Offset(400.0f, 1200.0f)),
        Pair(Offset(700.0f, 1100.0f), Offset(700.0f, 1200.0f)),
        Pair(Offset(800.0f, 1100.0f), Offset(800.0f, 1200.0f)),

        //linea centro horizontal 3
        Pair(Offset(300.0f,1300.0f), Offset(400.0f,1300.0f)),
        Pair(Offset(500.0f,1300.0f), Offset(600.0f,1300.0f)),
        Pair(Offset(700.0f,1300.0f), Offset(800.0f,1300.0f)),

        //linea centro vertical 3
        Pair(Offset(302.0f,1407.0f), Offset(302.0f,1507.0f)),
        Pair(Offset(402.0f,1407.0f), Offset(402.0f,1507.0f)),
        Pair(Offset(502.0f,1407.0f), Offset(502.0f,1507.0f)),
        Pair(Offset(602.0f,1407.0f), Offset(602.0f,1507.0f)),
        Pair(Offset(702.0f,1407.0f), Offset(702.0f,1507.0f)),
        Pair(Offset(802.0f,1407.0f), Offset(802.0f,1507.0f)),

        //linea centro horizontal 4
        Pair(Offset(300.0f,1620.0f), Offset(400.0f,1620.0f)),
        Pair(Offset(500.0f,1620.0f), Offset(600.0f,1620.0f)),
        Pair(Offset(700.0f,1620.0f), Offset(800.0f,1620.0f)),

        //linea centro vertical 4
        Pair(Offset(302.0f,1727.0f), Offset(302.0f,1827.0f)),
        Pair(Offset(402.0f,1727.0f), Offset(402.0f,1827.0f)),
        Pair(Offset(502.0f,1727.0f), Offset(502.0f,1827.0f)),
        Pair(Offset(602.0f,1727.0f), Offset(602.0f,1827.0f)),
        Pair(Offset(702.0f,1727.0f), Offset(702.0f,1827.0f)),
        Pair(Offset(802.0f,1727.0f), Offset(802.0f,1827.0f)),

        //lineas cerca de la porteria abajo
        Pair(Offset(0.0f,1997.0f), Offset(100.0f,1997.0f)),
        Pair(Offset(183.0f,1997.0f), Offset(283.0f,1997.0f)),
        Pair(Offset(383.0f,1997.0f), Offset(483.0f,1997.0f)),
        Pair(Offset(583.0f,1997.0f), Offset(683.0f,1997.0f)),
        Pair(Offset(783.0f,1997.0f), Offset(883.0f,1997.0f)),
        Pair(Offset(983.0f,1997.0f), Offset(1083.0f,1997.0f)),

        //lineas cerca de la porteria arriba
        Pair(Offset(0.0f,230.0f), Offset(100.0f,230.0f)),
        Pair(Offset(183.0f,230.0f), Offset(283.0f,230.0f)),
        Pair(Offset(383.0f,230.0f), Offset(483.0f,230.0f)),
        Pair(Offset(583.0f,230.0f), Offset(683.0f,230.0f)),
        Pair(Offset(783.0f,230.0f), Offset(883.0f,230.0f)),
        Pair(Offset(983.0f,230.0f), Offset(1083.0f,230.0f)),

        )
}

fun getScreenSize(context: Context): Pair<Int, Int> {
    val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    val width = displayMetrics.widthPixels
    val height = displayMetrics.heightPixels
    return Pair(width, height)
}