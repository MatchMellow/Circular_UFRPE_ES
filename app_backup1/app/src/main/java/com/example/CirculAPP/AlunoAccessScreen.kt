package com.example.app_circular_com_ajuda_do_chat.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AlunoAccessScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Primeira coluna (superior)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.65f)
                .background(Color(0xFF004AAD))
                .padding(16.dp), // Padding para deixar o conteúdo mais espaçado
            horizontalAlignment = Alignment.CenterHorizontally, // Alinhar conteúdo no centro horizontal
            verticalArrangement = Arrangement.Center // Alinhar conteúdo no centro vertical
        ) {
            Text(
                text = "Bem-vindo ao Sistema de Acesso do Aluno",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
        }

        // Segunda coluna (inferior)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.35f)
                .background(Color(0xFFB0B0B0))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { /* Ação do botão aqui */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Acessar")
            }
        }
    }
}
