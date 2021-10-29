package by.budanitskaya.l.quilixtest.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import by.budanitskaya.l.quilixtest.R
import by.budanitskaya.l.quilixtest.data.repository.SettingsRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    val navController by lazy {
        this.findNavController(R.id.nav_host_fragment_activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        settingsRepository.initializeApp()
        setupToolBar()
    }

    private fun setupToolBar() {
        NavigationUI.setupActionBarWithNavController(this, navController)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.setupActionBarWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            title = when (destination.id) {
                R.id.currencyInfoFragment -> this.resources
                    .getString(R.string.toolbar_fragment_currency_courses)
                R.id.settingsFragment -> this.resources
                    .getString(R.string.toolbar_currency_setting)
                else -> this.resources
                    .getString(R.string.default_title)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}