package com.fiap.lara.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fiap.lara.model.NutrizJourney

@Composable
fun JourneyCard(
    journey: NutrizJourney,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                StatusChip(label = journey.currentStage.label, color = journey.currentStage.color())
                StatusChip(label = journey.sentiment.label, color = journey.sentiment.color())
            }
            Text(
                text = journey.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = journey.babyProfile,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            LinearProgressIndicator(
                progress = { journey.readinessScore / 100f },
                modifier = Modifier.fillMaxWidth(),
                color = journey.sentiment.color(),
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.LocationOn, contentDescription = null)
                    Text(journey.city)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Speed, contentDescription = null)
                    Text("${journey.readinessScore}/100")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Favorite, contentDescription = null)
                    Text("Dia ${journey.postpartumDay}")
                }
            }
        }
    }
}
