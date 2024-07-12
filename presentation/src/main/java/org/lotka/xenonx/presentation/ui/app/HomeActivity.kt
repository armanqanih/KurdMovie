package org.lotka.xenonx.presentation.ui.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import org.lotka.xenonx.presentation.theme.MoviesAppTheme
import org.lotka.xenonx.presentation.ui.navigation.ScreensNavigation



@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            setContent {
                MoviesAppTheme {
                    SetBarColor(color = MaterialTheme.colorScheme.inverseOnSurface)

                    val navController = rememberNavController()
                    val keyboardController = LocalSoftwareKeyboardController.current
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        if (keyboardController != null) {
                            HomeApp(
                                activity = this@HomeActivity,
                                navController = navController,
                                onNavigateToRecipeDetailScreen = {
                                    navController.navigate(
                                        ScreensNavigation.DetailsScreen.route
                                    )
                                },
                                isDarkTheme = false,
                                onToggleTheme = { },
                                keyboardController = keyboardController,

                                )
                        }
                    }
                }
            }


        }


    }}
@Composable
private fun SetBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(key1 = color) {
        systemUiController.setSystemBarsColor(color)
    }
}
