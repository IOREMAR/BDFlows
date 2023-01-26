package com.example.bdflows.di

import android.content.Context
import androidx.room.Room
import com.example.bdflows.bd.ContactsDB
import com.example.bdflows.bd.ContactsEntity
import com.example.bdflows.utils.Constants.CONTACTS_TABLE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun privideDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ContactsDB::class.java,CONTACTS_TABLE)
            .allowMainThreadQueries().fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providesDAO (bd :ContactsDB) =
        bd.contactsDao()

    @Provides
    fun provideEntity()=ContactsEntity()


}