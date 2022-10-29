package app.unicornapp.mobile.android.unicorn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.unicornapp.mobile.android.unicorn.ui.theme.UnicornTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnicornTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(titles: List<String> = listOf("Unicorn", "App")) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column {
            Greeting("Unicorn")
            Greeting("App")
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UnicornTheme {
        MyApp()
    }
}
