package sk.momosilabs.recharger.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import sk.momosilabs.recharger.R
import sk.momosilabs.recharger.data.ChargingEvent
import sk.momosilabs.recharger.data.ChargingEventEntity
import sk.momosilabs.recharger.data.toModel
import sk.momosilabs.recharger.data.viewHolder.ChargingViewHolder


class ChargingRecyclerAdapter(
    options: FirebaseRecyclerOptions<ChargingEventEntity>,
    private val onClick: (ChargingEvent) -> Unit,
): FirebaseRecyclerAdapter<ChargingEventEntity, ChargingViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChargingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.charging_list_item, parent, false)
        return ChargingViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ChargingViewHolder, position: Int, event: ChargingEventEntity) {
        with (event.toModel()) {
            holder.bind(this)
        }
    }

}
