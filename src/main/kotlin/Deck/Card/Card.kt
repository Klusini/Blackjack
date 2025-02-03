package Deck.Card

enum class Suits {
    Hearts, Diamonds, Clubs, Spades
}

class Card(private val suit : Suits, private var value : Int) {
    fun getSuit(): Suits {
        return suit
    }

    fun getValue(): Int{
        return  value
    }
    fun setValue(newValue: Int){
        value = newValue
    }
}