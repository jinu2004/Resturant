package domain

import data.BillingItemList
import data.FoodItem

data class OrderLineUIState(
    val orderCoupon: List<OrderLineUIState> = emptyList(),
    val listOfItems: List<FoodItem> = emptyList(),
    val listOfItemsForNewOrder: List<FoodItem> = emptyList(),
    val billingItemList: List<BillingItemList> = emptyList()
)