package org.d3if2101.hitungenergipontensial.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data")
data class DataEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var inputMassa: Double,
    var inputTinggi: Double,
    var hasil : Double
)