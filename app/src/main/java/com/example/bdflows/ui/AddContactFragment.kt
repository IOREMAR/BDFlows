package com.example.bdflows.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.bdflows.bd.ContactsEntity
import com.example.bdflows.databinding.ContractDialogBinding
import com.example.bdflows.viewmodel.DatabaseViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddContactFragment :DialogFragment() {

    @Inject
    lateinit var entity : ContactsEntity

    private val viewModel: DatabaseViewModel by viewModels()

    lateinit var viewBinding : ContractDialogBinding

    private var contactId = 0
    private var name = ""
    private var phone = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         viewBinding = ContractDialogBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            imgClose.setOnClickListener {
                dismiss()
            }

            btnSave.setOnClickListener {
                name = edtName.text.toString()
                phone = edtPhone.text.toString()

                if (name.isEmpty() || phone.isEmpty()) {
                    Snackbar.make(it, "Name and Phone cannot be Empty!", Snackbar.LENGTH_SHORT)
                        .show()
                  }

                entity.id = contactId
                entity.name = name
                entity.phone = phone

                viewModel.saveContact(entity)

                edtName.setText("")
                edtPhone.setText("")

                dismiss()

            }

        }
    }
}