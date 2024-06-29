package rolek.bartlomiej.githubtrending.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import rolek.bartlomiej.githubtrending.R

@Composable
fun RequestError(
    modifier: Modifier = Modifier,
    onRetryButtonClick: () -> Unit,
    error: String? = null,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.error_message),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
        )

        if (error != null) {
            Text(
                text = error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
                textAlign = TextAlign.Center,
            )
        }

        Button(onClick = { onRetryButtonClick() }) {
            Text(
                text = stringResource(R.string.retry).uppercase()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RequestErrorPreview() {
    RequestError(modifier = Modifier, onRetryButtonClick = {})
}