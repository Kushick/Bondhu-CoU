package com.example.bondhucou

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.bondhucou.data.BondhuModel
import com.example.bondhucou.navigation.AppNavigation
import com.example.bondhucou.ui.theme.BondhuCoUTheme
import com.example.bondhucou.view.InformationScreen
import com.example.bondhucou.viewModel.DonorViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BondhuCoUTheme {
                val bondhuViewModel = ViewModelProvider(this)[BondhuViewModel::class.java]
                val donorViewModel = ViewModelProvider(this)[DonorViewModel::class.java]
                val navController = rememberNavController()
                val bondhu by bondhuViewModel.bondhu.collectAsState()

                AppNavigation(
                    bondhuViewModel, bondhu ?: BondhuModel(
                        "",
                        "", "", "", "", "",
                    ),
                    donorViewModel
                )
            }


        }
    }

}
