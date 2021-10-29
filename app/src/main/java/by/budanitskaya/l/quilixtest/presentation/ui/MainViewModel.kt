package by.budanitskaya.l.quilixtest.presentation.ui

import androidx.lifecycle.ViewModel
import by.budanitskaya.l.quilixtest.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val settingsRepository: SettingsRepository) : ViewModel() {

    fun initializeApp() = settingsRepository.initializeApp()


}