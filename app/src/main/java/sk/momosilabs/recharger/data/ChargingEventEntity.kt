package sk.momosilabs.recharger.data

import java.io.Serializable

/**
 * Entity to be persisted in Firebase (needs to contain only primitives)
 */
data class ChargingEventEntity(
    val id: Long = 0L,
    val amount: String = "0",
    val amountFromCar: String? = null,
    val percentageStart: Int = 0,
    val percentageFinish: Int = 100,
    val duration: Long? = null,
    val timestamp: Long = 0L,

    val odoMeter: Int = 0,
    val distanceDriven: Int = 0,

    val priceTotal: String = "0",

//    @DrawableRes
//    val image: Int? = null,
) : Serializable
