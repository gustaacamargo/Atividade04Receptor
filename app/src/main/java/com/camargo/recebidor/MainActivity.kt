package com.camargo.recebidor

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.action == Intent.ACTION_SEND && intent.type != null) {
            if ("text/plain" == intent.type) {
                val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
                if (sharedText != null) {
                    message_received.text = sharedText
                    Toast.makeText(this, sharedText, Toast.LENGTH_SHORT).show()
                }
            }
            else if (intent.type?.startsWith("image/") == true) {
                (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let {
                    // 4
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                    message_received.text = it.toString()

                    imageView.setImageURI(it)
                }

            }
        }
    }
}
