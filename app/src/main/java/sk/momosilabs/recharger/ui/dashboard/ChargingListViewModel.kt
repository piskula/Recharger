package sk.momosilabs.recharger.ui.dashboard

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sk.momosilabs.recharger.data.temp.DataSourceTemp
import sk.momosilabs.recharger.data.temp.DataGenerator.Companion.generateNextCharging

class ChargingListViewModel(val dataSource: DataSourceTemp): ViewModel() {

    val chargingListLiveData = dataSource.getChargingList()

    /* If the name and description are present, create new Charging and add it to the datasource */
    fun insertCharging() {
        val lastCharging = dataSource.getLastChargingEvent()

        dataSource.addCharging(
            generateNextCharging(lastCharging = lastCharging)
        )
    }
    
}

class ChargingListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChargingListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChargingListViewModel(
                dataSource = DataSourceTemp.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
