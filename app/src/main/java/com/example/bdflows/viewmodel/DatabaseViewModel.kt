package com.example.bdflows.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bdflows.bd.ContactsEntity
import com.example.bdflows.repository.DataStatus
import com.example.bdflows.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(private val Repo : DatabaseRepository): ViewModel() {


    private val _contactslist = MutableLiveData<DataStatus<List<ContactsEntity>>> ()







}