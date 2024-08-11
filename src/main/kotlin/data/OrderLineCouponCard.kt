package data

import util.OrderStatus

data class OrderLineCouponCard(
    var id: String,
    val tableNo: Int,
    val itemsTotalCount: Int,
    val ordered: Long,
    val status: String = OrderStatus.IN_KITCHEN
)
