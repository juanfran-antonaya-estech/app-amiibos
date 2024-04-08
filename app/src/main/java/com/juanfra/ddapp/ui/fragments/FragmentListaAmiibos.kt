package com.juanfra.ddapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.juanfra.ddapp.databinding.FragmentListaAmiibosBinding
import com.juanfra.ddapp.model.AmiiboModel
import com.juanfra.ddapp.model.data.gameserieinfo.Amiibo
import com.juanfra.ddapp.ui.adapters.AdaptadorListaAmiibos


class FragmentListaAmiibos : Fragment() {
    private lateinit var binding : FragmentListaAmiibosBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListaAmiibosBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setFragmentName("Lista de amiibos")

        viewModel.getAmiiboList().observe(requireActivity(),{ amiibos ->
            updateAdapter(amiibos)
        })
    }

    private fun updateAdapter(amiibos: ArrayList<Amiibo>?) {
        val adaptador = AdaptadorListaAmiibos(ArrayList(amiibos!!.sortedBy { it.name }))
        adaptador.updateData()
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvListaAmiibos.adapter = adaptador
        binding.rvListaAmiibos.layoutManager = layoutManager
        adaptador.updateData()
    }

    companion object {
        lateinit var viewModel: AmiiboModel
    }
}