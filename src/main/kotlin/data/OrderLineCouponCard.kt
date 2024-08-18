package data

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import util.OrderStatus


@Serializable
data class OrderLineCouponCard(
    @BsonId var id: String = ObjectId().toString(),
    val tableNo: Int,
    val itemsTotalCount: Int,
    val ordered: Long,
    val status: String = OrderStatus.IN_KITCHEN,
    val listOfItem: List<FoodItem>
)
