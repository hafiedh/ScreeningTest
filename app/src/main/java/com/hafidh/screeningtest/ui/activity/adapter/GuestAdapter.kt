package com.hafidh.screeningtest.ui.activity.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hafidh.screeningtest.data.model.GuestsItem
import com.hafidh.screeningtest.databinding.ItemGuestsBinding

@SuppressLint("NotifyDataSetChanged")
class GuestAdapter(
    private val data: List<GuestsItem>,
    val onClick: (GuestsItem) -> Unit
) : RecyclerView.Adapter<GuestAdapter.GuestAdapterViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): GuestAdapterViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemGuestsBinding.inflate(inflater, viewGroup, false)
        return GuestAdapterViewHolder(binding)
    }

    inner class GuestAdapterViewHolder(private val binding: ItemGuestsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GuestsItem) {
            binding.guestName.text = data.name
            binding.itemViewGuests.setOnClickListener {
                onClick(data)
            }
        }
    }

    override fun onBindViewHolder(holder: GuestAdapterViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }


}