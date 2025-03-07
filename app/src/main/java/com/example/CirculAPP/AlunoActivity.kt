package com.example.CirculAPP

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.CirculAPP.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class AlunoActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView
    private lateinit var circularStatusTextView: TextView
    private lateinit var localizacaoStatusTextView: TextView

    private val db = FirebaseFirestore.getInstance()
    private lateinit var motoristaRef: DocumentReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aluno) // Usando o layout que acabamos de criar

        circularStatusTextView = findViewById(R.id.circular_status)
        localizacaoStatusTextView = findViewById(R.id.localizacao_status)
        mapView = findViewById(R.id.mapView)

        // Inicializa o MapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        // Aqui você define o ID do motorista ou da rota
        motoristaRef = db.collection("localizacao_motorista").document("id_motorista")

        // Escuta as atualizações do Firebase
        motoristaRef.addSnapshotListener { documentSnapshot, e ->
            if (e != null) {
                Log.w("Firebase", "Erro ao acessar o documento", e)
                return@addSnapshotListener
            }

            if (documentSnapshot != null && documentSnapshot.exists()) {
                val status = documentSnapshot.getString("status")
                val latitude = documentSnapshot.getDouble("latitude")
                val longitude = documentSnapshot.getDouble("longitude")
                val localizacaoDisponivel = latitude != null && longitude != null

                // Atualiza o status do Circular
                circularStatusTextView.text = "Circular Status: $status"
                localizacaoStatusTextView.text = if (localizacaoDisponivel) {
                    "Localização: Disponível"
                } else {
                    "Localização: Não Disponível"
                }

                // Exibe a localização no mapa se disponível
                if (localizacaoDisponivel && status == "Rodando") {
                    val position = LatLng(latitude!!, longitude!!)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15f))
                    mMap.addMarker(MarkerOptions().position(position).title("Circular está aqui"))
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
    }

    // Não se esqueça de sobrescrever os métodos de ciclo de vida do MapView
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
