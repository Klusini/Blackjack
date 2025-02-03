package Players

import Deck.Card.Card
import Deck.Deck
import Players.Hand.Hand

class Dealer(private var dealerHand: Hand) {
    private var stop : Boolean = false
    fun getDealerHand() : Hand{
        return dealerHand
    }

    fun drawCard(deck: Deck) : Card{
        val randomCard = deck.randCard()
        dealerHand.addCard(randomCard)
        return randomCard
    }

    fun checkPlayerHand(playerHand : Hand) : Boolean{
        if((playerHand.sumOfValues() > dealerHand.sumOfValues() && dealerHand.sumOfValues() < 21) || dealerHand.sumOfValues() <= 15){
            return true
        }
        else{
            return false
        }
    }

    fun setStop(){
        stop = true
    }

    fun getStop() : Boolean{
        return stop
    }
}