package com.hafidh.screeningtest.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hafidh.screeningtest.R
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
                val isPalindromeOrNot = isPalindrome(name)
                MaterialAlertDialogBuilder(this)
                    .setTitle(isPalindromeOrNot)
                    .setPositiveButton(resources.getString(R.string.next)) { _, _ ->
                        Intent(this, EventOrGuestsActivity::class.java).also {
                            it.putExtra("name", name)
                            startActivity(it)
                        }
                    }.show()
            } else {
                binding.edName.error = "Nama tidak boleh kosong"
            }
        }
    }

    private fun validateName(name: String): Boolean {
        return name.isNotEmpty() && name.isNotBlank()
    }

    private fun isPalindrome(name: String): String {
        val trimmedName = name.filterNot { it.isWhitespace() }.lowercase()
        for (i in 0..trimmedName.length / 2) {
            if (trimmedName[i] != trimmedName[trimmedName.length - i - 1]) {
                return resources.getString(R.string.notPalindrome)
            }
        }
        return resources.getString(R.string.palindrome)
    }

}