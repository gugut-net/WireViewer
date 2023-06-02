package com.gugut.base_character_sdk.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RelatedTopic(
    @SerializedName("FirstURL")
    val firstURL: String,
    @SerializedName("Icon")
    val icon: Icon,
    @SerializedName("Result")
    val result: String,
    @SerializedName("Text")
    val text: String
)