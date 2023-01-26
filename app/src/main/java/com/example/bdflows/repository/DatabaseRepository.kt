package com.example.bdflows.repository

import com.example.bdflows.bd.ContactsDao
import com.example.bdflows.bd.ContactsEntity
import javax.inject.Inject

class DatabaseRepository @Inject constructor( private val dao: ContactsDao) {

    suspend fun saveContacts(entity: ContactsEntity)=dao.saveContact(entity)

    fun getAllContacts()=dao.getAllContacts()

}