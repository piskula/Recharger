package sk.momosilabs.recharger.data

import androidx.annotation.DrawableRes
import java.math.BigDecimal
import java.time.Duration
import java.time.ZonedDateTime

data class ChargingEvent(
    val id: Long,
    val amount: BigDecimal,
    val amountFromCar: BigDecimal? = null,
    val percentageStart: Int,
    val percentageFinish: Int,
    val duration: Duration? = null,
    val timestamp: ZonedDateTime,

    val odoMeter: Int,
    val distanceDriven: Int,

    val priceTotal: BigDecimal,

    @DrawableRes
    val image: Int? = null,
)
