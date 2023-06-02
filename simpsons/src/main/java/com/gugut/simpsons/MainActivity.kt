package com.gugut.simpsons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gugut.base_character_sdk.CharacterDskImpl
import com.gugut.base_character_sdk.utils.CharactersType
import com.gugut.simpsons.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        CharacterDskImpl.init(applicationContext, CharactersType.SIMPSONS)
    }
}