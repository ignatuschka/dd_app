package com.example.dd_app.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.dd_app.BuildConfig
import com.example.dd_app.data.data_source.local.ActivityLocalDataSource
import com.example.dd_app.data.data_source.local.ActivityLocalDataSourceImpl
import com.example.dd_app.data.data_source.local.AuthLocalDataSource
import com.example.dd_app.data.data_source.local.AuthLocalDataSourceImpl
import com.example.dd_app.data.data_source.local.LocationClient
import com.example.dd_app.data.data_source.local.UserLocalDataSource
import com.example.dd_app.data.data_source.local.UserLocalDataSourceImpl
import com.example.dd_app.data.data_source.remote.ActivityRemoteDataSource
import com.example.dd_app.data.data_source.remote.ActivityRemoteDataSourceImpl
import com.example.dd_app.data.database.DdDatabase
import com.example.dd_app.data.database.dao.MyActivityDao
import com.example.dd_app.data.database.dao.UserDao
import com.example.dd_app.data.network.DdApi
import com.example.dd_app.data.provider.DefaultResourceProvider
import com.example.dd_app.data.repository.ActivityRepositoryImpl
import com.example.dd_app.data.repository.AuthRepositoryImpl
import com.example.dd_app.data.repository.UserRepositoryImpl
import com.example.dd_app.domain.provider.ResourceProvider
import com.example.dd_app.domain.repository.ActivityRepository
import com.example.dd_app.domain.repository.AuthRepository
import com.example.dd_app.domain.repository.UserRepository
import com.example.dd_app.domain.usecase.ClearUserLoginUsecase
import com.example.dd_app.domain.usecase.DeleteMyActivityUsecase
import com.example.dd_app.domain.usecase.GetAllMyActivitiesUsecase
import com.example.dd_app.domain.usecase.GetAllUserActivitiesUsecase
import com.example.dd_app.domain.usecase.GetMyActivityByIdUsecase
import com.example.dd_app.domain.usecase.GetUserActivityUsecase
import com.example.dd_app.domain.usecase.GetUserByLoginUsecase
import com.example.dd_app.domain.usecase.GetUserLoginUsecase
import com.example.dd_app.domain.usecase.InsertMyActivityUsecase
import com.example.dd_app.domain.usecase.InsertUserUsecase
import com.example.dd_app.domain.usecase.SaveUserLoginUsecase
import com.example.dd_app.domain.usecase.UpdateCommentUsecase
import com.example.dd_app.domain.usecase.UpdateLoginAndUserNameUsecase
import com.example.dd_app.domain.usecase.UpdatePasswordUsecase
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideResourceProvider(app: Application): ResourceProvider {
        return DefaultResourceProvider(app)
    }

    @Provides
    @Singleton
    fun provideMapKit(app: Application): MapKit {
        MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)
        MapKitFactory.setLocale("ru_RU")
        MapKitFactory.initialize(app)
        return MapKitFactory.getInstance()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("auth_preferences", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): DdDatabase {
        return Room.databaseBuilder(app, DdDatabase::class.java, "dd_database").build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val baseUrl = BuildConfig.BASE_URL
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): DdApi {
        return retrofit.create(DdApi::class.java)
    }

    @Provides
    fun provideUserDao(db: DdDatabase): UserDao {
        return db.userDao()
    }

    @Provides
    fun provideMyActivityDao(db: DdDatabase): MyActivityDao {
        return db.myActivityDao()
    }

    @Provides
    fun provideLocalActivityDataSource(dao: MyActivityDao): ActivityLocalDataSource {
        return ActivityLocalDataSourceImpl(dao)
    }

    @Provides
    fun provideRemoteActivityDataSource(api: DdApi): ActivityRemoteDataSource {
        return ActivityRemoteDataSourceImpl(api)
    }

    @Provides
    fun provideLocalUserDataSource(dao: UserDao): UserLocalDataSource {
        return UserLocalDataSourceImpl(dao)
    }

    @Provides
    fun provideLocalAuthDataSource(sharedPreferences: SharedPreferences): AuthLocalDataSource {
        return AuthLocalDataSourceImpl(sharedPreferences)
    }

    @Provides
    fun provideActivityRepository(local: ActivityLocalDataSource, remote: ActivityRemoteDataSource): ActivityRepository {
        return ActivityRepositoryImpl(local, remote)
    }

    @Provides
    fun provideUserRepository(dataSource: UserLocalDataSource): UserRepository {
        return UserRepositoryImpl(dataSource)
    }

    @Provides
    fun provideAuthRepository(dataSource: AuthLocalDataSource): AuthRepository {
        return AuthRepositoryImpl(dataSource)
    }

    @Provides
    fun provideDeleteMyActivityUsecase(repository: ActivityRepository): DeleteMyActivityUsecase {
        return DeleteMyActivityUsecase(repository)
    }

    @Provides
    fun provideGetAllMyActivitiesUsecase(repository: ActivityRepository): GetAllMyActivitiesUsecase {
        return GetAllMyActivitiesUsecase(repository)
    }

    @Provides
    fun provideInsertMyActivityUsecase(repository: ActivityRepository): InsertMyActivityUsecase {
        return InsertMyActivityUsecase(repository)
    }

    @Provides
    fun provideGetMyActivityByIdUsecase(repository: ActivityRepository): GetMyActivityByIdUsecase {
        return GetMyActivityByIdUsecase(repository)
    }

    @Provides
    fun provideInsertUserUsecase(repository: UserRepository): InsertUserUsecase {
        return InsertUserUsecase(repository)
    }

    @Provides
    fun provideUpdatePasswordUsecase(repository: UserRepository): UpdatePasswordUsecase {
        return UpdatePasswordUsecase(repository)
    }

    @Provides
    fun provideGetUserByLoginUsecase(repository: UserRepository): GetUserByLoginUsecase {
        return GetUserByLoginUsecase(repository)
    }

    @Provides
    fun provideClearUserLoginUsecase(repository: AuthRepository): ClearUserLoginUsecase {
        return ClearUserLoginUsecase(repository)
    }

    @Provides
    fun provideSaveUserLoginUsecase(repository: AuthRepository): SaveUserLoginUsecase {
        return SaveUserLoginUsecase(repository)
    }

    @Provides
    fun provideGetUserLoginUsecase(repository: AuthRepository): GetUserLoginUsecase {
        return GetUserLoginUsecase(repository)
    }

    @Provides
    fun provideUpdateLoginAndUserNameUsecase(repository: UserRepository): UpdateLoginAndUserNameUsecase {
        return UpdateLoginAndUserNameUsecase(repository)
    }

    @Provides
    fun provideGetUserActivityUsecase(repository: ActivityRepository): GetUserActivityUsecase {
        return GetUserActivityUsecase(repository)
    }

    @Provides
    fun provideGetAllUserActivitiesUsecase(repository: ActivityRepository): GetAllUserActivitiesUsecase {
        return GetAllUserActivitiesUsecase(repository)
    }

    @Provides
    fun provideUpdateCommentUsecase(repository: ActivityRepository): UpdateCommentUsecase {
        return UpdateCommentUsecase(repository)
    }

    @Provides
    fun provideLocationClient(app: Application): LocationClient {
        return LocationClient(app)
    }
}
