package com.trubitsyna.homework.presentaion.auth

import android.os.Bundle
import android.view.View
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.textfield.TextInputLayout
import com.trubitsyna.homework.R
import com.trubitsyna.homework.data.local.model.auth.CheckNameResponse
import com.trubitsyna.homework.databinding.FragmentAuthBinding
import com.trubitsyna.homework.utils.Constants
import com.trubitsyna.homework.utils.ErrorMessageConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.fragment_auth) {
    private val binding by viewBinding(FragmentAuthBinding::bind)
    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
            binding.root.updatePadding(
                bottom = imeInsets.bottom
            )
            WindowInsetsCompat.Builder()
                .setInsets(
                    WindowInsetsCompat.Type.ime(),
                    Insets.of(
                        imeInsets.left,
                        0,
                        imeInsets.right,
                        imeInsets.bottom
                    )
                ).build()
        }
        with(binding) {
            buttonContinue.setOnClickListener {
                continueButtonListener()
            }

           editTextUsernameInput.doOnTextChanged { _, _, _, _ ->
                clearTextInputError(textInputLayoutUsernameInput)
            }

            editTextPasswordInput.doOnTextChanged { _, _, _, _ ->
                clearTextInputError(textInputLayoutPasswordInput)
            }
        }

        viewModel.validationResultLiveData.observe(viewLifecycleOwner) { response ->
            with(binding) {
                when (response) {
                    CheckNameResponse.SHORT_NAME -> {
                        showTextInputError(textInputLayoutUsernameInput, response.message)
                    }

                    CheckNameResponse.LONG_NAME -> {
                        showTextInputError(textInputLayoutUsernameInput, response.message)
                    }

                    CheckNameResponse.INVALID_CHAR_NAME -> {
                        showTextInputError(textInputLayoutUsernameInput, response.message)
                    }

                    else -> {
                        clearTextInputError(textInputLayoutUsernameInput)
                        textInputLayoutPasswordInput.visibility = View.VISIBLE
                        disableTextInput(textInputLayoutUsernameInput)
                    }
                }
            }
        }
        viewModel.userLiveData.observe(viewLifecycleOwner) {
            viewModel.saveUserData(it)
            findNavController().navigate(
                AuthFragmentDirections.actionAuthFragmentToFeedFragment()
            )
        }
    }

    private fun continueButtonListener() {
        with(binding) {
            if (isUsernameChecked() && isPasswordValid()) {
                clearTextInputError(textInputLayoutPasswordInput)
                val checkNameResponse = viewModel.validationResultLiveData.value
                if (checkNameResponse == CheckNameResponse.TAKEN) {
                    viewModel.login(getUsername(), getPassword())
                }
                if (checkNameResponse == CheckNameResponse.FREE) {
                    viewModel.register(getUsername(), getPassword())
                }
            }
            if (isUsernameChecked() && !isPasswordValid()) {
                showTextInputError(
                    textInputLayoutPasswordInput,
                    ErrorMessageConstants.SHORT_PASSWORD_MSG
                )
            }
            if (!isUsernameChecked()) {
                viewModel.checkName(getUsername())
            }
        }
    }

    private fun clearTextInputError(textInputLayout: TextInputLayout) {
        with(textInputLayout) {
            error = null
            isErrorEnabled = false
        }
    }

    private fun showTextInputError(textInputLayout: TextInputLayout, msg: String) {
        with(textInputLayout) {
            error = msg
        }
    }

    private fun disableTextInput(textInputLayout: TextInputLayout) {
        textInputLayout.isFocusable = false
        textInputLayout.isEnabled = false
    }

    private fun isUsernameChecked() =
        viewModel.validationResultLiveData.isInitialized

    private fun isPasswordValid() = getPassword().length >= Constants.VALID_LENGTH_PASSWORD

    private fun getUsername() = binding.editTextUsernameInput.editableText.toString()

    private fun getPassword() = binding.editTextPasswordInput.editableText.toString()
}