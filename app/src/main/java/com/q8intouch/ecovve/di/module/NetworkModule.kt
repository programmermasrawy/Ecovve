package com.q8intouch.ecovve.di.module


import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory
import com.github.leonardoxh.livedatacalladapter.LiveDataResponseBodyConverterFactory
import com.google.gson.GsonBuilder
import com.q8intouch.ecovve.network.EcovveNetworkInterceptor
import com.q8intouch.ecovve.network.apis.*
import com.q8intouch.ecovve.util.URLs.BASE_URL
import com.q8intouch.ecovve.util.URLs.URLL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    @Reusable
    fun provideUserApi(retrofit: Retrofit): UserAPI = retrofit.create(UserAPI::class.java)

    @Provides
    @Reusable
    fun provideAuthApi(retrofit: Retrofit): AuthAPI = retrofit.create(AuthAPI::class.java)

    @Provides
    @Reusable
    fun provideAccountInfoApi(retrofit: Retrofit): AccInfoApi = retrofit.create(AccInfoApi::class.java)

    @Provides
    @Reusable
    fun provideGiftApi(retrofit: Retrofit): GiftCardApi = retrofit.create(GiftCardApi::class.java)

    @Provides
    @Reusable
    fun provideOrdersApi(retrofit: Retrofit): OrdersAPI = retrofit.create(OrdersAPI::class.java)

    @Provides
    @Reusable
    fun provideReviewApi(retrofit: Retrofit): ReviewAPI = retrofit.create(ReviewAPI::class.java)


    @Provides
    @Reusable
    fun provideChatApi(retrofit: Retrofit): ChatAPI = retrofit.create(ChatAPI::class.java)

    @Provides
    @Reusable
    fun provideFriendApi(retrofit: Retrofit): FriendsAPI = retrofit.create(FriendsAPI::class.java)


    @Provides
    @Reusable
    fun provideCafesApi(retrofit: Retrofit): CafesApi = retrofit.create(CafesApi::class.java)

    @Provides
    @Reusable
    fun provideFAQApi(retrofit: Retrofit): FAQApi = retrofit.create(FAQApi::class.java)

    val gson =  GsonBuilder()
        .setLenient()
        .create();
    @Provides
    @Reusable
    fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(URLL)
                .client(okHttpClient)
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }
    @Provides
    @Reusable
    fun provideOkHttpClient(interceptor: EcovveNetworkInterceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .addInterceptor(logging)
            .addInterceptor(interceptor)
                .callTimeout(190,TimeUnit.SECONDS)
                .readTimeout(190,TimeUnit.SECONDS)
                .connectTimeout(190,TimeUnit.SECONDS)
                .writeTimeout(190,TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Reusable
    fun provideInterceptor(): EcovveNetworkInterceptor {
        return EcovveNetworkInterceptor()
    }

}