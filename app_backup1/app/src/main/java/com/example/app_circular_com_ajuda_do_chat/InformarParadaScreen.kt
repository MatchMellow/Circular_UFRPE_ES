package com.example.app_circular_com_ajuda_do_chat.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun InformarParadaScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Qual o motivo da parada?",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* logica para abastecimento */ }) {
            Text("Abastecimento")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { /* Llogica para troca de motorista */ }) {
            Text("Troca de motorista")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { /* logica para refeição */ }) {
            Text("Refeição")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}
