package sk.momosilabs.recharger.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import sk.momosilabs.recharger.R
import sk.momosilabs.recharger.data.ChargingEvent
import sk.momosilabs.recharger.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private val newFlowerActivityRequestCode = 1
    private val chargingListViewModel by viewModels<ChargingListViewModel> {
        ChargingListViewModelFactory(requireContext())
    }

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val dashboardViewModel =
//                ViewModelProvider(this).get(ChargingListViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        val headerAdapter = ChargingListHeaderAdapter()
        val chargingListAdapter = ChargingAdapter { charging -> onChargingClick(charging) }
        val concatAdapter = ConcatAdapter(headerAdapter, chargingListAdapter)

        val recyclerView: RecyclerView = root.findViewById(R.id.recycler_view)
        recyclerView.adapter = concatAdapter

        chargingListViewModel.flowersLiveData.observe(viewLifecycleOwner, {
            it?.let {
                if (it.isEmpty()) {
                    chargingListAdapter.submitList(mutableListOf<ChargingEvent>())
                } else {
                    chargingListAdapter.submitList(it as MutableList<ChargingEvent>)
                }
                headerAdapter.updateFlowerCount(it.size)
            }
        })

        val fab: View = root.findViewById(R.id.fab)
        fab.setOnClickListener {
            fabOnClick()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onChargingClick(event: ChargingEvent) {
        Toast.makeText(context, "Clicked ${event.id}", Toast.LENGTH_SHORT).show()
//        val intent = Intent(this, FlowerDetailActivity()::class.java)
//        intent.putExtra(FLOWER_ID, flower.id)
//        startActivity(intent)
    }

    private fun fabOnClick() {
        chargingListViewModel.insertCharging()
//        val intent = Intent(this, AddFlowerActivity::class.java)
//        startActivityForResult(intent, newFlowerActivityRequestCode)
    }

}
