package com.gugut.base_character_sdk.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gugut.base_character_sdk.domain.model.CharacterModel
import com.gugut.base_character_sdk.domain.usecase.GetCharactersUseCase
import com.gugut.base_character_sdk.utils.CharactersType
import com.gugut.base_character_sdk.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCoinsUseCaseUseCase: GetCharactersUseCase

) : ViewModel() {

    var prevData: List<CharacterModel>? = null

    var charactersType: CharactersType? = null

    private val _selectedChar: MutableLiveData<CharacterModel?> = MutableLiveData(null)
    val selectedChar: LiveData<CharacterModel?> get() = _selectedChar

    private val _chars: MutableLiveData<UIState<List<CharacterModel>>> =
        MutableLiveData(UIState.Loading)
    val chars: LiveData<UIState<List<CharacterModel>>> get() = _chars

    private val _search: MutableLiveData<List<CharacterModel>?> = MutableLiveData(null)
    val search: LiveData<List<CharacterModel>?> get() = _search


    fun setSelectedChar(item: CharacterModel) {
        _selectedChar.value = item
    }

     fun getCharacters(charactersType: CharactersType) {
         getCoinsUseCaseUseCase(charactersType).onEach { resource ->
            when(resource){
                is UIState.Success -> {
                    _chars.postValue(resource)
                }
                is UIState.Loading -> {

                }
                is UIState.Error -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchItem(string: String) {
        if (string.isNotBlank()) {
            prevData?.let {
                _search.value = it.filter { characterModel ->
                    characterModel.name.contains(string, ignoreCase = true) ||
                            characterModel.description.contains(string, ignoreCase = true)
                }
            }
        } else {
            _search.value = prevData
        }
    }
}