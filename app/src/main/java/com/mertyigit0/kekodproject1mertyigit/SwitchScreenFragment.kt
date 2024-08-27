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

        // Tüm switch'leri bir liste içinde toplama
        val switches = listOf(
            binding.switchEgo to R.id.switch_ego,
            binding.switchGiving to R.id.switch_giving,
            binding.switchHappiness to R.id.switch_happiness,
            binding.switchKindness to R.id.switch_kindness,
            binding.switchRespect to R.id.switch_respect,
            binding.switchOptimism to R.id.switch_optimism
        )

        // Switch durumlarını güncelleyen fonksiyon
        fun updateSwitches() {
            val isEgoChecked = binding.switchEgo.isChecked

            // Eğer Ego açık ise diğerlerini kapat
            if (isEgoChecked) {
                switches.filter { it.first != binding.switchEgo }
                    .forEach { it.first.isChecked = false }
                updateBottomNavigation(R.id.switch_ego)
                (activity as? MainActivity)?.setBottomNavigationVisibility(false)
            } else {
                (activity as? MainActivity)?.setBottomNavigationVisibility(true)
            }

            // Diğer switch'lerin durumlarını ve alt gezinmeyi güncelleme
            switches.filter { it.first != binding.switchEgo }.forEach { (switch, id) ->
                if (switch.isChecked) {
                    updateBottomNavigation(id)
                } else {
                    removeMenuItemForSwitch(id)
                }
            }
        }

        // Tüm switch'lere tek bir dinleyici ekleme
        switches.forEach { (switch, _) ->
            switch.setOnCheckedChangeListener { _, _ -> updateSwitches() }
        }

        // Switch durumlarını başlatma
        updateSwitches()
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
