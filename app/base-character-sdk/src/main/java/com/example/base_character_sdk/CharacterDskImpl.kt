package com.example.base_character_sdk

import android.content.Context
import android.content.Intent
import com.example.base_character_sdk.utils.CharactersType

object CharacterDskImpl : ICharacterDsk {

    private var initialized: Boolean = false

    override fun init(context: Context, applicationType: CharactersType) {
        // safe check so that  activity will only get initialized once.
        if (!initialized) {
            initialized = true
            Intent(context, CharActivity::class.java).apply {
                //Remove before Activity
                putExtra("APP_TYPE", applicationType)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(this)
            }
        }
    }
}