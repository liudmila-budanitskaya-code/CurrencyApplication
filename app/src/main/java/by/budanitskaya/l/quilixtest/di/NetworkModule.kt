package by.budanitskaya.l.quilixtest.di

import by.budanitskaya.l.quilixtest.data.network.apiservice.ApiService
import by.budanitskaya.l.quilixtest.BuildConfig
import by.budanitskaya.l.quilixtest.data.network.responsewrapper.SafeApiCall
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    const val TIME_OUT_DURATION = 30L

    @Provides
    @Singleton
    fun createApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(okHttpClient)
            .build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSafeApiCall(): SafeApiCall {
        return SafeApiCall()
    }

    @Provides
    @Singleton
    fun provideGlobalOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .callTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}

