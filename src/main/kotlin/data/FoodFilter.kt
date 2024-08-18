package data

import kotlinx.serialization.Serializable

@Serializable
data class FoodFilter(val foodCategoryName: String = "", val image: String = "", val items: Int = 0)
