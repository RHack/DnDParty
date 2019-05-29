package com.example.robhackemack.dndparty

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class CharacterDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)
        var nameEt = findViewById<TextView>(R.id.name_et)

        var characterSheet : CharacterSheetData = intent.getSerializableExtra("CharacterSheetData") as CharacterSheetData

        nameEt.text = characterSheet.name
    }
}