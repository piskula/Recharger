package sk.momosilabs.recharger.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import sk.momosilabs.recharger.R
import sk.momosilabs.recharger.data.ChargingEvent
import sk.momosilabs.recharger.data.viewHolder.ChargingViewHolder

class ChargingAdapter(private val onClick: (ChargingEvent) -> Unit) :
    ListAdapter<ChargingEvent, ChargingViewHolder>(ChargingDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChargingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.charging_list_item, parent, false)
        return ChargingViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ChargingViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event = event)
    }

}

object ChargingDiffCallback : DiffUtil.ItemCallback<ChargingEvent>() {
    override fun areItemsTheSame(oldItem: ChargingEvent, newItem: ChargingEvent): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ChargingEvent, newItem: ChargingEvent): Boolean {
        return oldItem.id == newItem.id
    }
}
