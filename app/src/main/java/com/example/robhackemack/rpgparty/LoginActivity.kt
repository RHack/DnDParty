package com.example.robhackemack.rpgparty

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEt = findViewById<EditText>(R.id.username_et)
        val playerBtn = findViewById<Button>(R.id.player_btn)
        val dmBtn = findViewById<Button>(R.id.dm_btn)

        playerBtn.setOnClickListener {
            openCharacterList(usernameEt.text.toString())
        }
        dmBtn.setOnClickListener {
            openCharacterCreator(usernameEt.text.toString())
        }
    }

    fun openCharacterCreator(username: String? = null) {
        intent = Intent(this, CharacterCreatorActivity::class.java)
        intent.putExtra("username", username)
        startActivity(intent)
    }
    fun openCharacterList(username: String? = null) {
        intent = Intent(this, CharacterListActivity::class.java)
        intent.putExtra("username", username)
        startActivity(intent)
    }

}
