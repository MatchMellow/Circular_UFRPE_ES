package com.example.CirculAPP

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.MapView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.remember

@Composable
fun MapaAoVivoScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Mapa Ao Vivo")

        // Exemplo de integração com o MapView do Google Maps
        GoogleMapView()
    }
}

@Composable
fun GoogleMapView() {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }

    DisposableEffect(mapView) {
        mapView.onCreate(null)
        onDispose {
            mapView.onDestroy()
        }
    }

    AndroidView(
        factory = {
            mapView.apply {
                getMapAsync { googleMap ->
                    // Aqui você pode adicionar configurações do GoogleMap
                    googleMap.uiSettings.isZoomControlsEnabled = true
                    // Adicionar mais configurações se necessário
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
