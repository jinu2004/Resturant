package domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.FoodItem
import domain.database.NetworkService
import domain.uiStates.manage_dishes.ManageDishesUiEvent
import domain.uiStates.manage_dishes.MangeDishUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import util.FileHandler
import java.io.File

class ManageDishesViewModel(private val mongoDbSource: NetworkService) : ViewModel() {

    private var _state = MutableStateFlow(MangeDishUiState())

    val uiState = _state.asStateFlow()
    val selectedItemsForDelete = mutableSetOf<FoodItem>()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.update {
                    it.copy(
                        categoryList = mongoDbSource.getAllCategory(),
                        foodItemList = mongoDbSource.getAllFood()
                    )
                }
            } catch (e: Exception) {
                println(e.printStackTrace())

            }
        }
    }

    fun onEvent(event: ManageDishesUiEvent) {
        when (event) {
            is ManageDishesUiEvent.AddNewCategory -> {
                viewModelScope.launch(Dispatchers.IO) {
                    mongoDbSource.addNewCategory(foodFilter = event.foodFilter)
                    try {
                        _state.update {
                            it.copy(
                                categoryList = mongoDbSource.getAllCategory(),
                                foodItemList = mongoDbSource.getAllFood()
                            )
                        }
                    } catch (e: Exception) {
                        println(e.printStackTrace())
                    }
                }
            }

            is ManageDishesUiEvent.AddNewFoodItem -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {

                        val appData = System.getenv("APPDATA")
                        if (appData != null) {
                            val folderName = "RestaurantApp"
                            val newDir = File(appData, folderName)
                            if (!newDir.exists()) {
                                val created = newDir.mkdirs()
                                if (created) {
                                    val parentFolder = "$appData/RestaurantApp"
                                    val imageFolder = "Dish_images"
                                    File(parentFolder, imageFolder).mkdirs()
                                }


                            }
                        }




                        FileHandler().saveImage(
                            file = File(event.coverPage),
                            "${System.getenv("APPDATA")}/RestaurantApp/Dish_images/${event.foodItem.image}.jpg"
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    withContext(Dispatchers.IO) {
                        mongoDbSource.addNewFood(event.foodItem)
                        try {
                            _state.update {
                                it.copy(
                                    categoryList = mongoDbSource.getAllCategory(),
                                    foodItemList = mongoDbSource.getAllFood()
                                )
                            }
                        } catch (e: Exception) {
                            println(e.printStackTrace())
                        }
                    }
                }
            }

            is ManageDishesUiEvent.EditFoodItem -> {

            }

            is ManageDishesUiEvent.FilterFoodItems -> {
                viewModelScope.launch(Dispatchers.IO) {
                    if (event.categoryName == "All") {
                        _state.update {
                            it.copy(
                                foodItemList = mongoDbSource.getAllFood(),
                                currentSelectedCategory = "All Dishes"
                            )
                        }
                    } else {
                        _state.update {
                            it.copy(
                                foodItemList = mongoDbSource.getAllFoodByCategory(event.categoryName),
                                currentSelectedCategory = event.categoryName
                            )
                        }
                    }
                }
            }

            is ManageDishesUiEvent.SelectFoodItemToDelete -> {
                selectedItemsForDelete.add(event.foodItem)
            }

            is ManageDishesUiEvent.DeleteFoodSelectedItem -> {
                viewModelScope.launch(Dispatchers.IO) {
                    selectedItemsForDelete.forEach { items ->
                        println(items.image)
                        mongoDbSource.delete(items)
                        try {
                            val file = File("${System.getenv("APPDATA")}/RestaurantApp/Dish_images/${items.image}.jpg")
                            file.delete()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                    try {
                        _state.update {
                            it.copy(
                                categoryList = mongoDbSource.getAllCategory(),
                                foodItemList = mongoDbSource.getAllFood()
                            )
                        }
                    } catch (e: Exception) {
                        println(e.printStackTrace())
                    }
                }
            }

            is ManageDishesUiEvent.UnSelectFoodItemToDelete -> {
                selectedItemsForDelete.remove(event.foodItem)
            }
        }
    }

}