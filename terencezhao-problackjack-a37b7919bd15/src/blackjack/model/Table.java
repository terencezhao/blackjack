package BlackJack.model;

/**
 * The Table class represents the BlackJack casino table and hold the bets played by the user and dealer
 * Created by terencezhao on 11/15/15.
 */
public class Table {

    private int playerBet;
    private int dealerBet;

    public Table() {
        this.playerBet = 0;
        this.dealerBet = 0;
    }

    public int getPlayerBet() {
        return playerBet;
    }

    public void setPlayerBet(int playerBet) {
        this.playerBet = playerBet;
    }

    public int getDealerBet() {
        return dealerBet;
    }

    public void setDealerBet(int dealerBet) {
        this.dealerBet = dealerBet;
    }

    public int takeWinnings() {
        int winnings = playerBet + dealerBet;
        playerBet = 0;
        dealerBet = 0;
        return winnings;
    }

}
