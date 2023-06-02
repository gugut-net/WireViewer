package com.example.base_character_sdk.domain.model

data class CharacterModel(
    val name: String,
    val description: String,
    val image: String? = null,
    val color: Int
)

