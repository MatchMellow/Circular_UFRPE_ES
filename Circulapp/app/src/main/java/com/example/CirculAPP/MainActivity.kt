package com.example.CirculAPP

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.CirculAPP.ui.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val termosAceitos = sharedPref.getBoolean("termos_aceitos", false)

        if (!termosAceitos) {
            startActivity(Intent(this, TermosDeUsoActivity::class.java))
            finish()
        } else {
            setContent {
                val navController = rememberNavController()
                val startDestination = intent.getStringExtra("startDestination") ?: "userSelection"

                Surface(color = MaterialTheme.colorScheme.background) {
                    NavGraph(navController = navController, startDestination = startDestination)
                }
            }
        }
    }
}