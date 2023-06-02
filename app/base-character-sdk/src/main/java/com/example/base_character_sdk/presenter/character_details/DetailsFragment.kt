package com.example.base_character_sdk.presenter.character_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.base_character_sdk.R
import com.example.base_character_sdk.databinding.FragmentDetailsBinding
import com.example.base_character_sdk.presenter.CharactersViewModel

class DetailsFragment : Fragment() {

    private val binding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    private val charsViewModel by lazy {
        ViewModelProvider(requireActivity())[CharactersViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        charsViewModel.selectedChar.observe(viewLifecycleOwner) {

            it?.let {
                when(it.color){
                    1->{
                        binding.root.setBackgroundColor(
                            binding.root.context.getColor(R.color.baby_blue)
                        )
                    }
                    2->{
                        binding.root.setBackgroundColor(
                            binding.root.context.getColor(R.color.light_green)
                        )
                    }
                    3->{
                        binding.root.setBackgroundColor(
                            binding.root.context.getColor(R.color.light_blue)
                        )
                    }
                    4->{
                        binding.root.setBackgroundColor(
                            binding.root.context.getColor(R.color.red_orange)
                        )

                    }
                }
                binding.apply {
                    charTitle.text = it.name
                    charDescription.text = it.description
                    Glide
                        .with(this.root)
                        .load(it.image)
                        .error(R.drawable.error_img_holder)
                        .placeholder(R.drawable.load_img_holder)
                        .into(charImage)
                }
            }
        }

        return binding.root
    }
}
