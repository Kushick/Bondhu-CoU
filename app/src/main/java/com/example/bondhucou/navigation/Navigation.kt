package com.example.bondhucou.navigation

import BondhuViewModel
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bondhu.view.MainScreen
import com.example.bondhucou.data.BondhuModel
import com.example.bondhucou.view.DonorScreen
import com.example.bondhucou.view.InformationScreen
import com.example.bondhucou.view.MenuScreen
import com.example.bondhucou.viewModel.DonorViewModel

@Composable
fun AppNavigation(
    bondhuViewModel: BondhuViewModel,
    bondhu: BondhuModel,
    donorViewModel: DonorViewModel
) {
    val navController = rememberNavController()
    val isLoggedInState = bondhuViewModel.isLoggedIn.collectAsState(initial = null)
    val isLoggedInNonNull = isLoggedInState.value ?: false

    NavHost(
        navController = navController,
        startDestination = if (isLoggedInNonNull) "MainScreen" else "InformationScreen"

    ) {

        composable("InformationScreen") {
            InformationScreen(navController, bondhuViewModel, bondhu)
        }
        composable("MainScreen") {
            MainScreen(navController, bondhuViewModel, bondhu)
        }
        composable("MenuScreen") {
            MenuScreen(navController, bondhuViewModel)
        }
        composable("DonorScreen") {
            DonorScreen(navController, donorViewModel, bondhu,bondhuViewModel)
        }
    }
}

