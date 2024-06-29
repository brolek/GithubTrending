package rolek.bartlomiej.githubtrending.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation

@Composable
fun RoundedAvatar(
    url: String,
    placeholder: ImageVector,
    modifier: Modifier = Modifier,
    crossfade: Boolean = true,
) {

    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(crossfade)
//            .placeholder(rememberVectorPainter(image = Icons.Default.Person),)
            .transformations(CircleCropTransformation())
            .build(),
        contentDescription = null,
        modifier = modifier
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            Image(
                imageVector = placeholder,
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .drawBehind {
                        drawCircle(
                            color = Color.Gray,
                            radius = this.size.maxDimension
                        )
                    },

                )
        } else {
            SubcomposeAsyncImageContent()
        }
    }
}