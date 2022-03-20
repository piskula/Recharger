package sk.momosilabs.recharger.data.viewHolder

import android.text.format.DateUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sk.momosilabs.recharger.R
import sk.momosilabs.recharger.data.ChargingEvent
import java.text.DecimalFormat

class ChargingViewHolder(itemView: View, val onClick: (ChargingEvent) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    companion object {
        private val decimalFormat = DecimalFormat("0.000 kWh")
        private val currencyFormat = DecimalFormat("0.00 €")
    }

    private val flowerTextView: TextView = itemView.findViewById(R.id.flower_text)
    private val flowerImageView: ImageView = itemView.findViewById(R.id.flower_image)
    private val chargingAmount: TextView = itemView.findViewById(R.id.capacity)
    private val chargingTime: TextView = itemView.findViewById(R.id.timestamp)
    private val price: TextView = itemView.findViewById(R.id.price)
    private var currentEvent: ChargingEvent? = null

    init {
        itemView.setOnClickListener {
            currentEvent?.let {
                onClick(it)
            }
        }
    }

    /* Bind flower name and image. */
    fun bind(event: ChargingEvent) {
        currentEvent = event

        chargingAmount.text =
            "${event.percentageFinish.minus(event.percentageStart)}% (${event.percentageStart} -> ${event.percentageFinish})"
        flowerTextView.text = decimalFormat.format(event.amount)
        chargingTime.text = DateUtils.getRelativeTimeSpanString(
            event.timestamp.toInstant().toEpochMilli(),
            System.currentTimeMillis(),
            DateUtils.SECOND_IN_MILLIS
        )
        price.text = currencyFormat.format(event.priceTotal)
        if (event.image != null) {
            flowerImageView.setImageResource(event.image)
        }
    }
}
