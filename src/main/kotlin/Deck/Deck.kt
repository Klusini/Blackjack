package Deck

import Deck.Card.Card
import Deck.Card.Suits
import kotlin.math.min

class Deck (howManyDecks: Int = 1){
    private var deck = mutableListOf<Card>()
    init {
        for (suit in Suits.entries) {
            for (n in 0..< howManyDecks) {
                for (i in 1..<14) {
                    deck.add(Card(suit,min(i, 10)))
                }
            }
        }
    }

    fun shuffleDeck() {
        deck.shuffle()
    }

    fun randCard() : Card{
        val card = deck.random()
        deck.remove(card)
        return card
    }
}