package org.d3if2101.hitungenergipotensial.model

import org.d3if2101.hitungenergipontensial.db.DataEntity

fun DataEntity.convert(): Hasil {
    val hasil = (inputMassa * 9.8 * inputTinggi)

    return Hasil(inputMassa, inputTinggi, hasil)
}