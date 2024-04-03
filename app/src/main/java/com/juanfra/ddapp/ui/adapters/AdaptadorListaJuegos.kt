package com.juanfra.ddapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanfra.ddapp.databinding.HolderGameSerieBinding
import com.juanfra.ddapp.model.data.gameserieinfo.GameSerie

class AdaptadorListaJuegos (val listado: ArrayList<GameSerie>) : RecyclerView.Adapter<AdaptadorListaJuegos.MiCelda>() {
    inner class MiCelda(val binding: HolderGameSerieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderGameSerieBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun getItemCount(): Int {
        return listado.size
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val serie = listado[position]

        holder.binding.tvSerie.text = serie.name
        holder.binding.tvCode.text = serie.key
    }
}