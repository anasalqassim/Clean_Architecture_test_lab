package com.anas.cleanarchitecturetestlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.anas.cleanarchitecturetestlab.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        Firebase.auth.signInWithEmailAndPassword("test@test.com","123456789").addOnSuccessListener {
            Log.d(TAG, "onCreate: sucess")
        }.addOnFailureListener {
            Log.d(TAG, "onCreate: $it")
        }
    }
}