package com.juanfra.ddapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juanfra.ddapp.databinding.HolderAmiiboBinding
import com.juanfra.ddapp.model.data.gameserieinfo.Amiibo

class AdaptadorListaAmiibos(var listado: ArrayList<Amiibo>) : RecyclerView.Adapter<AdaptadorListaAmiibos.CeldaAmiibo>() {
    inner class CeldaAmiibo(var binding : HolderAmiiboBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CeldaAmiibo {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderAmiiboBinding.inflate(layoutInflater, parent, false)
        return CeldaAmiibo(binding)
    }

    override fun getItemCount(): Int {
       return listado.size
    }

    override fun onBindViewHolder(holder: CeldaAmiibo, position: Int) {
        val amiibo = listado[position]

        holder.binding.tvNombreAmiiboLista.text = amiibo.name
        Glide.with(holder.itemView)
            .load(amiibo.image)
            .into(holder.binding.ivImagenAmiiboLista)
    }

    fun updateData() {
        notifyDataSetChanged()
    }
}