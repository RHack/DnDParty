package com.example.robhackemack.dndparty

import android.support.v7.widget.RecyclerView
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.list_item_character.view.*
import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream

class CharacterListAdapter(val context: Context, val username: String?, val items: CharacterSheetDataList, val listener: (CharacterSheetData) -> Unit) :
        RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    companion object {
        private const val TYPE_CHARACTER = 0
        private const val TYPE_FOOTER = 1
    }

    val characterSheets: CharacterSheetDataList = items

    override fun getItemCount() = characterSheets.size

    override fun getItemViewType(position: Int): Int {
        return if (position == characterSheets.size-1) TYPE_FOOTER else TYPE_CHARACTER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_CHARACTER -> ViewHolder(parent.inflate(R.layout.list_item_character))
            TYPE_FOOTER -> ViewHolder(parent.inflate(R.layout.list_item_add), username)
            else -> throw RuntimeException("viewType $viewType doesn't exist.")
        }
    }

    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItemViewType(position) == 1) {
            holder.bind()
        } else {
            holder.bind(characterSheets[position], listener)
        }
    }

    class ViewHolder (view: View, username: String? = null) : RecyclerView.ViewHolder(view) {

        var characterNameTv = view.character_name
        val viewItem = view
        val user = username
        fun bind(characterSheet: CharacterSheetData, listener: (CharacterSheetData) -> Unit) = with(viewItem) {
            characterNameTv.text = characterSheet.name
            setOnClickListener {
                val characterDirectory = context.getDir("Characters", 0)
                val characterFile = File(characterDirectory, characterSheet.name)

                if (characterFile.exists()) {
                    val characterInfo = ObjectInputStream(FileInputStream(characterFile)).use { it.readObject() } as CharacterSheetData
                    val intent = Intent(context, CharacterDetailActivity::class.java)
                    intent.putExtra("CharacterSheetData", characterInfo)
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, "${characterSheet.name} Clicked", Toast.LENGTH_LONG).show()
                }
            }
        }
        fun bind() = with(viewItem) {
            setOnClickListener {
                val intent = Intent(context, CharacterCreatorActivity::class.java)
                intent.putExtra("username", user)
                context.startActivity(intent)
            }
        }
    }

}