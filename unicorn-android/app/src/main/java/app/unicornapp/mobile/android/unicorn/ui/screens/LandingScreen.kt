package app.unicornapp.mobile.android.unicorn.ui.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.unicornapp.mobile.android.unicorn.R
import app.unicornapp.mobile.android.unicorn.ui.navigation.Screen
import app.unicornapp.mobile.android.unicorn.ui.navigation.SetupNavGraph
import app.unicornapp.mobile.android.unicorn.ui.theme.PrimaryColor
import app.unicornapp.mobile.android.unicorn.ui.theme.TertiaryColor
import app.unicornapp.mobile.android.unicorn.ui.theme.TransparentColor
import app.unicornapp.mobile.android.unicorn.ui.util.createGradientEffect

@Composable
fun LandingScreen(
    navController: NavController
    // TODO-FIXME navigator: DestinationsNavigator
) {
    SetupNavGraph(navController = navController as NavHostController)
    val gradientColors = listOf(
        PrimaryColor,
        PrimaryColor
    )
    var showExpandedText by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = createGradientEffect(
                    colors = gradientColors,
                    isVertical = true
                )
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 0.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.unicorn_white),
                contentDescription = stringResource(id = R.string.unicorn_logo_content_description),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(150.dp)
            )
            ClickableText(
                text = AnnotatedString(
                    stringResource(id = R.string.unicorn_landing_screen_welcome_message)
                ),
                style = MaterialTheme.typography.h3.copy(
                    color = Color.White,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    showExpandedText = !showExpandedText
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedVisibility(visible = showExpandedText) {
                Text(
                    text = "Unicorn App",
                    color = Color.White,
                    style = MaterialTheme.typography.body1.copy(color = Color.White),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    Log.d("LandingScreen", "navController: $navController, route: ${Screen.LoginScreen.route}")
                    navController.navigate(
                        // TODO-FIXME Screen.MainScreen.route
                        Screen.LoginScreen.route
                    )
                    /* TODO-FIXME
                    navigator.navigate(BottomNavigationMainScreenDestination(
                        name = "TestUser"
                    ))
                    */
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .background(TransparentColor)
            ) {
                Text(text = "GetStarted")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    /* TODO-FIXME
                    navigator.navigate(
                    )
                    */
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .background(TransparentColor)
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

}

@Composable
@Preview
fun LandingScreenPreview() {
    LandingScreen(navController = rememberNavController())
}

/* TODO-FIXME
@Composable
@Preview
fun LandingScreenPreview() {
    LandingScreen(navigator = MockDestinationsNavigator())
}
*/