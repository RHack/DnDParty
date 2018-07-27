package com.example.robhackemack.dndparty

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import java.io.File
import java.io.FileOutputStream
import java.io.Serializable

class CharacterCreatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_creator)

        val username : String? = intent.getStringExtra("username")

        val characterParcel : Parcel? = null
        val nameEt = findViewById<EditText>(R.id.name_et)
        val playerEt = findViewById<EditText>(R.id.player_et)
        val classEt = findViewById<EditText>(R.id.class_et)
        val raceEt = findViewById<EditText>(R.id.race_et)
        val speedEt = findViewById<EditText>(R.id.speed_et)
        val alignmentSp = findViewById<Spinner>(R.id.alignment_sp)
        val alignments = resources.getStringArray(R.array.alignment_array)

        val createBtn = findViewById<Button>(R.id.create_character_btn)

        playerEt.setText(username, TextView.BufferType.EDITABLE)

        val alignmentAdapter = ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, alignments )
        alignmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        alignmentSp.setAdapter(alignmentAdapter)

        alignmentSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(alignmentAdapter: AdapterView<*>, spinner: View,
                                        alignment: Int, id: Long) {
                val selectedAlignment = alignmentAdapter.getItemAtPosition(alignment).toString()
            }

            override fun onNothingSelected(alignmentAdapter: AdapterView<*>) {}
        }
        
        createBtn.setOnClickListener {
            characterParcel?.writeString(nameEt.text.toString())
            val newCharacter = CharacterSheetData(nameEt.text.toString())
            createCharacterFile(newCharacter)
            intent = Intent(this, CharacterListActivity::class.java)
            startActivity(intent)
        }
    }

    fun createCharacterFile(characterInfo: CharacterSheetData) {
        val characterDirectory = getDir("Characters", 0)
        val characterName = characterInfo.name
        val characterFile = File(characterDirectory, characterInfo.name)
        var characterContent = ""
        characterContent += "name:$characterName"
        var characterStream = FileOutputStream(characterFile, true)
        characterStream.write(characterContent.toByteArray())
    }

}