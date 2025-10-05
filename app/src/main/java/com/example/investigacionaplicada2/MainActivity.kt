package com.example.investigacionaplicada2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- Aquí agregamos los listeners de los botones ---
        val btnCanciones = findViewById<Button>(R.id.btnCanciones)
        val btnAlbumes = findViewById<Button>(R.id.btnAlbumes)
        val btnArtistas = findViewById<Button>(R.id.btnArtistas)

        btnCanciones.setOnClickListener {
            startActivity(Intent(this, CancionesActivity::class.java))
        }

        btnAlbumes.setOnClickListener {
            startActivity(Intent(this, AlbumesActivity::class.java))
        }

        btnArtistas.setOnClickListener {
            startActivity(Intent(this, ArtistasActivity::class.java))
        }
    }
}