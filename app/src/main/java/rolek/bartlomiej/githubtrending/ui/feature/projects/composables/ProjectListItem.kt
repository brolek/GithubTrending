package rolek.bartlomiej.githubtrending.ui.feature.projects.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import rolek.bartlomiej.githubtrending.model.ProjectItem
import rolek.bartlomiej.githubtrending.ui.common.RoundedAvatar

@Composable
fun ProjectListItem(project: ProjectItem, onItemClick: (ProjectItem) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(project)
            }
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            RoundedAvatar(
                url = project.owner.avatar_url,
                placeholder = Icons.Default.Person,
                modifier = Modifier
                    .size(60.dp)
                    .padding(end = 12.dp)
            )
            Column {
                Text(
                    text = project.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = project.owner.login,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth()
                )

            }
        }
        Text(
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            text = project.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AssistChip(onClick = { }, label = { Text(text = project.language) })
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.Star, contentDescription = null)
            Text(text = project.watchers.toString(), style = MaterialTheme.typography.titleMedium)
        }
    }

}