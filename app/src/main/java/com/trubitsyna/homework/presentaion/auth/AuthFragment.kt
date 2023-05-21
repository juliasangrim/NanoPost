package com.trubitsyna.homework.presentaion.auth

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.trubitsyna.homework.R
import com.trubitsyna.homework.data.local.model.auth.CheckNameResponse
import com.trubitsyna.homework.databinding.FragmentAuthBinding
import com.trubitsyna.homework.utils.clearError
import com.trubitsyna.homework.utils.disable
import com.trubitsyna.homework.utils.showError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.fragment_auth) {
    private val binding by viewBinding(FragmentAuthBinding::bind)
    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonContinue.setOnClickListener {
                checkNameListener()
            }

            editTextUsernameInput.doOnTextChanged { _, _, _, _ ->
                textInputLayoutUsernameInput.clearError()
            }

            editTextPasswordInput.doOnTextChanged { _, _, _, _ ->
                textInputLayoutPasswordInput.clearError()
            }
        }

        viewModel.validationResultLiveData.observe(viewLifecycleOwner) { response ->
            with(binding) {
                when (response) {
                    CheckNameResponse.SHORT_NAME -> {
                        textInputLayoutUsernameInput.showError(
                            getString(R.string.error_msg_short_username)
                        )
                    }

                    CheckNameResponse.LONG_NAME -> {
                        textInputLayoutUsernameInput.showError(
                            getString(R.string.error_msg_long_username)
                        )
                    }

                    CheckNameResponse.INVALID_CHAR_NAME -> {
                        textInputLayoutUsernameInput.showError(
                            getString(R.string.error_msg_invalid_char_username)
                        )
                    }

                    else -> {
                        textInputLayoutUsernameInput.clearError()
                        textInputLayoutPasswordInput.visibility = View.VISIBLE
                        textInputLayoutUsernameInput.disable()
                        buttonContinue.setOnClickListener {
                            checkPasswordListener()
                        }
                    }
                }
            }
        }

        viewModel.userLiveData.observe(viewLifecycleOwner) {
            viewModel.saveUserData(it)
            findNavController().navigate(
                AuthFragmentDirections.actionAuthFragmentToNavigation()
            )
        }
    }

    private fun checkNameListener() {
        viewModel.checkUsername(getUsername())
    }

    private fun checkPasswordListener() {
        val username = getUsername()
        val password = getPassword()
        if (viewModel.isPasswordValid(getPassword())) {
            viewModel.auth(username, password)
        } else {
            binding.textInputLayoutPasswordInput.showError(
                getString(R.string.error_msg_short_password)
            )
        }
    }

    private fun getUsername() = binding.editTextUsernameInput.editableText.toString()

    private fun getPassword() = binding.editTextPasswordInput.editableText.toString()
}