package com.example.github_client.domain.repository

import com.example.github_client.domain.model.Project

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val HTTPS_API_GITHUB_URL = "https://api.github.com/"

class ProjectRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl(HTTPS_API_GITHUB_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val githubService: GithubService = retrofit.create(GithubService::class.java)

    suspend fun listProject(userId:String): Response<List<Project>> = githubService.listProject(userId)

    suspend fun getProject(userId: String, projectName: String): Response<Project> = githubService.getProject(userId,projectName)

    companion object Factroy {
        val instance: ProjectRepository
            @Synchronized get() {
                return ProjectRepository()
            }
    }

}