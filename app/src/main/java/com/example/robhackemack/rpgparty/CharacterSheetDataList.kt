package com.example.robhackemack.rpgparty

import android.os.Parcel

class CharacterSheetDataList : ArrayList<CharacterSheetData>(){

    fun add(name: String) {
        val characterSheet = CharacterSheetData(name)
        this.add(characterSheet)
    }

}