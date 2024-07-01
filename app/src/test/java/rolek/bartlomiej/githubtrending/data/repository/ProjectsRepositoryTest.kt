package rolek.bartlomiej.githubtrending.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import rolek.bartlomiej.githubtrending.data.api.ApiService

@ExperimentalCoroutinesApi
class ProjectsRepositoryImplTest {

    private lateinit var repository: ProjectsRepositoryImpl
    private val apiService: ApiService = mock()
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = ProjectsRepositoryImpl(apiService, testDispatcher)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


}