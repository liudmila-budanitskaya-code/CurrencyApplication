package by.budanitskaya.l.quilixtest.di

import android.content.Context
import android.content.SharedPreferences
import by.budanitskaya.l.quilixtest.data.datasource.CurrencyDataSource
import by.budanitskaya.l.quilixtest.data.datasource.CurrencyDataSourceImpl
import by.budanitskaya.l.quilixtest.data.network.apiservice.ApiService
import by.budanitskaya.l.quilixtest.data.repository.SettingsRepository
import by.budanitskaya.l.quilixtest.data.repository.SettingsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PreferenceModule {

    @Provides
    @Singleton
    fun invokePreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            STORAGE_NAME,
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun getSettingsRepository(
        @ApplicationContext context: Context,
        sharedPreferences: SharedPreferences
    ): SettingsRepository {
        return SettingsRepositoryImpl(context, sharedPreferences)
    }

    const val STORAGE_NAME = "Currency Settings"
}