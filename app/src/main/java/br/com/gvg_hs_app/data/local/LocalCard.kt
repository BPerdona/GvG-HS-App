package br.com.gvg_hs_app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.gvg_hs_app.data.domain.Card

@Entity
data class LocalCard(
    @PrimaryKey
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

fun List<LocalCard>.asDomainModel() : List<Card>{
    return map{
        Card(
            cardId = it.cardId,
            name = it.name,
            type = it.type,
            img = it.img,
            cost = it.cost,
            attack = it.attack,
            health = it.health,
            artist = it.artist,
            rarity = it.rarity,
            flavor = it.flavor,
            playerClass = it.playerClass
        )
    }
}