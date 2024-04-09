package com.juanfra.ddapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdaptadorViewPager(val activity: FragmentActivity, val listaFragment: List<Fragment>): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return listaFragment.size
    }
    override fun createFragment(position: Int): Fragment {
        return listaFragment[position]
    }
}