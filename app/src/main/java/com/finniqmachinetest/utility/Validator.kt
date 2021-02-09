package com.finniqmachinetest.utility

import android.text.InputFilter
import android.util.Patterns
import java.util.regex.Pattern

class Validator  {

    fun Validator() {}

    fun isValidEmail(target: CharSequence?): Boolean { // return false in fail case
        return if (target == null) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }

    fun isValidPhoneNumber(phone: CharSequence): Boolean { // return false in fail case
        return if (phone.length != 10) {
            false
        } else {
            val regex = "(0/91)?[7-9][0-9]{9}"
            val r = Pattern.compile(regex)
            val m = r.matcher(phone)
            m.find()
            //return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }
    fun isMatch(password : CharSequence?,confirmPassword : CharSequence?): Boolean { // retrn fail in fail case
        if (password == null || confirmPassword==null) return true
        return if (password.equals(confirmPassword)) {
            true
        } else {
            false
        }
    }
    fun isEmpty(target: CharSequence?): Boolean { // retrn true in fail case
        if (target == null) return true
        return if (target.length == 0) {
            true
        } else {
            false
        }
    }




    fun applyFilter(blockCharSet: String): InputFilter? {
        return InputFilter { source, start, end, dest, dstart, dend ->
            if (source != null && blockCharSet.contains("" + source)) {
                ""
            } else null
        }
    }
}