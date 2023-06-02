package com.gugut.base_character_sdk.data.repository_impl

import com.gugut.base_character_sdk.data.remote.CharactersApi
import com.gugut.base_character_sdk.domain.repository.CharactersRepository
import com.gugut.base_character_sdk.utils.CharactersType
import com.gugut.base_character_sdk.data.remote.dto.CharactersDto
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val api: CharactersApi
): CharactersRepository {

    override suspend fun getCharacters(type: CharactersType): CharactersDto {
        return api.getCharacters(type.type)
    }
}