package dev.ricknout.rugbyranker.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updatePadding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.Insetter
import dev.chrisbanes.insetter.setEdgeToEdgeSystemUiFlags
import dev.ricknout.rugbyranker.BuildConfig
import dev.ricknout.rugbyranker.R
import dev.ricknout.rugbyranker.databinding.ActivityMainBinding
import dev.ricknout.rugbyranker.info.ui.InfoViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val infoViewModel: InfoViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupNavigation()
        setupEdgeToEdge()
    }

    private fun setupViewModel() {
        infoViewModel.setVersion(BuildConfig.VERSION_NAME)
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navigationView.setupWithNavController(navController)
    }

    private fun setupEdgeToEdge() {
        binding.drawerLayout.setEdgeToEdgeSystemUiFlags()
        Insetter.builder().setOnApplyInsetsListener { view, insets, _ ->
            view.findViewById<View>(R.id.design_navigation_view).updatePadding(
                left = insets.systemWindowInsets.left,
                top = insets.systemWindowInsets.top,
                bottom = insets.systemWindowInsets.bottom
            )
        }.applyToView(binding.navigationView)
    }
}
