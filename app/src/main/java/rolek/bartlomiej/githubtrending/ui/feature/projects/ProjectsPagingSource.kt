package rolek.bartlomiej.githubtrending.ui.feature.projects

import androidx.paging.PagingSource
import androidx.paging.PagingState
import rolek.bartlomiej.githubtrending.data.repository.ProjectsRepository
import rolek.bartlomiej.githubtrending.model.ProjectItem
import javax.inject.Inject

class ProjectsPagingSource @Inject constructor(private val repository: ProjectsRepository) : PagingSource<Int, ProjectItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProjectItem> {
         try {
            val page = params.key ?: 1
            repository.getTrendingRepositories(page).onSuccess {



                    return LoadResult.Page(
                        data = it,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (it.isEmpty()) null else page + 1
                    )
                }.onFailure {
                    return LoadResult.Error(it)
                }


        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
        return LoadResult.Error(Exception("Unknown error"))
    }

    override fun getRefreshKey(state: PagingState<Int, ProjectItem>): Int? {
        return null
    }
}