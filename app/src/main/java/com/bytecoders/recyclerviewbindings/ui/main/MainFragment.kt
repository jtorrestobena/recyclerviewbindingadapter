package com.bytecoders.recyclerviewbindings.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.bytecoders.recyclerviewbindings.R
import com.bytecoders.recyclerviewbindings.databinding.MainFragmentBinding
import com.bytecoders.recyclerviewbindings.model.SampleModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: MainFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity() as MenuHost

        // Add menu items without using the Fragment Menu APIs
        // Note how we can tie the MenuProvider to the viewLifecycleOwner
        // and an optional Lifecycle.State (here, RESUMED) to indicate when
        // the menu should be visible
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.main_fragment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.settings_open -> {
                        findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        viewModel.load(PreferenceManager.getDefaultSharedPreferences(requireContext()))

        viewModel.itemClicked.observe(viewLifecycleOwner) {
            Snackbar.make(view, "You clicked on item ${it.text}", Snackbar.LENGTH_LONG).show()
        }

        viewModel.itemDeletedEvent.observe(viewLifecycleOwner) { deletedItem ->
            val modelItem: SampleModel = deletedItem.item as SampleModel
            val undoAction = Snackbar.make(view, "Deleted ${modelItem.text}", Snackbar.LENGTH_LONG)
            undoAction.setAction("Undo") { deletedItem.undoDelete() }
            undoAction.show()
        }

        viewModel.itemMovedEvent.observe(viewLifecycleOwner) {
            Snackbar.make(view, "Move item from ${it.first} to ${it.second}", Snackbar.LENGTH_LONG)
                .show()
        }
    }
}
