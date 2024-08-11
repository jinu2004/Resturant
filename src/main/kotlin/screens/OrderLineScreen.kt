package screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.BillingItemList
import data.FoodFilter
import data.OrderLineCouponCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import util.OrderStatus
import util.Res
import util.shadow

class OrderLineScreen {
    @Composable
    fun View() {
        rememberScrollState(0)
        Row(modifier = Modifier.fillMaxSize().padding(20.dp)) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(0.7f).fillMaxHeight(), userScrollEnabled = true
            ) {
                item {
                    OrderFilterButtons()
                    OrderCoupon()
                }
                item {
                    foodCategoryFilter()
                }
                item {
                    foodItems()
                }
            }
            Divider(modifier = Modifier.fillMaxHeight().width(2.dp), thickness = 2.dp)

            BillIngTab()

        }

    }

    @Composable
    private fun OrderFilterButtons() {

        var selectedButton by remember { mutableStateOf(0) }


        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(34.dp)

        ) {
            item {


                OutlinedButton(
                    onClick = { selectedButton = 0 },
                    modifier = Modifier.shadow(
                        offsetY = 2.dp,
                        offsetX = 2.dp,
                        color = if (selectedButton == 0) Color.White else Color(0x80000000),
                        radius = 20.dp
                    ).width(101.dp).height(38.dp),
                    border = BorderStroke(width = 2.dp, color = Color(0xFF000000)),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1FBE8)),
                    contentPadding = PaddingValues(5.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp, pressedElevation = 0.dp)
                ) {
                    Text(
                        text = "All", style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(Res.font.inter)),
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF000000),
                        ), modifier = Modifier.size(24.dp)
                    )

                    Box(
                        modifier = Modifier

                            .padding(start = 5.dp).width(20.dp).height(20.dp).clip(RoundedCornerShape(100))
                            .background(color = Color(0xFF30D721))
                    ) {
                        Text(
                            text = "10", style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(Res.font.inter)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF000000),
                            ), modifier = Modifier.align(Alignment.Center)
                        )
                    }


                }
            }
            item {
                OutlinedButton(
                    onClick = { selectedButton = 1 },
                    modifier = Modifier.shadow(
                        offsetY = 2.dp,
                        offsetX = 2.dp,
                        color = if (selectedButton == 1) Color.White else Color(0x80000000),
                        radius = 20.dp
                    ).wrapContentWidth().height(38.dp),
                    border = BorderStroke(width = 2.dp, color = Color(0xFF000000)),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1FBE8)),
                    contentPadding = PaddingValues(5.dp)
                ) {
                    Text(
                        text = "Dine In", style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(Res.font.inter)),
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF000000),
                        ), modifier = Modifier.padding(horizontal = 10.dp)
                    )

                    Box(
                        modifier = Modifier

                            .padding(horizontal = 5.dp).width(20.dp).height(20.dp).clip(RoundedCornerShape(100))
                            .background(color = Color(0xFF194E1E))

                    ) {
                        Text(
                            text = "10", style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(Res.font.inter)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF000000),
                            ), modifier = Modifier.align(Alignment.Center)
                        )
                    }


                }
            }
            item {
                OutlinedButton(
                    onClick = { selectedButton = 2 },
                    modifier = Modifier.shadow(
                        offsetY = 2.dp,
                        offsetX = 2.dp,
                        color = if (selectedButton == 2) Color.White else Color(0x80000000),
                        radius = 20.dp
                    ).wrapContentWidth().height(38.dp),
                    border = BorderStroke(width = 2.dp, color = Color(0xFF000000)),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1FBE8)),
                    contentPadding = PaddingValues(5.dp)
                ) {
                    Text(
                        text = "Wait List", style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(Res.font.inter)),
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF000000),
                        ), modifier = Modifier.padding(horizontal = 10.dp)
                    )

                    Box(
                        modifier = Modifier.padding(horizontal = 5.dp).width(20.dp).height(20.dp)
                            .clip(RoundedCornerShape(100)).background(color = Color(0xFFEB7E30))

                    ) {
                        Text(
                            text = "10", style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(Res.font.inter)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF000000),
                            ), modifier = Modifier.align(Alignment.Center)
                        )
                    }


                }
            }
            item {
                OutlinedButton(
                    onClick = { selectedButton = 3 },
                    modifier = Modifier.shadow(
                        offsetY = 2.dp,
                        offsetX = 2.dp,
                        color = if (selectedButton == 3) Color.White else Color(0x80000000),
                        radius = 20.dp
                    ).wrapContentWidth().height(38.dp),
                    border = BorderStroke(width = 2.dp, color = Color(0xFF000000)),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1FBE8)),
                    contentPadding = PaddingValues(5.dp)
                ) {
                    Text(
                        text = "Take Away", style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(Res.font.inter)),
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF000000),
                        ), modifier = Modifier.padding(horizontal = 10.dp)
                    )

                    Box(
                        modifier = Modifier.padding(horizontal = 5.dp).width(20.dp).height(20.dp)
                            .clip(RoundedCornerShape(100)).background(color = Color(0xFF8230EB))

                    ) {
                        Text(
                            text = "10", style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(Res.font.inter)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF000000),
                            ), modifier = Modifier.align(Alignment.Center)
                        )
                    }


                }
            }
            item {
                OutlinedButton(
                    onClick = { selectedButton = 4 },
                    modifier = Modifier.shadow(
                        offsetY = 2.dp,
                        offsetX = 2.dp,
                        color = if (selectedButton == 4) Color.White else Color(0x80000000),
                        radius = 20.dp
                    ).wrapContentWidth().height(38.dp),
                    border = BorderStroke(width = 2.dp, color = Color(0xFF000000)),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1FBE8)),
                    contentPadding = PaddingValues(5.dp)
                ) {
                    Text(
                        text = "Served", style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(Res.font.inter)),
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF000000),
                        ), modifier = Modifier.padding(horizontal = 10.dp)
                    )

                    Box(
                        modifier = Modifier.padding(horizontal = 5.dp).width(20.dp).height(20.dp)
                            .clip(RoundedCornerShape(100)).background(color = Color.Black)

                    ) {
                        Text(
                            text = "10", style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(Res.font.inter)),
                                fontWeight = FontWeight(500),
                                color = Color.White,
                            ), modifier = Modifier.align(Alignment.Center)
                        )
                    }


                }
            }
        }


    }

    @Composable
    private fun OrderCoupon() {

        val listofCoupon = listOf(
            OrderLineCouponCard(
                id = "28678", tableNo = 5, ordered = 2L, status = OrderStatus.SERVED, itemsTotalCount = 2
            ),
            OrderLineCouponCard(
                id = "2BN78", tableNo = 7, ordered = 15L, status = OrderStatus.IN_KITCHEN, itemsTotalCount = 2
            ),
            OrderLineCouponCard(
                id = "28FD6", tableNo = 2, ordered = 2L, status = OrderStatus.WAIT_LIST, itemsTotalCount = 2
            ),
            OrderLineCouponCard(
                id = "2OP78", tableNo = 1, ordered = 30L, status = OrderStatus.READY, itemsTotalCount = 2
            ),
            OrderLineCouponCard(
                id = "2SG6E", tableNo = 9, ordered = 8L, status = OrderStatus.SERVED, itemsTotalCount = 2
            ),

            )


        val listState = rememberLazyListState(initialFirstVisibleItemIndex = 0, initialFirstVisibleItemScrollOffset = 2)
        val scope = rememberCoroutineScope()

        Box(modifier = Modifier.fillMaxWidth().padding(top = 30.dp)) {

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(40.dp),
                userScrollEnabled = true,
                modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                state = listState
            ) {
                items(listofCoupon) {

                    var backgroundColor by remember { mutableStateOf(Color(0xFF30D721)) }
                    var textColor by remember { mutableStateOf(Color(0xFF000000)) }

                    when (it.status) {
                        OrderStatus.SERVED -> {
                            backgroundColor = Color.Black
                            textColor = Color.White
                        }

                        OrderStatus.IN_KITCHEN -> {
                            backgroundColor = Color(0xFF30D721)
                            textColor = Color(0xFF000000)
                        }

                        OrderStatus.WAIT_LIST -> {
                            backgroundColor = Color(0xFFE78843)
                            textColor = Color(0xFF000000)
                        }

                        OrderStatus.READY -> {
                            backgroundColor = Color(0xFF9543E7)
                            textColor = Color(0xFF000000)
                        }


                    }

                    Card(
                        modifier = Modifier.shadow(offsetY = 4.dp, offsetX = 4.dp, color = Color.Black, radius = 20.dp)
                            .width(288.dp).height(120.dp),
                        shape = RoundedCornerShape(20.dp),
                        backgroundColor = backgroundColor
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize().padding(13.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(verticalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxHeight()) {
                                Text(
                                    text = "Order #${it.id}", style = TextStyle(
                                        fontSize = 20.sp,
                                        fontFamily = FontFamily(Font(Res.font.inter)),
                                        fontWeight = FontWeight(500),
                                        color = textColor,
                                    )
                                )

                                Text(
                                    text = "Item: ${it.itemsTotalCount}x", style = TextStyle(
                                        fontSize = 20.sp,
                                        fontFamily = FontFamily(Font(Res.font.inter)),
                                        fontWeight = FontWeight(500),
                                        color = textColor,

                                        )
                                )
                                Text(
                                    text = "${it.ordered} minutes ago", style = TextStyle(
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily(Font(Res.font.inter)),
                                        fontWeight = FontWeight(400),
                                        color = textColor,

                                        )
                                )
                            }

                            Column(
                                verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxHeight()
                            ) {
                                Text(
                                    text = " Table ${it.tableNo}",
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        fontFamily = FontFamily(Font(Res.font.inter)),
                                        fontWeight = FontWeight(500),
                                        color = textColor
                                    ),
                                )

                                Card(
                                    modifier = Modifier.wrapContentSize(),
                                    backgroundColor = Color(0xFFF1FBE8),
                                    shape = RoundedCornerShape(size = 22.dp)
                                ) {
                                    Text(
                                        text = it.status, style = TextStyle(
                                            fontSize = 14.sp,
                                            fontFamily = FontFamily(Font(Res.font.inter)),
                                            fontWeight = FontWeight(500),
                                            color = Color.Black

                                        ), modifier = Modifier.padding(10.dp)
                                    )
                                }
                            }


                        }

                    }
                }
            }

            IconButton(
                onClick = {
                    scope.launch {
                        listState.scrollToItem(
                            listState.firstVisibleItemIndex,
                            listState.firstVisibleItemScrollOffset + 400
                        )
                    }
                },
                modifier = Modifier.align(Alignment.CenterStart).padding(20.dp)
                    .background(color = Color(0x91F1FBE8), shape = RoundedCornerShape(100)),
            ) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, "", tint = Color.Black)
            }
            IconButton(
                onClick = {
                    scope.launch {
                        listState.scrollToItem(
                            listState.firstVisibleItemIndex,
                            listState.firstVisibleItemScrollOffset - 400
                        )
                    }
                },
                modifier = Modifier.align(Alignment.CenterEnd).padding(20.dp)
                    .background(color = Color(0x91F1FBE8), shape = RoundedCornerShape(100))
            ) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, "", tint = Color.Black)
            }

        }


    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun foodCategoryFilter() {

        Column(modifier = Modifier.fillMaxWidth().padding(top = 30.dp)) {
            Text(
                text = "Foodies Menu", style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(Res.font.inter)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                )
            )

            val foodFilter = listOf(
                FoodFilter("All Menu", "icons8-ice-cream-sundae-48.png", 124),
                FoodFilter("Dessert", "icons8-cherry-cheesecake-48.png", 14),
                FoodFilter("Fast Food", "icons8-hamburger-48.png", 24),
                FoodFilter("Sea Food", "icons8-fish-food-48.png", 14),
                FoodFilter("Meal", "icons8-ham-48.png", 12),
            )
            var selectedIndex by remember { mutableStateOf(0) }

            LazyRow(
                modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(30.dp),
                userScrollEnabled = true
            ) {
                foodFilter.forEachIndexed { index, it ->
                    item {
                        Card(
                            onClick = { selectedIndex = index },
                            modifier = Modifier.shadow(
                                offsetY = 2.dp,
                                offsetX = 2.dp,
                                color = if (selectedIndex == index) Color.White else Color(0x80000000),
                                radius = 20.dp
                            ).wrapContentSize(),
                            backgroundColor = Color(0xFFF1FBE8),
                            border = BorderStroke(width = 2.dp, color = Color(0xFF000000)),
                            shape = RoundedCornerShape(size = 20.dp)
                        ) {
                            Row(
                                modifier = Modifier.wrapContentWidth().padding(10.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Image(
                                    painter = painterResource("icons/${it.image}"),
                                    contentDescription = "image description",
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier.size(50.dp).align(Alignment.CenterVertically)
                                )
                                Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                                    Text(
                                        text = it.foodCategoryName, style = TextStyle(
                                            fontSize = 20.sp,
                                            fontFamily = FontFamily(Font(Res.font.inter)),
                                            fontWeight = FontWeight(600),
                                            color = Color(0xFF000000),
                                        )
                                    )
                                    Text(
                                        text = "${it.items} items", style = TextStyle(
                                            fontSize = 20.sp,
                                            fontFamily = FontFamily(Font(Res.font.inter)),
                                            fontWeight = FontWeight(600),
                                            color = Color(0xFF454343),
                                        )
                                    )
                                }
                            }
                        }
                    }
                }


            }


        }

    }

    @Composable
    private fun foodItems() {

        LazyVerticalGrid(
            columns = GridCells.FixedSize(230.dp),
            modifier = Modifier.fillMaxWidth().height((2000).dp).padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp), horizontalArrangement = Arrangement.spacedBy(28.dp),
            userScrollEnabled = true
        ) {

            items(120) {
                Card(
                    modifier = Modifier.border(
                        width = 2.dp,
                        color = Color(0xFF000000),
                        shape = RoundedCornerShape(size = 20.dp)
                    ).width(246.dp).height(222.dp),
                    backgroundColor = Color(0xFFF1FBE8),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Row(
                            modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(30.dp)
                        ) {
                            Card(modifier = Modifier.size(85.dp), shape = RoundedCornerShape(100)) {
                                Image(
                                    painter = painterResource("images/OIP.jpeg"),
                                    "",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(14.dp)
                            ) {
                                IconButton(
                                    onClick = {},
                                    modifier = Modifier.padding(1.dp).width(20.dp).height(20.dp)
                                        .background(color = Color(0xFFEB7E30), shape = RoundedCornerShape(100))
                                        .clip(RoundedCornerShape(100))
                                ) {
                                    Icon(Icons.Default.Close, "")
                                }

                                Text(
                                    text = "0", style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(Res.font.inter)),
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF000000),
                                    )
                                )
                                IconButton(
                                    onClick = {},
                                    modifier = Modifier.padding(1.dp).width(20.dp).height(20.dp)
                                        .background(color = Color(0xFF30D721), shape = RoundedCornerShape(100))
                                        .clip(RoundedCornerShape(100))
                                ) {
                                    Icon(Icons.Default.Add, "")
                                }

                            }
                        }

                        Column(
                            modifier = Modifier.fillMaxSize().padding(horizontal = 18.dp),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "Lunch",
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    fontFamily = FontFamily(Font(Res.font.inter)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF454343),
                                )
                            )
                            Text(
                                text = "Grilled Salmon Steak",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(Res.font.inter)),
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF000000),
                                )
                            )

                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(
                                    text = "Price",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(Res.font.inter)),
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF000000),
                                    )

                                )
                                Text(
                                    text = "$15.00",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(Res.font.inter)),
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF8230EB),
                                    )
                                )
                            }
                        }


                    }
                }
            }

        }


    }


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun BillIngTab() {

        val scope = rememberCoroutineScope()

        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(10.dp)) {

            Card(
                modifier = Modifier.shadow(offsetY = 3.dp, offsetX = 3.dp, color = Color.Black, radius = 20.dp)
                    .fillMaxWidth().fillMaxHeight(0.85f),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(2.dp, color = Color.Black),
                backgroundColor = Color(0xFFF1FBE8)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(
                                text = "Table No #03",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontFamily = FontFamily(Font(Res.font.inter)),
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF000000),

                                    )
                            )
                            Text(
                                text = "Order #4386",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(Res.font.inter)),
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF454343),

                                    )
                            )
                        }
                        Row {
                            IconButton(onClick = {}) {
                                Icon(Icons.Outlined.Edit, "")
                            }
                            IconButton(onClick = {}) {
                                Icon(Icons.Outlined.Delete, "", tint = Color(0xFFE78843))
                            }
                        }

                    }
                    Divider()
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(vertical = 10.dp).fillMaxWidth()
                    ) {
                        Text(
                            text = "Ordered items",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(Res.font.inter)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF000000),
                            )
                        )
                        Text(
                            text = "04",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily(Font(Res.font.inter)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF454343),
                            )
                        )
                    }


                    val listOfitem = listOf(BillingItemList(2, "Thanduri chiken", 230))


                    LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.45f), userScrollEnabled = true) {
                        items(listOfitem) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(start = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "x${it.count} ${it.itemName}",
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        fontFamily = FontFamily(Font(Res.font.inter)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF454343),
                                    )
                                )
                                Text(
                                    text = "$${it.totalPrize}",
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        fontFamily = FontFamily(Font(Res.font.inter)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF000000),
                                    )
                                )

                            }
                        }
                    }
                    Divider(modifier = Modifier.padding(vertical = 5.dp))
                    Text(
                        text = "Payment Summary",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(Res.font.inter)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                        )

                    )
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "SubTotal",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(Res.font.inter)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF454343),
                            )
                        )
                        Text(
                            text = "$77",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(Res.font.inter)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF000000),
                            )
                        )

                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Tax",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(Res.font.inter)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF454343),
                            )
                        )
                        Text(
                            text = "$77",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(Res.font.inter)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF000000),
                            )
                        )

                    }

                    Divider()
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(vertical = 10.dp).fillMaxWidth()
                    ) {
                        Text(
                            text = "Total Payable",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(Res.font.inter)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF000000),
                            )
                        )
                        Text(
                            text = "$84",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily(Font(Res.font.inter)),
                                fontWeight = FontWeight(700),
                                color = Color.Black,
                            )
                        )
                    }

                }

            }


            Row(
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).padding(top = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                var buttonIsPressed2 by remember { mutableStateOf(false) }

                Card(
                    onClick = {
                        scope.launch {
                            buttonIsPressed2 = true
                            delay(200)
                            buttonIsPressed2 = false
                        }
                    }, modifier = Modifier
                        .shadow(
                            offsetY = 2.dp,
                            offsetX = 4.dp,
                            color = if (buttonIsPressed2) Color.White else Color.Black,
                            radius = 20.dp
                        ),
                    backgroundColor = Color(0xFFF1FBE8),
                    shape = RoundedCornerShape(size = 20.dp),
                    border = BorderStroke(2.dp, Color.Black)

                ) {
                    Row(
                        modifier = Modifier.wrapContentWidth().padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Icon(
                            painterResource("icons/print.svg"),
                            "",
                            modifier = Modifier.padding(start = 10.dp).size(30.dp)
                        )
                        Text(
                            text = "Print",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(Res.font.inter)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF000000),
                            ),
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }
                }


                var buttonIsPressed by remember { mutableStateOf(false) }





                Card(
                    onClick = {
                        scope.launch {
                            buttonIsPressed = true
                            delay(200)
                            buttonIsPressed = false
                        }


                    }, modifier = Modifier
                        .shadow(
                            offsetY = 3.dp,
                            offsetX = 5.dp,
                            color = if (buttonIsPressed) Color.White else Color.Black,
                            radius = 20.dp
                        ),
                    backgroundColor = Color(0xFF30D721),
                    shape = RoundedCornerShape(size = 20.dp),
                    border = BorderStroke(2.dp, Color.Black)

                ) {

                    Row(
                        modifier = Modifier.wrapContentWidth().padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painterResource("icons/bagIcon.svg"),
                            "",
                            modifier = Modifier.padding(start = 20.dp).size(30.dp)
                        )
                        Text(
                            text = "Place Order",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(Res.font.inter)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF000000),
                            ),
                            modifier = Modifier.padding(horizontal = 20.dp)
                        )
                    }
                }

            }


        }
    }


}