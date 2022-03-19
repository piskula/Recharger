package sk.momosilabs.recharger.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import sk.momosilabs.recharger.data.temp.Post
import sk.momosilabs.recharger.databinding.FragmentNotificationsBinding


class NotificationsFragment : Fragment() {

    companion object {
        private const val TAG = "NotificationsFragment"
    }

    private var _binding: FragmentNotificationsBinding? = null

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mPostAdapter: PostAdapter
    private lateinit var mDatabase: DatabaseReference

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val mManager = LinearLayoutManager(context)
        mManager.reverseLayout = true
        mManager.stackFromEnd = true

        mRecyclerView = binding.rv
        mRecyclerView.layoutManager = mManager

        mDatabase = Firebase.database.reference.child("posts")
        val options = FirebaseRecyclerOptions.Builder<Post>()
            .setQuery(mDatabase.limitToLast(5), Post::class.java)
            .build()

        mPostAdapter = PostAdapter(options)
        mRecyclerView.adapter = mPostAdapter

        binding.fabNotif.setOnClickListener { fabOnClick() }

        return root
    }

    override fun onStart() {
        super.onStart()
        mPostAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        mPostAdapter.stopListening()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fabOnClick() {
        val count = mPostAdapter.itemCount
        Log.i(TAG, "item amount $count")

        val lastTitle = if (count > 0) mPostAdapter.getItem(count - 1).title else 0
        Log.i(TAG, "last title: $lastTitle")
        mDatabase.push().setValue(
            Post(
                title = lastTitle + 1,
                body = "ou yeah body",
            )
        )
        // remove when done in separate activity (?)
        mRecyclerView.smoothScrollToPosition(count)
    }

}
