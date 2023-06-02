package com.gugut.base_character_sdk.data.remote.dto

import com.google.gson.annotations.SerializedName

//Data Transfer Object
data class CharactersDto(
    @SerializedName("Abstract")
    val `abstract`: String,
    @SerializedName("AbstractSource")
    val abstractSource: String,
    @SerializedName("AbstractText")
    val abstractText: String,
    @SerializedName("AbstractURL")
    val abstractURL: String,
    @SerializedName("Answer")
    val answer: String,
    @SerializedName("AnswerType")
    val answerType: String,
    @SerializedName("Definition")
    val definition: String,
    @SerializedName("DefinitionSource")
    val definitionSource: String,
    @SerializedName("DefinitionURL")
    val definitionURL: String,
    @SerializedName("Entity")
    val entity: String,
    @SerializedName("Heading")
    val heading: String,
    @SerializedName("Image")
    val image: String,
    @SerializedName("ImageHeight")
    val imageHeight: Int,
    @SerializedName("ImageIsLogo")
    val imageIsLogo: Int,
    @SerializedName("ImageWidth")
    val imageWidth: Int,
    @SerializedName("Infobox")
    val infobox: String,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("Redirect")
    val redirect: String,
    @SerializedName("RelatedTopics")
    val relatedTopics: List<RelatedTopic>,
    @SerializedName("Results")
    val results: List<Any>,
    @SerializedName("Type")
    val type: String
)

//fun CharactersDto.toChars(item: RelatedTopic): CharacterModel {
//    val items = item.text.split('-')
//    return CharacterModel(
//        name = if (items.size >= 2) items[0] else "Invalid name",
//        description = if (items.size >= 2) items[1] else "No description",
//        image = item.icon.uRL.run { "${CharactersApi.IMAGE_BASE_URL}${this}" }
//    )
//}

//fun List<RelatedTopic>?.mapToCharacterModel(): List<CharacterModel> {
//    return this?.map {
//        val items = it.text.split('-')
//        CharacterModel(
//            name = if (items.size >= 2) items[0] else "Invalid name",
//            description = if (items.size >= 2) items[1] else "No description",
//            image = it.icon.uRL.run { "${CharactersApi.IMAGE_BASE_URL}${this}" }
//        )
//    } ?: emptyList()
//}