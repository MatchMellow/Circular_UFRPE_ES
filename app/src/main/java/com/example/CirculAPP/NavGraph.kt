package com.example.CirculAPP

import MotoristaOptionsScreen
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.circulapp.ui.AlunoScreen
import com.example.circulapp.ui.HorariosScreen
import com.example.circulapp.ui.InformarParadaScreen
import com.example.circulapp.ui.IniciarRotaScreen
import com.example.circulapp.ui.Int2Screen
import com.example.circulapp.ui.ItinerarioScreen
import com.example.circulapp.ui.MotoristaLoginScreen
import com.example.circulapp.ui.UserSelectionScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "userSelection") {

        composable("userSelection") {
            UserSelectionScreen(
                onAlunoSelected = { navController.navigate("alunoScreen") },
                onMotoristaSelected = { navController.navigate("motoristaLogin") }
            )
        }

        composable("alunoScreen") {
            AlunoScreen(navController)
        }

        composable("motoristaLogin") {
            MotoristaLoginScreen(navController)
        }

        composable("motoristaOptions") {
            MotoristaOptionsScreen(
                navController = navController,
                onIniciarRota = {
                    // Navegar para MapsActivity com um Intent
                    val context = LocalContext.current
                    val intent = Intent(context, MapsActivity::class.java)
                    context.startActivity(intent)
                },
                onInformarParada = { navController.navigate("informarParada") }
            )
        }

        composable("iniciarRota") {
            IniciarRotaScreen(navController)
        }

        composable("informarParada") {
            InformarParadaScreen(navController)
        }

        composable("itinerario") {
            ItinerarioScreen(navController)
        }

        composable("horarios") {
            HorariosScreen(navController)
        }

        composable("aposAs19hScreen") {
            Int2Screen(navController)
        }
    }
}
