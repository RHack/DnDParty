package com.example.robhackemack.dndparty

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject


data class CharacterSheetData(val charName: String, val charPlayer: String? = null, var charClass: String? = null, var charRace: String? = null,
                              var charSpeed: Int? = null, var charAlignment: String? = null, var charProficiencyBonus: Int? = null, var charStr: Int? = null, var charDex: Int? = null,
                              var charCon: Int? = null, var charInt: Int? = null, var charWis: Int? = null,
                              var charCha: Int? = null, var charMaxHP: Int? = null, var charAC: Int? = null,
                              var charMaxHitDice: Int? = null, var charHitDie: Int? = null,
                              var charTrainedSkills: MutableList<String?>? = null,
                              var charSavingThrows: MutableList<String?>? = null, var charHP: Int? = null, var charTempHP: Int? = null,
                              var charInitiative: Int? = null, var charHitDice: Int? = null) : JSONObject() {
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

//    constructor(parcel: Parcel?) : this(parcel?.readString(), parcel?.readString(),
//            parcel?.readString(), parcel?.readString(), parcel?.readInt(), parcel?.readInt(),
//            parcel?.readInt(), parcel?.readInt(), parcel?.readInt(), parcel?.readInt(),
//            parcel?.readInt(), parcel?.readInt(), parcel?.readInt(), parcel?.readInt(),
//            parcel?.readInt(), parcel?.readInt(), parcel?.readStringList(null),
//            parcel?.readStringList(null), parcel?.readInt(), parcel?.readInt(), parcel?.readInt(),
//            parcel?.readInt())

//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(name)
//        parcel.writeString(characterClass)
//        parcel.writeString(race)
//        parcel.writeString(alignment)
//        writeNullableInt(parcel, strength)
//        writeNullableInt(parcel, dexterity)
//        writeNullableInt(parcel, constitution)
//        writeNullableInt(parcel, intelligence)
//        writeNullableInt(parcel, wisdom)
//        writeNullableInt(parcel, charisma)
//        writeNullableInt(parcel, maxHitPoints)
//        writeNullableInt(parcel, armorClass)
//        writeNullableInt(parcel, speed)
//        writeNullableInt(parcel, maxHitDice)
//        writeNullableInt(parcel, hitDie)
//        writeNullableInt(parcel, proficiencyBonus)
//        parcel.writeStringList(trainedSkills)
//        parcel.writeStringList(savingThrows)
//        writeNullableInt(parcel, hitPoints)
//        writeNullableInt(parcel, temporaryHitPoints)
//        writeNullableInt(parcel, initiative)
//        writeNullableInt(parcel, hitDice)
//
//    }

//    fun writeNullableInt(parcel: Parcel, nullableInt: Int?) {
//        if (nullableInt != null) {
//            parcel.writeInt(nullableInt)
//        }
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }

//    companion object CREATOR : Parcelable.Creator<CharacterSheetData> {
//        override fun createFromParcel(parcel: Parcel): CharacterSheetData {
//            return CharacterSheetData(parcel)
//        }
//
//        override fun newArray(size: Int): Array<CharacterSheetData?> {
//            return arrayOfNulls(size)
//        }
//    }
//}

//private fun Parcel.writeStringList(trainedSkills: Unit?) {

}
