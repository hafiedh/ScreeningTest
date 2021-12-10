package com.hafidh.screeningtest.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hafidh.screeningtest.data.model.GuestsItem
import com.hafidh.screeningtest.databinding.ActivityGuestBinding
import com.hafidh.screeningtest.ui.activity.EventOrGuestsActivity.Companion.RESULT_CODE_GUEST
import com.hafidh.screeningtest.ui.activity.adapter.GuestAdapter
import com.hafidh.screeningtest.ui.activity.viewmodel.GuestViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class GuestActivity : AppCompatActivity() {
    private val binding: ActivityGuestBinding by lazy { ActivityGuestBinding.inflate(layoutInflater) }
    private val viewModel: GuestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getData()
        viewModel.guest.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state ->
                handleState(state)
            }.launchIn(lifecycleScope)
    }

    private fun handleState(state: GuestViewModel.UiData) {
        when (state) {
            is GuestViewModel.UiData.Success -> onDataLoaded(state.data)
            is GuestViewModel.UiData.Error -> Snackbar.make(
                binding.root,
                "Data Error",
                Snackbar.LENGTH_SHORT
            ).show()
            else -> return
        }
    }

    private fun onGuestItemClick(guest: GuestsItem) {
        val intent = Intent().apply {
            putExtra(EventOrGuestsActivity.EXTRA_NAME_GUEST, guest.name)
            putExtra(EventOrGuestsActivity.EXTRA_BIRTH_GUEST, guest.birthdate)
        }
        setResult(RESULT_CODE_GUEST, intent)
        finish()
    }

    private fun onDataLoaded(guests: List<GuestsItem>) {

        val adapter = GuestAdapter(guests, ::onGuestItemClick)
        binding.rvGuest.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            this.adapter = adapter
        }
        Snackbar.make(binding.root, "Data Success ${guests}", Snackbar.LENGTH_SHORT).show()
    }


}