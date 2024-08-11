package domain

import data.FoodItem
import org.litote.kmongo.coroutine.CoroutineDatabase

class MongoDbSource(db: CoroutineDatabase):databaseInterface {
    private val data = db.getCollection<FoodItem>()

    override suspend fun addNewFood(foodItem: FoodItem): Boolean {
        return data.insertOne(foodItem).wasAcknowledged()
    }

    override suspend fun getAllFood(): List<FoodItem> {
        return data.watch<FoodItem>().withDocumentClass()
    }

    override suspend fun getAllFoodByCategory(string: String): List<FoodItem> {
        return data.find(string).toList()
    }

    override fun delete(name: String) {

    }
}