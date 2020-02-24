package com.example.github_client.ui.project

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.github_client.domain.model.Project
import com.example.github_client.domain.repository.ProjectRepository
import kotlinx.coroutines.launch

class ProjectViewModel (private val myApplication: Application,
                        private val mProjectID: String): AndroidViewModel(myApplication) {

    private val repository = ProjectRepository.instance
    val projectLiveData: MutableLiveData<Project> = MutableLiveData()

    val project = MutableLiveData<Project>()

    init {
        loadProject()
    }

    private fun loadProject() {

        viewModelScope.launch {

            try {
                val project = repository.getProject(myApplication.getString(R.string.github_user_name), mProjectID)
                if (project.isSuccessful) {
                    projectLiveData.postValue(project.body())
                }
            } catch (e: Exception) {
                Log.e("loadProject:Failed", e.stackTrace.toString())
            }
        }
    }

    fun setProject(project: Project) {
        this.project.postValue(project)
    }

    class Factory(private val application: Application, private val projectID: String) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return ProjectViewModel(application, projectID) as T
        }
    }
}