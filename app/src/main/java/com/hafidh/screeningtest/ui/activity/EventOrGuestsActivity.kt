package com.hafidh.screeningtest.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.hafidh.screeningtest.R
import com.hafidh.screeningtest.databinding.ActivityEvenOrGuestsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventOrGuestsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEvenOrGuestsBinding

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.data != null) {
            if (result.resultCode == RESULT_CODE_EVENT) {
                val selectedValue =
                    result.data?.getStringExtra(EXTRA_VALUE_EVENT)
                binding.btnEvent.text = "$selectedValue"
            }
            if (result.resultCode == RESULT_CODE_GUEST) {
                val selectedValue =
                    result.data?.getStringExtra(EXTRA_NAME_GUEST)
                val birthDate = result.data?.getStringExtra(EXTRA_BIRTH_GUEST)
                val birthDateSplit = birthDate?.split("-")
                val birthValue = birthDateSplit?.get(2)?.toInt()
                birthValue?.let {
                    Toast.makeText(
                        this,
                        when {
                            it % 2 == 0 && it % 3 == 0 -> "Ios"
                            it % 2 == 0 -> "BlackBerry"
                            it % 3 == 0 -> "Android"
                            else -> "Feature Phone"
                        }, Toast.LENGTH_LONG
                    ).show()
                }
                binding.btnGuest.text = "$selectedValue"
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEvenOrGuestsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setName()
        onButtonEventClicked()
        onButtonGuestClicked()
    }

    private fun onButtonGuestClicked() {
        binding.btnGuest.setOnClickListener {
            resultLauncher.launch(Intent(this, GuestActivity::class.java))
        }
    }

    private fun setName() {
        val name = intent.getStringExtra("name")
        val stringBuilder = buildString {
            append(resources.getString(R.string.nameInEvent))
            append(" ")
            append(name)
        }
        binding.tvName.text = stringBuilder
    }

    private fun onButtonEventClicked() {
        binding.btnEvent.setOnClickListener {
            resultLauncher.launch(Intent(this, EventActivity::class.java))
        }
    }


    companion object {
        const val RESULT_CODE_EVENT = 1
        const val RESULT_CODE_GUEST = 2
        const val EXTRA_VALUE_EVENT = "extra_name"
        const val EXTRA_NAME_GUEST = "extra_name_guest"
        const val EXTRA_BIRTH_GUEST = "extra_birth_guest"
    }
}