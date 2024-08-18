package domain.uiStates.orderline

import data.FoodItem
import util.OrderStatus

sealed class OrderLineUiEvent {
    data class AddToNewOrder(val foodItem: FoodItem, val count: Int) : OrderLineUiEvent()
    data class PlaceNewOrder(val tableNo: Int) : OrderLineUiEvent()
    data class FilterFoodItems(val filter: String) : OrderLineUiEvent()
    data class FilterOrderCoupon(val status: String = OrderStatus.IN_KITCHEN) : OrderLineUiEvent()
    data class deleteOrder(val id: String) : OrderLineUiEvent()
}