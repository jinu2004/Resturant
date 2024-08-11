package domain

import data.FoodItem

interface databaseInterface {

    suspend fun addNewFood(foodItem: FoodItem): Boolean

    suspend fun getAllFood(): List<FoodItem>

    suspend fun getAllFoodByCategory(string: String): List<FoodItem>

    fun delete(name: String)
}