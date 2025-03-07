package com.example.circulapp.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

@Composable
fun MapScreen() {
    val db = FirebaseFirestore.getInstance()
    val motoristaRef = db.collection("localizacao_motorista").document("id_motorista")

    // Assumindo que MapView já foi configurado no layout
    // Substitua isso com a implementação real do mapa

    val mapView = MapView(LocalContext.current)
    mapView.getMapAsync { googleMap ->
        motoristaRef.addSnapshotListener { documentSnapshot, e ->
            if (e != null) {
                Log.w("Firebase", "Erro ao acessar o documento", e)
                return@addSnapshotListener
            }

            if (documentSnapshot != null && documentSnapshot.exists()) {
                val latitude = documentSnapshot.getDouble("latitude")
                val longitude = documentSnapshot.getDouble("longitude")

                if (latitude != null && longitude != null) {
                    val position = LatLng(latitude, longitude)
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15f))
                    googleMap.addMarker(MarkerOptions().position(position).title("Circular está aqui"))
                }
            }
        }
    }

    // Certifique-se de que você está lidando corretamente com o ciclo de vida do MapView
}
