package com.berkan.rijksdataapp.di

import com.berkan.rijksdataapp.BuildConfig
import com.berkan.rijksdataapp.BuildConfig.BASE_URL
import com.berkan.rijksdataapp.data.Repository
import com.berkan.rijksdataapp.data.remote.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesOkHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val newUrl = original.url
                    .newBuilder()
                    .addQueryParameter("key", BuildConfig.API_KEY)
                    .addQueryParameter("ps", "50")
                    .build()

                val requestBuilder = original.newBuilder()
                    .url(newUrl)

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit
        .Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun providesRepository(apiService: ApiService) = Repository(apiService)

    @Singleton
    @Provides
    fun providesMoshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

}