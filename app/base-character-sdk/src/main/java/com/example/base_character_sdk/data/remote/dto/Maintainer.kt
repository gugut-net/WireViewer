package com.example.base_character_sdk.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Maintainer(
    @SerializedName("github")
    val github: String
)