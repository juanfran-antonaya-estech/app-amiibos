package com.juanfra.ddapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.juanfra.ddapp.databinding.HolderGameSerieBinding
import com.juanfra.ddapp.model.AmiiboModel
import com.juanfra.ddapp.model.data.gameserieinfo.GameSerie

class AdaptadorListaJuegos (val listado: ArrayList<GameSerie>) : RecyclerView.Adapter<AdaptadorListaJuegos.MiCelda>() {
    inner class MiCelda(val binding: HolderGameSerieBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var viewModel: AmiiboModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderGameSerieBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun getItemCount(): Int {
        return listado.size
    }

    fun updateDataset() {
        notifyDataSetChanged()
    }

    fun obtainViewModel (viewModel: AmiiboModel) {
        this.viewModel = viewModel
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val serie = listado[position]

        holder.binding.tvSerie.text = serie.name
        if (serie.key!!.contains(',')){
            holder.binding.tvCode.text = "${serie.key!!.split(',').size.toString()} Subseries"
            holder.itemView.setOnClickListener{
                var arraydekeys = serie.key!!.split(',')
                var arrayDeGameSerie = arrayListOf<GameSerie>()
                var n = 1
                for (i in arraydekeys) {
                    arrayDeGameSerie.add(GameSerie(i, "${serie.name}, serie ${n}"))
                    n++
                }
                viewModel.setList(arrayDeGameSerie)
            }
        } else {
            holder.binding.tvCode.text = serie.key
            holder.itemView.setOnClickListener{
                viewModel.setAmiiboList(serie.key!!)
                viewModel.setPage(1)
            }
        }

    }
}