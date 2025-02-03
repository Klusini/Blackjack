package Players

import Deck.Card.Card
import Deck.Deck
import Players.Hand.Hand

class Player(private val name : String, private var playerHand: Hand) {
    private var stop : Boolean = false
    private var bet : Int = 0
    fun drawCard(deck : Deck, playerName: String) : Card {
        val randomCard = deck.randCard()
        playerHand.addCard(randomCard)
        checkForWin()
        return randomCard
    }

    private fun checkForWin(){
        if(playerHand.checkHowMuch() == 0){
            println("U have oczko!")
            stop = true
        }
        else if(playerHand.checkHowMuch() == 1){
            println("Fura, u have " + playerHand.sumOfValues() + " that's too much!")
            stop = true
        }
        else{
            stop = false
        }
    }

    fun placeBet(newBet : Int){
        bet = newBet
    }

    fun getName() : String{
        return name
    }

    fun setStop(){
        stop = true
    }

    fun getStop() : Boolean{
        return stop
    }

    fun getPlayerHand(): Hand {
        return playerHand
    }
}