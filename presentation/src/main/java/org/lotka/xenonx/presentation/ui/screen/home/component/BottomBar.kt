package org.lotka.xenonx.presentation.ui.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.ui.navigation.ScreensNavigation
import org.lotka.xenonx.presentation.ui.screen.home.MovieListUiEvent




@Composable
fun HomeBottomBar(
    navController: NavController,
    onEvent: (MovieListUiEvent) -> Unit
) {
    val items = listOf(
        BottomNavigationItem(
            icon = Icons.Rounded.Home,
            text = stringResource(R.string.popular)),

        BottomNavigationItem(
            icon = Icons.Rounded.Home,
            text = stringResource(R.string.upcoming)))

    val selected = rememberSaveable {
        mutableIntStateOf(0) }

    NavigationBar {
        Row (
            modifier = Modifier.background(color =  MaterialTheme.colorScheme.inverseOnSurface)
        ){
      items.forEachIndexed { index, bottomNavigationItem ->
          NavigationBarItem(
              selected = selected.intValue == index,
              onClick = {
                  selected.intValue = index
                   when(selected.intValue){
                       0 -> {
                           onEvent(MovieListUiEvent.Navigate)
                           navController.popBackStack()
                           navController.navigate(ScreensNavigation.PopularMovieListScreen.route)

                       }

                       1 ->{
                           onEvent(MovieListUiEvent.Navigate)
                           navController.popBackStack()
                           navController.navigate(ScreensNavigation.UpcomingMovieListScreen.route)
                       }
                   }
              },
              icon = {
                  Icon(
                      imageVector = bottomNavigationItem.icon,
                      contentDescription = bottomNavigationItem.text,
                      tint = MaterialTheme.colorScheme.onBackground
                  )
              },
              label = {
                  Text(text = bottomNavigationItem.text)
              })
      } }
    }

}

data class BottomNavigationItem(
    val icon: ImageVector,
    val text: String
)