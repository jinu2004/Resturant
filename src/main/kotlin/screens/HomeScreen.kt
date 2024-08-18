package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sun.tools.javac.Main
import domain.viewmodel.MainViewModel
import domain.viewmodel.ManageDishesViewModel
import navcontroller.CustomNavigationHost
import navcontroller.ListOfScreen
import navcontroller.NavController
import navcontroller.rememberNavController
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import util.Res

class HomeScreen() {


    @OptIn(KoinExperimentalAPI::class)
    @Composable
    fun View() {
        val navController by rememberNavController(ListOfScreen.OrderLine.label)

        Column(
            modifier = Modifier.fillMaxSize().defaultMinSize(1300.dp, 800.dp).background(color = Color(0xFFF1FBE8))
        ) {
            topBar(navController)
            CustomNavigationHost(
                navController, manageDishesViewModel = koinViewModel<ManageDishesViewModel>(),
                mainViewModel = koinViewModel<MainViewModel>()
            )

        }


    }


    @OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
    @Composable
    private fun topBar(navController: NavController) {
        var selectedTab by remember { mutableIntStateOf(0) }
        Column(modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "Dashboard",
                        style = TextStyle(
                            fontSize = 34.sp,
                            fontFamily = FontFamily(Font(Res.font.inter)),
                            fontWeight = FontWeight.Black,
                            color = Color(0xFF000000),
                        ),
                        modifier = Modifier.padding()
                    )

                    LazyRow(
                        modifier = Modifier.padding(),
                        horizontalArrangement = Arrangement.spacedBy(45.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        item {
                            Text(
                                text = "Order Line",
                                style = TextStyle(
                                    fontSize = 21.sp,
                                    fontFamily = FontFamily(Font(Res.font.inter)),
                                    fontWeight = FontWeight(600),
                                    color = if (selectedTab == 0) Color(0xFF000000) else Color(0xFF7B7878),
                                ),
                                modifier = Modifier.onClick {
                                    selectedTab = 0
                                    navController.navigate(ListOfScreen.OrderLine.label)

                                }
                            )
                        }

                        item {
                            Text(
                                text = "Manage Table",
                                style = TextStyle(
                                    fontSize = 21.sp,
                                    fontFamily = FontFamily(Font(Res.font.inter)),
                                    fontWeight = FontWeight(600),
                                    color = if (selectedTab == 1) Color(0xFF000000) else Color(0xFF7B7878),
                                ),
                                modifier = Modifier.onClick { selectedTab = 1 }
                            )
                        }

                        item {
                            Text(
                                text = "Manage Dishes",
                                style = TextStyle(
                                    fontSize = 21.sp,
                                    fontFamily = FontFamily(Font(Res.font.inter)),
                                    fontWeight = FontWeight(600),
                                    color = if (selectedTab == 2) Color(0xFF000000) else Color(0xFF7B7878),
                                ),
                                modifier = Modifier.onClick {
                                    selectedTab = 2
                                    navController.navigate(ListOfScreen.ManageDishes.label)
                                }
                            )
                        }


                    }
                }


                Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    Card(
                        onClick = {},
                        modifier = Modifier
                            .padding(2.dp)
                            .shadow(
                                elevation = 4.dp,
                                spotColor = Color(0x00000000),
                                ambientColor = Color(0x0000000),
                                shape = RoundedCornerShape(100)
                            )
                            .width(48.dp)
                            .height(48.dp)
                            .background(color = Color(0xFFF1FBE8)),
                        shape = RoundedCornerShape(100),
                        border = BorderStroke(width = 2.dp, color = Color(0xFF000000)),
                        backgroundColor = Color(0xFFF1FBE8),
                    ) {
                        Icon(Icons.Outlined.MailOutline, "", modifier = Modifier.padding(15.dp))
                    }
                    Card(
                        onClick = {},
                        modifier = Modifier
                            .padding(2.dp)
                            .shadow(
                                elevation = 4.dp,
                                spotColor = Color(0x00000000),
                                ambientColor = Color(0x0000000),
                                shape = RoundedCornerShape(100),
                                clip = true
                            )
                            .width(48.dp)
                            .height(48.dp)
                            .background(color = Color(0xFFF1FBE8)),
                        shape = RoundedCornerShape(100),
                        border = BorderStroke(width = 2.dp, color = Color(0xFF000000)),
                        backgroundColor = Color(0xFFF1FBE8),
                    ) {
                        Icon(Icons.Outlined.Notifications, "", modifier = Modifier.padding(15.dp))
                    }


                }
            }

            Divider(modifier = Modifier.fillMaxWidth().padding(top = 10.dp), thickness = 2.dp, color = Color.Black)
        }


    }

}


