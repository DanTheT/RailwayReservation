package com.example.railwayreservation.passenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.railwayreservation.R
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class PassengerChgPw : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var currentPw: EditText
    private lateinit var newPw: EditText
    private lateinit var confirmPw: EditText

    private lateinit var btnChgPw: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        setContentView(R.layout.activity_passenger_chg_pw)

        //change psw function
        currentPw = findViewById(R.id.currentPwEditText)
        newPw = findViewById(R.id.newPwEditText)
        confirmPw = findViewById(R.id.confirmPwEditText)
        btnChgPw = findViewById(R.id.btnChgPw)

        btnChgPw.setOnClickListener {
            var current: String = currentPw.text.toString()
            var new: String = newPw.text.toString()
            var confirm: String = confirmPw.text.toString()
            if (TextUtils.isEmpty(current) || TextUtils.isEmpty(new) || TextUtils.isEmpty(confirm)) {
                Toast.makeText(this@PassengerChgPw, "Please fill all the fields", Toast.LENGTH_LONG)
                    .show()
            }else{
                if (new == confirm){
                    val user = auth.currentUser
                    if(user != null && user.email != null) {
                        // Get auth credentials from the user for re-authentication. The example below shows
                        // email and password credentials but there are multiple possible providers,
                        // such as GoogleAuthProvider or FacebookAuthProvider.
                        val credential = EmailAuthProvider
                            .getCredential(user.email!!, current)

                        // Prompt the user to re-provide their sign-in credentials
                        user.reauthenticate(credential)
                            .addOnCompleteListener {
                                if(it.isSuccessful){
                                    Toast.makeText(this, "Re-authenticate Success", Toast.LENGTH_LONG)
                                        .show()

                                    //start change psw
                                    user!!.updatePassword(new)
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                Toast.makeText(this, "Password Changes Successfully", Toast.LENGTH_LONG)
                                                    .show()
                                                auth.signOut()
                                                startActivity(Intent(this, PassengerLogin::class.java))
                                                finish()
                                            }
                                        }

                                }else{
                                    Toast.makeText(this, "Current Password Incorrect", Toast.LENGTH_LONG)
                                        .show()
                                }
                            }
                    }else{
                        startActivity(Intent(this, PassengerLogin::class.java))

                    }
                }else{
                    Toast.makeText(this, "Password not matching !", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }


}

