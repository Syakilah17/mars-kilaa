package com.example.mars_kilaa.data.api

import com.example.mars_kilaa.data.model.PhotoModel
import retrofit2.http.GET

interface PhotoApiService {
    @GET("list")
    suspend fun getPhotos(): List<PhotoModel>
}