package com.gugut.base_character_sdk.domain.usecase

import com.gugut.base_character_sdk.data.remote.CharactersApi
import com.gugut.base_character_sdk.data.remote.dto.RelatedTopic
import com.gugut.base_character_sdk.domain.model.CharacterModel
import com.gugut.base_character_sdk.domain.repository.CharactersRepository
import com.gugut.base_character_sdk.utils.CharactersType
import com.gugut.base_character_sdk.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository,
    private val dispatcher: CoroutineDispatcher

) {
    operator fun invoke(charactersType: CharactersType): Flow<UIState<List<CharacterModel>>> = flow {
        try {
            emit(UIState.Loading)

            val charList: List<CharacterModel> =
                repository.getCharacters(charactersType).relatedTopics.mapToCharacterModel()

            emit(UIState.Success(charList))

        } catch (e: HttpException) {
            // unexpected error
            emit(UIState.Error(e))
        } catch (e: IOException) {
            //"Could not reach server, check internet"
            emit(UIState.Error(e))
        } catch (e: Exception) {
            emit(UIState.Error(e))
        }
    }.flowOn(dispatcher)

}

fun List<RelatedTopic>?.mapToCharacterModel(): List<CharacterModel> {
    var counter = 0
    return this?.map {
        val items = it.text.split('-')
        if (counter < 4) {
            counter++
        } else {
            counter = 1
        }
        CharacterModel(
            color = counter,
            name = if (items.size >= 2) items[0] else "Invalid name",
            description = if (items.size >= 2) items[1] else "No description",
            image = it.icon.uRL.run {
                "${CharactersApi.IMAGE_BASE_URL}${this}"
            }
        )

    } ?: emptyList()
}