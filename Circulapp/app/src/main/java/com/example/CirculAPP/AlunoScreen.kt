package com.example.CirculAPP.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.CirculAPP.R

@Composable
fun AlunoScreen(
    navController: NavController,
    onAcessarMapa: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.65f)
                .background(Color(0xFF004AAD))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "\n\nBem-vindo",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Image(
                painter = painterResource(id = R.drawable.imagem),
                contentDescription = "Imagem ilustrativa",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            )

            BasicText(
                text = "_________________",
                style = TextStyle(
                    color = Color.White,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize.value.sp
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Clique em 'Acessar' para acompanhar o circular em tempo real",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.35f)
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { onAcessarMapa() },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF004AAD),
                    contentColor = Color.White
                )
            ) {
                Text("Acessar")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { navController.navigate("itinerario") },
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp)
                        .padding(horizontal = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF004AAD),
                        contentColor = Color.White
                    )
                ) {
                    Text("Itinerário")
                }

                Button(
                    onClick = { navController.navigate("horarios") },
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp)
                        .padding(horizontal = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF004AAD),
                        contentColor = Color.White
                    )
                ) {
                    Text("Horários")
                }
            }

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(45.dp),
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
