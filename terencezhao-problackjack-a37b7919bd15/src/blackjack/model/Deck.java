package BlackJack.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The Deck class represents a standard 52 deck of cards.
 * It is initialized with each card value with each suit.
 * Created by terencezhao on 11/15/15.
 */
public class Deck {

    private List<Card> cards;

    /**
     * Create a standard 52 card deck
     */
    public Deck() {
        cards = new ArrayList<>();
        for(Card.Value value : Card.Value.values()) {
            for(Card.Suit suit : Card.Suit.values()) {
                cards.add(new Card(value, suit));
            }
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    /**
     * This method shuffles the cards by doing a iterative random selection from one deck into a new deck
     * @param cards
     * @return
     */
    public static List<Card> shuffle(List<Card> cards) {
        List<Card> shuffled = new ArrayList<>();
        while(!cards.isEmpty()) {
            int randomCard = ThreadLocalRandom.current().nextInt(cards.size());
            shuffled.add(cards.remove(randomCard));
        }
        return shuffled;
    }
}
