package br.com.gvg_hs_app.data

data class Card(
    val cardId: String,
    val name: String,
    val type: String,
    val img: String,
    val cost: Int,
    val attack: Int = 0,
    val health: Int = 0,
    val artist: String,
    val rarity: String,
    val flavor: String,
    val playerClass: String
)
