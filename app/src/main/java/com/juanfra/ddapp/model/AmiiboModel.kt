package com.juanfra.ddapp.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.juanfra.ddapp.model.data.gameserieinfo.Amiibo
import com.juanfra.ddapp.model.data.gameserieinfo.GameSerie
import kotlinx.coroutines.launch

class AmiiboModel() : ViewModel() {
    private lateinit var repo : Repositorio
    private val seriesLiveData: MutableLiveData<ArrayList<GameSerie>> = MutableLiveData<ArrayList<GameSerie>>()
    private val actualPageLiveData: MutableLiveData<Int> = MutableLiveData<Int>()
    private val currentFragmentNameLiveData: MutableLiveData<String> = MutableLiveData<String>()
    private val amiiboListLiveData: MutableLiveData<ArrayList<Amiibo>> = MutableLiveData<ArrayList<Amiibo>>()
    private val amiiboLiveData: MutableLiveData<Amiibo> = MutableLiveData<Amiibo>()

    fun getGameSerieList(): LiveData<ArrayList<GameSerie>> {
        return seriesLiveData
    }

    fun setList(array: ArrayList<GameSerie>) {

        viewModelScope.launch {
            val respuesta = repo.getGameseriesFromAPI()

            val code = respuesta.code()

            if (code == 200) {
                val listaSeries = respuesta.body()
                listaSeries?.let {
                    seriesLiveData.postValue(it.gameSerie as ArrayList<GameSerie>)
                }
            }
        }
        downloadGameSeries()
    }
    fun setDefaultList() {
        viewModelScope.launch {
            val respuesta = repo.getGameseriesFromAPI()

            val code = respuesta.code()

            if (code == 200) {
                val listaSeries = respuesta.body()
                listaSeries?.let {
                    seriesLiveData.postValue(compressRepeats(it.gameSerie as ArrayList<GameSerie>)!!)
                }
            }
        }
    }
    fun downloadGameSeries() {
// acciones para descargar la información de la serie
        setDefaultList()
    }

    fun initializeRepo(context: Context) {
        this.repo = Repositorio()
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
        viewModelScope.launch {
            if (key.contains(',')) {
                val keyarray = key.split(",")
                val amiibolist = ArrayList<Amiibo>()

                for (kee in keyarray){
                    val response = repo.getAmiiboListFromApi()

                    if (response.isSuccessful && response.code() == 200) {
                        amiibolist.addAll(response.body()!!.amiibo)
                    }
                }
                Log.d("la lista de amiibos", amiibolist.toString())
                amiiboListLiveData.postValue(amiibolist)
            } else {
                val response = repo.getAmiiboListFromApi()

                if (response.isSuccessful) {
                    if (response.code() == 200){
                        amiiboListLiveData.postValue(response.body()?.amiibo)
                    }
                }
            }
        }
    }
    fun setAmiiboListFromMultipleKeys(keylist: List<String>) {

    }

    private fun updateAmiiboList() {

    }

    fun getAmiiboList(): MutableLiveData<ArrayList<Amiibo>> {
        return amiiboListLiveData
    }

    fun setAmiibo(amiibo: Amiibo) {

    }

    private fun updateAmiibo() {

    }

    fun getAmiibo(): MutableLiveData<Amiibo>{
        updateAmiibo()
        return amiiboLiveData
    }

    fun compressRepeats(gameSeries: ArrayList<GameSerie>): ArrayList<GameSerie>? {
        val diffnames = getDiff(gameSeries)

        val compressedlist = ArrayList<GameSerie>()

        for(name in diffnames){
            compressedlist.add(GameSerie("", name))
        }
        for(cserie in compressedlist){
            for (gserie in gameSeries) {
                if (cserie.name.equals(gserie.name)) {
                    cserie.key = cserie.key + ",${gserie.key}"
                }
            }
            cserie.key = cserie.key!!.trim(',')
        }

        return compressedlist
    }

    private fun getDiff(gameSeries: ArrayList<GameSerie>): ArrayList<String> {
        val diffnames = arrayListOf<String>()

        for(serie in gameSeries){
            if (!diffnames.contains(serie.name)){
                diffnames.add(serie.name!!)
            }
        }
        return diffnames
    }



    //class AmiiboFactory(private val context: Context) : ViewModelProvider.Factory {
    //    override fun <T : ViewModel> create(modelClass: Class<T>): T {
    //        return modelClass.getConstructor(Context::class.java).newInstance(context)
    //    }
    //}
}