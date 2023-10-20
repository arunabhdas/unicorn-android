package app.unicornapp.mobile.android.unicorn.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.unicornapp.mobile.android.unicorn.ui.navigation.CustomAppBar
import app.unicornapp.mobile.android.unicorn.ui.navigation.DrawerBody
import app.unicornapp.mobile.android.unicorn.ui.navigation.MenuItem
import app.unicornapp.mobile.android.unicorn.ui.navigation.SetupNavGraph
import app.unicornapp.mobile.android.unicorn.ui.theme.UnicornTheme
import kotlinx.coroutines.launch


@Composable
fun MainScreen(
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "home",
                        title = "Home",
                        route = "home_screen",
                        contentDescription = "Navigate to Home",
                        icon = Icons.Default.Home
                    ),
                    MenuItem(
                        id = "contact",
                        title = "Contact",
                        route = "contact_screen",
                        contentDescription = "Navigate to Contact",
                        icon = Icons.Default.Email
                    ),
                    MenuItem(
                        id = "notifications",
                        title = "Notifications",
                        route = "notification_screen",
                        contentDescription = "Navigate to Notifications",
                        icon = Icons.Default.Notifications
                    )
                ),
                onItemClick = {menuItem ->
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    println("Clicked on ${menuItem.title}")
                    navController.navigate(route = menuItem.route)
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {

            // TODO-FIXME-CLEANUP-SHOULD-THIS-BE-CALLED-EARLIER SetupNavGraph(navController = navController as NavHostController)
            SetupNavGraph(navController = navController as NavHostController)
            CustomAppBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    UnicornTheme {
        MainScreen(
            navController = rememberNavController()
        )
    }
}