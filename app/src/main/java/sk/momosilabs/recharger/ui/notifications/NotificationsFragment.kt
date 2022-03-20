package sk.momosilabs.recharger.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import sk.momosilabs.recharger.data.ChargingEvent
import sk.momosilabs.recharger.data.ChargingEventEntity
import sk.momosilabs.recharger.data.temp.DataGenerator.Companion.generateNextCharging
import sk.momosilabs.recharger.data.toEntity
import sk.momosilabs.recharger.data.toModel
import sk.momosilabs.recharger.databinding.FragmentNotificationsBinding


class NotificationsFragment : Fragment() {

    companion object {
        private const val TAG = "NotificationsFragment"
    }

    private var _binding: FragmentNotificationsBinding? = null

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mChargingRecyclerAdapter: ChargingRecyclerAdapter
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

        mDatabase = Firebase.database.reference.child("chargingHistory")
        val options = FirebaseRecyclerOptions.Builder<ChargingEventEntity>()
            .setQuery(mDatabase.limitToLast(5), ChargingEventEntity::class.java)
            .build()

        mChargingRecyclerAdapter = ChargingRecyclerAdapter(options) { charging -> onChargingClick(charging) }
        mRecyclerView.adapter = mChargingRecyclerAdapter

        binding.fabNotif.setOnClickListener { fabOnClick() }

        return root
    }

    override fun onStart() {
        super.onStart()
        mChargingRecyclerAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        mChargingRecyclerAdapter.stopListening()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fabOnClick() {
        val count = mChargingRecyclerAdapter.itemCount
        val lastCharging = if (count > 0) mChargingRecyclerAdapter.getItem(count - 1).toModel() else null

        mDatabase.push().setValue(
            generateNextCharging(lastCharging = lastCharging).toEntity()
        )
        // remove when done in separate activity (?)
        mRecyclerView.smoothScrollToPosition(count)
    }

    private fun onChargingClick(event: ChargingEvent) {
        Toast.makeText(context, "Clicked ${event.id}", Toast.LENGTH_SHORT).show()
        // intent, startActivity with charging detail
    }

}
