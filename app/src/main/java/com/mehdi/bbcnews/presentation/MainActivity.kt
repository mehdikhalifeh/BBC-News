package com.mehdi.bbcnews.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mehdi.bbcnews.presentation.navigation.SetUpNavigation
import com.mehdi.bbcnews.presentation.ui.theme.BBCNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val newsListViewModel: NewsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BBCNewsTheme {
                navController = rememberNavController()
                SetUpNavigation(
                    navController = navController,
                    newsListViewModel = newsListViewModel
                )
            }
        }
    }
}
