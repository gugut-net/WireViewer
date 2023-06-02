package com.gugut.base_character_sdk.domain.repository

import com.gugut.base_character_sdk.utils.CharactersType
import com.gugut.base_character_sdk.data.remote.dto.CharactersDto

interface CharactersRepository {
    suspend fun getCharacters(type: CharactersType): CharactersDto
}