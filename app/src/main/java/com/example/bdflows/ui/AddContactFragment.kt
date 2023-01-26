package com.example.bdflows.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.bdflows.databinding.ContractDialogBinding

class AddContactFragment :DialogFragment() {

    lateinit var viewBinding : ContractDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         viewBinding = ContractDialogBinding.inflate(inflater)
        return viewBinding.root
    }
}