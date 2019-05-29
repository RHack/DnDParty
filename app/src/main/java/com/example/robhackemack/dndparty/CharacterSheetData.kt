package com.example.robhackemack.dndparty

import java.io.Serializable

data class CharacterSheetData(val charName: String, val charPlayer: String? = null, var charClass: String? = null, var charRace: String? = null,
                              var charSpeed: Int? = null, var charAlignment: String? = null, var charProficiencyBonus: Int? = null, var charStr: Int? = null, var charDex: Int? = null,
                              var charCon: Int? = null, var charInt: Int? = null, var charWis: Int? = null,
                              var charCha: Int? = null, var charMaxHP: Int? = null, var charAC: Int? = null,
                              var charMaxHitDice: Int? = null, var charHitDie: Int? = null,
                              var charTrainedSkills: MutableList<String?>? = null,
                              var charSavingThrows: MutableList<String?>? = null, var charHP: Int? = null, var charTempHP: Int? = null,
                              var charInitiative: Int? = null, var charHitDice: Int? = null) : Serializable {
    val name = charName
    val player = charPlayer
    var characterClass = charClass
    var race = charRace
    var speed = charSpeed
    var alignment = charAlignment
    var proficiencyBonus = charProficiencyBonus
    var strength = charStr
    var dexterity = charDex
    var constitution = charCon
    var intelligence = charInt
    var wisdom = charWis
    var charisma = charCha
    var maxHitPoints = charMaxHP
    var armorClass = charAC
    var maxHitDice = charMaxHitDice
    var hitDie = charHitDie
    var trainedSkills = charTrainedSkills
    var savingThrows = charSavingThrows
    var hitPoints = charHP
    var temporaryHitPoints = charTempHP
    var initiative = charInitiative
    var hitDice = charHitDice
}
