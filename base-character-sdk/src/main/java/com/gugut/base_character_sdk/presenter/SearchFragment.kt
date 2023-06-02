package com.gugut.base_character_sdk.presenter

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.SearchView
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.gugut.base_character_sdk.databinding.LayoutControllerBinding
import com.gugut.base_character_sdk.presenter.characters_list.adapters.CharacterAdapter
import com.gugut.base_character_sdk.utils.UIState

class SearchFragment : Fragment() {

    private val binding by lazy {
        LayoutControllerBinding.inflate(layoutInflater)
    }

    private val charsViewModel by lazy {
        ViewModelProvider(requireActivity())[CharactersViewModel::class.java]
    }

    private val charAdapter by lazy {
        CharacterAdapter {
            charsViewModel.setSelectedChar(it)
            binding.slidingPaneLayout.openPane()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val slidingPaneLayout = binding.slidingPaneLayout

        slidingPaneLayout.lockMode = SlidingPaneLayout.LOCK_MODE_LOCKED
        // Connect the SlidingPaneLayout to the system back button.
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            SportsListOnBackPressedCallback(slidingPaneLayout)
        )

        binding.searchFragment.charRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = charAdapter
        }

        binding.searchFragment.searchChar.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    charsViewModel.searchItem(it)
                }
                return false
            }
        })

        charsViewModel.search.observe(viewLifecycleOwner) {
            it?.let {
                charAdapter.updateChars(it)
            }
        }

        charsViewModel.chars.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Loading -> {

                }
                is UIState.Success -> {
                    charsViewModel.prevData = it.data
                    charAdapter.updateChars(it.data)
                    charsViewModel.setSelectedChar(it.data.first())
                }
                is UIState.Error -> {
                    AlertDialog.Builder(requireActivity())
                        .setTitle("Error")
                        .setMessage(it.error.localizedMessage)
                        .setNegativeButton("DISMISS") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
            }
        }

        if (charsViewModel.prevData == null) {
            charsViewModel.charactersType?.let { charsViewModel.getCharacters(it) }
        }

        return binding.root
    }
}

class SportsListOnBackPressedCallback(
    private val slidingPaneLayout: SlidingPaneLayout
) : OnBackPressedCallback(
    // Set the default 'enabled' state to true only if it is slidable (i.e., the panes
    // are overlapping) and open (i.e., the detail pane is visible).
    slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen
), SlidingPaneLayout.PanelSlideListener {

    init {
        slidingPaneLayout.addPanelSlideListener(this)
    }

    override fun handleOnBackPressed() {
        // Return to the list pane when the system back button is pressed.
        slidingPaneLayout.closePane()
    }

    override fun onPanelSlide(panel: View, slideOffset: Float) {}

    override fun onPanelOpened(panel: View) {
        // Intercept the system back button when the detail pane becomes visible.
        isEnabled = true
    }

    override fun onPanelClosed(panel: View) {
        // Disable intercepting the system back button when the user returns to the
        // list pane.
        isEnabled = false
    }
}