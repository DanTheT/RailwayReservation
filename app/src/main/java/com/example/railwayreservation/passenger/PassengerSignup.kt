package com.example.railwayreservation.passenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.railwayreservation.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.DateFormat
import java.util.*

class PassengerSignup : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var btnSignUp: Button
    private lateinit var name: EditText
    private lateinit var phoneNo: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btnGoToLogin: Button

    var db: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_signup)

        btnSignUp = findViewById(R.id.btnSignUp)
        name = findViewById(R.id.editTextTextName)
        phoneNo = findViewById(R.id.editTextTextPhoneNo)
        email = findViewById(R.id.editTextTextEmailAddress)
        password = findViewById(R.id.editTextTextPassword)
        btnGoToLogin = findViewById(R.id.btnGoToLogin)



        btnGoToLogin.setOnClickListener {
            startActivity(Intent(this, PassengerLogin::class.java))
            finish()
        }

        auth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance()
        myRef = db?.reference?.child("User")
        signUp()
    }

    private fun signUp() {
        btnSignUp.setOnClickListener {


            if (TextUtils.isEmpty(name.text.toString())) {
                name.error = "Please enter your name."
                name.requestFocus()
                return@setOnClickListener

            } else if (TextUtils.isEmpty(phoneNo.text.toString())) {
                phoneNo.error = "Please enter your phone number."
                phoneNo.requestFocus()
                return@setOnClickListener

            } else if (TextUtils.isEmpty(email.text.toString())) {
                email.error = "Please enter your email."
                email.requestFocus()
                return@setOnClickListener

            } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
                email.error = "Please enter a valid email."
                email.requestFocus()
                return@setOnClickListener

            } else if (TextUtils.isEmpty(password.text.toString())) {
                password.error = "Please enter your password."
                password.requestFocus()
                return@setOnClickListener

            }

            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val currentUser = auth.currentUser
                        val currentUserDb = myRef?.child((currentUser?.uid!!))
                        val currentDateTimeString = DateFormat.getDateTimeInstance().format(Date())


                        currentUserDb?.child("name")?.setValue((name.text.toString().trim()))
                        currentUserDb?.child("email")?.setValue((email.text.toString().trim()))
                        currentUserDb?.child("phone")?.setValue((phoneNo.text.toString().trim()))
                        currentUserDb?.child("status")?.setValue("Active")
                        currentUserDb?.child("since")?.setValue(currentDateTimeString)
                        currentUserDb?.child("points")?.setValue("100")





                        Toast.makeText(
                            baseContext,
                            "Sign Up Successful. Verification email has been sent. Proceed to Login!",
                            Toast.LENGTH_SHORT
                        ).show()

                        val user = auth.currentUser
                        user!!.sendEmailVerification()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    startActivity(Intent(this, PassengerLogin::class.java))
                                    finish()
                                }
                            }


                    } else {
                        Toast.makeText(
                            baseContext,
                            "Sign Up failed. The email you registered has already been used. Please use another email to sign up.",
                            Toast.LENGTH_SHORT
                        ).show()
                        //User already exist or network failure.

                    }
                }
        }
    }
}

