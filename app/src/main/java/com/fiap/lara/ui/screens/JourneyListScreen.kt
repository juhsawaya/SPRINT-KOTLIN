package com.fiap.lara.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fiap.lara.model.NutrizJourney
import com.fiap.lara.ui.components.AppTopBar
import com.fiap.lara.ui.components.JourneyCard

@Composable
fun JourneyListScreen(
    journeys: List<NutrizJourney>,
    onBack: () -> Unit,
    onJourneyClick: (NutrizJourney) -> Unit,
    onRegister: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onRegister,
                icon = { Icon(Icons.Filled.PersonAdd, contentDescription = null) },
                text = { Text("Nova nutriz") }
            )
        }
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                AppTopBar(title = "Jornadas LARA", onBack = onBack)
                Text(
                    text = "Nutrizes acompanhadas por memoria emocional, score de prontidao e etapa da jornada.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 96.dp)
                ) {
                    items(journeys, key = { it.id }) { journey ->
                        JourneyCard(journey = journey, onClick = { onJourneyClick(journey) })
                    }
                }
            }
        }
    }
}
