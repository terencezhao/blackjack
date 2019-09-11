package BlackJack;

import BlackJack.controller.BlackJackController;
import BlackJack.model.Dealer;
import BlackJack.model.Player;
import BlackJack.model.Table;
import BlackJack.view.BlackJackView;

/**
 * This is the main driver class for the BlackJack game.
 * Run this class to start the BlackJack game.
 * Created by terencezhao on 11/12/15.
 */
public class BlackJack {

    public static final int SHOE_SIZE = 6;
    public static int PLAYER_STARTING_MONEY = 1000;
    public static int DEALER_STARTING_MONEY = 100000;

    public static void main(String[] args) {

        Player user = new Player(PLAYER_STARTING_MONEY);
        Dealer dealer = new Dealer(DEALER_STARTING_MONEY, SHOE_SIZE);
        Table table = new Table();

        BlackJackView view = new BlackJackView();
        BlackJackController controller = new BlackJackController(user, dealer, table, view);
    }
}
