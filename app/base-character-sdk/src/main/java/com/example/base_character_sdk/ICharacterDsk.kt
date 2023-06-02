package com.example.base_character_sdk

import android.content.Context
import com.example.base_character_sdk.utils.CharactersType

interface ICharacterDsk {
    fun init(context: Context, ApplicationType: CharactersType)
}