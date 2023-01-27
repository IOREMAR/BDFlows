package com.example.bdflows.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bdflows.adapter.ContactsAdapter
import com.example.bdflows.databinding.ActivityMainBinding
import com.example.bdflows.repository.DataStatus
import com.example.bdflows.viewmodel.DatabaseViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var activitBinding : ActivityMainBinding

    @Inject
    lateinit var Adapter : ContactsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         activitBinding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(activitBinding.root)
        val viewModel: DatabaseViewModel by viewModels()
        activitBinding.apply {
            setSupportActionBar(toolbar)

            btnShowDialog.setOnClickListener {
                AddContactFragment().show(supportFragmentManager,AddContactFragment().tag)
            }

            viewModel.getAllContacts()
            viewModel.contactlist.observe(this@MainActivity){
                when(it.status){
                    DataStatus.Status.LOADING -> {
                        loading.isVisible = true
                        emptyBody.isVisible = false
                            }
                    DataStatus.Status.SUCCESS -> {
                        loading.isVisible = false
                        ShowLoader(it.isEmpty!!)
                        Adapter.differ.submitList(it.data)
                        rvContacts.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = Adapter
                        }
                        }
                    DataStatus.Status.ERROR ->{
                        loading.isVisible = false

                        Snackbar.make(activitBinding.root,it.message!!, Snackbar.LENGTH_SHORT).show()

                    }
                }
            }

        }
    }


    fun ShowLoader( show : Boolean = false ){
        activitBinding.apply {
            if(show){
                emptyBody.visibility = View.VISIBLE
                listBody.visibility = View.GONE
            }else{
                emptyBody.visibility = View.GONE
                listBody.visibility = View.VISIBLE
            }

        }



    }
}