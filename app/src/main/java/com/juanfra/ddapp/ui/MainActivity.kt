package com.juanfra.ddapp.ui

import android.os.Bundle
import android.view.DragEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.juanfra.ddapp.R
import com.juanfra.ddapp.databinding.ActivityMainBinding
import com.juanfra.ddapp.ui.adapters.TutorialAdapter2
import com.juanfra.ddapp.ui.fragments.FragmentListaAmiibos
import com.juanfra.ddapp.ui.fragments.FragmentListaJuegos

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragments = arrayListOf(FragmentListaJuegos(), FragmentListaAmiibos(), FragmentListaJuegos())
        val adaptador = TutorialAdapter2(this, fragments)

        binding.vp2.adapter = adaptador
        actualizartv()
        binding.btListaJuegos.setOnClickListener{
            binding.vp2.currentItem = 0
        }

        binding.vp2.setOnDragListener { v, event ->
            actualizartv()
            event.result
        }

    }

    private fun actualizartv() {
        binding.tvCurrentFragment.text = binding.vp2.currentItem.toString()
    }
}