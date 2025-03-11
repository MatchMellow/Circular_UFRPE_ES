package com.example.circulapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.navigation.NavController
import com.example.CirculAPP.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun InformarParadaScreen(navController: NavController) {
    val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("motorista/localizacao")

    var selecionouParada by remember { mutableStateOf(false) }
    var tempoRestante by remember { mutableStateOf(10) }
    val scope = rememberCoroutineScope()

    fun selecionarParada(parada: String) {
        selecionouParada = true
        tempoRestante = 10

        databaseRef.setValue(
            mapOf(
                "latitude" to null,
                "longitude" to null,
                "status" to "parado",
                "parada" to parada
            )
        )

        scope.launch {
            while (tempoRestante > 0) {
                delay(1000L)
                tempoRestante--
            }
            selecionouParada = false
        }
    }

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

                Spacer(modifier = Modifier.height(0.dp))

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
                    text = "Qual o motivo da parada?",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
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
                if (selecionouParada) {
                    Text(
                        text = "Aguarde $tempoRestante segundos para iniciar a rota ou informar outra parada...",
                        color = Color(0xFF004AAD),
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = { selecionarParada("Abastecimento") },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(45.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selecionouParada) Color.Gray else Color(0xFF004AAD)
                    ),
                    enabled = !selecionouParada
                ) {
                    Text("Abastecimento", color = Color.White)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
                ) {
                    Button(
                        onClick = { selecionarParada("Troca de Turno") },
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selecionouParada) Color.Gray else Color(0xFF004AAD)
                        ),
                        enabled = !selecionouParada
                    ) {
                        Text("Troca de turno", color = Color.White)
                    }

                    Button(
                        onClick = { selecionarParada("Refeição") },
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selecionouParada) Color.Gray else Color(0xFF004AAD)
                        ),
                        enabled = !selecionouParada
                    ) {
                        Text("Refeição", color = Color.White)
                    }
                }

                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(45.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selecionouParada) Color.Gray else Color(0xFF004AAD)
                    ),
                    enabled = !selecionouParada
                ) {
                    Text("Voltar", color = Color.White)
                }
            }
        }
    }
}
