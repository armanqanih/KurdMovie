package org.lotka.xenonx.presentation.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.ui.screen.home.component.HomeBottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (
    navController: NavController
){

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
        }

    ) {
        Box(modifier = Modifier.padding(it))   {

        }

    }



}