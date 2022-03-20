package sk.momosilabs.recharger.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sk.momosilabs.recharger.R

class ChargingListHeaderAdapter: RecyclerView.Adapter<ChargingListHeaderAdapter.HeaderViewHolder>() {
    private var itemCount: Int = 0

    /* ViewHolder for displaying header. */
    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val countNumberTextView: TextView = itemView.findViewById(R.id.count_number_text)

        fun bind(itemCount: Int) {
            countNumberTextView.text = "${itemCount} events"
        }
    }

    /* Inflates view and returns HeaderViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.charging_list_header_item, parent, false)
        return HeaderViewHolder(view)
    }

    /* Binds number of events to the header. */
    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(itemCount)
    }

    /* Returns number of items, since there is only one item in the header return one  */
    override fun getItemCount(): Int {
        return 1
    }

    /* Updates header to display number of charging events when an event is added or subtracted. */
    fun updateItemCount(newItemCount: Int) {
        itemCount = newItemCount
        notifyDataSetChanged()
    }
}