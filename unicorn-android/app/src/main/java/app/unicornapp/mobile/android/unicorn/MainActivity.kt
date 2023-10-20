package app.unicornapp.mobile.android.unicorn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.unicornapp.mobile.android.unicorn.ui.screens.LandingScreen
import app.unicornapp.mobile.android.unicorn.ui.screens.MainScreen
import app.unicornapp.mobile.android.unicorn.ui.theme.UnicornTheme
import app.unicornapp.mobile.android.unicorn.viewmodel.UnicornViewModel

class MainActivity : ComponentActivity() {
    lateinit var navController: NavController
    private val viewModel: UnicornViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        setContent {
            UnicornTheme {
                navController = rememberNavController()
                // MainScreen(navController)
                LandingScreen(navController = navController)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}



@Composable
fun WelcomeScreen(titles: List<String> = listOf("Unicorn", "App")) {

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary
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
    Surface(color = MaterialTheme.colors.primary) {
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


