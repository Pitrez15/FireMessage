package com.example.firemessage

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firemessage.databinding.ActivitySignInBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask


class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    private val RC_SIGN_IN = 1

    private val signInProviders = listOf(AuthUI.IdpConfig.EmailBuilder().setAllowNewAccounts(true).setRequireName(true).build())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.accountSignIn.setOnClickListener {

            val intent = AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(signInProviders).setLogo(R.drawable.ic_fire_emoji).build()
            startActivityForResult(intent, RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {

                val progressDialog = indeterminateProgressDialog("")

                startActivity(intentFor<MainActivity>().newTask().clearTask())
                progressDialog.dismiss()
            }
        }

    }
}