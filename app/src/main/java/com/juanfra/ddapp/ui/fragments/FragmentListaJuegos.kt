package com.juanfra.ddapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.juanfra.ddapp.R
import com.juanfra.ddapp.databinding.FragmentListaJuegosBinding
import com.juanfra.ddapp.model.Repositorio
import com.juanfra.ddapp.ui.adapters.AdaptadorListaJuegos


class FragmentListaJuegos : Fragment() {
    private lateinit var binding: FragmentListaJuegosBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaJuegosBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var repo = Repositorio(requireContext())

        val adaptadorListaJuegos = AdaptadorListaJuegos(repo.getAllGameseries()!!)
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvSeries.adapter = adaptadorListaJuegos
        binding.rvSeries.layoutManager = layoutManager
    }


}