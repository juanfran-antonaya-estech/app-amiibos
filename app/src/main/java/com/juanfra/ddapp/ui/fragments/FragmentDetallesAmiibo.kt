package com.juanfra.ddapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.juanfra.ddapp.R
import com.juanfra.ddapp.databinding.FragmentDetallesAmiiboBinding
import com.juanfra.ddapp.model.AmiiboModel
import com.juanfra.ddapp.model.data.gameserieinfo.Amiibo
import com.juanfra.ddapp.model.data.gameserieinfo.Games3DS
import com.juanfra.ddapp.model.data.gameserieinfo.GamesSwitch
import com.juanfra.ddapp.model.data.gameserieinfo.GamesWiiU


class FragmentDetallesAmiibo : Fragment() {
    private lateinit var binding: FragmentDetallesAmiiboBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetallesAmiiboBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAmiibo().observe(requireActivity()){
            fillAmiiboData(it)
        }
    }

    private fun fillAmiiboData(amiibo: Amiibo) {
        Glide.with(this)
            .load(amiibo.image).placeholder(R.drawable.amiibonotfound)
            .into(binding.ivFotoAmiibo)

        binding.tvAmiiboName.text = amiibo.name
        with(binding.vllAmiiboAttributes){
            this.removeAllViews()
            addView(addKeyAndValueSingle("Series", amiibo.amiiboSeries))
            addView(addKeyAndValueSingle("Serie de videojuegos", amiibo.gameSeries))
            addView(addKeyAndValueSingle("Personaje", amiibo.character))
            addView(addKeyAndValueSingle("Tipo de amiibo", amiibo.type))
            addView(addKeyAndValueSingle("Head", amiibo.head))
            addView(addKeyAndValueSingle("Tail", amiibo.tail))
        }

        fill3dsUses(binding.vll3dsUses, amiibo.games3DS)
        fillWUUses(binding.vllWUUses, amiibo.gamesWiiU)
        fillSwitchUses(binding.vllSwitchUses, amiibo.gamesSwitch)
    }

    private fun fillSwitchUses(vll: LinearLayout, games: ArrayList<GamesSwitch>) {
        vll.removeAllViews()
        for (game in games) {
            vll.addView(addKeyAndValueSingle(game.gameName, game.amiiboUsage[0].Usage?.replace(" / ", "\n")))
        }
    }

    private fun fill3dsUses(vll: LinearLayout, games: ArrayList<Games3DS>) {
        vll.removeAllViews()
        for (game in games) {
            vll.addView(addKeyAndValueSingle(game.gameName, game.amiiboUsage[0].Usage?.replace(" / ", "\n")))
        }
    }

    private fun fillWUUses(vll: LinearLayout, games: ArrayList<GamesWiiU>) {
        vll.removeAllViews()
        for (game in games) {
            vll.addView(addKeyAndValueSingle(game.gameName, game.amiiboUsage[0].Usage?.replace(" / ", "\n")))
        }
    }

    private fun addKeyAndValueSingle(key: String?, value: String?): View? {
        val hll = LinearLayout(this.context)
        hll.setLayoutParams(
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )
        hll.orientation = LinearLayout.HORIZONTAL
        hll.addView(addTextview(key))
        hll.addView(addTextviewToLeft(value))
        hll.setPadding(40,20,40,20)

        return hll
    }

    private fun addTextviewToLeft(text: String?): View? {
        val tv = addTextview(text)
        tv?.textAlignment = TextView.TEXT_ALIGNMENT_TEXT_END
        return tv
    }

    private fun addTextview(text: String?): View? {
        val tv = TextView(requireContext())
        tv.text = text
        tv.setLayoutParams(
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1F
            )
        )
        tv.textSize = 18F

        return tv
    }

    companion object {
        lateinit var viewModel: AmiiboModel
    }
}