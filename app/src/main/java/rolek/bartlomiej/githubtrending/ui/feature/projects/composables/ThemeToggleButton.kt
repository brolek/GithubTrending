package rolek.bartlomiej.githubtrending.ui.feature.projects.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NightlightRound
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun ThemeToggleButton(
    isDarkTheme: Boolean,
    onToggle: () -> Unit
) {
    IconButton(onClick = onToggle) {
        if (isDarkTheme) {
            Icon(
                imageVector = Icons.Filled.WbSunny,
                contentDescription = "Switch to Light Theme",
            )
        } else {
            Icon(
                imageVector = Icons.Filled.NightlightRound,
                contentDescription = "Switch to Dark Theme",
            )
        }
    }
}