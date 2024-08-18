package domain.database

import data.FoodFilter
import data.FoodItem
import data.OrderLineCouponCard
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class MongoDbSource(db: CoroutineDatabase) : NetworkService {
    private val dataFoods = db.getCollection<FoodItem>()
    private val foodCouponCard = db.getCollection<OrderLineCouponCard>()
    private val category = db.getCollection<FoodFilter>()


    override suspend fun addNewFood(foodItem: FoodItem): Boolean {
        return dataFoods.insertOne(foodItem).wasAcknowledged()
    }

    override suspend fun getAllFood(): List<FoodItem> {
        return dataFoods.find().toList()
    }

    override suspend fun getAllFoodByCategory(string: String): List<FoodItem> {
        return dataFoods.find(FoodItem::category eq string).toList()
    }

    override suspend fun delete(foodItem: FoodItem): Boolean {
        return dataFoods.deleteOne(FoodItem::id eq foodItem.id).wasAcknowledged()
    }

    override suspend fun addNewOrder(orderLineCouponCard: OrderLineCouponCard): Boolean {
        return foodCouponCard.insertOne(orderLineCouponCard).wasAcknowledged()
    }

    override suspend fun allOrder(): List<OrderLineCouponCard> {
        return foodCouponCard.find().toList()
    }

    override suspend fun ordersByDate(date: Long): List<OrderLineCouponCard> {
        return foodCouponCard.find(OrderLineCouponCard::ordered eq date).toList()
    }

    override suspend fun ordersByStatus(status: String): List<OrderLineCouponCard> {
        return foodCouponCard.find(OrderLineCouponCard::status eq status).toList()
    }

    override suspend fun deleteOrder(id: String): Boolean {
        return foodCouponCard.deleteOneById(id).wasAcknowledged()
    }

    override suspend fun addNewCategory(foodFilter: FoodFilter): Boolean {
        return category.insertOne(foodFilter).wasAcknowledged()
    }

    override suspend fun deleteCategory(foodFilter: FoodFilter): Boolean {
        return category.deleteOne(FoodFilter::foodCategoryName eq foodFilter.foodCategoryName).wasAcknowledged()
    }

    override suspend fun getAllCategory(): List<FoodFilter> {
        return category.find().toList()
    }
}