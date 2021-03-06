package com.example.robhackemack.dndparty

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_character_list.*

class CharacterListActivity : AppCompatActivity() {
    private val characterSheets = CharacterSheetDataList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        val username:String? = intent.getStringExtra("username")

        getCharacters()
        addCharacterSheets()

        character_list_rv.layoutManager = LinearLayoutManager(this)
        character_list_rv.adapter = CharacterListAdapter(this, username, characterSheets) {
            Toast.makeText(this, "${it.name} Clicked", Toast.LENGTH_LONG).show()
//            intent = Intent(this, CharacterDetailActivity::class.java)
//            intent.putExtra("CharacterSheetData", it)
//            startActivity(intent)
        }
    }

    private fun addCharacterSheets() {
        characterSheets.add("Dellett")
        characterSheets.add("Patched")
        characterSheets.add("Athena")
        characterSheets.add("Maokai")
        characterSheets.add("Bruce Hefe")
        characterSheets.add("Nevos")
        characterSheets.add("Penumbra")
        characterSheets.add("Drudge")
        characterSheets.add("Venin")
        characterSheets.add("Ander the Mild")
        characterSheets.add("Ish F'mael")
        characterSheets.add("Add Character")
    }

    private fun getCharacters() {
        val characterDirectory = getDir("Characters", 0)
        for (file in characterDirectory.listFiles()) {
            characterSheets.add(file.name)
        }
    }
}