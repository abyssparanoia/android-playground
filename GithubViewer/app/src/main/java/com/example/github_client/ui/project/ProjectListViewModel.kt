package com.example.github_client.ui.project

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.example.github_client.R
import com.example.github_client.domain.model.Project
import com.example.github_client.domain.repository.ProjectRepository
import kotlinx.coroutines.launch
import java.lang.Exception


class ProjectListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProjectRepository.instance
    var projectListLiveData: MutableLiveData<List<Project>> = MutableLiveData()

    init {
        loadProjectList()
    }

    private fun loadProjectList() {

        viewModelScope.launch {
            try {
                val request = repository.listProject(getApplication<Application>().getString(R.string.github_user_name))
                if (request.isSuccessful) {
                    projectListLiveData.postValue(request.body())
                }
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }
}