package com.example.bondhucou.view

import BondhuViewModel
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bondhucou.R
import com.example.bondhucou.data.BondhuModel
import com.example.bondhucou.firebase.FirebaseRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformationScreen(
    navController: NavController,
    bondhuViewModel: BondhuViewModel,
    bondhu: BondhuModel?
) {

    val context = LocalContext.current
    var isLoading by bondhuViewModel.isLoading

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Red,
                        titleContentColor = Color.White
                    ),
                    title = {
                        Text(
                            text = "বন্ধু",
                            fontSize = 40.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.navigate("MenuScreen")
                            }
                        ) {
                            Icon(
                                Icons.Filled.List, "menu",
                                tint = Color.White,
                                modifier = Modifier.size(45.dp)
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                navController.navigate("DonorScreen")

                            }
                        ) {
                            Icon(
                                Icons.Filled.Search, "search for donor",
                                tint = Color.White,
                                modifier = Modifier.size(45.dp)
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Item(bondhuViewModel, bondhu)
                Button(
                    onClick = {
                        if (bondhuViewModel.name.value.isNotEmpty()
                            && bondhuViewModel.department.value.isNotEmpty()
                            && bondhuViewModel.session.value.isNotEmpty()
                            && bondhuViewModel.bloodGroup.value.isNotEmpty()
                            && bondhuViewModel.contactNumber.value.isNotEmpty()
                        ) {
                            bondhuViewModel.updateBondhuModel()
                            bondhuViewModel.setLoggedIn()
                            bondhuViewModel.saveUserInfo()
                            FirebaseRepository.addUser(
                                BondhuModel(
                                    name = bondhuViewModel.name.value,
                                    department = bondhuViewModel.department.value,
                                    session = bondhuViewModel.session.value,
                                    bloodGroup = bondhuViewModel.bloodGroup.value,
                                    contactNumber = bondhuViewModel.contactNumber.value
                                )
                            )
                            navController.navigate("MainScreen") {
                                popUpTo("InformationScreen") {
                                    inclusive = true
                                }
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Please fill all the fields",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ),
                    shape = RectangleShape
                ) {
                    Text(text = "Enter")
                }
                Image(
                    painter = painterResource(R.drawable.bondhu_logo),
                    contentDescription = "Bondhu logo",
                    modifier = Modifier
                        .size(220.dp)
                        .padding(20.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
            }
        }

    }
}

@Composable
fun Item(
    bondhuViewModel: BondhuViewModel,
    bondhu: BondhuModel?
) {
    Column {
        Card(
            modifier = Modifier
                .width(500.dp)
                .padding(5.dp)
                .height(95.dp)
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                text = "Enter your name",
                color = Color.Red,
                textAlign = TextAlign.Center
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = bondhuViewModel.name.value,
                onValueChange = { bondhuViewModel.name.value = it },
                colors = TextFieldDefaults.colors(
                    selectionColors = TextSelectionColors(
                        handleColor = Color.White,
                        backgroundColor = Color.Red
                    ),
                    cursorColor = Color.Red,
                    focusedPlaceholderColor = Color.Red,
                    focusedIndicatorColor = Color.Red
                )
            )
        }


        Card(
            modifier = Modifier
                .width(500.dp)
                .padding(5.dp)
                .height(95.dp)
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Enter your department",
                color = Color.Red,
                textAlign = TextAlign.Center
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = bondhuViewModel.department.value,
                onValueChange = { bondhuViewModel.department.value = it },
                colors = TextFieldDefaults.colors(
                    selectionColors = TextSelectionColors(
                        handleColor = Color.White,
                        backgroundColor = Color.Red
                    ),
                    cursorColor = Color.Red,
                    focusedPlaceholderColor = Color.Red,
                    focusedIndicatorColor = Color.Red
                )
            )
        }

        Card(
            modifier = Modifier
                .width(500.dp)
                .padding(5.dp)
                .height(95.dp)
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Enter your session",
                color = Color.Red,
                textAlign = TextAlign.Center
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = bondhuViewModel.session.value,
                onValueChange = { bondhuViewModel.session.value = it },
                colors = TextFieldDefaults.colors(
                    selectionColors = TextSelectionColors(
                        handleColor = Color.White,
                        backgroundColor = Color.Red
                    ),
                    cursorColor = Color.Red,
                    focusedPlaceholderColor = Color.Red,
                    focusedIndicatorColor = Color.Red
                )
            )
        }


        Card(
            modifier = Modifier
                .width(500.dp)
                .padding(5.dp)
                .height(95.dp)
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Enter your bloodGroup",
                color = Color.Red,
                textAlign = TextAlign.Center
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = bondhuViewModel.bloodGroup.value,
                onValueChange = { bondhuViewModel.bloodGroup.value = it },
                colors = TextFieldDefaults.colors(
                    selectionColors = TextSelectionColors(
                        handleColor = Color.White,
                        backgroundColor = Color.Red
                    ),
                    cursorColor = Color.Red,
                    focusedPlaceholderColor = Color.Red,
                    focusedIndicatorColor = Color.Red
                )
            )
        }


        Card(
            modifier = Modifier
                .width(500.dp)
                .padding(5.dp)
                .height(95.dp)
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Enter your contactNumber",
                color = Color.Red,
                textAlign = TextAlign.Center
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = bondhuViewModel.contactNumber.value,
                onValueChange = { bondhuViewModel.contactNumber.value = it },
                colors = TextFieldDefaults.colors(
                    selectionColors = TextSelectionColors(
                        handleColor = Color.White,
                        backgroundColor = Color.Red
                    ),
                    cursorColor = Color.Red,
                    focusedPlaceholderColor = Color.Red,
                    focusedIndicatorColor = Color.Red
                )
            )
        }


    }

}