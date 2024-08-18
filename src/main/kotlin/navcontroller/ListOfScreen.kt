package navcontroller

import androidx.compose.runtime.Composable
import domain.viewmodel.MainViewModel
import domain.viewmodel.ManageDishesViewModel
import screens.ManageDishes
import screens.OrderLineScreen

enum class ListOfScreen(
    val label: String,
    //
) {
    OrderLine(
        label = "Order Line"
    ),
    ManageTable(
        label = "Manage Table"
    ),
    ManageDishes(
        label = "Manage Dishes"
    )


}

@Composable
fun CustomNavigationHost(
    navController: NavController, manageDishesViewModel: ManageDishesViewModel, mainViewModel: MainViewModel
) {
    NavigationHost(navController) {
        composable(ListOfScreen.OrderLine.label) {
            OrderLineScreen(mainViewModel).View()
        }
        composable(ListOfScreen.ManageDishes.label) {
            ManageDishes(manageDishesViewModel).View()
        }

    }.build()
}