package com.example.stalkerogm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.stalkerogm.databinding.ActivitySignInBinding
import com.example.stalkerogm.network.Urls
import org.json.JSONObject

class SignInActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignInBinding


    lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(this)


        binding.buttRegist.setOnClickListener {
            val intent = Intent(this,Regist::class.java)
            startActivity(intent)
        }


        binding.buttVhod.setOnClickListener{
            val maillog = binding.mailLog.text.toString()
            val passwordlog = binding.passwordLog.text.toString()

            val request = JsonObjectRequest(
                Request.Method.POST,

                Urls.LOGIN_URL,

                JSONObject()
                    .put("email", maillog)
                    .put("password",passwordlog),

            Response.Listener<JSONObject> { response ->
                Toast.makeText(this,"все ок",Toast.LENGTH_SHORT).show()


                val intent = Intent(this,Meny::class.java)
                startActivity(intent)


            },
            Response.ErrorListener {
                Toast.makeText(this,"не ок",Toast.LENGTH_SHORT).show()

            }

            )
            requestQueue.add(request)







        }


    }
}