package org.d3if2101.hitungenergipontensial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if2101.hitungenergipontensial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{hitungPotensial()}
        binding.resetButton.setOnClickListener{ulang()}

    }
    private fun hitungPotensial(){
        val panjang = binding.massaPotensialInp.text.toString()
        if(TextUtils.isEmpty(panjang)){
            Toast.makeText(this, R.string.massa_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val lebar = binding.gravitasiPotensialInp.text.toString()
        if(TextUtils.isEmpty(lebar)){
            Toast.makeText(this, R.string.gravitasi_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val sisi = binding.tinggiPontensialInp.text.toString()
        if(TextUtils.isEmpty(lebar)){
            Toast.makeText(this, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val hasil = panjang.toDouble() * lebar.toDouble() * sisi.toDouble()
        binding.hasilTextView.text = getString(R.string.hasil_x, hasil)

    }
    private fun ulang(){
        binding.massaPotensialInp.text = null
        binding.gravitasiPotensialInp.text = null
        binding.tinggiPontensialInp.text = null
        binding.hasilTextView.text = null
    }
}