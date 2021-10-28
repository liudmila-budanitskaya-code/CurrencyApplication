package by.budanitskaya.l.quilixtest.di

import android.content.Context
import android.content.SharedPreferences
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

    const val STORAGE_NAME = "Currency Settings"

}