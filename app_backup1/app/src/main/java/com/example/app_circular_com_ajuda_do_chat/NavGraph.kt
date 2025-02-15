package com.example.app_circular_com_ajuda_do_chat.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app_circular_com_ajuda_do_chat.ui.*

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
                onIniciarRota = { navController.navigate("iniciarRota") },
                onInformarParada = { navController.navigate("informarParada") }
            )
        }

        composable("iniciarRota") {
            IniciarRotaScreen(navController)
        }

        composable("informarParada") {
            InformarParadaScreen(navController)
        }
    }
}
