package com.app.comicapp.di

import com.app.comicapp.common.Config
import com.app.comicapp.data.apis.author.AuthorApi
import com.app.comicapp.data.apis.comment.CommentApi
import com.app.comicapp.data.apis.user.ChapterApi
import com.app.comicapp.data.apis.user.ComicApi
import com.app.comicapp.data.apis.user.UserApi
import com.app.comicapp.data.entities.Author
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
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor{
        val  httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
    @Provides
    @Singleton
    fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(httpLoggingInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return MoshiConverterFactory.create(moshi)
    }


    @Provides
    @Singleton
    @Named("LocalSite")
    fun provideRetrofit(okHttpClient: OkHttpClient, moshiConverterFactory: MoshiConverterFactory ) :Retrofit{
        return Retrofit.Builder().addConverterFactory(moshiConverterFactory).baseUrl(Config.Url).client(okHttpClient).build()

    }

    @Provides
    @Singleton
    fun provideUserApi(@Named("LocalSite") retrofit: Retrofit): UserApi{
        return retrofit.create(UserApi::class.java)
    }


    @Provides
    @Singleton
    fun provideChapterApi(@Named("LocalSite") retrofit: Retrofit): ChapterApi{
        return retrofit.create(ChapterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideComicApi(@Named("LocalSite") retrofit: Retrofit): ComicApi{
        return retrofit.create(ComicApi::class.java)
    }


    @Provides
    @Singleton
    fun provideAuthorApi(@Named("LocalSite") retrofit: Retrofit): AuthorApi {
        return retrofit.create(AuthorApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCommentApi(@Named("LocalSite") retrofit: Retrofit): CommentApi {
        return retrofit.create(CommentApi::class.java)
    }

}