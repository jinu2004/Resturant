package data

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class FoodItem(
    @BsonId val id: String = ObjectId().toString(),
    val name: String = "",
    val image: String = "",
    val price: Int = 0,
    val category: String = ""
)
