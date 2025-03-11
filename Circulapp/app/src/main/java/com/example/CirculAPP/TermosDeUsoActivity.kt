package com.example.CirculAPP

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class TermosDeUsoActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_termos_de_uso)

        val webView: WebView = findViewById(R.id.webView)
        val btnAceitar: Button = findViewById(R.id.btnAceitar)
        val btnNaoAceitar: Button = findViewById(R.id.btnNaoAceitar)

        webView.loadUrl("file:///android_asset/termos_de_uso.html")

        btnAceitar.setOnClickListener {
            Log.d("TermosDeUsoActivity", "Botão Aceitar clicado")

            val sharedPref = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putBoolean("termos_aceitos", true)
                apply()
            }

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        btnNaoAceitar.setOnClickListener {
            finishAffinity()
        }
    }
}