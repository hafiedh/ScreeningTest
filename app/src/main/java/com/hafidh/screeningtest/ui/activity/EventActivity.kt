package com.hafidh.screeningtest.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafidh.screeningtest.R
import com.hafidh.screeningtest.data.model.EventItem
import com.hafidh.screeningtest.databinding.ActivityEventBinding
import com.hafidh.screeningtest.ui.activity.EventOrGuestsActivity.Companion.EXTRA_VALUE_EVENT
import com.hafidh.screeningtest.ui.activity.EventOrGuestsActivity.Companion.RESULT_CODE_EVENT
import com.hafidh.screeningtest.ui.activity.adapter.EventAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class EventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventBinding
    private val guests = ArrayList<EventItem>()
    private val eventAdapter: EventAdapter by lazy {
        EventAdapter(guests, ::onEventItemClick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Events"
        initRecyclerView()
    }

    private fun initRecyclerView() {
        guests.addAll(list)
        binding.rvEvent.apply {
            layoutManager = LinearLayoutManager(this@EventActivity)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            adapter = eventAdapter
        }
    }

    private fun onEventItemClick(event: EventItem) {
        val intent = Intent(this, EventOrGuestsActivity::class.java).also {
            it.putExtra(EXTRA_VALUE_EVENT, event.name)
        }
        setResult(RESULT_CODE_EVENT, intent)
        finish()
    }

        private val list: ArrayList<EventItem>
        @SuppressLint("Recycle")
        get() {
            val name = resources.getStringArray(R.array.nameEvent)
            val date = resources.getStringArray(R.array.date)
            val photo = resources.obtainTypedArray(R.array.photo)
            val listEvents = ArrayList<EventItem>()
            for (i in name.indices) {
                val events = EventItem(name[i], date[i], photo.getResourceId(i, -1))
                listEvents.add(events)
            }
            return listEvents
        }


}