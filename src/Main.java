
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        // Make a deck (object) with every info
        Stack<Card> deck = new Stack<>();
        setCardNames(deck); // Making names and value for deck
        int money = 500;
        int bet=0;
        System.out.println("Wellcome to Blackjack!");
        outer: while (true) {
            if (money == 0)
                break;
            // Preparation for game
            int dealer=0;
            while (dealer < 17) { // home hand must be over of 17 in the orginal game rule
                int increase = Integer.parseInt(String.valueOf(deck.pop()));
                dealer += increase;
                Collections.shuffle(deck);
            }

            Card playerFirstCard = deck.pop();
            Card playerSecondCard = deck.pop();
            int player = (playerFirstCard.getValue() + playerSecondCard.getValue());
            
            if (player > 21 && playerSecondCard.getValue() == 11) { // you can use to ice value 11 or 1
                player -= 10;
            }

            // You already create hands for player and home than you ready for play game.
            System.out.println("Current balance " + money + " dollar");
            System.out.println("Your cards " + playerFirstCard.getName() + " " + playerFirstCard.getSuit() + " ve " + playerSecondCard.getName() + " " + playerSecondCard.getSuit() + "\nYour score : " + player);
            System.out.println("In order to play, you must donate $100 or $50. To leave the game, enter \"Exit\" or \"0\".");
            for (;;) {
                String txt = inp.next();
                if (txt.charAt(1) == '1') {
                    bet += 100;
                    money -= 100;
                    break;
                } else if (txt.charAt(1) == '5') {
                    bet += 50;
                    money -= 50;
                    break;
                } else if (txt.charAt(0) == 'e' || txt.charAt(0) == '0') {
                    break outer;
                } else
                    System.out.println("You entered a wrong key. Please type $100, $50 or exit");
                        }
            System.out.println("Press + for pick up new card. Press any key to continue."); // its about game introduction.
            while (player < 21) {
                String txt = inp.next();
                if (txt.charAt(0) == '+') {
                    Card newCard = deck.pop();
                    player += newCard.getValue();
                    if (player > 21 && newCard.getValue() == 11) { // you can use to ice value 11 or 1
                        player -= 10;
                    }
                    player +=newCard.getValue();
                    System.out.println("New card " + newCard.getName() + " " + newCard.getSuit() + ", Whole hand score " + player);
                } else
                    break;
            }
            money = result(player, dealer, money, bet);

            System.out.println("\n");
        }
        System.out.println("Game Over. You gain " + money + " dollar. Congratulations...");
        /*
         * try zone
         * System.out.println(homeValue);
         * System.out.println(player);
         */
    }

    public static void setCardNames(Stack<Card> deck) {
            for (int i = 1; i < 13; i++) {
            deck.push(new Card(i,"Diamonds",true));

        }
        for (int i = 1; i < 13; i++) {
            deck.push(new Card(i,"Hearts",true));

        }
        for (int i = 1; i < 13; i++) {
            deck.push(new Card(i,"Spades",true));

        }
        for (int i = 1; i < 13; i++) {
            deck.push(new Card(i,"Clubs",true));
        }

    }

    public static int result(int player, int dealer, int money, int bet) {
        if (player > 21) {
            System.out.println("Player is lose. Dealer score : " + dealer + " Winner is the dealer.");
            return money;
        } else if (dealer > 21) {
            System.out.println("The dealer is lose. Player score : " + player+ "Winner is the player.");
            money += (bet * 2);
            return money;
        } else if (player > dealer) {
            System.out.println(
                    "Player score is " + player + ", dealer score is " + dealer + ".\nWinner is the player.");
            money += (bet * 2);
            return money;
        } else if (player < dealer) {
            System.out
                    .println("Player score is " + player + ", dealer score is " + dealer + ".\nWinner is the dealer.");
            return money;
        } else if (player == dealer)
            System.out.println("Player score is " + player + ", dealer score is " + dealer + ".\nDraw.");
        money += bet;
        return money;
    }
}

