package BlackJack.model;

/**
 * The Card class represents one of 52 playing cards in a standard deck.
 * Created by terencezhao on 11/15/15.
 */
public class Card {

    /**
     * We use an enum for the value with the name of the card face along with the numerical value associated.
     */
    public enum Value {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10), ACE(11);

        private int value;

        private Value(int value) {
            this.value = value;
        }
    }

    /**
     * Each card has one of four suits
     */
    public enum Suit {
        DIAMONDS, CLUBS, HEARTS, SPADES;
    }

    private Suit suit;
    private Value value;

    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return value + " of " + suit.name();
    }

    public Value getValue() {
        return value;
    }

    public int getNumericalValue() {
        return value.value;
    }

    /**
     * Prints out the formatted card file name
     * @return
     */
    public String toFileName() {
        String fileName = value.name() + suit.name();
        return fileName;
    }

}
