package BlackJack.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Player class represents a BlackJack player.
 * A player has a hand consisting of some number of cards and an amount of money for betting.
 * Created by terencezhao on 11/15/15.
 */
public class Player {

    private List<Card> hand;
    private int money;

    public Player(int startingMoney) {
        this.money = startingMoney;
        this.hand = new ArrayList<>();
    }

    public int bet(int amount) {
        money -= amount;
        return amount;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public int getHandValue() {
        int total = 0;
        for(Card card : hand) {
            if(card.getValue().equals(Card.Value.ACE) && total > 21) {
                total += 1;
            } else {
                total += card.getNumericalValue();
            }
        }
        return total;
    }

    public int getHandSize() {
        return hand.size();
    }

    public void discardHand() {
        hand.clear();
    }

    public List<Card> getHand() {
        return hand;
    }

}
