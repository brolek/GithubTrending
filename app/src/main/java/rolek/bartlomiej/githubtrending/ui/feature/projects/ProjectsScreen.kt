package rolek.bartlomiej.githubtrending.ui.feature.projects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import rolek.bartlomiej.githubtrending.R
import rolek.bartlomiej.githubtrending.model.ProjectItem
import rolek.bartlomiej.githubtrending.ui.base.SIDE_EFFECTS_KEY
import rolek.bartlomiej.githubtrending.ui.common.Progress
import rolek.bartlomiej.githubtrending.ui.common.RequestError

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
        topBar = { TopAppBar(title = { Text(text = stringResource(R.string.header)) }) },
    ) {

        when {
            state.isLoading -> Progress()
            state.isError -> RequestError(onRetryButtonClick = { onEventSent(ProjectsContract.Event.Retry) })
            else -> ProjectsList(projects = state.projectsList) { onEventSent(ProjectsContract.Event.ProjectClicked(it)) }
        }
    }

}

@Composable
fun ProjectsList(
    projects: List<ProjectItem>,
    onProjectClicked: (ProjectItem) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)){
        items(count = projects.size){quote->
            QuoteItem(quote = projects[quote])
        }
    }
}

@Composable
fun QuoteItem(quote: ProjectItem) {
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement =Arrangement.spacedBy(4.dp) ) {
        Text(text = quote.name)
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}