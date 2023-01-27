package com.example.bdflows.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bdflows.bd.ContactsEntity
import com.example.bdflows.databinding.ItemContractsBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactsAdapter @Inject constructor(): RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>()    {

    private lateinit var adapterBinding: ItemContractsBinding


    private val differCallback = object : DiffUtil.ItemCallback<ContactsEntity>() {
        override fun areItemsTheSame(oldItem: ContactsEntity, newItem: ContactsEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ContactsEntity, newItem: ContactsEntity): Boolean {
            return oldItem == newItem
        }

    }


    val differ = AsyncListDiffer(this, differCallback)

    inner class ContactsViewHolder : RecyclerView.ViewHolder (adapterBinding.root){

        fun setData(item : ContactsEntity){
            adapterBinding.apply {
                tvName.text = item.name
                tvPhone.text = item.phone
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        adapterBinding = ItemContractsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ContactsViewHolder()
    }

    override fun getItemCount(): Int {
     return   differ.currentList.size
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
       holder.setData(differ.currentList.get(position))
    }

}