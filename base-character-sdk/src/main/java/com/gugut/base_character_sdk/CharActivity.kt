package com.gugut.base_character_sdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.gugut.base_character_sdk.presenter.CharactersViewModel
import com.gugut.base_character_sdk.utils.CharactersType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharActivity : AppCompatActivity() {

    private val charsViewModel by lazy {
        ViewModelProvider(this)[CharactersViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_char)

        charsViewModel.charactersType = intent.getSerializableExtra("APP_TYPE") as? CharactersType
        supportFragmentManager.findFragmentById(R.id.frag_container) as NavHostFragment
    }
}
