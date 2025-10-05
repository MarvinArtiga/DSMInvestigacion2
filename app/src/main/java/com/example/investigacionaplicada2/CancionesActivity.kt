package com.example.investigacionaplicada2

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.investigacionaplicada2.adapters.CancionAdapter
import com.example.investigacionaplicada2.models.Cancion
import com.google.firebase.firestore.FirebaseFirestore

class CancionesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CancionAdapter
    private val canciones = mutableListOf<Cancion>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canciones)

        recyclerView = findViewById(R.id.recyclerCanciones)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CancionAdapter(canciones)
        recyclerView.adapter = adapter

        val searchView = findViewById<SearchView>(R.id.searchCanciones)
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

        cargarCanciones()
    }

    private fun cargarCanciones() {
        db.collection("canciones").get()
            .addOnSuccessListener { result ->
                canciones.clear()
                for (doc in result) {
                    val cancion = doc.toObject(Cancion::class.java)
                    cancion.id = doc.id
                    canciones.add(cancion)
                }
                adapter.notifyDataSetChanged()
            }
    }

    private fun filter(text: String?) {
        val filtrado = canciones.filter { it.titulo?.contains(text ?: "", true) == true }
        adapter.updateList(filtrado)
    }
}