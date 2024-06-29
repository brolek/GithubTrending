package rolek.bartlomiej.githubtrending.ui.feature.projects.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import rolek.bartlomiej.githubtrending.model.ProjectItem

@Composable
fun ProjectsList(
    modifier: Modifier = Modifier,
    projects: List<ProjectItem>,
    onItemClick: (ProjectItem) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        items(projects.size) { project ->
            ProjectListItem(project = projects[project], onItemClick = onItemClick)
            HorizontalDivider(modifier = Modifier.padding(start = 24.dp, end = 24.dp))
        }
    }

}