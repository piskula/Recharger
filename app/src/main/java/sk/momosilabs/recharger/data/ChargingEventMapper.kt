package sk.momosilabs.recharger.data

import java.math.BigDecimal
import java.text.DecimalFormat
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

private val decimalFormat = DecimalFormat("0.000")

fun ChargingEvent.toEntity() = ChargingEventEntity(
    id = id,
    amount = decimalFormat.format(amount),
    amountFromCar = amountFromCar?.let { decimalFormat.format(it) },
    percentageStart = percentageStart,
    percentageFinish = percentageFinish,
    duration = duration?.seconds,
    timestamp = timestamp.toEpochSecond(),
    odoMeter = odoMeter,
    distanceDriven = distanceDriven,
    priceTotal = decimalFormat.format(priceTotal),
)

fun ChargingEventEntity.toModel() = ChargingEvent(
    id = id,
    amount = decimalFormat.parse(amount)!!.toDouble().let { BigDecimal.valueOf(it) },
    amountFromCar = if (amountFromCar != null) decimalFormat.parse(amountFromCar)!!.toDouble().let { BigDecimal.valueOf(it) } else null,
    percentageStart = percentageStart,
    percentageFinish = percentageFinish,
    duration = duration?.let { Duration.ofSeconds(it) },
    timestamp = ZonedDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault()),
    odoMeter = odoMeter,
    distanceDriven = distanceDriven,
    priceTotal = decimalFormat.parse(priceTotal)!!.toDouble().let { BigDecimal.valueOf(it) },
)
