package com.mertyigit0.kekodproject1mertyigit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.switchmaterial.SwitchMaterial
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

        // Diğer Switch'lerin durumunu kontrol et
        fun updateSwitches() {
            if (switchEgo.isChecked) {
                updateBottomNavigation(R.id.switch_ego)
            } else if (switchGiving.isChecked) {
                updateBottomNavigation(R.id.switch_giving)
            } else if (switchHappiness.isChecked) {
                updateBottomNavigation(R.id.switch_happiness)
            } else if (switchKindness.isChecked) {
                updateBottomNavigation(R.id.switch_kindness)
            } else if (switchRespect.isChecked) {
                updateBottomNavigation(R.id.switch_respect)
            } else if (switchOptimism.isChecked) {
                updateBottomNavigation(R.id.switch_optimism)
            }
        }

        // Switch'lerin durumunu başlat
        updateSwitches()

        // Switch'lerin değişimini dinle
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

