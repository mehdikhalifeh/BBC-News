package com.mehdi.bbcnews.component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.mehdi.bbcnews.component.navigation.SetUpNavigation
import com.mehdi.bbcnews.component.ui.theme.BBCNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val newsListViewModel: NewsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BBCNewsTheme {
                navController = rememberAnimatedNavController()
                SetUpNavigation(
                    navController = navController,
                    newsListViewModel = newsListViewModel
                )
            }
        }
    }
}