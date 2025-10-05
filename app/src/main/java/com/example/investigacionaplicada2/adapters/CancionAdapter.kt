package com.example.investigacionaplicada2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.investigacionaplicada2.R
import com.example.investigacionaplicada2.models.Cancion

class CancionAdapter(private var canciones: List<Cancion>) :
    RecyclerView.Adapter<CancionAdapter.CancionViewHolder>() {

    class CancionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.txtTitulo)
        val artista: TextView = view.findViewById(R.id.txtArtista)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CancionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cancion, parent, false)
        return CancionViewHolder(view)
    }

    override fun onBindViewHolder(holder: CancionViewHolder, position: Int) {
        val cancion = canciones[position]
        holder.titulo.text = cancion.titulo
        holder.artista.text = cancion.artista
    }

    override fun getItemCount(): Int = canciones.size

    fun updateList(nuevaLista: List<Cancion>) {
        canciones = nuevaLista
        notifyDataSetChanged()
    }
}