package com.mertyigit0.kekodproject1mertyigit

import android.os.Bundle
import android.view.View
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




        val navHostFragment = binding.fragmentContainerView.getFragment<NavHostFragment>()
        val navController = navHostFragment?.findNavController()
        if (navController != null) {
            binding.bottomNavigationView.setupWithNavController(navController)
        }
    }

    private fun getMenuItemIdForSwitch(switchId: Int): Int? {
        return when (switchId) {
            R.id.switch_giving -> R.id.givingFragment
            R.id.switch_happiness -> R.id.happinessFragment
            R.id.switch_kindness -> R.id.kindnessFragment
            R.id.switch_respect -> R.id.respectFragment
            R.id.switch_optimism -> R.id.optimismFragment
            else -> null
        }
    }

    private fun addMenuItem(menuItemId: Int, titleResId: Int, iconResId: Int) {
        val menu = binding.bottomNavigationView.menu
        if (menu.findItem(menuItemId) == null && menu.size() < 5) {
            menu.add(0, menuItemId, 0, titleResId).setIcon(iconResId)
        } else if (menu.size() == 5) {
            // Eğer menüde 5 item varsa, Snackbar göster
            Snackbar.make(binding.root, R.string.menu_limit_reached, Snackbar.LENGTH_SHORT).show()
        }
    }


    fun updateBottomNavigation(selectedItemId: Int) {
        val menuItemId = getMenuItemIdForSwitch(selectedItemId) ?: return

        val (titleResId, iconResId) = when (menuItemId) {
            R.id.givingFragment -> R.string.giving to R.drawable.ic_giving
            R.id.happinessFragment -> R.string.happiness to R.drawable.ic_happiness
            R.id.kindnessFragment -> R.string.kindness to R.drawable.ic_kindness
            R.id.respectFragment -> R.string.respect to R.drawable.ic_respect
            R.id.optimismFragment -> R.string.optimism to R.drawable.ic_optimism
            else -> return
        }

        addMenuItem(menuItemId, titleResId, iconResId)
    }

    fun removeMenuItemForSwitch(switchId: Int) {
        val menuItemId = getMenuItemIdForSwitch(switchId) ?: return
        binding.bottomNavigationView.menu.removeItem(menuItemId)
    }

    fun setBottomNavigationVisibility(isVisible: Boolean) {
        // Bottom Navigation visibility ayarlama işlemi
        binding.bottomNavigationView.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}
