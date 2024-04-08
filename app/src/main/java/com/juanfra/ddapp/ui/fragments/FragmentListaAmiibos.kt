package com.juanfra.ddapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.juanfra.ddapp.R
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

        viewModel.getAmiiboList().observe(requireActivity(),{ amiibos ->
            updateAdapter(amiibos)
        })
    }

    private fun updateAdapter(amiibos: ArrayList<Amiibo>?) {
        val adaptador = AdaptadorListaAmiibos(amiibos!!)
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvListaAmiibos.adapter = adaptador
        binding.rvListaAmiibos.layoutManager = layoutManager
    }

    companion object {
        lateinit var viewModel: AmiiboModel
    }
}