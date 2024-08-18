@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)

package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import data.FoodFilter
import data.FoodItem
import domain.uiStates.manage_dishes.ManageDishesUiEvent
import domain.viewmodel.ManageDishesViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import util.Res
import util.shadow
import java.io.File

class ManageDishes(private val viewModel: ManageDishesViewModel) {

    @Composable
    fun View() {
        var searchText by remember { mutableStateOf("") }
        Row(modifier = Modifier.fillMaxSize().background(color = Color(0xFFF1FBE8))) {

            foodCategoryFilter()
            Divider(modifier = Modifier.fillMaxHeight().width(2.dp), thickness = 2.dp, color = Color.Black)
            Column(modifier = Modifier.fillMaxSize().padding(horizontal = 30.dp, vertical = 20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Manage Dishes",
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontFamily = FontFamily(Font(Res.font.inter)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                        )
                    )

                    OutlinedTextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        shape = RoundedCornerShape(20.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF30D721),
                            unfocusedBorderColor = Color.Black
                        ),
                        leadingIcon = { Icon(Icons.Outlined.Search, "") }
                    )

                }

                foodList()


            }
        }


    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun foodCategoryFilter() {
        Column(
            modifier = Modifier.fillMaxHeight().width(300.dp).background(color = Color(0xFFD4EDBE))

        ) {
            Column(
                modifier = Modifier.fillMaxHeight(0.8f).padding(start = 10.dp, end = 10.dp, top = 20.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Text(
                    text = "Foodies Menu", style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(Res.font.inter)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000),
                    ),
                    modifier = Modifier.fillMaxWidth().padding(top = 0.dp),
                    textAlign = TextAlign.Center
                )

                rememberCoroutineScope()


                var foodFilter by remember { mutableStateOf(emptyList<FoodFilter>()) }


                LaunchedEffect(Unit) {
                    viewModel.uiState.collect {
                        foodFilter = it.categoryList
                    }
                }


                var selectedIndex by remember { mutableStateOf(foodFilter.size + 1) }

                LazyColumn(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    userScrollEnabled = true
                ) {

                    item {
                        Card(
                            onClick = {
                                selectedIndex = foodFilter.size + 1
                                viewModel.onEvent(ManageDishesUiEvent.FilterFoodItems("All"))
                            },
                            modifier = Modifier.shadow(
                                offsetY = 2.dp,
                                offsetX = 2.dp,
                                color = if (selectedIndex == foodFilter.size + 1) Color.Transparent else Color(
                                    0x80000000
                                ),
                                radius = 20.dp
                            ).wrapContentHeight().width(250.dp),
                            backgroundColor = Color(0xFFF1FBE8),
                            border = BorderStroke(width = 2.dp, color = Color(0xFF000000)),
                            shape = RoundedCornerShape(size = 20.dp)
                        ) {
                            Row(
                                modifier = Modifier.wrapContentWidth()
                                    .padding(horizontal = 20.dp, vertical = 10.dp),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                Image(
                                    painter = painterResource("icons/categoryIcons/icons8-ice-cream-sundae-48.png"),
                                    contentDescription = "image description",
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier.size(50.dp).align(Alignment.CenterVertically)
                                )
                                Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                                    Text(
                                        text = "All", style = TextStyle(
                                            fontSize = 20.sp,
                                            fontFamily = FontFamily(Font(Res.font.inter)),
                                            fontWeight = FontWeight(600),
                                            color = Color(0xFF000000),
                                        ),
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }

                    foodFilter.forEachIndexed { index, it ->
                        item {
                            Card(
                                onClick = {
                                    selectedIndex = index
                                    viewModel.onEvent(ManageDishesUiEvent.FilterFoodItems(it.foodCategoryName))
                                },
                                modifier = Modifier.shadow(
                                    offsetY = 2.dp,
                                    offsetX = 2.dp,
                                    color = if (selectedIndex == index) Color.Transparent else Color(0x80000000),
                                    radius = 20.dp
                                ).wrapContentHeight().width(250.dp),
                                backgroundColor = Color(0xFFF1FBE8),
                                border = BorderStroke(width = 2.dp, color = Color(0xFF000000)),
                                shape = RoundedCornerShape(size = 20.dp)
                            ) {
                                Row(
                                    modifier = Modifier.wrapContentWidth()
                                        .padding(horizontal = 20.dp, vertical = 10.dp),
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    Image(
                                        painter = painterResource("icons/categoryIcons/${it.image}"),
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
                                            ),
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }
                        }
                    }


                }


            }
            Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp, color = Color.Black)
            Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF1FBE8))) {

                val scope = rememberCoroutineScope()
                var buttonIsPressed by remember { mutableStateOf(false) }
                var isOpen by remember { mutableStateOf(false) }


                if (isOpen) addCategory(onDismissRequest = { isOpen = false }, onConfirmation = { isOpen = false })

                Card(
                    onClick = {
                        scope.launch {
                            buttonIsPressed = true
                            delay(200)
                            buttonIsPressed = false
                        }
                        isOpen = true

                    }, modifier = Modifier
                        .shadow(
                            offsetY = 2.dp,
                            offsetX = 2.dp,
                            color = if (buttonIsPressed) Color.White else Color.Black,
                            radius = 15.dp
                        ).align(Alignment.Center),
                    backgroundColor = Color(0xFF30D721),
                    shape = RoundedCornerShape(size = 15.dp),
                    border = BorderStroke(2.dp, Color.Black)

                ) {

                    Row(
                        modifier = Modifier.wrapContentWidth().padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Outlined.Add,
                            "",
                            modifier = Modifier.padding(start = 20.dp).size(30.dp)
                        )
                        Text(
                            text = "Add New Category",
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

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun foodList() {
        Card(
            backgroundColor = Color(0xFFE9F5DE),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxSize().padding(top = 20.dp),
            elevation = 0.dp
        ) {
            var categoryName by remember { mutableStateOf("All Dishes") }
            var itemCount by remember { mutableStateOf(0) }
            LaunchedEffect(Unit) {
                viewModel.uiState.collectLatest {
                    categoryName = it.currentSelectedCategory
                    itemCount = it.foodItemList.size
                }
            }


            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp, vertical = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$categoryName  ($itemCount)",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(Res.font.inter)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                        ),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                    var deleteIconSize by remember { mutableStateOf(0.dp) }

                    deleteIconSize = if (viewModel.selectedItemsForDelete.size == 0) 0.dp else 45.dp

                    Card(
                        onClick = { viewModel.onEvent(ManageDishesUiEvent.DeleteFoodSelectedItem) },
                        modifier = Modifier
                            .shadow(
                                elevation = 4.dp,
                                spotColor = Color(0x00000000),
                                ambientColor = Color(0x0000000),
                                shape = RoundedCornerShape(100)
                            )
                            .size(40.dp)
                            .background(color = Color(0xFFF1FBE8)),
                        shape = RoundedCornerShape(100),
                        border = BorderStroke(width = 2.dp, color = Color(0xFF000000)),
                        backgroundColor = Color(0xFFF1FBE8),
                    ) {
                        Icon(Icons.Outlined.Delete, "", modifier = Modifier.padding(5.dp))
                    }

                }

                foodItems()

            }
        }
    }


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun foodItems() {

        var isOpen by remember { mutableStateOf(false) }
        var foodItem by remember { mutableStateOf(emptyList<FoodItem>()) }

        ArrayList<Int>()

        if (isOpen) addNewFoodItem(
            onDismissRequest = { isOpen = !isOpen },
            onConfirmation = { isOpen = !isOpen })


        LaunchedEffect(Unit) {
            viewModel.uiState.collect {
                foodItem = it.foodItemList
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp),
            modifier = Modifier.fillMaxWidth().height((2000).dp).padding(start = 20.dp, top = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp), horizontalArrangement = Arrangement.spacedBy(10.dp),
            userScrollEnabled = true
        ) {

            item {
                Card(
                    modifier = Modifier.border(
                        width = 2.dp,
                        color = Color(0xFF000000),
                        shape = RoundedCornerShape(size = 20.dp)
                    ).size(200.dp),
                    backgroundColor = Color(0xFFF1FBE8),
                    shape = RoundedCornerShape(20.dp),
                    onClick = { isOpen = !isOpen }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Box(
                            Modifier
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFF000000),
                                    shape = RoundedCornerShape(size = 10.dp)
                                )
                                .width(45.dp)
                                .height(45.dp)
                                .background(color = Color(0xFF30D721), shape = RoundedCornerShape(size = 10.dp))
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Icon(Icons.Filled.Add, "", modifier = Modifier.align(Alignment.Center))
                        }


                        Text(
                            text = "Add New Dish To Dessert",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(Res.font.inter)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Center,
                            ), modifier = Modifier.width(169.dp)
                                .height(46.dp)

                        )

                    }
                }
            }

            foodItem.forEachIndexed { _, item ->
                item {
                    var isChecked by remember { mutableStateOf(false) }
                    Card(
                        modifier = Modifier.border(
                            width = 2.dp,
                            color = if (isChecked) Color.Red else Color(0xFF000000),
                            shape = RoundedCornerShape(size = 20.dp)
                        ).fillMaxWidth().fillMaxHeight(),
                        backgroundColor = Color(0xFFF1FBE8),
                        shape = RoundedCornerShape(20.dp),
                    ) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Row(
                                modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp).fillMaxWidth(),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Card(modifier = Modifier.size(85.dp), shape = RoundedCornerShape(100)) {
                                    Image(
                                        bitmap = loadImageBitmap(
                                            File("${System.getenv("APPDATA")}/RestaurantApp/Dish_images/${item.image}.jpg").inputStream()
                                                .buffered()
                                        ),
                                        "",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                }

                                println(viewModel.selectedItemsForDelete.toString())

                                Checkbox(checked = isChecked, onCheckedChange = {
                                    isChecked = !isChecked
                                    if (isChecked) {
                                        viewModel.onEvent(ManageDishesUiEvent.SelectFoodItemToDelete(item))
                                    }
                                    if (!isChecked)
                                        viewModel.onEvent(ManageDishesUiEvent.UnSelectFoodItemToDelete(item))
                                })

                            }

                            Column(
                                modifier = Modifier.fillMaxSize().padding(horizontal = 18.dp, vertical = 10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Text(
                                    text = item.category,
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        fontFamily = FontFamily(Font(Res.font.inter)),
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF454343),
                                    )
                                )
                                Text(
                                    text = item.name,
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily(Font(Res.font.inter)),
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF000000),
                                    )
                                )

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
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
                                        text = "$${item.price}",
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


    }


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun addCategory(onDismissRequest: () -> Unit, onConfirmation: () -> Unit) {
        var categoryName by remember { mutableStateOf("") }
        var coverPage by remember { mutableStateOf("icons8-fish-food-48.png") }
        var isImageOpen by remember { mutableStateOf(false) }

        if (isImageOpen) coverPage = SelectCategoryImage(onDismissRequest = { isImageOpen = !isImageOpen })



        Dialog(onDismissRequest = onDismissRequest, properties = DialogProperties(dismissOnBackPress = true)) {
            Card(modifier = Modifier.wrapContentSize(), shape = RoundedCornerShape(20.dp)) {
                LazyColumn(
                    modifier = Modifier.wrapContentSize().padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Text(
                            text = "Add New Category",
                            style = TextStyle(
                                fontSize = 30.sp,
                                fontFamily = FontFamily(Font(Res.font.inter)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF000000),
                            ),
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
                        )
                    }
                    item {
                        Card(modifier = Modifier.size(85.dp), shape = RoundedCornerShape(100), onClick = {
                            isImageOpen = !isImageOpen
                        }) {
                            Image(
                                painterResource("icons/categoryIcons/$coverPage"),
                                "",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    item {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                            value = categoryName,
                            onValueChange = { categoryName = it },
                            maxLines = 3,
                            readOnly = false,
                            label = {
                                Text(
                                    "Category ", fontWeight = FontWeight(1000),
                                    fontFamily = FontFamily(Font(Res.font.inter)),
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            },
                            placeholder = {
                                Text(
                                    "Category Name", fontWeight = FontWeight(1000),
                                    fontFamily = FontFamily(Font(Res.font.inter)),
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        )
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth().padding(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            TextButton(
                                onClick = { onDismissRequest() },
                                modifier = Modifier.padding(8.dp),
                            ) {
                                Text("Dismiss")
                            }
                            TextButton(
                                onClick = {
                                    onConfirmation()
                                    viewModel.onEvent(
                                        ManageDishesUiEvent.AddNewCategory(
                                            FoodFilter(
                                                foodCategoryName = categoryName,
                                                image = coverPage
                                            )
                                        )
                                    )
                                },
                                modifier = Modifier.padding(8.dp),
                            ) {
                                Text("Confirm")
                            }
                        }
                    }

                }


            }
        }
    }


    @Composable
    private fun addNewFoodItem(
        onDismissRequest: () -> Unit,
        onConfirmation: () -> Unit
    ) {

        var showFilePicker by remember { mutableStateOf(false) }
        var dishName by remember { mutableStateOf("") }
        var category by remember { mutableStateOf("") }
        var categoryExpand by remember { mutableStateOf(false) }
        var price by remember { mutableStateOf(0) }
        var coverPage by remember { mutableStateOf("") }
        var rememberAlert by mutableStateOf(false)
        FilePicker(show = showFilePicker, fileExtensions = listOf("jpg")) { file ->
            if (file != null && dishName.isNotEmpty()) {
                coverPage = file.path
            }
            showFilePicker = !showFilePicker

        }



        Dialog(onDismissRequest = onDismissRequest) {
            Card {


                if (rememberAlert) {
                    AlertDialogPopUP(
                        onDismissRequest = { rememberAlert = !rememberAlert },
                        onConfirmation = { rememberAlert = !rememberAlert },
                        "Warning",
                        "fill All input fields"
                    )
                }


                LazyColumn(
                    modifier = Modifier.wrapContentSize().padding(30.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Text(
                            text = "Add New Dish",
                            fontWeight = FontWeight(1000),
                            fontFamily = FontFamily(Font(Res.font.inter)),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(20.dp)
                        )
                    }

                    item {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = dishName,
                            onValueChange = { dishName = it },
                            maxLines = 3,
                            readOnly = false,
                            label = {
                                Text(
                                    "Item Name", fontWeight = FontWeight(1000),
                                    fontFamily = FontFamily(Font(Res.font.inter)),
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            },
                            placeholder = {
                                Text(
                                    "Item Name", fontWeight = FontWeight(1000),
                                    fontFamily = FontFamily(
                                        Font(
                                            Res.font.inter
                                        )
                                    ),
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        )
                    }

                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {

                            Box(modifier = Modifier.fillMaxWidth()) {
                                OutlinedTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = category,
                                    onValueChange = { category = it },
                                    maxLines = 1,
                                    readOnly = true,
                                    label = {
                                        Text(
                                            "Select category",
                                            fontWeight = FontWeight(1000),
                                            fontFamily = FontFamily(Font(Res.font.inter)),
                                            fontSize = 16.sp,
                                            modifier = Modifier.padding(start = 10.dp)
                                        )
                                    },
                                    trailingIcon = {
                                        androidx.compose.material3.IconButton(
                                            onClick = { categoryExpand = !categoryExpand }
                                        ) {
                                            androidx.compose.material3.Icon(Icons.Filled.ArrowDropDown, "")
                                        }
                                    },
                                    placeholder = {
                                        Text(
                                            "--Select category--",
                                            fontWeight = FontWeight(1000),
                                            fontFamily = FontFamily(Font(Res.font.inter)),
                                            fontSize = 16.sp,
                                            modifier = Modifier.padding(start = 10.dp)
                                        )
                                    },
                                )
                                DropdownMenu(
                                    expanded = categoryExpand,
                                    onDismissRequest = { categoryExpand = !categoryExpand })
                                {
                                    var foodFilter by remember { mutableStateOf(emptyList<FoodFilter>()) }
                                    LaunchedEffect(Unit) {
                                        viewModel.uiState.collectLatest {
                                            foodFilter = it.categoryList
                                        }
                                    }

                                    foodFilter.forEach {
                                        DropdownMenuItem(
                                            onClick = {
                                                category = it.foodCategoryName
                                                categoryExpand = !categoryExpand
                                            },
                                            text = {
                                                Text(
                                                    it.foodCategoryName, fontWeight = FontWeight(1000),
                                                    fontFamily = FontFamily(Font(Res.font.inter)),
                                                    fontSize = 16.sp,
                                                    modifier = Modifier.padding(start = 10.dp)
                                                )
                                            })
                                    }
                                }
                            }
                        }
                    }

                    item {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(0.4f).onFocusEvent {
                                    if (it.isFocused) {
                                        showFilePicker = !showFilePicker
                                    }
                                },
                                value = coverPage,
                                onValueChange = { coverPage = it },
                                maxLines = 1,
                                readOnly = true,
                                label = {
                                    Text(
                                        "image", fontWeight = FontWeight(1000),
                                        fontFamily = FontFamily(Font(Res.font.inter)),
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                },
                                placeholder = {
                                    Text(
                                        "image", fontWeight = FontWeight(1000),
                                        fontFamily = FontFamily(Font(Res.font.inter)),
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                },
                                leadingIcon = {
                                    androidx.compose.material3.IconButton(
                                        onClick = { }
                                    ) {
                                        androidx.compose.material3.Icon(
                                            painterResource("images/imagePlace Holder.svg"),
                                            null
                                        )
                                    }

                                },
                            )



                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = price.toString(),
                                onValueChange = { s ->
                                    if (s.all { it.isDigit() }) {
                                        price = s.toInt()
                                    }
                                },
                                maxLines = 3,
                                readOnly = false,
                                label = {
                                    Text(
                                        "Price", fontWeight = FontWeight(1000),
                                        fontFamily = FontFamily(Font(Res.font.inter)),
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                },
                                placeholder = {
                                    Text(
                                        "Price", fontWeight = FontWeight(1000),
                                        fontFamily = FontFamily(Font(Res.font.inter)),
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }
                    }

                    item {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth().padding(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            TextButton(
                                onClick = { onDismissRequest() },
                                modifier = Modifier.padding(8.dp),
                            ) {
                                Text("Dismiss")
                            }
                            TextButton(
                                onClick = {
                                    onConfirmation()

                                    if (coverPage.isNotEmpty() && dishName.isNotEmpty() && category.isNotEmpty()) {
                                        val imageName = dishName + System.currentTimeMillis()
                                        viewModel.onEvent(
                                            ManageDishesUiEvent.AddNewFoodItem(
                                                FoodItem(
                                                    image = imageName,
                                                    name = dishName,
                                                    price = price,
                                                    category = category
                                                ),
                                                coverPage
                                            )
                                        )
                                    } else {
                                        rememberAlert = true
                                    }
                                },
                                modifier = Modifier.padding(8.dp),
                            ) {
                                Text("Confirm")
                            }
                        }
                    }

                }
            }

        }

    }


    @Composable
    fun AlertDialogPopUP(
        onDismissRequest: () -> Unit,
        onConfirmation: () -> Unit,
        dialogTitle: String,
        dialogText: String,
    ) {
        AlertDialog(
            icon = {
                Icon(Icons.Outlined.Info, contentDescription = "Example Icon")
            },
            title = {
                Text(text = dialogTitle)
            },
            text = {
                Text(text = dialogText)
            },
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun SelectCategoryImage(onDismissRequest: () -> Unit): String {
        var rememberSelectedImage by remember { mutableStateOf("icons8-bagel-48.png") }
        val directoryPath = "src/main/resources/icons/categoryIcons"
        val directory = File(directoryPath)
        val listImages = remember { mutableSetOf(rememberSelectedImage) }

        if (directory.exists() && directory.isDirectory) {
            val file = directory.listFiles()
            file?.forEach {
                listImages.add(it.name)
            }
        }

        Dialog(onDismissRequest = onDismissRequest) {
            Card {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(50.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(20.dp)
                ) {
                    items(listImages.toList()) {
                        Card(
                            shape = RoundedCornerShape(100),
                            border = BorderStroke(2.dp, color = Color.Black),
                            onClick = { rememberSelectedImage = it}) {
                            Image(
                                painterResource("icons/categoryIcons/$it"),
                                "",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }


        return rememberSelectedImage
    }


}


