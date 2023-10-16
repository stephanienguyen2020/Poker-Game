import java.util.Random;

public class Deck {
    private Card[] theDeck;
    private int top; // Represents the current position in the deck
    
    private static final int NUMBER_OF_CARDS = 52;

    public Deck() {
        // Constructor: Creates a standard deck of 52 cards
        theDeck = new Card[NUMBER_OF_CARDS];
        top = 0;
        int count = 0;
        
        // Populates the deck with cards of various suits and values
        for (int s = 0; s < 4; s++) {
            for (int v = 0; v < 13; v++) {
                theDeck[count] = new Card(s + 1, v + 1);
                count++;
            }
        }
        
        shuffle(); // Shuffles the deck after it's created
    }

    public void shuffle() {
        // Shuffles the deck using the Fisher-Yates shuffle algorithm
        Random r = new Random();
        for (int i = NUMBER_OF_CARDS - 1; i >= 0; i--) {
            // Selects a random position between 0 and i
            int pos = r.nextInt(i + 1);
            
            // Swaps the card at the random position with the card at position i
            // This ensures a random and unbiased shuffle
            Card temp = theDeck[i];
            theDeck[i] = theDeck[pos];
            theDeck[pos] = temp;
        }
    }

    // If there are no more cards to deal (top > 51), this method returns a joker card (suit = 0, value = 0)
    public Card deal() {
        // Deals the top card from the deck
        if (top > 51) {
            return new Card(0, 0); // Returns a joker card if all cards have been dealt
        } else {
            return theDeck[top++];
        }
        // The use of 'top++' ensures that the card at the current top position is returned,
        // and then 'top' is incremented for the next deal.
    }

    public void reset() {
        top = 0; // Resets the top position to the beginning of the deck
        shuffle(); // Shuffles the deck again after resetting it
    }
}
