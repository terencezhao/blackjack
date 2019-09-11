package BlackJack.controller;

import BlackJack.model.Card;
import BlackJack.model.Dealer;
import BlackJack.model.Player;
import BlackJack.model.Table;
import BlackJack.view.BlackJackView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This the controller class for the BlackJack game.
 * It contains the model and view resources so that it can manage interactions between them.
 * Created by terencezhao on 11/14/15.
 */
public class BlackJackController {

    private Table table;
    private Dealer dealer;
    private Player player;
    private BlackJackView view;

    public BlackJackController(Player player, Dealer dealer, Table table, BlackJackView view) {
        this.table = table;
        this.view = view;
        this.player = player;
        this.dealer = dealer;
        initializeView();

        setUserEventListeners();
        setBetEventListeners();
    }

    /**
     * Sets up the casino table showing the player's and dealer's money.
     * Sets the bets to zero.
     */
    private void initializeView() {
        view.setPlayerMoney(player.getMoney());
        view.setDealerMoney(dealer.getMoney());
        view.setPlayerBet(table.getPlayerBet());
        view.setDealerBet(table.getDealerBet());
    }

    /**
     * Adds an action listener to the HIT button for the user to draw an additional card.
     * If the player busts, the bet money is lost to the dealer
     * Another action listener is added the the STAND button used to hold the current hand.
     * The dealer than plays and the winner of the round is awarded the bet.
     */
    public void setUserEventListeners() {
        view.getResetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player = new Player(BlackJack.BlackJack.PLAYER_STARTING_MONEY);
                dealer = new Dealer(BlackJack.BlackJack.DEALER_STARTING_MONEY, BlackJack.BlackJack.SHOE_SIZE);
                table = new Table();
                view = new BlackJackView();
                BlackJackController controller = new BlackJackController(player, dealer, table, view);
            }
        });
        view.getHitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Card dealtCard = dealer.dealCard();
                view.getLog().append("\nYou were a dealt a " + dealtCard.toString());
                player.addCardToHand(dealtCard);
                view.getLog().append("\nYou have " + player.getHandValue());
                displayPlayerHand();
                if (player.getHandValue() > 21) {
                    view.getLog().append("\nYou busted!");
                    dealer.addMoney(table.takeWinnings());
                    view.setDealerMoney(dealer.getMoney());
                    player.discardHand();
                    dealer.discardHand();
                    view.getHitButton().setEnabled(false);
                    view.getStandButton().setEnabled(false);

                    table.setPlayerBet(0);
                    table.setDealerBet(0);
                    view.setPlayerBet(table.getPlayerBet());
                    view.setDealerBet(table.getDealerBet());

                    for(JButton betButton : view.getBetButtons()) {
                        betButton.setEnabled(true);
                    }

                    if(player.getMoney() <= 0) {
                        view.getLog().append("YOU ARE BROKE! GAME OVER!");
                        return;
                    }
                }
            }
        });
        view.getStandButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (dealer.getHandValue() < 21 && dealer.getHandValue() < player.getHandValue()) {
                    Card dealtCard = dealer.dealCard();
                    view.getLog().append("\nDealer was dealt a " + dealtCard.toString());
                    dealer.addCardToHand(dealtCard);
                    view.getLog().append("\nDealer has " + dealer.getHandValue());
                }
                if (dealer.getHandValue() > 21) {
                    view.getLog().append("\nDealer Busts");
                    player.addMoney(table.takeWinnings());
                    view.setPlayerMoney(player.getMoney());
                    if(dealer.getMoney() <= 0) {
                        view.getLog().append("YOU BEAT THE HOUSE! YOU WIN!");
                        return;
                    }
                } else {
                    view.getLog().append("\nDealer Wins");
                    dealer.addMoney(table.takeWinnings());
                    view.setDealerMoney(dealer.getMoney());
                    if(player.getMoney() <= 0) {
                        view.getLog().append("YOU ARE BROKE! GAME OVER!");
                        return;
                    }
                }

                table.setPlayerBet(0);
                table.setDealerBet(0);
                view.setPlayerBet(table.getPlayerBet());
                view.setDealerBet(table.getDealerBet());

                player.discardHand();
                dealer.discardHand();
                view.getHitButton().setEnabled(false);
                view.getStandButton().setEnabled(false);

                for(JButton betButton : view.getBetButtons()) {
                    betButton.setEnabled(true);
                }
            }
        });
    }

    /**
     * Add action listeners to each bet button of various amounts.
     * Another deal button action listener starts each round of blackjack after a bet is made.
     */
    public void setBetEventListeners() {
        for (JButton button : view.getBetButtons()) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String bet = button.getText().replace("$", " ").trim();
                    int betAmount = Integer.parseInt(bet);
                    if(betAmount > player.getMoney()) {
                        view.getLog().append("\nInsufficient money for bet");
                    } else {
                        table.setPlayerBet(table.getPlayerBet() + player.bet(betAmount));
                        view.setPlayerBet(table.getPlayerBet());
                        view.setPlayerMoney(player.getMoney());
                        view.getLog().append("\nPlayer raised the bet by $" + bet);
                        view.getDealButton().setEnabled(true);
                    }
                }
            });
        }

        view.getDealButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.setDealerBet(dealer.bet(table.getPlayerBet()));
                view.setDealerBet(table.getDealerBet());
                view.setDealerMoney(dealer.getMoney());
                view.getLog().append("\nDealer matched player's bet of " + table.getDealerBet());
                Card firstCard = dealer.dealCard();
                view.getLog().append("\nYou were dealt a " + firstCard.toString());
                player.addCardToHand(firstCard);
                Card secondCard = dealer.dealCard();
                view.getLog().append("\nYou were dealt a " + secondCard.toString());
                player.addCardToHand(secondCard);
                view.getLog().append("\nYou have " + player.getHandValue());
                displayPlayerHand();

                for(JButton betButton : view.getBetButtons()) {
                    betButton.setEnabled(false);
                }
                view.getDealButton().setEnabled(false);
                view.getHitButton().setEnabled(true);
                view.getStandButton().setEnabled(true);
            }
        });
    }

    /**
     * Draws the cards in the players hand.
     */
    private void displayPlayerHand() {
        view.getTable().removeAll();
        for (Card card : player.getHand()) {
            String fileName = System.getProperty("user.dir") + "/src/BlackJack/cards/" + card.toFileName() + ".png";
            ImageIcon icon = new ImageIcon(fileName);
            Image image = icon.getImage(); // transform it
            Image newimg = image.getScaledInstance(200, 300,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            icon = new ImageIcon(newimg);  // transform it back
            JLabel cardLabel = new JLabel();
            cardLabel.setIcon(icon);
            view.getTable().add(cardLabel);
            view.getTable().revalidate();
            view.getTable().repaint();
        }
    }

}
