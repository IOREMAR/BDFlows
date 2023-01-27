package com.example.bdflows.viewmodel

import android.provider.ContactsContract.Data
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bdflows.bd.ContactsEntity
import com.example.bdflows.repository.DataStatus
import com.example.bdflows.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(private val Repo : DatabaseRepository): ViewModel() {


    private val _contactslist = MutableLiveData<DataStatus<List<ContactsEntity>>> ()

    val contactlist : LiveData<DataStatus<List<ContactsEntity>>> get() = _contactslist

    fun saveContact (entity: ContactsEntity) = viewModelScope.launch {
        Repo.saveContacts(entity)
    }

    fun getAllContacts()=
        viewModelScope.launch {
        _contactslist.postValue(DataStatus.loading())
            Repo.getAllContacts().catch {
                _contactslist.postValue(DataStatus.error(it.message.toString()))
            }
                .collect{
                    _contactslist.postValue(DataStatus.succes(it,it.isEmpty()))
                }
        }


}