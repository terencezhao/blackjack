package BlackJack.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Dealer class is an extension of Player who has a shoe of cards and is able to deal cards.
 * Created by terencezhao on 11/15/15.
 */
public class Dealer extends Player {

    private List<Card> shoe;

    public Dealer(int startingMoney, int numberOfDecks) {
        super(startingMoney);
        shoe = new ArrayList<>();
        for(int i = 0; i < numberOfDecks; i++) {
            Deck deck = new Deck();
            shoe.addAll(Deck.shuffle(deck.getCards()));
        }
    }

    public Card dealCard() {
        return shoe.remove(0);
    }
}
