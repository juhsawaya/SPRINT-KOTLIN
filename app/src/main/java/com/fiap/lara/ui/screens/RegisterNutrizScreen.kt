package com.fiap.lara.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fiap.lara.model.NutrizForm
import com.fiap.lara.ui.components.AppTopBar

@Composable
fun RegisterNutrizScreen(
    onBack: () -> Unit,
    onSubmit: (NutrizForm) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var homeAddress by remember { mutableStateOf("") }
    var babyProfile by remember { mutableStateOf("") }
    val canSubmit = name.isNotBlank() &&
        city.isNotBlank() &&
        homeAddress.isNotBlank() &&
        babyProfile.isNotBlank()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            AppTopBar(title = "Cadastrar nutriz", onBack = onBack)
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "Simule o registro feito pelo hospital parceiro no dia 0. A LARA inicia o acolhimento apenas depois de 48h.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                FormField("Nome da nutriz", name) { name = it }
                FormField("Cidade", city) { city = it }
                FormField("Endereco de casa ou bairro", homeAddress) { homeAddress = it }
                OutlinedTextField(
                    value = babyProfile,
                    onValueChange = { babyProfile = it },
                    label = { Text("Perfil do bebe e contexto da mae") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 4
                )
                Text(
                    text = if (canSubmit) {
                        "Pronto para ativar a jornada mockada."
                    } else {
                        "Preencha todos os campos para registrar a nutriz."
                    },
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = if (canSubmit) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
                Button(
                    enabled = canSubmit,
                    onClick = {
                        onSubmit(
                            NutrizForm(
                                name = name,
                                city = city,
                                homeAddress = homeAddress,
                                babyProfile = babyProfile
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ativar jornada LARA")
                }
            }
        }
    }
}

@Composable
private fun FormField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}
