package domain

import data.FoodItem

sealed class OrderLineUiEvent {
    data class addToNewOrder(val foodItem: FoodItem) : OrderLineUiEvent()
    data object placeNewOrder : OrderLineUiEvent()
    data class filterItems(val filter: String) : OrderLineUiEvent()
}