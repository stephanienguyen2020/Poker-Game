import java.util.Collections;
import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand; // The cards held by the player
    private double bankroll; // The player's total tokens
    private double bet; // The current bet amount

    public Player() {
        hand = new ArrayList<Card>();
        bankroll = 50; // Initialize the player's tokens to 50
    }

    public void addCard(Card c) {
        // Add the card 'c' to the player's hand
        hand.add(c);
    }

    /* Deducts 'amt' tokens from the player's bankroll for the current bet */
    public void bets(int amt) {
        bankroll -= amt;
        bet = amt; // Set the current bet amount
    }

    /* If the player wins, 'odds' tokens are added to their existing tokens. */
    public void winnings(double odds) {
        bankroll += odds;
    }

    /* Removes all cards from the player's hand. */
    public void resetHand() {
        hand.clear();
    }

    public double getBankroll() {
        return bankroll;
    }

    /* Removes a card from the hand. If the hand does not contain that card, this method does nothing. */
    public void removeCard(Card c) {
        hand.remove(c);
    }

    public void sortHand() {
        Collections.sort(hand);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void displayHand() {
        System.out.print("The hand is:");
        sortHand();

        for (Card c : getHand()) {
            System.out.print("\t" + c);
        }

        System.out.println();
    }

    /* Given a position 'i', returns the card at that position in the hand. Returns null otherwise. */
    public Card getCard(int i) {
        if (i >= 0 && i < hand.size()) {
            return hand.get(i);
        } else {
            return null;
        }
    }
}
