package com.example.tp1_poudroux

import android.util.Log
import android.Manifest
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.View.OnClickListener
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity(), OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 10)
        val button = findViewById<Button>(R.id.bt_send)
        button.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val num = findViewById<EditText>(R.id.et_phone).text.toString()
        val msg = findViewById<EditText>(R.id.et_message).text.toString()

        if (!num.isNullOrEmpty() && !msg.isNullOrEmpty()) {
            val arrayofNum = num.split(";")
            for (numero in arrayofNum) {
                if (numero.length >= 4 && numero.toIntOrNull() != null) {
                    sendMessage(numero, msg)
                } else {
                    Log.d("TAG", "Numero non numérique ou moins de quatre chiffres")
                }
            }
        }
        else {
            throw Exception("Numero vide")
        }
    }

    fun sendMessage(num: String, msg: String)
    {
        val smsmanager = SmsManager.getDefault() as SmsManager
        smsmanager.sendTextMessage(num, null, msg, null, null)

    }

}