import Deck.Card.Card
import Deck.Deck
import Players.Dealer
import Players.Hand.Hand
import Players.Player

class Game(private var playersNumber : Int) {
    private var playersList = listOf<Player>()
    init {
        if (playersNumber !in 1..4) {
            playersNumber = 2
        }
        else{
            playersList = List(playersNumber) { Player("Player $it", Hand()) }
        }
        playRound()
    }

    private fun checkWinner(player: Player, dealer: Dealer){
        if(player.getPlayerHand().sumOfValues() <= 21 && dealer.getDealerHand().sumOfValues() > 21){
            println(player.getName() + " Won!")
            println("${player.getName()} points points: ${player.getPlayerHand().sumOfValues()}, dealer's points ${dealer.getDealerHand().sumOfValues()}")
        }
        else if((player.getPlayerHand().sumOfValues() > dealer.getDealerHand().sumOfValues() && player.getPlayerHand().sumOfValues() <= 21 && dealer.getDealerHand().sumOfValues() <= 21)){
                println(player.getName() + " Won!")
                println("${player.getName()} points points: ${player.getPlayerHand().sumOfValues()}, dealer's points ${dealer.getDealerHand().sumOfValues()}")
        }
        else if((player.getPlayerHand().sumOfValues() == dealer.getDealerHand().sumOfValues() && player.getPlayerHand().sumOfValues() <= 21)){
            println("It's a draw between ${player.getName()} and dealer")
            println("${player.getName()} points points: ${player.getPlayerHand().sumOfValues()}, dealer's points ${dealer.getDealerHand().sumOfValues()}")
        }
        else{
            println(player.getName() + " lost!")
            println("${player.getName()} points points: ${player.getPlayerHand().sumOfValues()}, dealer's points ${dealer.getDealerHand().sumOfValues()}")
        }
    }

    private fun playRound() {
        val deck = Deck()
        val dealer = Dealer(Hand())
        var card: Card

        for (player in playersList) {
            println("${player.getName()}, place your bet")

            var playersBet: Int? = null
            while (playersBet == null) {
                print("Enter bet: ")
                val input = readlnOrNull()
                playersBet = input?.toIntOrNull()
                if (playersBet == null) {
                    println("Bet must be a valid number. Try again.")
                }
            }

            player.placeBet(playersBet)
        }

        for (i in 0..<2) {
            deck.shuffleDeck()
            for (player in playersList) {
                card = player.drawCard(deck, player.getName())
                println("${player.getName()} drew ${card.getValue()}")
            }
            card = dealer.drawCard(deck)
            if(i != 1) {
                println("Dealer drew ${card.getValue()}")
            }
        }

        while (true) {
            var check : String
            var counter = 0
            for (player in playersList) {
                if (!player.getStop()) {
                    deck.shuffleDeck()
                    println("it's ${player.getName()} turn, write DRAW if you want do draw a card or write STOP if u want to stop \n \n")
                    println("Total points: ${player.getPlayerHand().sumOfValues()}")
                    if(dealer.getDealerHand().getHand().size <= 2){
                        println("Dealer's points ${dealer.getDealerHand().getHand()[0].getValue()} + ?")
                    }
                    else{
                        println("Dealer's points ${dealer.getDealerHand().sumOfValues()}")
                    }
                    check = readlnOrNull()!!
                    if (check == "DRAW") {
                        card = player.drawCard(deck, player.getName())
                        println("${player.getName()} drew ${card.getValue()}")
                    }

                    else {
                        player.setStop()
                    }
                }
                else{
                    counter++
                }
            }
            for(player in playersList){
                if(!dealer.getStop()) {
                    deck.shuffleDeck()
                    if (dealer.checkPlayerHand(player.getPlayerHand())) {
                        card = dealer.drawCard(deck)
                        println("Dealer drew ${card.getValue()}")
                    } else {
                        dealer.setStop()
                    }
                }
                else{
                    counter++
                }
            }

            if(counter == playersNumber + 1){
                for(player in playersList){
                    checkWinner(player,dealer)
                }
                break
            }
        }
    }
}