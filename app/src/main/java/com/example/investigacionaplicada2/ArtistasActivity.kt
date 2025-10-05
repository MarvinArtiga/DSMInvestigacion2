package com.example.investigacionaplicada2

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.investigacionaplicada2.adapters.ArtistaAdapter
import com.example.investigacionaplicada2.models.Artista
import com.google.firebase.firestore.FirebaseFirestore

class ArtistasActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArtistaAdapter
    private val artistas = mutableListOf<Artista>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artistas)

        recyclerView = findViewById(R.id.recyclerArtistas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ArtistaAdapter(artistas)
        recyclerView.adapter = adapter

        val searchView = findViewById<SearchView>(R.id.searchArtistas)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filter(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return true
            }
        })

        cargarArtistas()
    }

    private fun cargarArtistas() {
        db.collection("artistas").get()
            .addOnSuccessListener { result ->
                artistas.clear()
                for (doc in result) {
                    val artista = doc.toObject(Artista::class.java)
                    artista.id = doc.id
                    artistas.add(artista)
                }
                adapter.notifyDataSetChanged()
            }
    }

    private fun filter(text: String?) {
        val filtrado = artistas.filter { it.nombre?.contains(text ?: "", true) == true }
        adapter.updateList(filtrado)
    }
}
