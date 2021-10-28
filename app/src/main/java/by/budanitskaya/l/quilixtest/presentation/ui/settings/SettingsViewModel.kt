package by.budanitskaya.l.quilixtest.presentation.ui.settings

import androidx.lifecycle.ViewModel
import by.budanitskaya.l.quilixtest.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(val settingsRepository: SettingsRepository) :
    ViewModel() {

    fun getSettings() = settingsRepository.getSettingsInfo()

}

