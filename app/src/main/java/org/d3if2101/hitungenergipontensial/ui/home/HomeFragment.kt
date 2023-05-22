package org.d3if2101.hitungenergipotensial.ui.home

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if2101.hitungenergipontensial.R
import org.d3if2101.hitungenergipontensial.databinding.FragmentHomeBinding
import org.d3if2101.hitungenergipontensial.db.DataDb
import org.d3if2101.hitungenergipotensial.model.Hasil


class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by lazy {
        val db = DataDb.getInstance(requireContext())
        val factory = HomeViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.gravitasiPotensialInp.isEnabled = false
        binding.button.setOnClickListener{ hitungEnergiPotensial() }
        binding.resetButton.setOnClickListener{ ulang() }
        viewModel.getHasil().observe(requireActivity(), { showResult(it) })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                    R.id.action_homeFragment_to_historiFragment)
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    resId = R.id.action_homeFragment_to_aboutFragment
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showResult(result: Hasil?) {
        if (result == null) return
        binding.hasilTextView.text = getString(R.string.hasil_x, result.hasil)
    }

    private fun ulang() {
        binding.massaPotensialInp.text = null
        binding.tinggiPontensialInp.text = null
        binding.hasilTextView.text = null
    }

    private fun hitungEnergiPotensial() {
        val massa = binding.massaPotensialInp.text.toString()
        val tinggi = binding.tinggiPontensialInp.text.toString()

        if(TextUtils.isEmpty(massa) || TextUtils.isEmpty(tinggi)){
            Toast.makeText(context, R.string.input_invalid, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitung(
            massa.toDouble(),
            tinggi.toDouble()
        )
    }
}