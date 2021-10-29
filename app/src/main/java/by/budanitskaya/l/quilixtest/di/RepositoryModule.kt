package by.budanitskaya.l.quilixtest.di

import by.budanitskaya.l.quilixtest.data.datasource.CurrencyDataSource
import by.budanitskaya.l.quilixtest.data.datasource.CurrencyDataSourceImpl
import by.budanitskaya.l.quilixtest.data.network.apiservice.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun getCurrencyDataSource(
        apiService: ApiService
    ): CurrencyDataSource {
        return CurrencyDataSourceImpl(apiService)
    }
}