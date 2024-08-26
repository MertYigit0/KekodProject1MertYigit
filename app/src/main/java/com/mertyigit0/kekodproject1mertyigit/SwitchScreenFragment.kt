package com.mertyigit0.kekodproject1mertyigit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mertyigit0.kekodproject1mertyigit.databinding.FragmentSwitchScreenBinding

class SwitchScreenFragment : Fragment(R.layout.fragment_switch_screen) {

    private var _binding: FragmentSwitchScreenBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSwitchScreenBinding.bind(view)

        // Switch'ler
        val switchEgo = binding.switchEgo
        val switchGiving = binding.switchGiving
        val switchHappiness = binding.switchHappiness
        val switchKindness = binding.switchKindness
        val switchRespect = binding.switchRespect
        val switchOptimism = binding.switchOptimism

        // Function to update switch states
        fun updateSwitches() {
            if (switchEgo.isChecked) {
                // Disable other switches if Ego is checked
                switchGiving.isChecked = false
                switchHappiness.isChecked = false
                switchKindness.isChecked = false
                switchRespect.isChecked = false
                switchOptimism.isChecked = false

                // Update Bottom Navigation with Ego
                updateBottomNavigation(R.id.switch_ego)
            } else {
                // Check if any other switch is on
                if (switchGiving.isChecked || switchHappiness.isChecked || switchKindness.isChecked || switchRespect.isChecked || switchOptimism.isChecked) {
                    // If any other switch is on, turn off Ego
                    switchEgo.isChecked = false
                }
            }

            // Update Bottom Navigation based on the other switches
            if (switchGiving.isChecked) updateBottomNavigation(R.id.switch_giving)
            if (switchHappiness.isChecked) updateBottomNavigation(R.id.switch_happiness)
            if (switchKindness.isChecked) updateBottomNavigation(R.id.switch_kindness)
            if (switchRespect.isChecked) updateBottomNavigation(R.id.switch_respect)
            if (switchOptimism.isChecked) updateBottomNavigation(R.id.switch_optimism)

            // Remove menu items for unchecked switches
            if (!switchEgo.isChecked) removeMenuItemForSwitch(R.id.switch_ego)
            if (!switchGiving.isChecked) removeMenuItemForSwitch(R.id.switch_giving)
            if (!switchHappiness.isChecked) removeMenuItemForSwitch(R.id.switch_happiness)
            if (!switchKindness.isChecked) removeMenuItemForSwitch(R.id.switch_kindness)
            if (!switchRespect.isChecked) removeMenuItemForSwitch(R.id.switch_respect)
            if (!switchOptimism.isChecked) removeMenuItemForSwitch(R.id.switch_optimism)
        }

        // Initialize the switch states
        updateSwitches()

        // Listen for changes in switch states
        switchEgo.setOnCheckedChangeListener { _, _ -> updateSwitches() }
        switchGiving.setOnCheckedChangeListener { _, _ -> updateSwitches() }
        switchHappiness.setOnCheckedChangeListener { _, _ -> updateSwitches() }
        switchKindness.setOnCheckedChangeListener { _, _ -> updateSwitches() }
        switchRespect.setOnCheckedChangeListener { _, _ -> updateSwitches() }
        switchOptimism.setOnCheckedChangeListener { _, _ -> updateSwitches() }
    }

    private fun updateBottomNavigation(selectedItemId: Int) {
        (activity as? MainActivity)?.updateBottomNavigation(selectedItemId)
    }

    private fun removeMenuItemForSwitch(switchId: Int) {
        (activity as? MainActivity)?.removeMenuItemForSwitch(switchId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
