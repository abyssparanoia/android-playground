package com.example.github_client.domain.repository

import com.example.github_client.domain.model.Project

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("/users/{user}/repos")
    suspend fun listProject(@Path("user") user:String): Response<List<Project>>

    @GET("/repos/{user}/{reponame}")
    suspend fun getProject(@Path("user") user:String, @Path("reponame") projectName: String): Response<Project>
}