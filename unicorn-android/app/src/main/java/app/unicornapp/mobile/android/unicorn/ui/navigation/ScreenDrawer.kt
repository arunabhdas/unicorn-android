package app.unicornapp.mobile.android.unicorn.ui.navigation

sealed class ScreenDrawer {
    object HomeScreen: Screen(route = "home_screen")
    object ContactScreen: Screen(route = "contact_screen")
    object NotificationScreen: Screen(route = "notification_screen")
    object HomeDetailScreen: Screen(route = "home_detail_screen")
}

