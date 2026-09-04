package com.fiap.lara.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fiap.lara.model.JourneyStage
import com.fiap.lara.model.Sentiment
import com.fiap.lara.ui.theme.LaraNavy
import com.fiap.lara.ui.theme.LaraPink
import com.fiap.lara.ui.theme.LaraRose
import com.fiap.lara.ui.theme.LaraSlate
import com.fiap.lara.ui.theme.LaraSuccess
import com.fiap.lara.ui.theme.LaraWarning

@Composable
fun StatusChip(label: String, color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(color.copy(alpha = 0.12f), RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            color = color,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}

fun JourneyStage.color(): Color = when (this) {
    JourneyStage.Birth -> LaraSlate
    JourneyStage.Welcome -> LaraPink
    JourneyStage.Education -> LaraWarning
    JourneyStage.Invitation -> LaraNavy
    JourneyStage.Conversion -> LaraSuccess
}

fun Sentiment.color(): Color = when (this) {
    Sentiment.Secure -> LaraSuccess
    Sentiment.Tired -> LaraWarning
    Sentiment.Hesitant -> LaraRose
    Sentiment.Ready -> LaraNavy
}
