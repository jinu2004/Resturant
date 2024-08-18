package domain.uiStates.manage_dishes

import data.FoodFilter
import data.FoodItem

data class MangeDishUiState(
    var categoryList: List<FoodFilter> = emptyList(),
    val foodItemList: List<FoodItem> = emptyList(),
    val currentSelectedCategory: String = "All Dishes",
)
