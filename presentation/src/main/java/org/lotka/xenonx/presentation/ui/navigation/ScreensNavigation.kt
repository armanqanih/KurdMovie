package org.lotka.xenonx.presentation.ui.navigation

sealed class ScreensNavigation(val route: String) {

    object HomeScreen : ScreensNavigation(route = "home_screen")
    object PopularMovieListScreen : ScreensNavigation(route = "HomeScreen")
    object UpcomingMovieListScreen : ScreensNavigation(route = "HomeScreen")
    object DetailsScreen : ScreensNavigation(route = "details_screen")




}