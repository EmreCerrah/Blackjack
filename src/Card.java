public class Card {
    /**
     * @author EmreCerrah
     * @version 1.1
     * @since 28.04.2022
     */
    private String suit;
    private String name;
    private int value;

    public Card(int value, String suit, boolean jack) {
        // for Blackjack rules
        this.suit = suit;
        if (value == 1) {
            this.name = "Ace";
            this.value = 11;
        } else if (value == 11) {
            this.name = "Jack of";
            this.value = 10;
        } else if (value == 12) {
            this.name = "Queen of";
            this.value = 10;
        } else if (value == 13) {
            this.name = "King of";
            this.value = 10;
        } else {
            this.value = value;
            this.name = String.valueOf(value);
        }

    }

    public void Card(int value, String suit) {
        // for regular game setup
        this.value = value;
        this.suit = suit;

        if (value == 1) {
            this.name = "Ace";
        } else if (value == 11) {
            this.name = "Jack of";
        } else if (value == 12) {
            this.name = "Queen of";
        } else if (value == 13) {
            this.name = "King of";
        } else {
            this.name = String.valueOf(value);
        }
    }

    public String showCard() {
        return name + " " + suit;

    }

    @Override
    public String toString() {
        return ""+value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}