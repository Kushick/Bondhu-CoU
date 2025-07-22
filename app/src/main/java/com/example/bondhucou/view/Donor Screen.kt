package com.example.bondhucou.view

import BondhuViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bondhucou.data.BondhuModel
import com.example.bondhucou.viewModel.DonorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonorScreen(
    navController: NavController,
    donorViewModel: DonorViewModel,
    bondhu: BondhuModel,
    bondhuViewModel: BondhuViewModel
) {

    val donors by donorViewModel.donors.collectAsState()
    val isLoading by donorViewModel.isLoading.collectAsState()

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
                            bondhuViewModel.isOpened.value = !bondhuViewModel.isOpened.value

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
            modifier = Modifier.padding(paddingValues)
        ) {
            Text(
                text = "Selected Blood Group: ${bondhuViewModel.selectedBloodGroup.value}",
                modifier = Modifier.padding(16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { bondhuViewModel.isOpened.value = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {
                    Text("Select Blood Group")
                }


                Button(
                    onClick = {
                        donorViewModel.search(bondhuViewModel.selectedBloodGroup.value)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ),
                    shape = RectangleShape
                ) {
                    Text("Search for donors")
                }
            }
            BloodGroupDropdownMenu(bondhuViewModel)

            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.Red,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(50.dp)
                )
            } else {
                val uniqueValidDonors = donors
                    .filter { !it.session.isNullOrBlank() && !it.name.isNullOrBlank() && !it.contactNumber.isNullOrBlank() }
                    .distinctBy { it.contactNumber to it.session }

                if (uniqueValidDonors.isNotEmpty()) {
                    LazyColumn {
                        items(uniqueValidDonors) { donor ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .height(100.dp)
                            ) {

                                Text(
                                    text = "${donor.name} -> ${donor.bloodGroup} -> ${donor.contactNumber}",
                                    modifier = Modifier.padding(12.dp)
                                )
                            }
                        }
                    }
                }else{
                    Text(
                        text = "No donors found.",
                        modifier = Modifier.padding(16.dp),
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun BloodGroupDropdownMenu(
    bondhuViewModel: BondhuViewModel
) {
    val bloodGroups = listOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")

    DropdownMenu(
        expanded = bondhuViewModel.isOpened.value,
        onDismissRequest = { bondhuViewModel.isOpened.value = false },
        modifier = Modifier.size(250.dp),
    ) {
        bloodGroups.forEach { group ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = group,
                        color = Color.Red
                    )
                },
                onClick = {
                    bondhuViewModel.selectedBloodGroup.value = group
                    bondhuViewModel.isOpened.value = false
                }
            )
        }
    }
}
