package sk.momosilabs.recharger.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import sk.momosilabs.recharger.R
import sk.momosilabs.recharger.data.temp.Post


class PostAdapter(
    options: FirebaseRecyclerOptions<Post>,
): FirebaseRecyclerAdapter<Post, PostViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int, post: Post) {
        holder.title.text = "${post.title}"
        holder.body.text = post.body
    }

}
