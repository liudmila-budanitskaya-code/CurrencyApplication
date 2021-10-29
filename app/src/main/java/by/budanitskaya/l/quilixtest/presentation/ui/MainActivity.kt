package by.budanitskaya.l.quilixtest.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import by.budanitskaya.l.quilixtest.R
import by.budanitskaya.l.quilixtest.utils.getCurrentDatePlusDays
import by.budanitskaya.l.quilixtest.utils.getCurrentFormatDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(
            MainViewModel::class.java
        )
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private val navController by lazy {
        this.findNavController(R.id.nav_host_fragment_activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.initializeApp()
        NavigationUI.setupActionBarWithNavController(this, navController)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.currencyInfoFragment, R.id.settingsFragment
            )
        )
        this.setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}