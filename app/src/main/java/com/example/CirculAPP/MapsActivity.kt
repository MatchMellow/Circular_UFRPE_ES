package com.example.CirculAPP

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationCallback
import com.example.CirculAPP.databinding.ActivityMapsBinding
import com.google.android.gms.location.LocationResult

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var motoristaMarker: MarkerOptions? = null  // Marcador do motorista

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult ?: return
            for (location in locationResult.locations) {
                if (location != null) {
                    val userLocation = LatLng(location.latitude, location.longitude)

                    // Verificando se o marcador do motorista já foi inicializado
                    if (motoristaMarker != null) {
                        // Atualizando a posição do marcador do motorista
                        motoristaMarker?.position(userLocation)
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation))
                    } else {
                        motoristaMarker = MarkerOptions().position(userLocation)
                            .title("Circular Está aqui")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_motorista))
                        mMap.addMarker(motoristaMarker!!) // Usando "!!" para garantir que não é nulo
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Obter o fragmento de mapa e aguardar que o mapa esteja pronto
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Verificar permissões de localização e obter a localização do motorista
        checkLocationPermission()
    }

    // Função para verificar se as permissões estão concedidas
    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Se a permissão foi concedida, obtemos a localização
            getDeviceLocation()
        } else {
            // Solicita permissão
            requestPermissions.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    // Função para obter a localização do motorista
    private fun getDeviceLocation() {
        val locationRequest = LocationRequest.create().apply {
            interval = 10000  // Atualiza a cada 10 segundos
            fastestInterval = 5000  // A cada 5 segundos para obter dados mais rápidos
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        // Inicia as atualizações de localização
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
        }
    }

    // Se a permissão for solicitada, a resposta será tratada aqui
    private val requestPermissions =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                getDeviceLocation()
            } else {
                Toast.makeText(this, "Permissão de localização negada", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Centraliza o mapa na UFRPE
        val ufrpe = LatLng(-8.017335662754297, -34.949115979298256) // Coordenadas aproximadas da UFRPE
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ufrpe, 15f))  // Centraliza o mapa na UFRPE com zoom

        // Adicionar marcador para Zootecnia
        mMap.addMarker(MarkerOptions().position(LatLng(-8.020046094447412, -34.95411159356041)).title("Zootecnia"))

        // Adicionar marcador para CEAGRI
        mMap.addMarker(MarkerOptions().position(LatLng(-8.017728749495607, -34.94478152968631)).title("Ceagri Computação"))

        // Adicionar marcador para Reitoria da UFRPE
        mMap.addMarker(MarkerOptions().position(LatLng(-8.014528748288745, -34.95043753092098)).title("Reitoria da UFRPE"))

        mMap.addMarker(MarkerOptions().position(LatLng(-8.018405587113017, -34.95003668228588)).title("Cegoe"))

        mMap.addMarker(MarkerOptions().position(LatLng(-8.01621201175587, -34.94950232571275)).title("Biblioteca Setorial"))

        mMap.addMarker(MarkerOptions().position(LatLng(-8.015908378317642, -34.950759580760014)).title("Guarita Piscina"))

    }
}
