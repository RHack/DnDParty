package com.example.robhackemack.dndparty

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
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

        val nameEt = findViewById<EditText>(R.id.name_et)
        val playerEt = findViewById<EditText>(R.id.player_et)
        val classEt = findViewById<EditText>(R.id.class_et)
        val raceEt = findViewById<EditText>(R.id.race_et)
        val speedEt = findViewById<EditText>(R.id.speed_et)

        val alignmentSp = findViewById<Spinner>(R.id.alignment_sp)
        val alignments = resources.getStringArray(R.array.alignment_array)
        val proficiencyBonusEt = findViewById<EditText>(R.id.proficiency_bonus_et)

        val strengthTv = findViewById<TextView>(R.id.strength_modifier_tv)
        val strengthEt = findViewById<EditText>(R.id.strength_et)
        val athleticsEt = findViewById<EditText>(R.id.athletics_modifier_et)
        val athleticsCb = findViewById<CheckBox>(R.id.athletics_trained_cb)

        val dexterityTv = findViewById<TextView>(R.id.dexterity_modifier_tv)
        val dexterityEt = findViewById<EditText>(R.id.dexterity_et)
        val constitutionTv = findViewById<TextView>(R.id.constitution_modifier_tv)
        val constitutionEt = findViewById<EditText>(R.id.constitution_et)
        val intelligenceTv = findViewById<TextView>(R.id.intelligence_modifier_tv)
        val intelligenceEt = findViewById<EditText>(R.id.intelligence_et)
        val wisdomTv = findViewById<TextView>(R.id.wisdom_modifier_tv)
        val wisdomEt = findViewById<EditText>(R.id.wisdom_et)
        val charismaTv = findViewById<TextView>(R.id.charisma_modifier_tv)
        val charismaEt = findViewById<EditText>(R.id.charisma_et)

        val maxHitPointsEt = findViewById<EditText>(R.id.max_hit_points_et)
        val maxHitDiceEt = findViewById<EditText>(R.id.max_hit_dice_et)
        val hitDieEt = findViewById<EditText>(R.id.hit_die_et)
        val armorClassEt = findViewById<EditText>(R.id.armor_class_et)

        val createBtn = findViewById<Button>(R.id.create_character_btn)

        playerEt.setText(username, TextView.BufferType.EDITABLE)

        val alignmentAdapter = ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, alignments )
        var selectedAlignment: String? = null
        alignmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        alignmentSp.setAdapter(alignmentAdapter)

        alignmentSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(alignmentAdapter: AdapterView<*>, spinner: View,
                                        alignment: Int, id: Long) {
                selectedAlignment = alignmentAdapter.getItemAtPosition(alignment).toString()
            }

            override fun onNothingSelected(alignmentAdapter: AdapterView<*>) {}
        }

        fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
            this.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(editable: Editable?) {
                    afterTextChanged.invoke(editable.toString())
                }
            })
        }

        fun calculateAbilityScoreModifier(abilityScore: Int) : Int {
            return (abilityScore - 10) / 2
        }

        fun updateSkillModifier(abilityScoreModifierNumber: Int, skillModifier: EditText,
                                trainedBox: CheckBox) {
            var skillModifierNumber = abilityScoreModifierNumber

            if(trainedBox.isChecked()) {
                skillModifierNumber += 2
            }
            if(skillModifierNumber < 0) {
                skillModifier.setText("$skillModifierNumber")
            } else {
                skillModifier.setText("+$skillModifierNumber")
            }
        }

        fun updateAbilityScoreModifier(abilityScoreModifierNumber: Int, abilityScoreModifier: TextView) {
            if(abilityScoreModifierNumber < 0) {
                abilityScoreModifier.text = "( $abilityScoreModifierNumber )"
            } else {
                abilityScoreModifier.text = "( +$abilityScoreModifierNumber )"
            }
        }

        fun updateModifier(abilityScore: EditText, abilityScoreModifierTv: TextView) {
            val abilityScoreModifierNumber : Int

            if (abilityScore.text.isEmpty()) {
                abilityScoreModifierNumber = 0
            } else {
                abilityScoreModifierNumber =
                        calculateAbilityScoreModifier(abilityScore.text.toString().toInt())
            }

            when(abilityScore) {
                strengthEt -> {
                    updateAbilityScoreModifier(abilityScoreModifierNumber, strengthTv)
                    updateSkillModifier(abilityScoreModifierNumber, athleticsEt, athleticsCb)
                }

            }
        }

        strengthEt.afterTextChanged{updateModifier(strengthEt, strengthTv)}
        dexterityEt.afterTextChanged{updateModifier(dexterityEt, dexterityTv)}
        constitutionEt.afterTextChanged{updateModifier(constitutionEt, constitutionTv)}
        intelligenceEt.afterTextChanged{updateModifier(intelligenceEt, intelligenceTv)}
        wisdomEt.afterTextChanged{updateModifier(wisdomEt, wisdomTv)}
        charismaEt.afterTextChanged{updateModifier(charismaEt, charismaTv)}

        fun numberFormatCheck(editText: EditText): Int? {
            if (editText.text.isEmpty()) {
                return null
            } else {
                return editText.text.toString().toInt()
            }
        }

        createBtn.setOnClickListener {
            val characterName = nameEt.text.toString()
            val characterPlayer = playerEt.text.toString()
            val characterClass = classEt.text.toString()
            val characterRace = raceEt.text.toString()
            val characterSpeed = numberFormatCheck(speedEt)
            val characterProficiencyBonus = numberFormatCheck(proficiencyBonusEt)
            val characterStrength = numberFormatCheck(strengthEt)
            val characterDexterity = numberFormatCheck(dexterityEt)
            val characterConstitution = numberFormatCheck(constitutionEt)
            val characterIntelligence = numberFormatCheck(intelligenceEt)
            val characterWisdom = numberFormatCheck(wisdomEt)
            val characterCharisma = numberFormatCheck(charismaEt)
            val characterMaxHitPoints = numberFormatCheck(maxHitPointsEt)
            val characterMaxHitDice = numberFormatCheck(maxHitDiceEt)
            val characterHitDie = numberFormatCheck(hitDieEt)
            val characterArmorClass = numberFormatCheck(armorClassEt)

            val newCharacter = CharacterSheetData(characterName, characterPlayer, characterClass,
                    characterRace, characterSpeed, selectedAlignment, characterProficiencyBonus,
                    characterStrength, characterDexterity, characterConstitution,
                    characterIntelligence, characterWisdom, characterCharisma,
                    characterMaxHitPoints, characterMaxHitDice, characterHitDie,
                    characterArmorClass)
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