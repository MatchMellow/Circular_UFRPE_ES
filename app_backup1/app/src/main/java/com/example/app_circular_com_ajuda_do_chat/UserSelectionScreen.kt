package com.example.app_circular_com_ajuda_do_chat.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import com.example.app_circular_com_ajuda_do_chat.R

@Composable
fun UserSelectionScreen(onAlunoSelected: () -> Unit, onMotoristaSelected: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Parte superior com a imagem e texto
        Column(
            modifier = Modifier
                .weight(0.65f)
                .background(Color(0xFF004AAD))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Exibindo a imagem
            Image(
                painter = painterResource(id = R.drawable.imagem),
                contentDescription = "Imagem de exemplo",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, bottom = 25.dp)
            )

            Text(
                text = "Olá, você é aluno ou motorista?",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White, // Cor do texto
                modifier = Modifier.padding(top = 0.dp, bottom = 0.dp)
            )

            BasicText(
                text = "_________________",
                style = TextStyle(color = Color.White, fontSize = MaterialTheme.typography.headlineMedium.fontSize),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 0.dp)
            )

            Text(
                text = "Selecione abaixo uma das opções",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                textAlign = TextAlign.Start
            )
        }

        // Parte branca com os botões um abaixo do outro
        Box(
            modifier = Modifier
                .weight(0.35f)
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
            ) {
                Button(
                    onClick = onAlunoSelected,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004AAD))
                ) {
                    Text(
                        text = "Aluno",
                        color = Color.White
                    )

                }
                Button(
                    onClick = onMotoristaSelected,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004AAD))
                ) {
                    Text(
                        text = "Motorista",
                        color = Color.White
                    )
                }
            }
        }
    }
}
