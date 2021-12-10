package com.hafidh.screeningtest.ui.activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hafidh.screeningtest.data.model.EventItem
import com.hafidh.screeningtest.databinding.ItemEventBinding

class EventAdapter(
    private val list: List<EventItem>,
    val onClick: (eventItem: EventItem) -> Unit
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {



    inner class EventViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun eventBind(event: EventItem) {
            with(binding) {
                ivPhoto.setImageResource(event.photo)
                tvNameEvent.text = event.name
                tvDate.text = event.date
            }
            binding.itemView.setOnClickListener {
                onClick(event)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(
            ItemEventBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.eventBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}