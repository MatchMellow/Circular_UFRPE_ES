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
import androidx.navigation.NavController
import com.example.app_circular_com_ajuda_do_chat.R

@Composable
fun AlunoScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.65f)
                .background(Color(0xFF004AAD))
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(0.dp))

            Image(
                painter = painterResource(id = R.drawable.imagem),
                contentDescription = "Imagem de exemplo",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, bottom = 25.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Bem-vindo",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Start
            )
            Spacer(modifier = Modifier.height(0.dp))
            BasicText(
                text = "_________________",
                style = TextStyle(
                    color = Color.White,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 0.dp)
            )
            Text(
                text = "Clique em 'Acessar' para acompanhar o circular em tempo real",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Start
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.35f)
                .background(Color.White)
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF004AAD),
                    contentColor = Color.White
                )
            ) {
                Text("Acessar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF004AAD),
                    contentColor = Color.White
                )
            ) {
                Text("Voltar")
            }
        }
    }
}
