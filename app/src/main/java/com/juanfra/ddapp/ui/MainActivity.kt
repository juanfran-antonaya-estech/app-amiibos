package com.juanfra.ddapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.juanfra.ddapp.databinding.ActivityMainBinding
import com.juanfra.ddapp.model.AmiiboModel
import com.juanfra.ddapp.ui.adapters.AdaptadorViewPager
import com.juanfra.ddapp.ui.fragments.FragmentDetallesAmiibo
import com.juanfra.ddapp.ui.fragments.FragmentListaAmiibos
import com.juanfra.ddapp.ui.fragments.FragmentListaJuegos

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this).get((AmiiboModel::class.java))
        viewModel.initializeRepo(this)
        viewModel.setDefaultList()

        FragmentListaJuegos.viewModel = viewModel
        FragmentListaAmiibos.viewModel = viewModel
        FragmentDetallesAmiibo.viewModel = viewModel

        val fragments = arrayListOf(FragmentListaJuegos(), FragmentListaAmiibos(), FragmentDetallesAmiibo())
        val adaptador = AdaptadorViewPager(this, fragments)

        binding.vp2.adapter = adaptador
        binding.btListaJuegos.setOnClickListener{
            binding.vp2.currentItem = 0
            viewModel.setFragmentName("Amiibos")
            viewModel.setDefaultList()
        }

        viewModel.getFragmentName().observe(this, {
            actualizartv(it)
        })

        viewModel.getCurrentPage().observe(this, {
            binding.vp2.currentItem = it
        })

    }

    private fun actualizartv(texto : String) {
        binding.tvCurrentFragment.text = texto
    }
}