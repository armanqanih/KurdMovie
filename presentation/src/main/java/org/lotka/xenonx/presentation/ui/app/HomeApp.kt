package org.lotka.xenonx.presentation.ui.app




import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.ui.navigation.ScreensNavigation


import org.lotka.xenonx.presentation.ui.screen.home.HomeViewModel
import org.lotka.xenonx.presentation.ui.screen.home.component.HomeBottomBar
import org.lotka.xenonx.presentation.ui.screen.popularMovie.PopularMovieListScreen


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")

@Composable
fun HomeApp(
    activity: HomeActivity,
    navController: NavHostController,
    onNavigateToRecipeDetailScreen: (String) -> Unit,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    keyboardController: SoftwareKeyboardController,

    ) {


    val scaffoldState = rememberScaffoldState()
    val viewModel = hiltViewModel<HomeViewModel>()
    val state = viewModel.state.collectAsState().value
    val bottomNavController = rememberNavController()



    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = if (state.isCurrentPopularScreen) stringResource(R.string.popular_movie)
                else stringResource(R.string.upcoming_movie),
                    fontSize = 20.sp,
                    modifier = Modifier.shadow(2.dp),
                    color = MaterialTheme.colorScheme.inverseOnSurface)
            })
        },
        bottomBar = {
            HomeBottomBar(navController = navController
                ,onEvent = viewModel::onEvent)
        },

        content = { _ ->
            NavHost(navController = navController,
                startDestination = ScreensNavigation.PopularMovieListScreen.route,
                enterTransition = {
                    // you can change whatever you want transition
                    EnterTransition.None
                },
                exitTransition = {
                    // you can change whatever you want transition
                    ExitTransition.None
                }) {
                composable(
                    route = ScreensNavigation.PopularMovieListScreen.route,
                ) {
                    PopularMovieListScreen(
                        navController = bottomNavController,
                        onEvent = viewModel::onEvent,
                        state = state
                    )

                }
                composable(
                    ScreensNavigation.DetailsScreen.route + "/{movieId}",
                    arguments = listOf(
                        navArgument("movieId") { type = NavType.IntType }
                    )
                ) { backStackEntry ->
//                    DetailsScreen(backStackEntry)
                }

            }

        },
    )
    }




