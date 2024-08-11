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
import navcontroller.CustomNavigationHost
import navcontroller.ListOfScreen
import navcontroller.rememberNavController
import util.Res

class HomeScreen {


    @Composable
    fun View() {

        val navController by rememberNavController(ListOfScreen.OrderLine.label)




        Column(
            modifier = Modifier.fillMaxSize().background(color = Color(0xFFF1FBE8))
        ) {
            topBar()
            CustomNavigationHost(navController)

        }


    }


    @OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
    @Composable
    private fun topBar() {
        var selectedTab by remember { mutableIntStateOf(0) }
        Column(modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                    Text(
                        text = "Dashbord",
                        style = TextStyle(
                            fontSize = 36.sp,
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
                                    fontSize = 24.sp,
                                    fontFamily = FontFamily(Font(Res.font.inter)),
                                    fontWeight = FontWeight(600),
                                    color = if (selectedTab == 0) Color(0xFF000000) else Color(0xFF7B7878),
                                ),
                                modifier = Modifier.onClick { selectedTab = 0 }
                            )
                        }

                        item {
                            Text(
                                text = "Manage Table",
                                style = TextStyle(
                                    fontSize = 24.sp,
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
                                    fontSize = 24.sp,
                                    fontFamily = FontFamily(Font(Res.font.inter)),
                                    fontWeight = FontWeight(600),
                                    color = if (selectedTab == 2) Color(0xFF000000) else Color(0xFF7B7878),
                                ),
                                modifier = Modifier.onClick { selectedTab = 2 }
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
                            .width(54.dp)
                            .height(54.dp)
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
                            .width(54.dp)
                            .height(54.dp)
                            .background(color = Color(0xFFF1FBE8)),
                        shape = RoundedCornerShape(100),
                        border = BorderStroke(width = 2.dp, color = Color(0xFF000000)),
                        backgroundColor = Color(0xFFF1FBE8),
                    ) {
                        Icon(Icons.Outlined.Notifications, "", modifier = Modifier.padding(15.dp))
                    }


                }
            }

            Divider(modifier = Modifier.fillMaxWidth().padding(top = 5.dp), thickness = 2.dp)
        }


    }






}


