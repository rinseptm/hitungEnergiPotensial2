package org.d3if2101.hitungenergipotensial.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if2101.hitungenergipotensial.model.Fisikawan
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/rinseptm/hitungEnergiPotensial/master/api/"
private const val BASE_URL_IMG = "https://raw.githubusercontent.com/rinseptm/hitungEnergiPotensial/master/api/img/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FisikawanApiService {
    @GET("fisikawan.json")
    suspend fun getResult(): List<Fisikawan>
}

object FisikawanApi {
    val service: FisikawanApiService by lazy {
        retrofit.create(FisikawanApiService::class.java)
    }
    fun getFisikawanUrl(gambar: String): String {
        return "$BASE_URL_IMG$gambar"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }