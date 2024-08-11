package navcontroller

import androidx.compose.runtime.Composable
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
    navController: NavController
) {
    NavigationHost(navController) {
        composable(ListOfScreen.OrderLine.label) {
            OrderLineScreen().View()
        }
        composable(ListOfScreen.ManageTable.label) {

        }

    }.build()
}