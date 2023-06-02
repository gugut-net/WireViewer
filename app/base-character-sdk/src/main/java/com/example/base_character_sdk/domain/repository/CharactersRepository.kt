package com.example.base_character_sdk.domain.repository

import com.example.base_character_sdk.data.remote.dto.CharactersDto
import com.example.base_character_sdk.utils.CharactersType

interface CharactersRepository {
    suspend fun getCharacters(type: CharactersType): CharactersDto
}