package com.example.naamjaap.activities


import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.example.naamjaap.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var storedVerificationId: String
    lateinit var resentToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth= FirebaseAuth.getInstance()
       // btnSignInPhn.setOnClickListener {
       // var intent = Intent(this, MainActivity::class.java)
       // startActivity(intent)
       //  }


      // sent otp code-----------------------------------------

        btn_getotp.setOnClickListener {
            var phoneno : String = et_Phone.text.toString().trim()

            if (!phoneno.isNotEmpty()){
                sendVerificationCode("+91$phoneno")

            }else{
                Toast.makeText(applicationContext, "Please enter phone No.", Toast.LENGTH_LONG).show()
            }

        }

      //sign up code--------------------------------------------

        btn_signup.setOnClickListener {
            var otp : String = et_otp.text.toString().trim()
            if (!otp.isNotEmpty()){

                verifyVerificationCode(otp)

            }else{
                Toast.makeText(applicationContext, "Please enter OTP", Toast.LENGTH_LONG).show()
            }


        }

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

                //This message automatically detect sms: so we signup without user efforts
                val code =credential.smsCode
                if (code!=null){
                    et_otp.setText(code)
                }

            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(applicationContext, "Auth Failed", Toast.LENGTH_LONG).show()

            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
               storedVerificationId = verificationId
                resentToken = token
                phone_layout.visibility = View.GONE
                otp_layout.visibility = View.VISIBLE
            }
        }



        @SuppressWarnings("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())

        } else {
            getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
        }


    }

    private fun sendVerificationCode(phoneNo: String){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNo,         //phone no verify
                60,          //Time duration
                TimeUnit.SECONDS,   //Unit of time out
                this,        //Activity(for call back binding)
                callbacks )          //OnVerificationStateChangeCallback

    }

    private fun verifyVerificationCode(code: String){

        val credential = PhoneAuthProvider.getCredential(storedVerificationId, code)

        signUp(credential)
    }

    private fun signUp(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user: FirebaseUser? = task.result?.user
                        Toast.makeText(applicationContext, "SignUp Successfully", Toast.LENGTH_LONG).show()
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {
                        if (task.exception is FirebaseAuthInvalidCredentialsException){
                            Toast.makeText(applicationContext,"Entered code is incorrect", Toast.LENGTH_LONG).show()

                            et_otp.setText("")
                    }
                }


        }
    }
}
