public class Card implements Comparable<Card> {
    private int suit; // Use integers 1-4 to encode the suit
    private int value; // Use integers 1-13 to encode the value

    public Card(int s, int v) {
        // Constructor: Create a card with suit s and value v
        suit = s;
        value = v;
    }

    public int compareTo(Card c) {
        // Compare cards for sorting purposes
        if (value == c.value) {
            if (suit == c.suit) {
                return 0; // Cards are equal
            } else if (suit > c.suit) {
                return 1; // Current card is greater
            } else {
                return -1; // Current card is smaller
            }
        } else if (value > c.value) {
            return 1; // Current card is greater
        } else {
            return -1; // Current card is smaller
        }
    }

    public String toString() {
        // Convert Card object to a human-readable string
        String s = "";
        String v = "";

        // Encode the suit as a string
        switch (suit) {
            case 1:
                s = "Clubs";
                break;
            case 2:
                s = "Diamonds";
                break;
            case 3:
                s = "Hearts";
                break;
            case 4:
                s = "Spades";
                break;
        }

        // Encode the value as a string
        switch (value) {
            case 1:
                v = "Ace";
                break;
            case 2:
                v = "Two";
                break;
            case 3:
                v = "Three";
                break;
            case 4:
                v = "Four";
                break;
            case 5:
                v = "Five";
                break;
            case 6:
                v = "Six";
                break;
            case 7:
                v = "Seven";
                break;
            case 8:
                v = "Eight";
                break;
            case 9:
                v = "Nine";
                break;
            case 10:
                v = "Ten";
                break;
            case 11:
                v = "Jack";
                break;
            case 12:
                v = "Queen";
                break;
            case 13:
                v = "King";
                break;
        }

        return v + " of " + s; // Return a formatted string
    }
}
