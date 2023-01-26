package com.example.bdflows.bd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bdflows.utils.Constants.CONTACTS_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveContact(contactsEntity: ContactsEntity)

    @Query("SELECT * FROM $CONTACTS_TABLE")
    fun getAllContacts(): Flow<MutableList<ContactsEntity>>

}