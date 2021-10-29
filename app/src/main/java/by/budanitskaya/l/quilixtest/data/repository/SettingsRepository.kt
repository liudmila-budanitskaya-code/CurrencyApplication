package by.budanitskaya.l.quilixtest.data.repository

import by.budanitskaya.l.quilixtest.presentation.models.CurrencyPresentationModel
import by.budanitskaya.l.quilixtest.presentation.models.SettingsModel

interface SettingsRepository {
    fun clearTemporaryStorage()
    fun initializeApp()
    fun setBoolean(key: String, value: Boolean, isAppInitialized: Boolean)
    fun getBoolean(key: String): Boolean
    fun getSettingsInfo(): List<SettingsModel>
    fun temporarilySave(charCode: String, isActive: Boolean)
    fun persistTemporaryChanges()
    fun tempApplyTemporaryChanges(model: SettingsModel): SettingsModel
    fun applyPrefsToCurrencyList(currencyInitialList: List<CurrencyPresentationModel>)
            : List<CurrencyPresentationModel>
}