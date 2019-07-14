package com.example.robhackemack.dndparty

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_character_creator.*
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable

class CharacterCreatorActivity : AppCompatActivity(), Serializable {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_creator)

        val username: String? = intent.getStringExtra("username")

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
        val strengthSaveEt = findViewById<EditText>(R.id.strength_save_modifier_et)
        val strengthSaveCb = findViewById<CheckBox>(R.id.strength_save_trained_cb)
        val athleticsEt = findViewById<EditText>(R.id.athletics_modifier_et)
        val athleticsCb = findViewById<CheckBox>(R.id.athletics_trained_cb)

        val dexterityTv = findViewById<TextView>(R.id.dexterity_modifier_tv)
        val dexterityEt = findViewById<EditText>(R.id.dexterity_et)
        val dexteritySaveEt = findViewById<EditText>(R.id.dexterity_save_modifier_et)
        val dexteritySaveCb = findViewById<CheckBox>(R.id.dexterity_save_trained_cb)
        val acrobaticsEt = findViewById<EditText>(R.id.acrobatics_modifier_et)
        val acrobaticsCb = findViewById<CheckBox>(R.id.acrobatics_trained_cb)
        val sleightOfHandEt = findViewById<EditText>(R.id.sleight_of_hand_modifier_et)
        val sleightOfHandCb = findViewById<CheckBox>(R.id.sleight_of_hand_trained_cb)
        val stealthEt = findViewById<EditText>(R.id.stealth_modifier_et)
        val stealthCb = findViewById<CheckBox>(R.id.stealth_trained_cb)

        val constitutionTv = findViewById<TextView>(R.id.constitution_modifier_tv)
        val constitutionEt = findViewById<EditText>(R.id.constitution_et)
        val constitutionSaveEt = findViewById<EditText>(R.id.constitution_save_modifier_et)
        val constitutionSaveCb = findViewById<CheckBox>(R.id.constitution_save_trained_cb)

        val intelligenceTv = findViewById<TextView>(R.id.intelligence_modifier_tv)
        val intelligenceEt = findViewById<EditText>(R.id.intelligence_et)
        val intelligenceSaveEt = findViewById<EditText>(R.id.intelligence_save_modifier_et)
        val intelligenceSaveCb = findViewById<CheckBox>(R.id.intelligence_save_trained_cb)
        val arcanaEt = findViewById<EditText>(R.id.arcana_modifier_et)
        val arcanaCb = findViewById<CheckBox>(R.id.arcana_trained_cb)
        val historyEt = findViewById<EditText>(R.id.history_modifier_et)
        val historyCb = findViewById<CheckBox>(R.id.history_trained_cb)
        val investigationEt = findViewById<EditText>(R.id.investigation_modifier_et)
        val investigationCb = findViewById<CheckBox>(R.id.investigation_trained_cb)
        val natureEt = findViewById<EditText>(R.id.nature_modifier_et)
        val natureCb = findViewById<CheckBox>(R.id.nature_trained_cb)
        val religionEt = findViewById<EditText>(R.id.religion_modifier_et)
        val religionCb = findViewById<CheckBox>(R.id.religion_trained_cb)

        val wisdomTv = findViewById<TextView>(R.id.wisdom_modifier_tv)
        val wisdomEt = findViewById<EditText>(R.id.wisdom_et)
        val wisdomSaveEt = findViewById<EditText>(R.id.wisdom_save_modifier_et)
        val wisdomSaveCb = findViewById<CheckBox>(R.id.wisdom_save_trained_cb)
        val animalHandlingEt = findViewById<EditText>(R.id.animal_handling_modifier_et)
        val animalHandlingCb = findViewById<CheckBox>(R.id.animal_handling_trained_cb)
        val insightEt = findViewById<EditText>(R.id.insight_modifier_et)
        val insightCb = findViewById<CheckBox>(R.id.insight_trained_cb)
        val medicineEt = findViewById<EditText>(R.id.medicine_modifier_et)
        val medicineCb = findViewById<CheckBox>(R.id.medicine_trained_cb)
        val perceptionEt = findViewById<EditText>(R.id.perception_modifier_et)
        val perceptionCb = findViewById<CheckBox>(R.id.perception_trained_cb)
        val survivalEt = findViewById<EditText>(R.id.survival_modifier_et)
        val survivalCb = findViewById<CheckBox>(R.id.survival_trained_cb)

        val charismaTv = findViewById<TextView>(R.id.charisma_modifier_tv)
        val charismaEt = findViewById<EditText>(R.id.charisma_et)
        val charismaSaveEt = findViewById<EditText>(R.id.charisma_save_modifier_et)
        val charismaSaveCb = findViewById<CheckBox>(R.id.charisma_save_trained_cb)
        val deceptionEt = findViewById<EditText>(R.id.deception_modifier_et)
        val deceptionCb = findViewById<CheckBox>(R.id.deception_trained_cb)
        val intimidationEt = findViewById<EditText>(R.id.intimidation_modifier_et)
        val intimidationCb = findViewById<CheckBox>(R.id.intimidation_trained_cb)
        val performanceEt = findViewById<EditText>(R.id.performance_modifier_et)
        val performanceCb = findViewById<CheckBox>(R.id.performance_trained_cb)
        val persuasionEt = findViewById<EditText>(R.id.persuasion_modifier_et)
        val persuasionCb = findViewById<CheckBox>(R.id.persuasion_trained_cb)

        var trainedSkills = MutableList<TextView?>(0) { null }
        var trainedSaves = MutableList<TextView?>(0) { null }

        val maxHitPointsEt = findViewById<EditText>(R.id.max_hit_points_et)
        val maxHitDiceEt = findViewById<EditText>(R.id.max_hit_dice_et)
        val hitDieEt = findViewById<EditText>(R.id.hit_die_et)
        val armorClassEt = findViewById<EditText>(R.id.armor_class_et)

        val createBtn = findViewById<Button>(R.id.create_character_btn)

        playerEt.setText(username, TextView.BufferType.EDITABLE)

        var proficiencyBonus = 0
        var strengthModifier = 0
        var dexterityModifier = 0
        var constitutionModifier = 0
        var intelligenceModifier = 0
        var wisdomModifier = 0
        var charismaModifier = 0

        val alignmentAdapter = ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, alignments)
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

        fun signModifier(proficiencyModifier: Int): String {
            return if (proficiencyModifier < 0) {
                "$proficiencyModifier"
            } else {
                "+$proficiencyModifier"
            }
        }

        fun updateProficiencyBonus() {
            proficiencyBonus = if (proficiencyBonusEt.text.isEmpty()) {
                0
            } else {
                proficiencyBonusEt.text.toString().toInt()
            }
        }

        fun trainSkill(skill: TextView?) {
            if (!trainedSkills.contains(skill)) {
                trainedSkills.add(skill)
            } else {
                Log.e("Skill Train Error", "Skill already trained")
            }
        }

        fun untrainSkill(skill: TextView?) {
            if (trainedSkills.contains(skill)) {
                trainedSkills.add(skill)
            } else {
                Log.e("Skill Train Error", "Skill not trained yet")
            }
        }

        fun trainSave(save: TextView?) {
            if (!trainedSaves.contains(save)) {
                trainedSaves.add(save)
            } else {
                Log.e("Save Train Error", "Save already trained")
            }
        }

        fun untrainSave(save: TextView?) {
            if (trainedSaves.contains(save)) {
                trainedSaves.add(save)
            } else {
                Log.e("Save Train Error", "Save not trained yet")
            }
        }

        fun checkedBox(proficiencyModifier: EditText, proficient: Boolean, skillOrSave: TextView? = null, savingThrow: Boolean? = null) {
            var proficiencyModifierNew = proficiencyModifier.text.toString().toInt()
            if (proficient) {
                proficiencyModifierNew += proficiencyBonus
                if (savingThrow != null && savingThrow) {
                    trainSave(skillOrSave)
                } else {
                    trainSkill(skillOrSave)
                }
            } else {
                proficiencyModifierNew -= proficiencyBonus
                if (savingThrow != null && savingThrow) {
                    untrainSave(skillOrSave)
                } else {
                    untrainSkill(skillOrSave)
                }
            }
            proficiencyModifier.setText(signModifier(proficiencyModifierNew))
        }

        fun calculateAbilityScoreModifier(abilityScore: Int): Int {
            return (abilityScore - 10) / 2
        }

        fun updateSkillOrSaveModifier(abilityScoreModifierNumber: Int, skillModifier: EditText,
                                      trainedBox: CheckBox) {
            var skillModifierNumber = abilityScoreModifierNumber

            if (trainedBox.isChecked) {
                skillModifierNumber += proficiencyBonus
            }
            skillModifier.setText(signModifier(skillModifierNumber))
        }

        fun updateAbilityScoreModifier(abilityScoreModifierNumber: Int, abilityScoreModifier: TextView) {
            if (abilityScoreModifierNumber < 0) {
                abilityScoreModifier.text = "( $abilityScoreModifierNumber )"
            } else {
                abilityScoreModifier.text = "( +$abilityScoreModifierNumber )"
            }
        }

        fun updateModifier(abilityScore: EditText) {
            val abilityScoreModifierNumber: Int = if (abilityScore.text.isEmpty() || abilityScore == proficiencyBonusEt) {
                0
            } else {
                calculateAbilityScoreModifier(abilityScore.text.toString().toInt())
            }

            when (abilityScore) {
                strengthEt -> {
                    strengthModifier = abilityScoreModifierNumber
                    updateAbilityScoreModifier(abilityScoreModifierNumber, strengthTv)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, athleticsEt, athleticsCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, strengthSaveEt, strengthSaveCb)
                }
                dexterityEt -> {
                    dexterityModifier = abilityScoreModifierNumber
                    updateAbilityScoreModifier(abilityScoreModifierNumber, dexterityTv)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, acrobaticsEt, acrobaticsCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, sleightOfHandEt, sleightOfHandCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, stealthEt, stealthCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, dexteritySaveEt, dexteritySaveCb)
                }
                constitutionEt -> {
                    constitutionModifier = abilityScoreModifierNumber
                    updateAbilityScoreModifier(abilityScoreModifierNumber, constitutionTv)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, constitutionSaveEt, constitutionSaveCb)
                }
                intelligenceEt -> {
                    intelligenceModifier = abilityScoreModifierNumber
                    updateAbilityScoreModifier(abilityScoreModifierNumber, intelligenceTv)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, arcanaEt, arcanaCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, historyEt, historyCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, investigationEt, investigationCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, natureEt, natureCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, religionEt, religionCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, intelligenceSaveEt, intelligenceSaveCb)
                }
                wisdomEt -> {
                    wisdomModifier = abilityScoreModifierNumber
                    updateAbilityScoreModifier(abilityScoreModifierNumber, wisdomTv)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, animalHandlingEt, animalHandlingCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, insightEt, insightCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, medicineEt, medicineCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, perceptionEt, perceptionCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, survivalEt, survivalCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, wisdomSaveEt, wisdomSaveCb)
                }
                charismaEt -> {
                    charismaModifier = abilityScoreModifierNumber
                    updateAbilityScoreModifier(abilityScoreModifierNumber, charismaTv)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, deceptionEt, deceptionCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, intimidationEt, intimidationCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, performanceEt, performanceCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, persuasionEt, persuasionCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, charismaSaveEt, charismaSaveCb)
                }
                proficiencyBonusEt -> {
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + strengthModifier, athleticsEt, athleticsCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + strengthModifier, strengthSaveEt, strengthSaveCb)

                    updateSkillOrSaveModifier(abilityScoreModifierNumber + dexterityModifier, acrobaticsEt, acrobaticsCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + dexterityModifier, sleightOfHandEt, sleightOfHandCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + dexterityModifier, stealthEt, stealthCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + dexterityModifier, dexteritySaveEt, dexteritySaveCb)

                    updateSkillOrSaveModifier(abilityScoreModifierNumber + constitutionModifier, constitutionSaveEt, constitutionSaveCb)

                    updateSkillOrSaveModifier(abilityScoreModifierNumber + intelligenceModifier, arcanaEt, arcanaCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + intelligenceModifier, historyEt, historyCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + intelligenceModifier, investigationEt, investigationCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + intelligenceModifier, natureEt, natureCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + intelligenceModifier, religionEt, religionCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + intelligenceModifier, intelligenceSaveEt, intelligenceSaveCb)

                    updateSkillOrSaveModifier(abilityScoreModifierNumber + wisdomModifier, animalHandlingEt, animalHandlingCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + wisdomModifier, insightEt, insightCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + wisdomModifier, medicineEt, medicineCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + wisdomModifier, perceptionEt, perceptionCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + wisdomModifier, survivalEt, survivalCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + wisdomModifier, wisdomSaveEt, wisdomSaveCb)

                    updateSkillOrSaveModifier(abilityScoreModifierNumber + charismaModifier, deceptionEt, deceptionCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + charismaModifier, intimidationEt, intimidationCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + charismaModifier, performanceEt, performanceCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + charismaModifier, persuasionEt, persuasionCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber + charismaModifier, charismaSaveEt, charismaSaveCb)
                }
            }
        }

        proficiencyBonusEt.afterTextChanged {
            updateProficiencyBonus()
            updateModifier(proficiencyBonusEt)
        }

        strengthEt.afterTextChanged { updateModifier(strengthEt) }
        athleticsCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(athleticsEt, isChecked, athletics_tv) }
        strengthSaveCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(strengthSaveEt, isChecked, strength_save_tv, true) }

        dexterityEt.afterTextChanged { updateModifier(dexterityEt) }
        acrobaticsCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(acrobaticsEt, isChecked, acrobatics_tv) }
        sleightOfHandCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(sleightOfHandEt, isChecked, sleight_of_hand_tv) }
        stealthCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(stealthEt, isChecked, stealth_tv) }
        dexteritySaveCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(dexteritySaveEt, isChecked, dexterity_save_tv, true) }

        constitutionEt.afterTextChanged { updateModifier(constitutionEt) }
        constitutionSaveCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(constitutionSaveEt, isChecked, constitution_save_tv, true) }

        intelligenceEt.afterTextChanged { updateModifier(intelligenceEt) }
        arcanaCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(arcanaEt, isChecked, arcana_tv) }
        historyCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(historyEt, isChecked, history_tv) }
        investigationCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(investigationEt, isChecked, investigation_tv) }
        natureCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(natureEt, isChecked, nature_tv) }
        religionCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(religionEt, isChecked, religion_tv) }
        intelligenceSaveCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(intelligenceSaveEt, isChecked, intelligence_save_tv, true) }

        wisdomEt.afterTextChanged { updateModifier(wisdomEt) }
        animalHandlingCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(animalHandlingEt, isChecked, animal_handling_tv) }
        insightCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(insightEt, isChecked, insight_tv) }
        medicineCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(medicineEt, isChecked, medicine_tv) }
        perceptionCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(perceptionEt, isChecked, perception_tv) }
        survivalCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(survivalEt, isChecked, survival_tv) }
        wisdomSaveCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(wisdomSaveEt, isChecked, wisdom_save_tv, true) }

        charismaEt.afterTextChanged { updateModifier(charismaEt) }
        deceptionCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(deceptionEt, isChecked, deception_tv) }
        intimidationCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(intimidationEt, isChecked, intimidation_tv) }
        performanceCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(performanceEt, isChecked, performance_tv) }
        persuasionCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(persuasionEt, isChecked, persuasion_tv) }
        charismaSaveCb.setOnCheckedChangeListener { buttonView, isChecked -> checkedBox(charismaSaveEt, isChecked, charisma_save_tv, true) }

        fun numberFormatCheck(editText: EditText): Int? {
            return if (editText.text.isEmpty()) {
                null
            } else {
                editText.text.toString().toInt()
            }
        }

        fun skillOrSaveListCheck(skillOrSaveList: MutableList<TextView?>): MutableList<String?> {
            var skillOrSaveStringList = MutableList<String?>(0) { null }

            for (skill in skillOrSaveList) {
                skillOrSaveStringList.add(skill?.text.toString())
            }

            return skillOrSaveStringList
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
            var trainedSkills: MutableList<String?> = skillOrSaveListCheck(trainedSkills)
            var trainedSaves: MutableList<String?> = skillOrSaveListCheck(trainedSaves)

            val newCharacter = CharacterSheetData(characterName, characterPlayer, characterClass,
                    characterRace, characterSpeed, selectedAlignment, characterProficiencyBonus,
                    characterStrength, characterDexterity, characterConstitution,
                    characterIntelligence, characterWisdom, characterCharisma,
                    characterMaxHitPoints, characterArmorClass, characterMaxHitDice, characterHitDie,
                    trainedSkills, trainedSaves)
            createCharacterFile(newCharacter)
            intent = Intent(this, CharacterListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createCharacterFile(characterInfo: CharacterSheetData) {
        val characterDirectory = getDir("Characters", 0)
        val characterFile = File(characterDirectory, characterInfo.name)
        ObjectOutputStream(FileOutputStream(characterFile)).use { it.writeObject(characterInfo) }
    }

}