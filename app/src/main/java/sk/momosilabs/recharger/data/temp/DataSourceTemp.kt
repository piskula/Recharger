package sk.momosilabs.recharger.data.temp

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import sk.momosilabs.recharger.data.ChargingEvent
import sk.momosilabs.recharger.data.chargingMockData

class DataSourceTemp(resources: Resources) {
    private val initialList = chargingMockData(resources)
//    private val initialList = listOf<ChargingEvent>()
    private val chargingListLiveData = MutableLiveData(initialList)

    fun addCharging(event: ChargingEvent) {
        val currentList = chargingListLiveData.value
        if (currentList == null) {
            chargingListLiveData.postValue(listOf(event))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, event)
            chargingListLiveData.postValue(updatedList)
        }
    }

    fun getLastChargingEvent(): ChargingEvent? {
        val list = chargingListLiveData.value
        if (list == null || list.isEmpty())
            return null
        else
            return list[0]
    }

    fun removeCharging(charging: ChargingEvent) {
        val currentList = chargingListLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(charging)
            chargingListLiveData.postValue(updatedList)
        }
    }

    fun getChargingById(id: Long): ChargingEvent? =
        chargingListLiveData.value?.let { events ->
            return events.firstOrNull{ it.id == id}
        }

    fun getChargingList(): LiveData<List<ChargingEvent>> = chargingListLiveData

    companion object {
        private var INSTANCE: DataSourceTemp? = null

        fun getDataSource(resources: Resources): DataSourceTemp {
            return synchronized(DataSourceTemp::class) {
                val newInstance = INSTANCE ?: DataSourceTemp(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}