package com.example.app_circular_com_ajuda_do_chat.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MotoristaOptionsScreen(
    navController: NavController,
    onIniciarRota: () -> Unit,
    onInformarParada: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bem vindo!", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Selecione uma das opções abaixo para iniciar rota ou informar uma parada",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onIniciarRota) { Text("Iniciar rota") }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onInformarParada) { Text("Informar parada") }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("userSelection") }) {
            Text("Sair")
        }
    }
}
