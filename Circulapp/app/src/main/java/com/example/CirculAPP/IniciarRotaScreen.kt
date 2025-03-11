package com.example.CirculAPP.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.CirculAPP.R

@Composable
fun IniciarRotaScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .weight(0.65f)
                .background(Color(0xFF004AAD))
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "\n\nBem-vindo",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(10.dp))

                Image(
                    painter = painterResource(id = R.drawable.imagem),
                    contentDescription = "Imagem de exemplo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)
                )
                BasicText(
                    text = "_________________",
                    style = MaterialTheme.typography.headlineMedium.copy(color = Color.White),
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Selecione a rota que deseja iniciar:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .weight(0.35f)
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
                ) {
                    Button(
                        onClick = {
                            Log.d("NAV", "Iniciando rota: 7h às 19h")
                            navController.navigate("mapsMotorista/7h às 19h")
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004AAD))
                    ) {
                        Text("7h às 19h", color = Color.White)
                    }

                    Button(
                        onClick = {
                            Log.d("NAV", "Iniciando rota: 19h às 22h")
                            navController.navigate("mapsMotorista/19h às 22h")
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004AAD))
                    ) {
                        Text("19h às 22h", color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(45.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004AAD))
                ) {
                    Text("Voltar", color = Color.White)
                }
            }
        }
    }
}
