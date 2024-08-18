package domain.uiStates.orderline

import data.BillingItemList
import data.FoodFilter
import data.FoodItem
import data.OrderLineCouponCard

data class OrderLineUIState(
    val orderCoupon: List<OrderLineCouponCard> = emptyList(),
    var listOfItems: List<FoodItem> = emptyList(),
    var listOfCategory:List<FoodFilter> = emptyList(),
    val listOfItemsForNewOrder: List<FoodItem> = emptyList(),
    val billingItemList: List<BillingItemList> = emptyList()
)
