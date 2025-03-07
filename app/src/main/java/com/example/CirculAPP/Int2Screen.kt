package com.example.circulapp.ui

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.CirculAPP.R


@Composable
fun Int2Screen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.65f)
                .background(Color(0xFF004AAD))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "\n\nMapa do itinerário",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(0.dp))

            Image(
                painter = painterResource(id = R.drawable.itinerariopos19),
                contentDescription = "Mapa do itinerário",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(0.dp))

            BasicText(
                text = "_________________",
                style = TextStyle(
                    color = Color.White,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Horário",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Text("🕒 19:00 - 22:00", color = Color.White)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.35f)
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(64.dp))

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
