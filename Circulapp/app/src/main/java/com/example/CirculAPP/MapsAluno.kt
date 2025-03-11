package com.example.CirculAPP

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.firebase.database.*
import com.example.CirculAPP.databinding.ActivityMapsBinding
import java.util.Calendar

class MapsAluno : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val database = FirebaseDatabase.getInstance()
    private val motoristaRef = database.getReference("motorista/localizacao")
    private val rotaRef = database.getReference("motorista/rota")
    private var motoristaMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        motoristaRef.keepSynced(true)
        listenMotoristaLocation()

        val btnVoltar = findViewById<Button>(R.id.btnVoltar)
        btnVoltar.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun listenMotoristaLocation() {
        motoristaRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("FirebaseDebug", "onDataChange DISPARADO!")

                val latitude = snapshot.child("latitude").getValue(Double::class.java)
                val longitude = snapshot.child("longitude").getValue(Double::class.java)
                val status = snapshot.child("status").getValue(String::class.java) ?: "Indisponível"
                val parada = snapshot.child("parada").getValue(String::class.java) ?: "Nenhuma"

                val rota = snapshot.child("rota").getValue(String::class.java) ?: "Desconhecida"

                Log.d("FirebaseDebug", "Latitude: $latitude, Longitude: $longitude, Status: $status, Parada: $parada, Rota: $rota")

                val mensagemTexto = findViewById<TextView>(R.id.mensagemTexto)
                val mensagemContainer = findViewById<LinearLayout>(R.id.mensagemContainer)

                runOnUiThread {
                    mensagemContainer.visibility = View.VISIBLE
                }

                if (latitude != null && longitude != null) {
                    val motoristaLocation = LatLng(latitude, longitude)

                    runOnUiThread {
                        motoristaMarker?.remove()
                        motoristaMarker = mMap.addMarker(
                            MarkerOptions()
                                .position(motoristaLocation)
                                .title("Circular Está Aqui")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_motorista))
                        )

                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(motoristaLocation, 16f))

                        if (status == "parado") {
                            when (parada) {
                                "Abastecimento" -> mensagemTexto.text = "🛑 Circular parou para: Abastecimento ⛽"
                                "Troca de Turno" -> mensagemTexto.text = "🛑 Circular parou para: Troca de Turno 🔄"
                                "Refeição" -> mensagemTexto.text = "🛑 Circular parou para: Refeição 🍽️"
                                "Outro" -> mensagemTexto.text = "🛑 Circular parou para: Outro motivo"
                                else -> mensagemTexto.text = "🛑 Circular parado em local desconhecido"
                            }
                        } else if (status == "em rota") {
                            mensagemTexto.text = "🚍 Circular está em rota! Rota: $rota"
                        } else {
                            mensagemTexto.text = "Localização indisponível"
                        }



                        Log.d("UIUpdate", "Mensagem atualizada para: ${mensagemTexto.text}")
                    }
                } else {
                    runOnUiThread {
                        mensagemTexto.text = "Localização indisponível"
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Erro ao acessar Firebase: ${error.message}")
                runOnUiThread {
                    findViewById<TextView>(R.id.mensagemTexto).text = "Erro ao obter localização"
                }
            }
        })

        rotaRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val rota = snapshot.getValue(String::class.java) ?: "Desconhecida"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Erro ao acessar Firebase para rota: ${error.message}")
            }
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnCameraMoveListener {
        }

        val ufrpe = LatLng(-8.017335662754297, -34.949115979298256)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ufrpe, 15f))

        val uiSettings = mMap.uiSettings
        uiSettings.isScrollGesturesEnabled = true
        uiSettings.isZoomGesturesEnabled = true

        addFixedMarkers()
    }

    private fun addFixedMarkers() {
        val locais = listOf(
            LatLng(-8.020046094447412, -34.95411159356041) to "Zootecnia",
            LatLng(-8.017728749495607, -34.94478152968631) to "Ceagri Computação",
            LatLng(-8.014528748288745, -34.95043753092098) to "Reitoria da UFRPE",
            LatLng(-8.018405587113017, -34.95003668228588) to "Cegoe",
            LatLng(-8.01621201175587, -34.94950232571275) to "Biblioteca Setorial",
            LatLng(-8.015908378317642, -34.950759580760014) to "Guarita Piscina"
        )

        locais.forEach { (posicao, titulo) ->
            mMap.addMarker(MarkerOptions().position(posicao).title(titulo))
        }
    }
}
