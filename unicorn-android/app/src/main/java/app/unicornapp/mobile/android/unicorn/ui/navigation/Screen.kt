package app.unicornapp.mobile.android.unicorn.ui.navigation

/**
 * Created by Das on 2022-11-05.
 */
sealed class Screen(val route: String) {
    object LandingScreen: Screen(route = "landing_screen")
    object LoginScreen: Screen(route = "login_screen")
    object MainScreen: Screen(route = "main_screen")

}
