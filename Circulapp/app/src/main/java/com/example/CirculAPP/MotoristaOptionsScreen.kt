package com.example.CirculAPP

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.CirculAPP.R

@Composable
fun MotoristaOptionsScreen(navController: NavController) {
    val context = LocalContext.current

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
                        .padding(top = 30.dp, bottom = 0.dp)
                )

                BasicText(
                    text = "_________________",
                    style = MaterialTheme.typography.headlineMedium.copy(color = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 0.dp)
                )

                Text(
                    text = "Selecione uma das opções abaixo para iniciar rota ou informar uma parada",
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
                            Log.d("NAV", "Navegando para escolha de horário da rota")
                            navController.navigate("iniciarRota")
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004AAD))
                    ) {
                        Text("Iniciar Rota", color = Color.White)
                    }

                    Button(
                        onClick = {
                            Log.d("NAV", "Navegando para Informar Parada")
                            navController.navigate("informarParada")
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004AAD))
                    ) {
                        Text("Informar Parada", color = Color.White)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        Log.d("NAV", "Saindo para UserSelection")
                        navController.navigate("userSelection")
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(45.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004AAD))
                ) {
                    Text("Sair", color = Color.White)
                }
            }
        }
    }
}
