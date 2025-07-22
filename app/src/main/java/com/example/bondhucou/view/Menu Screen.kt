package com.example.bondhucou.view

import BondhuViewModel
import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bondhucou.R
import com.example.bondhucou.data.BondhuModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController, bondhuViewModel: BondhuViewModel) {
    val context= LocalContext.current
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
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp, start = 30.dp, end = 30.dp)
        ) {
            Card(
                modifier = Modifier
                    .padding(paddingValues)
                    .height(200.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {


//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        IconButton(
//                            onClick = {
//
//                            }
//                        ) {
//                            Icon(
//                                Icons.Filled.Share, "Share",
//                                tint = Color.Red
//                            )
//                        }
//                        Text(
//                            text = "Share",
//                            color = Color.Red,
//                            fontSize = 30.sp,
//                            modifier=Modifier.clickable(
//                                onClick = {
//
//                                }
//
//                            )
//                        )
//                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        IconButton(
                            onClick = {
                                showCustomToast(context,"Designed by ICT department")
                            }
                        ) {
                            Icon(
                                Icons.Filled.Info, "info",
                                tint = Color.Red,
                            )
                        }
                        Text(
                            text = "Info",
                            color = Color.Red,
                            fontSize = 30.sp,
                            modifier=Modifier.clickable(
                                onClick = {
                                    showCustomToast(context,"Designed by ICT department")

                                }

                            )
                        )
                    }
                }
            }
        }
    }
}

fun showCustomToast(context: Context, message: String) {
    val layout = LinearLayout(context).apply {
        orientation = LinearLayout.HORIZONTAL
        setBackgroundResource(android.R.drawable.toast_frame)
        setPadding(24, 16, 24, 16)
    }

    val imageView = ImageView(context).apply {
        setImageResource(R.drawable.ict_association)
        layoutParams = LinearLayout.LayoutParams(60, 60)
    }

    // Add TextView (Message)
    val textView = TextView(context).apply {
        text = message
        setTextColor(android.graphics.Color.RED)
        setPadding(16, 0, 0, 0)
        textSize = 16f
    }
    layout.addView(imageView)
    layout.addView(textView)

    Toast(context).apply {
        duration = Toast.LENGTH_SHORT
        view = layout
        show()
    }
}


