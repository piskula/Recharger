package sk.momosilabs.recharger.ui.dashboard

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sk.momosilabs.recharger.R
import sk.momosilabs.recharger.data.ChargingEvent
import sk.momosilabs.recharger.data.DataSource
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.Duration
import java.time.ZonedDateTime
import kotlin.random.Random

class ChargingListViewModel(val dataSource: DataSource): ViewModel() {

    companion object {
        private const val BATTERY_CAPACITY = 77.4
        private val HUNDRED = BigDecimal.valueOf(100)
    }

    val flowersLiveData = dataSource.getFlowerList()

    /* If the name and description are present, create new Flower and add it to the datasource */
    fun insertCharging() {
        val lastCharging = dataSource.getLastChargingEvent()

        if (lastCharging == null) {
            dataSource.addCharging(
                ChargingEvent(
                    id = 1,
                    amount = BigDecimal.TEN,
                    percentageStart = 67,
                    percentageFinish = 80,
                    duration = Duration.ofMinutes(45),
                    timestamp = ZonedDateTime.now(),
                    odoMeter = 407,
                    distanceDriven = 400,
                    priceTotal = BigDecimal.valueOf(15, 1),
                    image = R.drawable.ev_station,
                )
            )
        } else {
            val maxCapacityPossible = lastCharging.percentageFinish.toBigDecimal().divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(BATTERY_CAPACITY))
            val randomConsumption = BigDecimal.valueOf(Random.nextLong(13, 23))
            val maxDistanceAllowed = maxCapacityPossible.divide(randomConsumption, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))
            val randomDistance = Random.nextInt(1, maxDistanceAllowed.intValueExact())

            val kilowattsSpent = BigDecimal.valueOf(randomDistance.toLong()).divide(HUNDRED, 2, RoundingMode.HALF_UP).multiply(randomConsumption)
            val percentageSpent = kilowattsSpent.divide(BigDecimal.valueOf(BATTERY_CAPACITY), 2, RoundingMode.HALF_UP).multiply(HUNDRED).setScale(0, RoundingMode.UP).toInt()

            val randomPrice = Random.nextDouble(0.09, 0.65)

            val chargedTo = Random.nextInt(lastCharging.percentageFinish - percentageSpent + 1, 100)
            val charged = BigDecimal.valueOf(chargedTo.toLong() - (lastCharging.percentageFinish - percentageSpent))
                .divide(HUNDRED, 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(BATTERY_CAPACITY)).setScale(3, RoundingMode.HALF_UP)

            dataSource.addCharging(
                ChargingEvent(
                    id = lastCharging.id.plus(1),
                    amount = charged,
                    percentageStart = lastCharging.percentageFinish - percentageSpent,
                    percentageFinish = chargedTo,
                    duration = Duration.ofMinutes(Random.nextLong(20, 1700)),
                    timestamp = ZonedDateTime.now(),
                    odoMeter = lastCharging.odoMeter.plus(randomDistance),
                    distanceDriven = randomDistance,
                    priceTotal = BigDecimal.valueOf(randomPrice),
                    image = R.drawable.ev_station,
                )
            )
        }
    }
    
}

class ChargingListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChargingListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChargingListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
