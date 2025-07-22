package com.example.bondhu.view

import BondhuViewModel
import android.R.attr.fontWeight
import android.R.attr.text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bondhucou.R
import com.example.bondhucou.data.BondhuModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    bondhuViewModel: BondhuViewModel,
    bondhu: BondhuModel
) {

    var isLoading = bondhuViewModel.isLoading.value

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
                                bondhuViewModel.isOpened.value = !bondhuViewModel.isOpened.value
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
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(R.drawable.bondhu_logo),
                    contentDescription = "Bondhu logo",
                    modifier = Modifier
                        .size(220.dp)
                        .padding(20.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
                Card(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column {
                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = "Name:${bondhuViewModel.name.value}",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = "Department:${bondhuViewModel.department.value}",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = "Session:${bondhuViewModel.session.value}",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = "Blood group:${bondhuViewModel.bloodGroup.value}",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = "Contact number:${bondhuViewModel.contactNumber.value}",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold
                            )
                        }

                    }
                }
                Card(
                    modifier = Modifier
                        .padding(start = 25.dp, end = 25.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Column() {
                        Text(
                            modifier = Modifier.padding(20.dp),
                            text = "Blood donation history",
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily.Serif,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )



                        Text(
                            modifier = Modifier.padding(20.dp),
                            text = if (bondhuViewModel.isDonated.value) {
                                "Last donated: ${bondhuViewModel.donateDate.value}"
                            } else {
                                "Donate blood to save lives "
                            },
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily.Serif,
                            fontSize = 15.sp
                        )
                    }

                }

                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Have you donated blood in this month?",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = bondhuViewModel.isChecked.value,
                            onCheckedChange = {
                                bondhuViewModel.isChecked.value = it
                                if (!it) bondhuViewModel.resetDonateDate()
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.Red,
                                uncheckedColor = Color.Gray
                            )
                        )

                        Text(
                            text = "Yes",
                            color = Color.Red
                        )

                    }

                    Button(
                        onClick = {
                            bondhuViewModel.updateDonateDate()
                            bondhuViewModel.isChecked.value = false
                        },
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Update")
                    }
                }

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Button(
                    onClick = {
                        bondhuViewModel.resetDonateDate()
                    },
                    modifier = Modifier.clip(
                        RoundedCornerShape(20.dp)
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Reset")
                }

                Spacer(
                    modifier = Modifier.height(30.dp)
                )

                Button(
                    onClick = {
                        bondhuViewModel.logout()

                        navController.navigate("InformationScreen") {
                            popUpTo("MainScreen") { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = Color.Red,
                    ),
                    shape = RectangleShape
                ) {
                    Text("Logout")
                }


            }


        }
    }
}