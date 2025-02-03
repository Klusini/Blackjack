package Players.Hand

import Deck.Card.Card

class Hand(private var hand: MutableList<Card> = mutableListOf()) {
    fun getHand() : MutableList<Card>{
        return hand
    }

    fun checkHowMuch() : Int{
        if(hand.sumOf { it.getValue() } == 21){
            return 0
        }
        else if(hand.sumOf { it.getValue() } > 21){
            return 1
        }
        else{
            return 2
        }
    }

    fun addCard(card : Card){
        if(card.getValue() == 1) {
            checkValue(card)
            hand.add(card)
        }
        else {
            hand.add(card)
        }
    }

    private fun checkValue(card: Card) : Card{
        if(hand.sumOf { it.getValue() } + 11 > 21){
            return card
        }
        else{
            card.setValue(11)
            return card
        }
    }

    fun sumOfValues() : Int{
        return hand.sumOf { it.getValue() }
    }
}