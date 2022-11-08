package app.unicornapp.mobile.android.unicorn

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.unicornapp.mobile.android.unicorn.data.MenuItem
import app.unicornapp.mobile.android.unicorn.ui.navigation.CustomAppBar
import app.unicornapp.mobile.android.unicorn.ui.navigation.DrawerBody
import app.unicornapp.mobile.android.unicorn.ui.navigation.DrawerHeader
import app.unicornapp.mobile.android.unicorn.ui.navigation.Screen
import app.unicornapp.mobile.android.unicorn.ui.navigation.SetupNavGraph
import app.unicornapp.mobile.android.unicorn.ui.theme.UnicornTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            UnicornTheme {
                navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                Scaffold(
                    scaffoldState = scaffoldState,
                    drawerContent = {
                        // TODO-FIXME-CLEANUP DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                MenuItem(
                                    id = "home",
                                    title = "home_screen",
                                    contentDescription = "Go to Home",
                                    icon = Icons.Default.Home
                                ),
                                MenuItem(
                                    id = "notifications",
                                    title = "notification_screen",
                                    contentDescription = "Go to Notifications",
                                    icon = Icons.Default.Notifications
                                ),
                                MenuItem(
                                    id = "contact",
                                    title = "contact_screen",
                                    contentDescription = "Go to Contact",
                                    icon = Icons.Default.Email
                                )
                            ),
                            onItemClick = {menuItem ->
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                }
                                println("Clicked on ${menuItem.title}")
                                navController.navigate(route = menuItem.title)
                            }
                        )
                    }
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {

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
        }
    }
}

@Composable
fun WelcomeScreen(titles: List<String> = listOf("Unicorn", "App")) {

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column {
            for (title in titles) {
                Greeting(title)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(color = MaterialTheme.colorScheme.primary) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "Welcome to $name!",

                )
            Text(
                text = "Welcome to $name!",
            )
        }
    }

}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    UnicornTheme {
        WelcomeScreen()
    }
}
