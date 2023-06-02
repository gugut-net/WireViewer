package com.gugut.base_character_sdk

import android.content.Context
import com.gugut.base_character_sdk.utils.CharactersType

interface ICharacterDsk {
    fun init(context: Context, ApplicationType: CharactersType)
}