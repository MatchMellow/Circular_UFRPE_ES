package com.example.circulapp.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.CirculAPP.R


@Composable
fun UserSelectionScreen(onAlunoSelected: () -> Unit, onMotoristaSelected: () -> Unit) {
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
                    text = "\nOlá, você é aluno ou motorista?",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
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
                    style = TextStyle(
                        color = Color.White,
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Selecione abaixo uma das opções",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Start)
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
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
                ) {
                    Button(
                        onClick = onAlunoSelected,
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004AAD))
                    ) {
                        Text("Aluno", color = Color.White)
                    }

                    Button(
                        onClick = onMotoristaSelected,
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004AAD))
                    ) {
                        Text("Motorista", color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (context is Activity) {
                            context.finish()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(45.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004AAD))
                ) {
                    Text("Sair", color = Color.White)
                }
            }
        }
    }
}
