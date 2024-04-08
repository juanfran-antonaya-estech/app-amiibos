package com.juanfra.ddapp.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.juanfra.ddapp.model.data.gameserieinfo.Amiibo
import com.juanfra.ddapp.model.data.gameserieinfo.GameSerie

class AmiiboModel() : ViewModel() {
    private lateinit var repo : Repositorio
    private val seriesLiveData: MutableLiveData<ArrayList<GameSerie>> = MutableLiveData<ArrayList<GameSerie>>()
    private val actualPageLiveData: MutableLiveData<Int> = MutableLiveData<Int>()
    private val currentFragmentNameLiveData: MutableLiveData<String> = MutableLiveData<String>()
    private val amiiboListLiveData: MutableLiveData<ArrayList<Amiibo>> = MutableLiveData<ArrayList<Amiibo>>()

    fun getGameSerieList(): LiveData<ArrayList<GameSerie>> {
        return seriesLiveData
    }

    fun setList(array: ArrayList<GameSerie>) {
        repo.setCurrentGameSerieList(array)
        downloadGameSeries()
    }
    fun setDefaultList() {
        repo.setDefaultList()
        downloadGameSeries()
    }
    fun downloadGameSeries() {
// acciones para descargar la información de la serie
        seriesLiveData.value = repo.getCurrentGameSerieList()
    }

    fun initializeRepo(context: Context) {
        this.repo = Repositorio(context)
    }

    fun getCurrentPage() : LiveData<Int> {
        actualPageLiveData.value = repo.getCurrentPage()
        return actualPageLiveData
    }
    fun getFragmentName() : LiveData<String> {
        currentFragmentNameLiveData.value = repo.getCurrentFragmentName()
        return currentFragmentNameLiveData
    }
    fun setFragmentName(string: String) {
        repo.setCurrentFragmentName(string)
        currentFragmentNameLiveData.value = repo.getCurrentFragmentName()
    }

    fun setPage(x : Int) {
        repo.setCurrentPage(x)
        actualPageLiveData.value = repo.getCurrentPage()
    }

    fun nextPage() {
        repo.navNext()
        actualPageLiveData.value = repo.getCurrentPage()
    }
    fun backPage() {
        repo.navBack()
        actualPageLiveData.value = repo.getCurrentPage()
    }

    fun setAmiiboList(key: String) {
        repo.setAmiiboList(key)
        updateAmiiboList()
    }

    private fun updateAmiiboList() {
        amiiboListLiveData.value = repo.getAmiiboList()
    }


    class AmiiboFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Context::class.java).newInstance(context)
        }
    }
}