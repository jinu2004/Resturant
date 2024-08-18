package domain.database

import data.FoodFilter
import data.FoodItem
import data.OrderLineCouponCard

 interface NetworkService {

    suspend fun addNewFood(foodItem: FoodItem): Boolean

    suspend fun getAllFood(): List<FoodItem>

    suspend fun getAllFoodByCategory(string: String): List<FoodItem>

    suspend fun delete(foodItem: FoodItem): Boolean

    suspend fun addNewOrder(orderLineCouponCard: OrderLineCouponCard): Boolean

    suspend fun allOrder(): List<OrderLineCouponCard>

    suspend fun ordersByDate(date: Long): List<OrderLineCouponCard>

    suspend fun ordersByStatus(status: String): List<OrderLineCouponCard>

    suspend fun deleteOrder(id: String): Boolean

    suspend fun addNewCategory(foodFilter: FoodFilter): Boolean

    suspend fun deleteCategory(foodFilter: FoodFilter): Boolean

    suspend fun getAllCategory(): List<FoodFilter>
}
