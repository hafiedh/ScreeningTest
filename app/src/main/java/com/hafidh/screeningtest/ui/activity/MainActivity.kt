package com.hafidh.screeningtest.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hafidh.screeningtest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goToEventOrGuest()
    }

    private fun goToEventOrGuest() {
        binding.btnNext.setOnClickListener {
            val name = binding.edName.text.toString()
            if (validateName(name)) {
                Intent(this, EventOrGuestsActivity::class.java).also {
                    it.putExtra("name", name)
                    startActivity(it)
                }
            }else {
                binding.edName.error = "Nama tidak boleh kosong"
            }
        }
    }

    private fun validateName(name: String): Boolean {
        return name.isNotEmpty() && name.isNotBlank()
    }

}