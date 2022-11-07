package app.unicornapp.mobile.android.unicorn.data

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * MenuItem for NavigtationDrawer
*/
data class MenuItem(
    val id: String,
    val title: String,
    val contentDescription: String,
    val icon: ImageVector
)
