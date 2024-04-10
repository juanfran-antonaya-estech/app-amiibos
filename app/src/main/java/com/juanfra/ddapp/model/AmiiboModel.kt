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

    fun initializeRepo(context: Context) {
        this.repo = Repositorio()
    }

    fun getCurrentPage() : LiveData<Int> {
        actualPageLiveData.value = repo.getCurrentPage()
        return actualPageLiveData
    }
    fun getFragmentName() : LiveData<String> {
        return currentFragmentNameLiveData
    }
    fun setFragmentName(string: String) {
        currentFragmentNameLiveData.value = string
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
                var contador = 0.0
                val tamanioArray = keyarray.size.toDouble()

                for (kee in keyarray){
                    val response = repo.getAmiiboListFromApi(kee!!)

                    if (response.isSuccessful && response.code() == 200) {
                        contador++
                        val intcontador = contador.toInt()
                        currentFragmentNameLiveData.postValue("Cargado ${intcontador} de ${keyarray.size} (${(contador / tamanioArray * 100).toInt()}%)")
                        amiibolist.addAll(response.body()!!.amiibo)
                    }
                }
                Log.d("la lista de amiibos", amiibolist.toString())
                currentFragmentNameLiveData.postValue("Mostrando amiibos de ${amiibolist.get(0)?.gameSeries}")
                amiiboListLiveData.postValue(ArrayList(amiibolist.sortedBy { it.name }.sortedBy { it.type }))
            } else {
                val response = repo.getAmiiboListFromApi(key!!)

                if (response.isSuccessful) {
                    if (response.code() == 200){
                        amiiboListLiveData.postValue(response.body()?.amiibo)
                        currentFragmentNameLiveData.postValue("Mostrando amiibos de ${response.body()?.amiibo?.get(0)?.gameSeries}")
                    }
                }
            }
        }
    }

    fun getAmiiboList(): MutableLiveData<ArrayList<Amiibo>> {
        return amiiboListLiveData
    }

    fun setAmiibo(amiibo: Amiibo) {
        amiiboLiveData.value = amiibo
    }

    fun getAmiibo(): MutableLiveData<Amiibo>{
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