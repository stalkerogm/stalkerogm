package com.example.stalkerogm

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.stalkerogm.databinding.ActivityRegistBinding
import com.example.stalkerogm.network.Urls
import org.json.JSONObject

class Regist : AppCompatActivity() {

    lateinit var binding: ActivityRegistBinding

    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(this)

        binding.buttEstAck.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }


        binding.buttRegist2.setOnClickListener {

            val name = binding.name.text.toString()
            val lastname = binding.lastName.text.toString()
            val maillog = binding.mailLog.text.toString()
            val passwordlog = binding.passwordLog.text.toString()
            val passwordlogpovtor = binding.passwordLogPovtor.text.toString()

            val requestJson = JSONObject()
                    .put("firstName", name)
                    .put("lastName", lastname)
                    .put("email", maillog)
                    .put("password", passwordlog)

            val request = JsonObjectRequest(
                    Request.Method.POST,
                    Urls.REGISTER_URL,
                    requestJson,

                    { reponse ->
                        Toast.makeText(this, "token -  ${reponse["token"]}", Toast.LENGTH_SHORT).show()
                    },
                    { error ->
                        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                        error.printStackTrace()
                    }
            )
            requestQueue.add(request)
        }
    }
}