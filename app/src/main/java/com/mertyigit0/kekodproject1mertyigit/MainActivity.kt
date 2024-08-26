package com.mertyigit0.kekodproject1mertyigit

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.mertyigit0.kekodproject1mertyigit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHostFragment = binding.fragmentContainerView.getFragment<NavHostFragment>()
        val navController = navHostFragment?.findNavController()
        if (navController != null) {
            binding.bottomNavigationView.setupWithNavController(navController)
        }
    }

    fun updateBottomNavigation(selectedItemId: Int) {
        val menu = binding.bottomNavigationView.menu

        // Menüde aynı isim ve ikonla bir öğe olup olmadığını kontrol et
        val itemIdToCheck = when (selectedItemId) {
            R.id.switch_ego -> R.id.egoFragment
            R.id.switch_giving -> R.id.givingFragment
            R.id.switch_happiness -> R.id.happinessFragment
            R.id.switch_kindness -> R.id.kindnessFragment
            R.id.switch_respect -> R.id.respectFragment
            R.id.switch_optimism -> R.id.optimismFragment
            else -> return
        }

        if (menu.findItem(itemIdToCheck) != null) {
            // Aynı isim ve ikonla bir menü öğesi zaten var, uyarı göster
            // Snackbar.make(binding.root, R.string.menu_item_already_exists, Snackbar.LENGTH_SHORT).show()
            return
        }

        if (menu.size() >= 5) {
            // Menüde 5'ten fazla öğe varsa, uyarı göster
            Snackbar.make(binding.root, R.string.menu_limit_reached, Snackbar.LENGTH_SHORT).show()
            return
        }

        // Yeni menü öğesi ekle
        when (selectedItemId) {
            R.id.switch_ego -> menu.add(0, R.id.egoFragment, 0, R.string.ego).setIcon(R.drawable.ic_ego)
            R.id.switch_giving -> menu.add(0, R.id.givingFragment, 0, R.string.giving).setIcon(R.drawable.ic_giving)
            R.id.switch_happiness -> menu.add(0, R.id.happinessFragment, 0, R.string.happiness).setIcon(R.drawable.ic_happiness)
            R.id.switch_kindness -> menu.add(0, R.id.kindnessFragment, 0, R.string.kindness).setIcon(R.drawable.ic_kindness)
            R.id.switch_respect -> menu.add(0, R.id.respectFragment, 0, R.string.respect).setIcon(R.drawable.ic_respect)
            R.id.switch_optimism -> menu.add(0, R.id.optimismFragment, 0, R.string.optimism).setIcon(R.drawable.ic_optimism)
        }
    }

    fun removeMenuItemForSwitch(switchId: Int) {
        val menu = binding.bottomNavigationView.menu
        val itemIdToRemove = when (switchId) {
            R.id.switch_ego -> R.id.egoFragment
            R.id.switch_giving -> R.id.givingFragment
            R.id.switch_happiness -> R.id.happinessFragment
            R.id.switch_kindness -> R.id.kindnessFragment
            R.id.switch_respect -> R.id.respectFragment
            R.id.switch_optimism -> R.id.optimismFragment
            else -> return
        }

        // Menüdeki öğeyi bul ve kaldır
        val menuItem = menu.findItem(itemIdToRemove)
        if (menuItem != null) {
            menu.removeItem(itemIdToRemove)
        }
    }
}
