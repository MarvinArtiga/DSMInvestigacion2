package com.example.investigacionaplicada2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.investigacionaplicada2.R
import com.example.investigacionaplicada2.models.Artista

class ArtistaAdapter(private var artistas: List<Artista>) :
    RecyclerView.Adapter<ArtistaAdapter.ArtistaViewHolder>() {

    class ArtistaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombreArtista)
        val txtGenero: TextView = itemView.findViewById(R.id.txtGeneroArtista)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_artista, parent, false)
        return ArtistaViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistaViewHolder, position: Int) {
        val artista = artistas[position]
        holder.txtNombre.text = artista.nombre
        holder.txtGenero.text = artista.genero
    }

    override fun getItemCount(): Int = artistas.size

    fun updateList(nuevaLista: List<Artista>) {
        artistas = nuevaLista
        notifyDataSetChanged()
    }
}
