package com.finniqmachinetest.view.signup

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.finniqmachinetest.R
import com.finniqmachinetest.utility.Validator
import com.finniqmachinetest.view.SuperActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*


class SignupActivity : SuperActivity() {
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var confirmPassword: EditText
    lateinit var emailLayout: TextInputLayout
    lateinit var passwordLayout: TextInputLayout
    lateinit var confirmPasswordLayout: TextInputLayout
    lateinit var btnSignUp: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateToolbarWithoutBackButton(resources.getString(R.string.title_toolbar))
        email = (findViewById<View>(R.id.edEmail) as EditText)
        password = (findViewById<View>(R.id.edPassword) as EditText)
        confirmPassword = (findViewById<View>(R.id.edConfirmPassword) as EditText)
        btnSignUp = (findViewById<View>(R.id.btnSubmit) as TextView)
        emailLayout = (findViewById<View>(R.id.edEmailLayout) as TextInputLayout)
        passwordLayout = (findViewById<View>(R.id.edPasswordLayout) as TextInputLayout)
        confirmPasswordLayout =
            (findViewById<View>(R.id.edConfirmPasswordLayout) as TextInputLayout)
        btnSignUp.setOnClickListener(listener)

        edEmail.setOnFocusChangeListener(emailFocusListener)
        edPassword.setOnFocusChangeListener(passwordFocusListener)
        edConfirmPassword.setOnFocusChangeListener(confirmPasswordFocusListener)
        edConfirmPassword.addTextChangedListener(confirmPasswordTextWatcher)
        checkDeviceTypeAndSetOrientation()
    }

    @SuppressLint("SourceLockedOrientationActivity")
    private fun checkDeviceTypeAndSetOrientation() {
        val screenLayoutSize =
            resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK
        if (screenLayoutSize == Configuration.SCREENLAYOUT_SIZE_SMALL || screenLayoutSize == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    var listener = View.OnClickListener {
        if (Validator().isEmpty(email.text.toString())) {
            emailLayout.error = resources.getString(R.string.error_email)
        } else if (!Validator().isValidEmail(email.text.toString())) {
            emailLayout.error = resources.getString(R.string.error_wrong_email)
        } else if (Validator().isEmpty(password.text.toString())) {
            passwordLayout.error = resources.getString(R.string.error_password)
        } else if (Validator().isEmpty(confirmPassword.text.toString())) {
            confirmPasswordLayout.error = resources.getString(R.string.error_confirmPassword)
        } else if (!Validator().isMatch(password.text.toString(), confirmPassword.text.toString())) {
            confirmPasswordLayout.error = resources.getString(R.string.error_passwordMisMatch)
        } else {
            sucessMessage(resources.getString(R.string.app_name), "Welcome ${email.text}")
        }


    }

    var emailFocusListener = View.OnFocusChangeListener { view, b ->
        if (!b) {
            if (Validator().isEmpty(email.text.toString()))
                emailLayout.error = resources.getString(R.string.error_email)
            else if (!Validator().isValidEmail(email.text.toString())) {
                emailLayout.error = resources.getString(R.string.error_wrong_email)
            }else{
                emailLayout.error = null
            }
        }
    }
    var passwordFocusListener = View.OnFocusChangeListener { view, b ->
        if (!b) {
            if (Validator().isEmpty(password.text.toString()))
                passwordLayout.error = resources.getString(R.string.error_password)
            else
                passwordLayout.error = null

        }
    }
    var confirmPasswordFocusListener = View.OnFocusChangeListener { view, b ->
        if (Validator().isEmpty(confirmPassword.text.toString())) {
            confirmPasswordLayout.error = resources.getString(R.string.error_confirmPassword)
        } else if (!Validator().isMatch(password.text.toString(), confirmPassword.text.toString())) {
            confirmPasswordLayout.error = resources.getString(R.string.error_passwordMisMatch)
        }
        else{
            confirmPasswordLayout.error = null
        }
    }

    var confirmPasswordTextWatcher = object : TextWatcher{
        override fun afterTextChanged(p0: Editable?) {

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if(Validator().isMatch(s.toString(), password.text.toString())){
                confirmPasswordLayout.error = null
                }else{
                confirmPasswordLayout.error =  resources.getString(R.string.error_passwordMisMatch)
            }
        }

    }
}
