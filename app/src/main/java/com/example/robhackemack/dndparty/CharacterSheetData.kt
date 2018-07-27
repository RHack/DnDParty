package com.example.robhackemack.dndparty

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject


data class CharacterSheetData(val charName: String, val charPlayer: String? = null, val charClass: String? = null, val charRace: String? = null,
                              val charAlignment: String? = null, val charStr: Int? = null, val charDex: Int? = null,
                              val charCon: Int? = null, val charInt: Int? = null, val charWis: Int? = null,
                              val charCha: Int? = null, val charMaxHP: Int? = null, val charAC: Int? = null,
                              val charSpeed: Int? = null, val charMaxHitDice: Int? = null, val charHitDie: Int? = null,
                              val charProficiencyBonus: Int? = null, val charTrainedSkills: Unit? = null,
                              val charSavingThrows: Unit? = null, var charHP: Int? = null, var charTempHP: Int? = null,
                              var charInitiative: Int? = null, val charHitDice: Int? = null) : JSONObject() {
    val name = charName
    val player = charPlayer
    val characterClass = charClass
    val race = charRace
    val alignment = charAlignment
    val strength = charStr
    val dexterity = charDex
    val constitution = charCon
    val intelligence = charInt
    val wisdom = charWis
    val charisma = charCha
    val maxHitPoints = charMaxHP
    val armorClass = charAC
    val speed = charSpeed
    val maxHitDice = charMaxHitDice
    val hitDie = charHitDie
    val proficiencyBonus = charProficiencyBonus
    val trainedSkills = charTrainedSkills
    val savingThrows = charSavingThrows
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
