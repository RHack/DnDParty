package com.example.robhackemack.dndparty

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_character_detail.*
import org.w3c.dom.Text

class CharacterDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        var nameEt = findViewById<TextView>(R.id.name_et)
        val playerEt = findViewById<TextView>(R.id.player_et)
        val classEt = findViewById<TextView>(R.id.class_et)
        val raceEt = findViewById<TextView>(R.id.race_et)
        val speedEt = findViewById<TextView>(R.id.speed_et)
        val alignmentEt = findViewById<TextView>(R.id.alignment_sp)
        val proficiencyBonusEt = findViewById<TextView>(R.id.proficiency_bonus_et)

        val strengthEt = findViewById<TextView>(R.id.strength_et)
        val strengthSaveEt = findViewById<TextView>(R.id.strength_save_modifier_et)
        val strengthSaveCb = findViewById<CheckBox>(R.id.strength_save_trained_cb)
        val athleticsEt = findViewById<TextView>(R.id.athletics_modifier_et)
        val athleticsCb = findViewById<CheckBox>(R.id.athletics_trained_cb)

        val strengthTv = findViewById<TextView>(R.id.strength_modifier_tv)
        val dexterityTv = findViewById<TextView>(R.id.dexterity_modifier_tv)
        val dexterityEt = findViewById<TextView>(R.id.dexterity_et)
        val dexteritySaveEt = findViewById<TextView>(R.id.dexterity_save_modifier_et)
        val dexteritySaveCb = findViewById<CheckBox>(R.id.dexterity_save_trained_cb)
        val acrobaticsEt = findViewById<TextView>(R.id.acrobatics_modifier_et)
        val acrobaticsCb = findViewById<CheckBox>(R.id.acrobatics_trained_cb)
        val sleightOfHandEt = findViewById<TextView>(R.id.sleight_of_hand_modifier_et)
        val sleightOfHandCb = findViewById<CheckBox>(R.id.sleight_of_hand_trained_cb)
        val stealthEt = findViewById<TextView>(R.id.stealth_modifier_et)
        val stealthCb = findViewById<CheckBox>(R.id.stealth_trained_cb)

        val constitutionTv = findViewById<TextView>(R.id.constitution_modifier_tv)
        val constitutionEt = findViewById<TextView>(R.id.constitution_et)
        val constitutionSaveEt = findViewById<TextView>(R.id.constitution_save_modifier_et)
        val constitutionSaveCb = findViewById<CheckBox>(R.id.constitution_save_trained_cb)

        val intelligenceTv = findViewById<TextView>(R.id.intelligence_modifier_tv)
        val intelligenceEt = findViewById<TextView>(R.id.intelligence_et)
        val intelligenceSaveEt = findViewById<TextView>(R.id.intelligence_save_modifier_et)
        val intelligenceSaveCb = findViewById<CheckBox>(R.id.intelligence_save_trained_cb)
        val arcanaEt = findViewById<TextView>(R.id.arcana_modifier_et)
        val arcanaCb = findViewById<CheckBox>(R.id.arcana_trained_cb)
        val historyEt = findViewById<TextView>(R.id.history_modifier_et)
        val historyCb = findViewById<CheckBox>(R.id.history_trained_cb)
        val investigationEt = findViewById<TextView>(R.id.investigation_modifier_et)
        val investigationCb = findViewById<CheckBox>(R.id.investigation_trained_cb)
        val natureEt = findViewById<TextView>(R.id.nature_modifier_et)
        val natureCb = findViewById<CheckBox>(R.id.nature_trained_cb)
        val religionEt = findViewById<TextView>(R.id.religion_modifier_et)
        val religionCb = findViewById<CheckBox>(R.id.religion_trained_cb)

        val wisdomTv = findViewById<TextView>(R.id.wisdom_modifier_tv)
        val wisdomEt = findViewById<TextView>(R.id.wisdom_et)
        val wisdomSaveEt = findViewById<TextView>(R.id.wisdom_save_modifier_et)
        val wisdomSaveCb = findViewById<CheckBox>(R.id.wisdom_save_trained_cb)
        val animalHandlingEt = findViewById<TextView>(R.id.animal_handling_modifier_et)
        val animalHandlingCb = findViewById<CheckBox>(R.id.animal_handling_trained_cb)
        val insightEt = findViewById<TextView>(R.id.insight_modifier_et)
        val insightCb = findViewById<CheckBox>(R.id.insight_trained_cb)
        val medicineEt = findViewById<TextView>(R.id.medicine_modifier_et)
        val medicineCb = findViewById<CheckBox>(R.id.medicine_trained_cb)
        val perceptionEt = findViewById<TextView>(R.id.perception_modifier_et)
        val perceptionCb = findViewById<CheckBox>(R.id.perception_trained_cb)
        val survivalEt = findViewById<TextView>(R.id.survival_modifier_et)
        val survivalCb = findViewById<CheckBox>(R.id.survival_trained_cb)

        val charismaTv = findViewById<TextView>(R.id.charisma_modifier_tv)
        val charismaEt = findViewById<TextView>(R.id.charisma_et)
        val charismaSaveEt = findViewById<TextView>(R.id.charisma_save_modifier_et)
        val charismaSaveCb = findViewById<CheckBox>(R.id.charisma_save_trained_cb)
        val deceptionEt = findViewById<TextView>(R.id.deception_modifier_et)
        val deceptionCb = findViewById<CheckBox>(R.id.deception_trained_cb)
        val intimidationEt = findViewById<TextView>(R.id.intimidation_modifier_et)
        val intimidationCb = findViewById<CheckBox>(R.id.intimidation_trained_cb)
        val performanceEt = findViewById<TextView>(R.id.performance_modifier_et)
        val performanceCb = findViewById<CheckBox>(R.id.performance_trained_cb)
        val persuasionEt = findViewById<TextView>(R.id.persuasion_modifier_et)
        val persuasionCb = findViewById<CheckBox>(R.id.persuasion_trained_cb)


        val maxHitPointsEt = findViewById<TextView>(R.id.max_hit_points_et)
        val maxHitDiceEt = findViewById<TextView>(R.id.max_hit_dice_et)
        val hitDieEt = findViewById<TextView>(R.id.hit_die_et)
        val armorClassEt = findViewById<TextView>(R.id.armor_class_et)

        val createBtn = findViewById<Button>(R.id.create_character_btn)

        var characterSheet : CharacterSheetData = intent.getSerializableExtra("CharacterSheetData") as CharacterSheetData
        val trainedSkills = characterSheet.trainedSkills


        fun signModifier(proficiencyModifier: Int): String {
            return if (proficiencyModifier < 0) {
                "$proficiencyModifier"
            } else {
                "+$proficiencyModifier"
            }
        }

        fun nullCheck(item: Any?): Boolean {
            if (item is TextView?) {
                return item != null && item.text.toString() != "null" && item.text.toString() != ""
            }
            return item != null
        }

        fun calculateAbilityScoreModifier(abilityScore: TextView?): Int {
            return if (nullCheck(abilityScore)) {
                (abilityScore?.text.toString().toInt() - 10) / 2
            } else {
                0
            }
        }

        fun isTrained(skillOrSave: TextView, skillOrSaveList: MutableList<String?>?): Boolean {
            if (skillOrSaveList == null) {
                return false
            } else if (skillOrSaveList.isNotEmpty()) {
                if (skillOrSaveList.contains(skillOrSave.text)) {
                    return true
                }
            }
            return false
        }

        fun updateAbilityScoreModifier(abilityScoreModifierNumber: Int, abilityScoreModifier: TextView) {
            if (abilityScoreModifier == null) {
                abilityScoreModifier
            }
            if (abilityScoreModifierNumber < 0) {
                abilityScoreModifier.text = "( $abilityScoreModifierNumber )"
            } else {
                abilityScoreModifier.text = "( +$abilityScoreModifierNumber )"
            }
        }

        fun updateSkillModifier(abilityScoreModifierNumber: Int, skillModifier: TextView,
                                skill: TextView, trainedBox: CheckBox) {
            var skillModifierNumber = abilityScoreModifierNumber

            if (isTrained(skill, characterSheet.trainedSkills)) {
                trainedBox.isChecked = true
                skillModifierNumber += proficiencyBonusEt.text.toString().toInt()
            }
            skillModifier.text = signModifier(skillModifierNumber)
        }

        fun updateSaveModifier(abilityScoreModifierNumber: Int, saveModifier: TextView,
                                save: TextView, trainedBox: CheckBox) {
            var saveModifierNumber = abilityScoreModifierNumber

            if (isTrained(save, characterSheet.savingThrows)) {
                trainedBox.isChecked = true
                saveModifierNumber += proficiencyBonusEt.text.toString().toInt()
            }
            saveModifier.text = signModifier(saveModifierNumber)
        }

        fun updateModifier(abilityScore: TextView) {
            val abilityScoreModifierNumber: Int = calculateAbilityScoreModifier(abilityScore)

            when (abilityScore) {

                strengthEt -> {
                    updateAbilityScoreModifier(abilityScoreModifierNumber, strengthTv)
                    updateSkillModifier(abilityScoreModifierNumber, athleticsEt, athletics_tv, athleticsCb)
                    updateSaveModifier(abilityScoreModifierNumber, strengthSaveEt, strength_save_tv, strengthSaveCb)
                }
                dexterityEt -> {
                    updateAbilityScoreModifier(abilityScoreModifierNumber, dexterityTv)
                    updateSkillModifier(abilityScoreModifierNumber, acrobaticsEt, acrobatics_tv, acrobaticsCb)
                    updateSkillModifier(abilityScoreModifierNumber, sleightOfHandEt, sleight_of_hand_tv, sleightOfHandCb)
                    updateSkillModifier(abilityScoreModifierNumber, stealthEt, stealth_tv, stealthCb)
                    updateSaveModifier(abilityScoreModifierNumber, dexteritySaveEt, dexterity_save_tv, dexteritySaveCb)
                }
                constitutionEt -> {
                    updateAbilityScoreModifier(abilityScoreModifierNumber, constitutionTv)
                    updateSaveModifier(abilityScoreModifierNumber, constitutionSaveEt, constitution_save_tv, constitutionSaveCb)
                }
                intelligenceEt -> {
                    updateAbilityScoreModifier(abilityScoreModifierNumber, intelligenceTv)
                    updateSkillModifier(abilityScoreModifierNumber, arcanaEt, arcana_tv, arcanaCb)
                    updateSkillModifier(abilityScoreModifierNumber, historyEt, history_tv, historyCb)
                    updateSkillModifier(abilityScoreModifierNumber, investigationEt, investigation_tv, investigationCb)
                    updateSkillModifier(abilityScoreModifierNumber, natureEt, nature_tv, natureCb)
                    updateSkillModifier(abilityScoreModifierNumber, religionEt, religion_tv, religionCb)
                    updateSaveModifier(abilityScoreModifierNumber, intelligenceSaveEt, intelligence_save_tv, intelligenceSaveCb)
                }
                wisdomEt -> {
                    updateAbilityScoreModifier(abilityScoreModifierNumber, wisdomTv)
                    updateSkillModifier(abilityScoreModifierNumber, animalHandlingEt, animal_handling_tv, animalHandlingCb)
                    updateSkillModifier(abilityScoreModifierNumber, insightEt, insight_tv, insightCb)
                    updateSkillModifier(abilityScoreModifierNumber, medicineEt, medicine_tv, medicineCb)
                    updateSkillModifier(abilityScoreModifierNumber, perceptionEt, perception_tv, perceptionCb)
                    updateSkillModifier(abilityScoreModifierNumber, survivalEt, survival_tv, survivalCb)
                    updateSaveModifier(abilityScoreModifierNumber, wisdomSaveEt, wisdom_save_tv, wisdomSaveCb)
                }
                charismaEt -> {
                    updateAbilityScoreModifier(abilityScoreModifierNumber, charismaTv)
                    updateSkillModifier(abilityScoreModifierNumber, deceptionEt, deception_tv, deceptionCb)
                    updateSkillModifier(abilityScoreModifierNumber, intimidationEt, intimidation_tv, intimidationCb)
                    updateSkillModifier(abilityScoreModifierNumber, performanceEt, performance_tv, performanceCb)
                    updateSkillModifier(abilityScoreModifierNumber, persuasionEt, persuasion_tv, persuasionCb)
                    updateSaveModifier(abilityScoreModifierNumber, charismaSaveEt, charisma_save_tv, charismaSaveCb)
                }
            }
        }

        nameEt.text = characterSheet.name
        playerEt.text = characterSheet.player
        classEt.text = characterSheet.characterClass
        raceEt.text = characterSheet.race
        alignmentEt.text = characterSheet.alignment

        hit_points_et.text =
                if (nullCheck(hit_points_et) ) {
                    characterSheet.hitPoints.toString()
                } else {
                    characterSheet.maxHitPoints.toString()
                }
        max_hit_points_et.text = characterSheet.maxHitPoints.toString()
        armorClassEt.text = characterSheet.armorClass.toString()
        proficiencyBonusEt.text = characterSheet.proficiencyBonus.toString()
        maxHitDiceEt.text =
                if (nullCheck(maxHitDiceEt)) {
                    characterSheet.hitDice.toString()
                } else {
                    characterSheet.maxHitDice.toString()
                }
        hitDieEt.text = characterSheet.maxHitDice.toString()
        speedEt.text = characterSheet.speed.toString()

        strengthEt.text = characterSheet.strength.toString()
        updateModifier(strengthEt)
        dexterityEt.text = characterSheet.dexterity.toString()
        updateModifier(dexterityEt)
        constitutionEt.text = characterSheet.constitution.toString()
        updateModifier(constitutionEt)
        intelligenceEt.text = characterSheet.intelligence.toString()
        updateModifier(intelligenceEt)
        wisdomEt.text = characterSheet.wisdom.toString()
        updateModifier(wisdomEt)
        charismaEt.text = characterSheet.charisma.toString()
        updateModifier(charismaEt)
    }
}