package com.mertyigit0.kekodproject1mertyigit

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
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
        menu.clear()
        when (selectedItemId) {
            R.id.switch_ego -> menu.add(0, R.id.egoFragment, 0, R.string.ego).setIcon(R.drawable.ic_ego)
            R.id.switch_giving -> menu.add(0, R.id.givingFragment, 0, R.string.giving).setIcon(R.drawable.ic_giving)
            R.id.switch_happiness -> menu.add(0, R.id.happinessFragment, 0, R.string.happiness).setIcon(R.drawable.ic_happiness)
            R.id.switch_kindness -> menu.add(0, R.id.kindnessFragment, 0, R.string.kindness).setIcon(R.drawable.ic_kindness)
            R.id.switch_respect -> menu.add(0, R.id.respectFragment, 0, R.string.respect).setIcon(R.drawable.ic_respect)
            R.id.switch_optimism -> menu.add(0, R.id.optimismFragment, 0, R.string.optimism).setIcon(R.drawable.ic_optimism)
        }
    }
}
