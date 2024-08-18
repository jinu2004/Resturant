package domain.uiStates.manage_dishes

import data.FoodFilter
import data.FoodItem

sealed class ManageDishesUiEvent {
    data class AddNewFoodItem(val foodItem: FoodItem,val coverPage:String) : ManageDishesUiEvent()
    data class AddNewCategory(val foodFilter: FoodFilter) : ManageDishesUiEvent()
    data class FilterFoodItems(val categoryName: String) : ManageDishesUiEvent()
    data class EditFoodItem(val foodItem: FoodItem) : ManageDishesUiEvent()
    data class SelectFoodItemToDelete(val foodItem: FoodItem) : ManageDishesUiEvent()
    data class UnSelectFoodItemToDelete(val foodItem: FoodItem) : ManageDishesUiEvent()
    data object DeleteFoodSelectedItem : ManageDishesUiEvent()


}