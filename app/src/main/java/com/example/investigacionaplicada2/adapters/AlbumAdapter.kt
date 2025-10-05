package com.example.investigacionaplicada2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.investigacionaplicada2.R
import com.example.investigacionaplicada2.models.Album

class AlbumAdapter(private var albumes: List<Album>) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombreAlbum)
        val txtAnio: TextView = itemView.findViewById(R.id.txtAnoAlbum)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albumes[position]
        holder.txtNombre.text = album.nombre
        holder.txtAnio.text = album.anio
    }

    override fun getItemCount(): Int = albumes.size

    fun updateList(nuevaLista: List<Album>) {
        albumes = nuevaLista
        notifyDataSetChanged()
    }
}
