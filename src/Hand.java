import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Hand {

    private ArrayList<Card> cards =new ArrayList<>();
    private int score;
    public Hand (Card one , Card two){

        cards.add(one);
        cards.add(two);
        score+= one.getValue()+two.getValue();
        // you can use to ice value 11 or 1
        if (getScore()>21&&two.getName()=="Ace"){
         score -= 10;
        }
    }

    public void addHand (Card e){
                cards.add(e);
        score += e.getValue();
        // you can use to ice value 11 or 1
        if (getScore()>21&&e.getName()=="Ace"){
            score -= 10;
        }
    }

    public int getScore(){
        return score;
    }
    public void showHand (){
        for (int i = 0; i< cards.size(); i++) {
            System.out.println(cards.get(i).showCard());
        }
    }

}
