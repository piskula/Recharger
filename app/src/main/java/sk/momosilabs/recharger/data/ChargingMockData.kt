package sk.momosilabs.recharger.data

import android.content.res.Resources
import sk.momosilabs.recharger.R
import java.math.BigDecimal
import java.time.ZonedDateTime

fun chargingMockData(resources: Resources): List<ChargingEvent> {
    return listOf(
        ChargingEvent(
            id = 11,
            amount = BigDecimal.valueOf(45265, 3),
            percentageStart = 22,
            percentageFinish = 80,
            timestamp = ZonedDateTime.now().minusSeconds(25),
            odoMeter = 12568,
            distanceDriven = 261,
            priceTotal = BigDecimal.valueOf(855, 2),
            image = R.drawable.ev_station,
//            name = resources.getString(R.string.flower1_name),
//            description = resources.getString(R.string.flower1_description)
        ),
        ChargingEvent(
            id = 10,
            amount = BigDecimal.valueOf(23825, 3),
            percentageStart = 39,
            percentageFinish = 69,
            timestamp = ZonedDateTime.now().minusMinutes(2),
            odoMeter = 12307,
            distanceDriven = 240,
            priceTotal = BigDecimal.valueOf(74, 1),
            image = R.drawable.ev_station,
        ),
        ChargingEvent(
            id = 9,
            amount = BigDecimal.valueOf(205, 2),
            percentageStart = 75,
            percentageFinish = 77,
            timestamp = ZonedDateTime.now().minusHours(4),
            odoMeter = 12067,
            distanceDriven = 75,
            priceTotal = BigDecimal.valueOf(11, 1),
            image = R.drawable.ev_station,
        ),
        ChargingEvent(
            id = 8,
            amount = BigDecimal.valueOf(352, 1),
            percentageStart = 12,
            percentageFinish = 89,
            timestamp = ZonedDateTime.now().minusDays(15),
            odoMeter = 11992,
            distanceDriven = 0,
            priceTotal = BigDecimal.valueOf(2618, 2),
        ),
        ChargingEvent(
            id = 7,
            amount = BigDecimal.valueOf(45265, 3),
            percentageStart = 0,
            percentageFinish = 0,
            timestamp = ZonedDateTime.now().minusDays(16),
            odoMeter = 0,
            distanceDriven = 0,
            priceTotal = BigDecimal.ZERO,
        ),
        ChargingEvent(
            id = 6,
            amount = BigDecimal.valueOf(35265, 3),
            percentageStart = 0,
            percentageFinish = 0,
            timestamp = ZonedDateTime.now().minusDays(29),
            odoMeter = 0,
            distanceDriven = 0,
            priceTotal = BigDecimal.ZERO,
        ),
        ChargingEvent(
            id = 5,
            amount = BigDecimal.valueOf(14560, 3),
            percentageStart = 0,
            percentageFinish = 0,
            timestamp = ZonedDateTime.now().minusDays(55),
            odoMeter = 0,
            distanceDriven = 0,
            priceTotal = BigDecimal.ZERO,
        ),
        ChargingEvent(
            id = 4,
            amount = BigDecimal.valueOf(65200, 3),
            percentageStart = 0,
            percentageFinish = 0,
            timestamp = ZonedDateTime.now().minusDays(96),
            odoMeter = 0,
            distanceDriven = 0,
            priceTotal = BigDecimal.ZERO,
        ),
        ChargingEvent(
            id = 3,
            amount = BigDecimal.valueOf(4562, 3),
            percentageStart = 0,
            percentageFinish = 0,
            timestamp = ZonedDateTime.now().minusDays(97),
            odoMeter = 0,
            distanceDriven = 0,
            priceTotal = BigDecimal.ZERO,
        ),
        ChargingEvent(
            id = 2,
            amount = BigDecimal.valueOf(3260101, 3),
            percentageStart = 0,
            percentageFinish = 0,
            timestamp = ZonedDateTime.now().minusDays(114),
            odoMeter = 0,
            distanceDriven = 0,
            priceTotal = BigDecimal.ZERO,
        ),
        ChargingEvent(
            id = 1,
            amount = BigDecimal.valueOf(21022, 3),
            percentageStart = 0,
            percentageFinish = 0,
            timestamp = ZonedDateTime.now().minusDays(190),
            odoMeter = 0,
            distanceDriven = 0,
            priceTotal = BigDecimal.ZERO,
        )
    )
}
