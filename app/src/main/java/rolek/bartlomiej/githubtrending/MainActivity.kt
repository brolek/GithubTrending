package rolek.bartlomiej.githubtrending

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import rolek.bartlomiej.githubtrending.api.util.ApiResult
import rolek.bartlomiej.githubtrending.model.RepoItem
import rolek.bartlomiej.githubtrending.ui.theme.GitHubTrendingTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val viewModel = viewModel<MainViewModel>()
            val result by viewModel.repos.collectAsState()

            GitHubTrendingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) { ->

                    Box(modifier = Modifier.fillMaxSize().padding(start = 4.dp, end = 4.dp),
                        contentAlignment = Alignment.Center) {
                        when(result){
                            is ApiResult.Loading->{
                                CircularProgressIndicator(modifier = Modifier.size(50.dp), color = Color.Blue)
                            }
                            is ApiResult.Error->{
                                Toast.makeText(this@MainActivity,result.error , Toast.LENGTH_SHORT).show()
                            }
                            is ApiResult.Success->{
                                val quotelist=result.data ?: emptyList()
                                LazyColumn(modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.spacedBy(10.dp)){
                                    items(count = quotelist.size){quote->
                                        QuoteItem(quote = quotelist[quote])
                                    }
                                }
//                    )
                            }

                            }
                        }
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                }
            }
        }
    }
}

@Composable
fun QuoteItem(quote: RepoItem) {
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement =Arrangement.spacedBy(4.dp) ) {
        Text(text = quote.name)
//        Text(text = quote.author, fontSize = 20.sp, fontWeight = FontWeight.Bold)
//        Text(text = quote.quote, fontSize = 18.sp)
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GitHubTrendingTheme {
        Greeting("Android")
    }
}