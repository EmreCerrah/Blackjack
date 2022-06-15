import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Game {

    public static final Game play=new Game();
    final static int NUMBER_OF_DECK=4;

    private Game() {
    }

    public void run() {
        Scanner inp = new Scanner(System.in);
        // Make a deck (object) with every info
        Stack<Card> deck = new Stack<>();
        setCardNames(deck); // Making names and value for deck
        Collections.shuffle(deck); //Shuffle to deck
        int money = 500;
        int bet=0;


        System.out.println("Wellcome to Blackjack!");
        outer: while (true) {
            if (money == 0)
                break;
            // Preparation for game
            Hand player = new Hand(deck.pop(),deck.pop());
            Collections.shuffle(deck);
            Hand dealer = new Hand(deck.pop(),deck.pop());

            while (dealer.getScore() < 17) { // home hand must be over of 17 in the orginal game rule
                dealer.addHand(deck.pop());
                Collections.shuffle(deck);
            }

            // You already create hands for player and home than you ready for play game.
            System.out.println("Current balance " + money + " dollar. "+deck.size()+" cards left.");
            System.out.println("Your cards :");
            player.showHand();
            System.out.println("Your score :");
            System.out.println(player.getScore());
            System.out.println("In order to play, you must donate $100 or $50. To leave the game, enter \"Exit\" or \"0\".");
            for (;;) {
                String txt = inp.next();
                if (txt.charAt(0) == '1') {
                    bet += 100;
                    money -= 100;
                    break;
                } else if (txt.charAt(0) == '5') {
                    bet += 50;
                    money -= 50;
                    break;
                } else if (txt.charAt(0) == 'e' || txt.charAt(0) == '0') {
                    break outer;
                } else
                    System.out.println("You entered a wrong key. Please type $100, $50 or exit");
            }

            System.out.println("Press + for pick up new card. Press any key to continue.");
            while (player.getScore() < 21) {
                String txt = inp.next();
                if (txt.charAt(0) == '+') {
                    System.out.println("new hand is");
                    player.addHand(deck.pop());
                    player.showHand();
                } else
                    break;
            }
            money = result(player, dealer, money, bet);

            System.out.println("\n");
        }
        System.out.println("Game Over. You gain " + money + " dollar. Congratulations...");

    }

    private void setCardNames(Stack<Card> deck) {

        for (int j=1; j<=NUMBER_OF_DECK;j++) {
            for (int i = 1; i < 13; i++) {
                deck.push(new Card(i, "Diamonds", true));

            }
            for (int i = 1; i < 13; i++) {
                deck.push(new Card(i, "Hearts", true));

            }
            for (int i = 1; i < 13; i++) {
                deck.push(new Card(i, "Spades", true));

            }
            for (int i = 1; i < 13; i++) {
                deck.push(new Card(i, "Clubs", true));
            }
        }
    }

    private int result(Hand player, Hand dealer, int money, int bet) {
        if (player.getScore() > 21) {
            System.out.println("Player is lose. Dealer score : " + dealer.getScore() + " Winner is the dealer.");
            return money;
        } else if (dealer.getScore() > 21) {
            System.out.println("The dealer is lose. Player score : " + player.getScore() + " Winner is the player.");
            money += (bet * 2);
            return money;
        } else if (player.getScore() > dealer.getScore()) {
            System.out.println(
                    "Player score is " + player.getScore() + ", dealer score is " + dealer.getScore() + ".\nWinner is the player.");
            money += (bet * 2);
            return money;
        } else if (player.getScore() < dealer.getScore()) {
            System.out
                    .println("Player score is " + player.getScore() + ", dealer score is " + dealer.getScore() + ".\nWinner is the dealer.");
            return money;
        } else if (player.getScore() == dealer.getScore())
            System.out.println("Player score is " + player.getScore() + ", dealer score is " + dealer.getScore() + ".\nDraw.");
        money += bet;
        return money;
    }
}
