package br.com.gvg_hs_app.data.source

import br.com.gvg_hs_app.data.domain.Card
import br.com.gvg_hs_app.data.local.LocalCard

data class SourceCardContainer(
    val sourceCard: List<SourceCard>
)

data class SourceCard(
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

fun SourceCardContainer.asDomainModel(): List<Card>{
    return sourceCard.map{
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

fun SourceCardContainer.asLocalModel(): List<LocalCard>{
    return sourceCard.map{
        LocalCard(
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