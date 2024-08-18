package domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.BillingItemList
import domain.database.NetworkService
import domain.uiStates.orderline.OrderLineUIState
import domain.uiStates.orderline.OrderLineUiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val mongodbInterface: NetworkService) : ViewModel() {


    private var _state = MutableStateFlow(OrderLineUIState())
    val uiState = _state.asStateFlow()

    private val billingItemList = mutableSetOf(BillingItemList())


    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.update {
                    it.copy(
                        listOfCategory = mongodbInterface.getAllCategory(),
                        listOfItems = mongodbInterface.getAllFood()
                    )
                }
            } catch (e: Exception) {
                println(e.printStackTrace())

            }
        }
    }


    fun onEvent(event: OrderLineUiEvent) {
        when (event) {
            is OrderLineUiEvent.AddToNewOrder -> {
                billingItemList.add(BillingItemList(event.count, event.foodItem, event.foodItem.price * event.count))
            }

            is OrderLineUiEvent.FilterFoodItems -> {
                viewModelScope.launch(Dispatchers.IO) {
                    if (event.filter == "All") {
                        _state.update {
                            it.copy(
                                listOfItems = mongodbInterface.getAllFood(),
                            )
                        }
                    } else {
                        _state.update {
                            it.copy(
                                listOfItems = mongodbInterface.getAllFoodByCategory(event.filter),
                            )
                        }
                    }
                }
            }

            is OrderLineUiEvent.PlaceNewOrder -> {


            }

            is OrderLineUiEvent.FilterOrderCoupon -> {

                viewModelScope.launch(Dispatchers.IO) {
                    _state.value = if (event.status == "All") {
                        _state.value.copy(orderCoupon = mongodbInterface.allOrder())
                    } else {
                        _state.value.copy(orderCoupon = mongodbInterface.ordersByStatus(event.status))
                    }
                }

                billingItemList.clear()

            }

            is OrderLineUiEvent.deleteOrder -> {
                viewModelScope.launch(Dispatchers.IO) { mongodbInterface.deleteOrder(event.id) }
            }

        }
    }


}