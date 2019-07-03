package com.example.robhackemack.dndparty

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

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

        var trainedSkills = MutableList<TextView?>(0) { null }

        val maxHitPointsEt = findViewById<TextView>(R.id.max_hit_points_et)
        val maxHitDiceEt = findViewById<TextView>(R.id.max_hit_dice_et)
        val hitDieEt = findViewById<TextView>(R.id.hit_die_et)
        val armorClassEt = findViewById<TextView>(R.id.armor_class_et)

        val createBtn = findViewById<Button>(R.id.create_character_btn)

        var characterSheet : CharacterSheetData = intent.getSerializableExtra("CharacterSheetData") as CharacterSheetData


        fun signModifier(proficiencyModifier: Int): String {
            if (proficiencyModifier < 0) {
                return "$proficiencyModifier"
            } else {
                return "+$proficiencyModifier"
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

        fun trainSkills(currentSkill: String) {
            for(skill in trainedSkills) {
                if (currentSkill == skill.toString()) {

                }
            }
        }

        fun checkedBox(proficiencyModifier: TextView, proficient: Boolean, skill: TextView? = null) {
            var proficiencyModifierNew = proficiencyModifier.text.toString().toInt()
            if (proficient) {
                proficiencyModifierNew += 2
                skill ?: trainSkill(skill)
            } else {
                proficiencyModifierNew -= 2
                skill ?: untrainSkill(skill)
            }
            proficiencyModifier.setText(signModifier(proficiencyModifierNew))
        }

        fun calculateAbilityScoreModifier(abilityScore: Int): Int {
            return (abilityScore - 10) / 2
        }

        fun updateAbilityScoreModifier(abilityScoreModifierNumber: Int, abilityScoreModifier: TextView) {
            if(abilityScoreModifier == null) {
                abilityScoreModifier
            }
            if (abilityScoreModifierNumber < 0) {
                abilityScoreModifier.text = "( $abilityScoreModifierNumber )"
            } else {
                abilityScoreModifier.text = "( +$abilityScoreModifierNumber )"
            }
        }

        fun updateSkillOrSaveModifier(abilityScoreModifierNumber: Int, skillModifier: TextView,
                                      trainedBox: CheckBox) {
            var skillModifierNumber = abilityScoreModifierNumber

            if (trainedBox.isChecked()) {
                skillModifierNumber += 2
            }
            skillModifier.setText(signModifier(skillModifierNumber))
        }

        fun updateModifier(abilityScore: TextView) {
            val abilityScoreModifierNumber: Int

            if (abilityScore.text.isEmpty()) {
                abilityScoreModifierNumber = 0
            } else {
                abilityScoreModifierNumber =
                        calculateAbilityScoreModifier(abilityScore.text.toString().toInt())
            }

            when (abilityScore) {
                strengthEt -> {
                    updateAbilityScoreModifier(abilityScoreModifierNumber, strengthTv)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, athleticsEt, athleticsCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, strengthSaveEt, strengthSaveCb)
                }
                dexterityEt -> {
                    updateAbilityScoreModifier(abilityScoreModifierNumber, dexterityTv)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, acrobaticsEt, acrobaticsCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, sleightOfHandEt, sleightOfHandCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, stealthEt, stealthCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, dexteritySaveEt, dexteritySaveCb)
                }
                constitutionEt -> {
                    updateAbilityScoreModifier(abilityScoreModifierNumber, constitutionTv)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, constitutionSaveEt, constitutionSaveCb)
                }
                intelligenceEt -> {
                    updateAbilityScoreModifier(abilityScoreModifierNumber, intelligenceTv)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, arcanaEt, arcanaCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, historyEt, historyCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, investigationEt, investigationCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, natureEt, natureCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, religionEt, religionCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, intelligenceSaveEt, intelligenceSaveCb)
                }
                wisdomEt -> {
                    updateAbilityScoreModifier(abilityScoreModifierNumber, wisdomTv)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, animalHandlingEt, animalHandlingCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, insightEt, insightCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, medicineEt, medicineCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, perceptionEt, perceptionCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, survivalEt, survivalCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, wisdomSaveEt, wisdomSaveCb)
                }
                charismaEt -> {
                    updateAbilityScoreModifier(abilityScoreModifierNumber, charismaTv)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, deceptionEt, deceptionCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, intimidationEt, intimidationCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, performanceEt, performanceCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, persuasionEt, persuasionCb)
                    updateSkillOrSaveModifier(abilityScoreModifierNumber, charismaSaveEt, charismaSaveCb)
                }

            }
        }

        nameEt.text = characterSheet.name
        strengthEt.text = characterSheet.strength.toString()
        dexterityEt.text = characterSheet.dexterity.toString()
    }
}