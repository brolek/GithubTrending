package rolek.bartlomiej.githubtrending.ui.feature.projects.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import rolek.bartlomiej.githubtrending.model.ProjectItem
import rolek.bartlomiej.githubtrending.ui.common.Progress
import rolek.bartlomiej.githubtrending.ui.common.RequestError

@Composable
fun ProjectsList(
    modifier: Modifier = Modifier,
    projects: LazyPagingItems<ProjectItem>,
    onRetry: () -> Unit,
    onItemClick: (ProjectItem) -> Unit
) {
    when (projects.loadState.refresh) {

        LoadState.Loading -> {
            Progress()
        }

        is LoadState.Error -> {
            RequestError(onRetryButtonClick = { onRetry.invoke() })
        }

        else -> {
            LazyColumn(modifier = modifier) {
                items(projects.itemCount) { index ->
                    projects[index]?.let { project ->
                        ProjectListItem(project = project, onItemClick = onItemClick)
                        HorizontalDivider(modifier = Modifier.padding(start = 24.dp, end = 24.dp))
                    }
                }
                if (projects.loadState.append is LoadState.Loading) {
                    item {
                        Box(
                            modifier = Modifier
                                .padding(vertical = 16.dp)
                                .fillParentMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                if (projects.loadState.append.endOfPaginationReached) {
                    item {
                        HorizontalDivider(modifier = Modifier.padding(start = 24.dp, end = 24.dp))
                    }
                }
            }
        }
    }
}