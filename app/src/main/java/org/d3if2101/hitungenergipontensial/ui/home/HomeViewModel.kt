package org.d3if2101.hitungenergipotensial.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2101.hitungenergipontensial.db.DataDao
import org.d3if2101.hitungenergipontensial.db.DataEntity
import org.d3if2101.hitungenergipotensial.model.Hasil


class HomeViewModel(private val db: DataDao): ViewModel() {

    private val hasilHitung = MutableLiveData<Hasil?>()

    fun hitung(inputMassa: Double, inputTinggi: Double) {
        val hasil = (inputMassa * 9.8 * inputTinggi)
        hasilHitung.value = Hasil(inputMassa, inputTinggi, hasil)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataHasil = DataEntity(
                    inputMassa = inputMassa,
                    inputTinggi = inputTinggi,
                    hasil = hasil
                )
                db.insert(dataHasil)
            }
        }
    }
    fun getHasil(): LiveData<Hasil?> = hasilHitung
}