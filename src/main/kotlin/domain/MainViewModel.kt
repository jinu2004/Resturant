package domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.BillingItemList
import data.FoodItem
import kotlinx.coroutines.launch

class MainViewModel(val mongodbInterface: databaseInterface) : ViewModel() {
    var state by mutableStateOf(OrderLineUIState())



    fun onEvent(event: OrderLineUiEvent) {
        when (event) {
            is OrderLineUiEvent.addToNewOrder -> {

            }

            is OrderLineUiEvent.filterItems -> {

                if (event.filter != "All") {
                    viewModelScope.launch {
                        state.copy(listOfItems = mongodbInterface.getAllFoodByCategory(event.filter))
                    }
                } else {
                    viewModelScope.launch {
                        state.copy(listOfItems = mongodbInterface.getAllFood())
                    }
                }
            }

            OrderLineUiEvent.placeNewOrder -> {

            }
        }
    }


}