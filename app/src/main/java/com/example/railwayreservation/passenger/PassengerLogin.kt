package com.example.railwayreservation.passenger

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.railwayreservation.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class PassengerLogin : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var btnGoToSignUp: Button
    private lateinit var btnLogin: Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btnForgotPw: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_login)

        btnGoToSignUp = findViewById(R.id.btnGoToSignUp)
        btnLogin = findViewById(R.id.btnLogin)
        email = findViewById(R.id.editTextTextEmailAddress)
        password = findViewById(R.id.editTextTextPassword)
        btnForgotPw = findViewById(R.id.btnForgotPw)


        btnGoToSignUp.setOnClickListener {
            startActivity(Intent(this,PassengerSignup::class.java))
            finish()
        }

        btnLogin.setOnClickListener {

            doLogin()
        }

        auth = FirebaseAuth.getInstance()



        btnForgotPw.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password: Please enter your email.")
            val view = layoutInflater.inflate(R.layout.forgotpw_dialog, null)

            val email :EditText = view.findViewById<EditText>(R.id.et_email)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->
                forgotPassword(email)
            })

            builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ ->  })
            builder.show()
        }
    }

    private fun forgotPassword(email: EditText) {
        if (TextUtils.isEmpty(email.text)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG).show()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            return
        }

        auth.sendPasswordResetEmail(email.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Reset link sent to your email", Toast.LENGTH_SHORT).show()
                    finish()
                }
                else {
                    Toast.makeText(this, "Unable to send reset mail", Toast.LENGTH_LONG)
                        .show()
                }
            }
    }

    private fun doLogin() {

        if (email.text.toString().isEmpty()) {
            email.error = "Please enter your email."
            email.requestFocus()
            return

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            email.error = "Please enter a valid email."
            email.requestFocus()
            return

        } else if (password.text.toString().isEmpty()) {
            password.error = "Please enter your password."
            password.requestFocus()
            return

        }

        auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                    Toast.makeText(
                        baseContext, "Login successful.",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this, PassengerHome::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        baseContext, "Login failed. Invalid email or password.",
                        Toast.LENGTH_SHORT
                    ).show()
                    email.setText("")
                    password.setText("")
                    updateUI(null)
                }
            }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {

        if (currentUser != null) {
            Toast.makeText(this, "Welcome to Raiden Railway!", Toast.LENGTH_SHORT).show()

            if (currentUser.isEmailVerified) {
                startActivity(Intent(this, PassengerHome::class.java))
                finish()}
        } else {
//            Toast.makeText(this, "Login unsuccessful. Please verify your email address.", Toast.LENGTH_SHORT).show()
            email.setText("")
            password.setText("")

        }

    }

}



