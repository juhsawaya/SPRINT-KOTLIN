package com.fiap.lara.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fiap.lara.model.NutrizJourney

@Composable
fun HomeScreen(
    journeys: List<NutrizJourney>,
    onStart: () -> Unit,
    onRegister: () -> Unit
) {
    val reachRate = 82
    val engagementRate = 47
    val conversionRate = 23
    val humanAlerts = journeys.count { it.needsHumanSupport || it.readinessScore < 20 }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
                Text(
                    text = "LARA",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Agente de IA com memoria emocional para captar doadoras de leite humano em uma jornada digital de 30 dias.",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
                        .padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HomeMetric(number = "$reachRate%", label = "alcance")
                        HomeMetric(number = "$engagementRate%", label = "engajamento")
                        HomeMetric(number = "$conversionRate%", label = "conversao")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        HomeMetric(number = journeys.size.toString(), label = "nutrizes ativas")
                        HomeMetric(number = humanAlerts.toString(), label = "handoffs")
                        HomeMetric(number = "30", label = "dias de jornada")
                    }
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = onStart,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ListAlt, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Acompanhar jornadas")
                }
                OutlinedButton(
                    onClick = onRegister,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Filled.PersonAdd, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Cadastrar nutriz")
                }
            }
        }
    }
}

@Composable
private fun HomeMetric(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = number,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.82f)
        )
    }
}
