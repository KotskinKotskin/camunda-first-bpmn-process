package bpmn2.storm.backend.model

import java.util.Date

data class BusinessTrip(
    val id: String? = null,
    val from: String? = null,
    val to: String? = null,
    val dateFrom: Date? = null,
    val dateTo: Date? = null,
    val reason: String? = null,
    val employee: String? = null,
    val amount: Long? = null,
    val approveResult: String? = null,
    val whatToFix: String? = null,
    val tripReady: Boolean = false,
    val tripProblemComment: String? = null,
    val flyDescription: String? =  null,
    val hotelDescription: String? = null
        )

