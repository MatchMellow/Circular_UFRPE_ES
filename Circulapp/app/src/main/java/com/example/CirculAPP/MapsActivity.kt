package com.example.CirculAPP

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.firebase.database.*
import com.example.CirculAPP.databinding.ActivityMapsMotoristaBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsMotoristaBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var databaseRef: DatabaseReference
    private var motoristaMarker: Marker? = null
    private var rotaEscolhida: String = "Não definida"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsMotoristaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        databaseRef = FirebaseDatabase.getInstance().getReference("motorista")

        rotaEscolhida = intent?.getStringExtra("rota") ?: "Não definida"

        Log.d("MapsActivity", "Rota escolhida recebida: $rotaEscolhida")

        salvarRotaNoFirebase(rotaEscolhida)

        binding.txtStatusMotorista.text = "🚍 Você está fazendo a rota: $rotaEscolhida!"

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapMotorista) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 500)
            .setMinUpdateIntervalMillis(300)
            .build()

        checkLocationPermission()

        binding.btnEncerrarRota.setOnClickListener {
            binding.txtStatusMotorista.text = "❌ Você está offline!"
            encerrarRota()
        }
    }

    private fun salvarRotaNoFirebase(rota: String) {
        val rotaAtivaRef = databaseRef.child("rotaAtiva")

        val dadosRota = mapOf(
            "horario" to rota,
            "status" to "ativa"
        )

        rotaAtivaRef.setValue(dadosRota)
            .addOnSuccessListener {
                Log.d("Firebase", "Rota ativa salva: $rota")
            }
            .addOnFailureListener { e ->
                Log.e("Firebase", "Erro ao salvar a rota: ${e.message}")
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            startLocationUpdates()
        } else {
            requestPermissions.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
        }
    }

    private val requestPermissions =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startLocationUpdates()
            } else {
                Toast.makeText(this, "Permissão de localização negada", Toast.LENGTH_SHORT).show()
            }
        }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult ?: return
            for (location in locationResult.locations) {
                val userLocation = LatLng(location.latitude, location.longitude)

                motoristaMarker?.remove()
                motoristaMarker = mMap.addMarker(
                    MarkerOptions()
                        .position(userLocation)
                        .title("Circular Está Aqui")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_motorista))
                )

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 16f))

                Log.d("Firebase", "Enviando localização: Lat=${location.latitude}, Lng=${location.longitude}")

                databaseRef.child("localizacao").setValue(
                    mapOf(
                        "latitude" to location.latitude,
                        "longitude" to location.longitude,
                        "status" to "em rota",
                        "rota" to rotaEscolhida
                    )
                ).addOnSuccessListener {
                    Log.d("Firebase", "Localização salva com sucesso! Rota: $rotaEscolhida")
                }.addOnFailureListener { e ->
                    Log.e("Firebase", "Erro ao salvar localização: ${e.message}")
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
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

    private fun encerrarRota() {
        fusedLocationClient.removeLocationUpdates(locationCallback)

        databaseRef.child("rotaAtiva").setValue(
            mapOf(
                "horario" to null,
                "status" to "inativa"
            )
        )

        databaseRef.child("localizacao").setValue(
            mapOf(
                "latitude" to null,
                "longitude" to null,
                "status" to "indisponível",
                "rota" to null
            )
        ).addOnSuccessListener {
            Log.d("Firebase", "Localização removida e rota encerrada!")
        }.addOnFailureListener { e ->
            Log.e("Firebase", "Erro ao encerrar rota: ${e.message}")
        }

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
