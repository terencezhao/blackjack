package BlackJack.view;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by terencezhao on 11/14/15.
 */
public class BlackJackView {

    private JFrame frame;
    private JButton hitButton;
    private JButton standButton;
    private List<JButton> betButtons;
    private JButton deal;
    private JLabel playerBet;
    private JLabel dealerBet;
    private JLabel playerMoney;
    private JLabel dealerMoney;
    private JTextArea log;
    private JPanel table;
    private JButton resetButton;

    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 1000;

    public BlackJackView() {
        JFrame frame = new JFrame("BlackJack");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        // Create user buttons
        frame.add(createUserButtons(), BorderLayout.SOUTH);

        // Create casino table view for displaying cards
        frame.add(createCasinoTable(), BorderLayout.CENTER);

        // Create money amount view
        frame.add(createBettingPanel(), BorderLayout.EAST);

        // Create game log
        frame.add(createGameLogPanel(), BorderLayout.WEST);

        // Create money display with current bet and total money
        frame.add(createMoneyPanel(), BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public JButton getResetButton() {
        return resetButton;
    }
    public JTextArea getLog() {
        return log;
    }

    public void setLog(String logEntry) {
        this.log.setText(logEntry);
    }

    public JLabel getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(int money) {
        this.playerMoney.setText("PLAYER'S MONEY: " + String.valueOf(money));
    }

    public void setDealerMoney(int money) {
        this.dealerMoney.setText("DEALER'S MONEY: " + String.valueOf(money));
    }

    public JLabel getPlayerBet() {
        return playerBet;
    }

    public void setPlayerBet(int playerBet) {
        this.playerBet.setText("PLAYER'S BET: " + String.valueOf(playerBet));
    }

    public JLabel getDealerBet() {
        return dealerBet;
    }

    public JButton getDealButton() {
        return deal;
    }

    public List<JButton> getBetButtons() {
        return betButtons;
    }

    public void setDealerBet(int dealerBet) {
        this.dealerBet.setText("DEALER'S BET: " + String.valueOf(dealerBet));
    }

    public JButton getHitButton() {
        return hitButton;
    }

    public JButton getStandButton() {
        return standButton;
    }

    public JPanel getTable() {
        return table;
    }

    private JPanel createUserButtons() {
        JPanel userButtons = new JPanel(new GridLayout());
        hitButton = new JButton("HIT");
        hitButton.setEnabled(false);
        standButton = new JButton("STAND");
        standButton.setEnabled(false);
        userButtons.add(hitButton);
        userButtons.add(standButton);
        return userButtons;
    }

    private JPanel createCasinoTable() {
        table = new JPanel();
        JLabel card = new JLabel("CARD IMAGE GOES HERE");
        table.add(card);
        return table;
    }

    private JPanel createBettingPanel() {
        JPanel betPanel = new JPanel();
        betPanel.setLayout(new GridLayout(7, 1));
        JButton oneDollarBetButton = new JButton("$1");
        JButton fiveDollarBetButton =  new JButton("$5");
        JButton tenDollarBetButton = new JButton("$10");
        JButton twentyFiveDollarBetButton = new JButton("$25");
        JButton fiftyDollarBetButton = new JButton("$50");
        JButton oneHundredDollarBetButton = new JButton("$100");
        deal = new JButton("DEAL");
        betButtons = new ArrayList<>();
        betButtons.add(oneDollarBetButton);
        betButtons.add(fiveDollarBetButton);
        betButtons.add(tenDollarBetButton);
        betButtons.add(twentyFiveDollarBetButton);
        betButtons.add(fiftyDollarBetButton);
        betButtons.add(oneHundredDollarBetButton);
        for(JButton betButton : betButtons) {
            betPanel.add(betButton);
        }
        betPanel.add(deal);
        deal.setEnabled(false);
        return betPanel;
    }

    private JScrollPane createGameLogPanel() {
        log = new JTextArea("BLACKJACK GAME LOG");
        DefaultCaret caret = (DefaultCaret)log.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        JScrollPane displayPane = new JScrollPane(log);
        return displayPane;
    }

    private JPanel createMoneyPanel() {
        JPanel moneyPanel = new JPanel();
        moneyPanel.setLayout(new GridLayout(1, 5));
        playerBet = new JLabel();
        dealerBet = new JLabel();
        playerMoney = new JLabel();
        dealerMoney = new JLabel();
        resetButton = new JButton("RESET");
        moneyPanel.add(playerBet);
        moneyPanel.add(dealerBet);
        moneyPanel.add(playerMoney);
        moneyPanel.add(dealerMoney);
        moneyPanel.add(resetButton);
        return moneyPanel;
    }

}
