package com.gugut.base_character_sdk.presenter.characters_list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gugut.base_character_sdk.R
import com.gugut.base_character_sdk.databinding.CharaterItemBinding
import com.gugut.base_character_sdk.domain.model.CharacterModel

class CharacterAdapter(
    private val characters: MutableList<CharacterModel> = mutableListOf(),
    private val itemClick: (CharacterModel) -> Unit
) : RecyclerView.Adapter<CharacterViewHolder>() {

    fun updateChars(newChar: List<CharacterModel>) {
        characters.clear()
        characters.addAll(newChar)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(
            CharaterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(characters[position], itemClick)
}

class CharacterViewHolder(
    private val binding: CharaterItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(characterModel: CharacterModel, clickItem: (CharacterModel) -> Unit) {
        binding.charName.text = characterModel.name

        when(characterModel.color){
            1->{
                binding.root.setCardBackgroundColor(
                    binding.root.context.getColor(R.color.baby_blue)
                )
            }
            2->{
                binding.root.setCardBackgroundColor(
                    binding.root.context.getColor(R.color.light_green)
                )
            }
            3->{
                binding.root.setCardBackgroundColor(
                    binding.root.context.getColor(R.color.light_blue)
                )
            }
            4->{
                binding.root.setCardBackgroundColor(
                    binding.root.context.getColor(R.color.red_orange)
                )

            }
        }

        itemView.setOnClickListener {
            clickItem(characterModel)
        }
    }

}