package com.fiap.lara.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fiap.lara.model.NutrizJourney
import com.fiap.lara.ui.components.AppTopBar
import com.fiap.lara.ui.components.StatusChip
import com.fiap.lara.ui.components.color

@Composable
fun JourneyDetailScreen(
    journey: NutrizJourney?,
    onBack: () -> Unit,
    onHumanHandoff: (NutrizJourney) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            AppTopBar(title = "Detalhes da nutriz", onBack = onBack)
            if (journey == null) {
                Text("Jornada nao encontrada.")
                return@Column
            }
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    StatusChip(label = journey.currentStage.label, color = journey.currentStage.color())
                    StatusChip(label = journey.sentiment.label, color = journey.sentiment.color())
                }
                Text(
                    text = journey.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                InfoRow(Icons.Filled.LocationOn, "${journey.homeAddress} - BLH a ${journey.nearestMilkBank.distanceKm} km")
                InfoRow(Icons.Filled.AutoAwesome, "Score de prontidao: ${journey.readinessScore}/100")
                LinearProgressIndicator(
                    progress = { journey.readinessScore / 100f },
                    modifier = Modifier.fillMaxWidth(),
                    color = journey.sentiment.color(),
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
                DetailSection("Perfil do bebe", journey.babyProfile)
                DetailSection("Banco de leite sugerido", "${journey.nearestMilkBank.name}\nTelefone: ${journey.nearestMilkBank.phone}\nProximo horario: ${journey.nearestMilkBank.nextAvailableSlot}")
                BulletCard(title = "Memoria emocional", items = journey.emotionalMemory)
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "Mensagens da jornada",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        journey.messages.forEach { message ->
                            Text(
                                text = "Dia ${message.day} - ${message.title}",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = message.text,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                if (journey.readinessScore >= 70) {
                    Button(onClick = { onHumanHandoff(journey) }, modifier = Modifier.fillMaxWidth()) {
                        Icon(Icons.Filled.LocationOn, contentDescription = null)
                        Text("Simular convite para o BLH")
                    }
                } else {
                    OutlinedButton(onClick = { onHumanHandoff(journey) }, modifier = Modifier.fillMaxWidth()) {
                        Icon(Icons.Filled.SupportAgent, contentDescription = null)
                        Text("Acionar atendente Lactare")
                    }
                }
            }
        }
    }
}

@Composable
private fun BulletCard(title: String, items: List<String>) {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            items.forEach { item ->
                Text("- $item", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
private fun DetailSection(title: String, body: String) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
        Text(body, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun InfoRow(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        Text(text, style = MaterialTheme.typography.bodyMedium)
    }
}
