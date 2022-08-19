package com.example.core.data.source.remote.network

//@Module
//@InstallIn(SingletonComponent::class)
//object RetrofitModule {
//
//    @Singleton
//    @Provides
//    fun provideMoshiConverter(): Moshi = Moshi.Builder()
//        .add(KotlinJsonAdapterFactory())
//        .build()
//
//    @DomainRetrofit
//    @Singleton
//    @Provides
//    fun provideDomainRetrofit(moshi: Moshi): Retrofit.Builder = Retrofit.Builder()
//        .baseUrl(DOMAIN_API_URL)
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
//
//
//    private const val DOMAIN_API_URL = "https://story-api.dicoding.dev/v1/"
//}