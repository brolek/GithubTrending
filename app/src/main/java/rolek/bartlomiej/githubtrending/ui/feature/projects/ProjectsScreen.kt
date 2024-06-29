package rolek.bartlomiej.githubtrending.ui.feature.projects

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import rolek.bartlomiej.githubtrending.R
import rolek.bartlomiej.githubtrending.ui.base.SIDE_EFFECTS_KEY
import rolek.bartlomiej.githubtrending.ui.common.Progress
import rolek.bartlomiej.githubtrending.ui.common.RequestError
import rolek.bartlomiej.githubtrending.ui.feature.projects.composables.ProjectsList
import rolek.bartlomiej.githubtrending.ui.feature.projects.composables.ThemeToggleButton
import rolek.bartlomiej.githubtrending.ui.theme.ThemeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectsScreen(
    state: ProjectsContract.State,
    effectFlow: Flow<ProjectsContract.Effect>?,
    onEventSent: (event: ProjectsContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: ProjectsContract.Effect.Navigation) -> Unit
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val snackBarMessage = stringResource(R.string.error_message)

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is ProjectsContract.Effect.DataWasLoaded -> {
                    snackBarHostState.showSnackbar(
                        message = snackBarMessage,
                        duration = SnackbarDuration.Short
                    )
                }

                is ProjectsContract.Effect.Navigation.ToDetails -> {
                    onNavigationRequested(effect)
                }
            }
        }?.collect()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.header)) }, actions = {
                ThemeToggleButton(isDarkTheme = ThemeState.darkModeState.value) {
                    val isDark = ThemeState.darkModeState.value
                    val theme = when (isDark) {
                        true -> AppCompatDelegate.MODE_NIGHT_NO
                        false -> AppCompatDelegate.MODE_NIGHT_YES
                    }

                    AppCompatDelegate.setDefaultNightMode(theme)
                    ThemeState.darkModeState.value = !isDark
                }
            })
        },
    ) { padding ->
        when {
            state.isLoading -> Progress()
            state.isError -> RequestError(onRetryButtonClick = { onEventSent(ProjectsContract.Event.Retry) })
            else -> ProjectsList(
                projects = state.projectsPager.collectAsLazyPagingItems(),
                onRetry = { onEventSent(ProjectsContract.Event.Retry) },
                modifier = Modifier.padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                ),
            ) {
                onEventSent(ProjectsContract.Event.ProjectClicked(it))
            }
        }
    }
}