package com.mertyigit0.kekodproject1mertyigit

// SwitchScreenFragment.kt
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.mertyigit0.kekodproject1mertyigit.R
import com.mertyigit0.kekodproject1mertyigit.databinding.FragmentSwitchScreenBinding
import com.mertyigit0.viewmodel.SwitchViewModel

class SwitchScreenFragment : Fragment(R.layout.fragment_switch_screen) {

    private var _binding: FragmentSwitchScreenBinding? = null
    private val binding get() = _binding!!

    // ViewModel'i fragment ile bağlama
    private val switchViewModel: SwitchViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSwitchScreenBinding.bind(view)

        // ViewModel'deki verileri gözlemleme
        observeSwitches()

        // Switch durumlarını güncelleme
        setSwitchListeners()
    }

    private fun observeSwitches() {
        // Ego Switch gözlemi
        switchViewModel.isEgoChecked.observe(viewLifecycleOwner, Observer { isChecked ->
            binding.switchEgo.isChecked = isChecked
            if (isChecked) {
                updateBottomNavigation(R.id.switch_ego)
                (activity as? MainActivity)?.setBottomNavigationVisibility(false)
            } else {
                (activity as? MainActivity)?.setBottomNavigationVisibility(true)
            }
        })

        // Diğer switch'ler gözlemi
        switchViewModel.isGivingChecked.observe(viewLifecycleOwner, Observer { isChecked ->
            binding.switchGiving.isChecked = isChecked
            handleBottomNavigationUpdate(isChecked, R.id.switch_giving)
        })

        switchViewModel.isHappinessChecked.observe(viewLifecycleOwner, Observer { isChecked ->
            binding.switchHappiness.isChecked = isChecked
            handleBottomNavigationUpdate(isChecked, R.id.switch_happiness)
        })

        switchViewModel.isKindnessChecked.observe(viewLifecycleOwner, Observer { isChecked ->
            binding.switchKindness.isChecked = isChecked
            handleBottomNavigationUpdate(isChecked, R.id.switch_kindness)
        })

        switchViewModel.isRespectChecked.observe(viewLifecycleOwner, Observer { isChecked ->
            binding.switchRespect.isChecked = isChecked
            handleBottomNavigationUpdate(isChecked, R.id.switch_respect)
        })

        switchViewModel.isOptimismChecked.observe(viewLifecycleOwner, Observer { isChecked ->
            binding.switchOptimism.isChecked = isChecked
            handleBottomNavigationUpdate(isChecked, R.id.switch_optimism)
        })
    }

    private fun setSwitchListeners() {
        // Ego switch dinleyicisi
        binding.switchEgo.setOnCheckedChangeListener { _, isChecked ->
            switchViewModel.setEgoChecked(isChecked)
        }

        // Diğer switch'ler için dinleyici
        binding.switchGiving.setOnCheckedChangeListener { _, isChecked ->
            switchViewModel.setGivingChecked(isChecked)
        }

        binding.switchHappiness.setOnCheckedChangeListener { _, isChecked ->
            switchViewModel.setHappinessChecked(isChecked)
        }

        binding.switchKindness.setOnCheckedChangeListener { _, isChecked ->
            switchViewModel.setKindnessChecked(isChecked)
        }

        binding.switchRespect.setOnCheckedChangeListener { _, isChecked ->
            switchViewModel.setRespectChecked(isChecked)
        }

        binding.switchOptimism.setOnCheckedChangeListener { _, isChecked ->
            switchViewModel.setOptimismChecked(isChecked)
        }
    }

    private fun handleBottomNavigationUpdate(isChecked: Boolean, id: Int) {
        if (isChecked) {
            updateBottomNavigation(id)
        } else {
            removeMenuItemForSwitch(id)
        }
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

