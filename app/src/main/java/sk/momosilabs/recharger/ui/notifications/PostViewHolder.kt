package sk.momosilabs.recharger.ui.notifications

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sk.momosilabs.recharger.R

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title: TextView = itemView.findViewById(R.id.post_title)
    val body: TextView = itemView.findViewById(R.id.post_body)

}
