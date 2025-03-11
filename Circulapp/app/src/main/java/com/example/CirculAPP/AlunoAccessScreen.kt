package com.example.circulapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AlunoAccessScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.65f)
                .background(Color(0xFF004AAD))
        ) {
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.35f)
                .background(Color(0xFFB0B0B0))
        ) {
        }
    }
}
