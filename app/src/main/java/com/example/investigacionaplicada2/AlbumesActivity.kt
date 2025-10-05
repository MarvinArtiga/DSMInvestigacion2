package com.example.investigacionaplicada2

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.investigacionaplicada2.adapters.AlbumAdapter
import com.example.investigacionaplicada2.models.Album
import com.google.firebase.firestore.FirebaseFirestore

class AlbumesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AlbumAdapter
    private val albumes = mutableListOf<Album>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albumes)

        recyclerView = findViewById(R.id.recyclerAlbumes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AlbumAdapter(albumes)
        recyclerView.adapter = adapter

        val searchView = findViewById<SearchView>(R.id.searchAlbumes)
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

        cargarAlbumes()
    }

    private fun cargarAlbumes() {
        db.collection("albumes").get()
            .addOnSuccessListener { result ->
                albumes.clear()
                for (doc in result) {
                    val album = doc.toObject(Album::class.java)
                    album.id = doc.id
                    albumes.add(album)
                }
                adapter.notifyDataSetChanged()
            }
    }

    private fun filter(text: String?) {
        val filtrado = albumes.filter { it.nombre?.contains(text ?: "", true) == true }
        adapter.updateList(filtrado)
    }
}
