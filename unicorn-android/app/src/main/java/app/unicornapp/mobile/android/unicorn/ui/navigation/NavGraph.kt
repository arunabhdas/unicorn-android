package app.unicornapp.mobile.android.unicorn.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.unicornapp.mobile.android.unicorn.ui.screens.ContactScreen
import app.unicornapp.mobile.android.unicorn.ui.screens.HomeScreen
import app.unicornapp.mobile.android.unicorn.ui.screens.HomeDetailScreen
import app.unicornapp.mobile.android.unicorn.ui.screens.LandingScreen
import app.unicornapp.mobile.android.unicorn.ui.screens.LoginScreen
import app.unicornapp.mobile.android.unicorn.ui.screens.MainScreen
import app.unicornapp.mobile.android.unicorn.ui.screens.NotificationScreen


@Composable
fun SetupDrawerNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ScreenDrawer.HomeScreen.route
        // TODO-FIXME-CLEANUP startDestination = Screen.MainScreen.route
    ) {

        composable(
            route = ScreenDrawer.HomeScreen.route
        ) {
            HomeScreen(
                navController = navController
            )
        }

        composable(
            route = ScreenDrawer.ContactScreen.route
        ) {
            ContactScreen(
                navController = navController
            )
        }

        composable(
            route = ScreenDrawer.NotificationScreen.route
        ) {
            NotificationScreen(
                navController = navController
            )
        }

    }
}

@Composable
fun SetupRootNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LandingScreen.route
        // TODO-FIXME-CLEANUP startDestination = Screen.MainScreen.route
    ) {

        composable(
            route = Screen.LandingScreen.route
        ) {
            LandingScreen(
                navController = navController
            )
        }

        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(
                navController = navController
            )
        }

        composable(
            route = Screen.MainScreen.route
        ) {
            MainScreen(
                navController = navController
            )
        }
    }
}