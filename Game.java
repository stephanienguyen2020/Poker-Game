import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Player p;
    private Deck cards;

    public Game(String[] testHand) {
        // Constructor for testing purposes
        // Create a player's hand using the contents of testHand
        p = new Player();
        cards = new Deck();

        for (String input : testHand) {
            // Use the input string to create a card and add it to the player's hand
            char s = input.charAt(0);
            int suit = 0;

            switch (s) {
                case 'c':
                    suit = 1;
                    break;
                case 'd':
                    suit = 2;
                    break;
                case 'h':
                    suit = 3;
                    break;
                case 's':
                    suit = 4;
                    break;
            }

            int value = Integer.parseInt(input.substring(1));
            Card newCard = new Card(suit, value);
            p.addCard(newCard);
        }
    }

    public Game() {
        // Constructor for playing a normal game
        p = new Player();
        cards = new Deck();
    }

    public void dealHand() {
        for (int i = 0; i < 5; i++) {
            dealToPlayer();
        }
    }

    public void play() {
        boolean continueGame = true;
        System.out.println("Welcome to Video Poker!");

        while (continueGame && p.getBankroll() > 0) {
            System.out.println("YOUR TOKENS: " + p.getBankroll());
            System.out.print("Would you like to play a round? (y/n): ");
            Scanner user = new Scanner(System.in);
            char choice = user.nextLine().toLowerCase().charAt(0);
            continueGame = true;

            if (choice == 'n') {
                continueGame = false;
            }

            if (continueGame) {
                int bet = 0;

                while (bet < 1 || bet > 5 || bet > p.getBankroll()) {
                    System.out.print("How many tokens to bet this hand? (1 to 5): ");
                    bet = user.nextInt();

                    if (bet > p.getBankroll()) {
                        System.out.println("You don't have enough tokens!");
                    }
                }

                if (p.getHand().size() == 0) {
                    cards.reset();
                    dealHand();
                }

                p.bets(bet);
                p.displayHand();
                boolean exchanged = exchangeCards();

                if (exchanged) {
                    p.displayHand();
                }

                p.sortHand();
                String result = checkHand(p.getHand());
                System.out.println("You got a " + result + "! ");
                int payout = getPayout(result) * bet;
                System.out.println("PAYOUT: " + payout + " tokens");
                p.winnings(payout);
                p.resetHand();
            }
        }

        if (p.getBankroll() <= 0) {
            System.out.println("Sorry, you are out of tokens. :( ");
        }

        System.out.println("Thank you for playing Video Poker!");
    }

    public boolean exchangeCards() {
        Scanner user = new Scanner(System.in);
        System.out.print("How many cards (0-5) would you like to exchange? ");
        int exchangeCount = Integer.parseInt(user.nextLine());
        int i = 0;
        ArrayList<Card> removed = new ArrayList<Card>();
        int[] positions = new int[exchangeCount];

        while (i < exchangeCount) {
            System.out.print("Which card (1-5) would you like to exchange? ");
            int position = Integer.parseInt(user.nextLine());
            boolean continueLoop = true;

            while (continueLoop) {
                continueLoop = false;

                if (position < 1 || position > 5) {
                    System.out.println("Card position must be between 1 and 5");
                    System.out.print("Please enter another position: ");
                    position = Integer.parseInt(user.nextLine());
                    continueLoop = true;
                } else {
                    for (int prev : positions) {
                        if (prev == position) {
                            System.out.println("You cannot exchange a card that you previously exchanged.");
                            System.out.print("Please enter another position: ");
                            position = Integer.parseInt(user.nextLine());
                            continueLoop = true;
                        }
                    }
                }
            }

            positions[i] = position;
            Card removedCard = p.getCard(position - 1);
            removed.add(removedCard);
            i++;
        }

        for (Card removedCard : removed) {
            p.removeCard(removedCard);
            dealToPlayer();
        }

        return exchangeCount > 0;
    }

    public void dealToPlayer() {
        Card dealt = null;
        boolean alreadyPresent = true;

        while (alreadyPresent) {
            dealt = cards.deal();

            if (dealt.getSuit() == 0 && dealt.getValue() == 0) {
                cards.reset();
                dealt = cards.deal();
            }

            alreadyPresent = false;

            for (Card c : p.getHand()) {
                if (c.compareTo(dealt) == 0) {
                    alreadyPresent = true;
                }
            }
        }

        p.addCard(dealt);
    }

    public String checkHand(ArrayList<Card> hand) {
        // This method evaluates the poker hand and returns a description

        if (checkRoyalFlush(hand))
            return "Royal Flush";
        else if (checkStraightFlush(hand))
            return "Straight Flush";
        else if (checkFourOfAKind(hand))
            return "Four of a Kind";
        else if (checkFullHouse(hand))
            return "Full House";
        else if (checkFlush(hand))
            return "Flush";
        else if (checkStraight(hand))
            return "Straight";
        else if (checkThreeOfAKind(hand))
            return "Three of a Kind";
        else if (checkTwoPairs(hand))
            return "Two Pairs";
        else if (checkPair(hand))
            return "One Pair";
        else
            return "No pair";
    }

    public int getPayout(String hand) {
        // Calculate and return the payout based on the hand

        if (hand.equals("Royal Flush"))
            return 250;
        else if (hand.equals("Straight Flush"))
            return 50;
        else if (hand.equals("Four of a Kind"))
            return 25;
        else if (hand.equals("Full House"))
            return 6;
        else if (hand.equals("Flush"))
            return 5;
        else if (hand.equals("Straight"))
            return 4;
        else if (hand.equals("Three of a Kind"))
            return 3;
        else if (hand.equals("Two Pairs"))
            return 2;
        else if (hand.equals("One Pair"))
            return 1;
        else
            return 0;
    }

    public boolean checkStraight(ArrayList<Card> hand) {
        // Check if the hand has a straight

        int count = 0;

        for (int i = 1; i < hand.size(); i++) {
            int thisValue = hand.get(i).getValue();
            int prevValue = hand.get(i - 1).getValue();

            if (thisValue == prevValue + 1) {
                count++;
            }
        }

        if (count == 4) {
            return true;
        } else {
            return checkHighAceStraight(hand);
        }
    }

    public boolean checkHighAceStraight(ArrayList<Card> hand) {
        // Check for a straight with an Ace as a high card

        int count = 0;

        for (int i = 2; i < hand.size(); i++) {
            int thisValue = hand.get(i).getValue();
            int prevValue = hand.get(i - 1).getValue();

            if (thisValue == prevValue + 1) {
                count++;
            }
        }

        if (count == 3) {
            int firstValue = hand.get(0).getValue();
            int lastValue = hand.get(hand.size() - 1).getValue();

            if (firstValue == 1 && lastValue == 13) {
                return true;
            }
        }

        return false;
    }

    public boolean checkFlush(ArrayList<Card> hand) {
        // Check for a flush

        int count = 0;

        for (int i = 1; i < hand.size(); i++) {
            int prevSuit = hand.get(i - 1).getSuit();
            int thisSuit = hand.get(i).getSuit();

            if (prevSuit == thisSuit) {
                count++;
            }
        }

        if (count == 4) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkRoyalFlush(ArrayList<Card> hand) {
        // Check for a Royal Flush

        return checkFlush(hand) && checkHighAceStraight(hand);
    }

    public boolean checkStraightFlush(ArrayList<Card> hand) {
        // Check for a Straight Flush

        return checkFlush(hand) && checkStraight(hand);
    }

    public int checkOfAKind(ArrayList<Card> hand, int x) {
        // Check for 'x' of a kind (e.g., pair, three of a kind, four of a kind)

        for (int i = 0; i < hand.size() - 1; i++) {
            int count = 1;
            Card current = hand.get(i);

            for (int j = i + 1; j < hand.size(); j++) {
                Card compare = hand.get(j);

                if (current.getValue() == compare.getValue()) {
                    count++;
                }

                if (count == x) {
                    return i;
                }
            }
        }

        return -1;
    }

    public boolean checkPair(ArrayList<Card> hand) {
        // Check for a pair

        return (checkOfAKind(hand, 2) != -1);
    }

    public boolean checkFourOfAKind(ArrayList<Card> hand) {
        // Check for four of a kind

        return (checkOfAKind(hand, 4) != -1);
    }

    public boolean checkThreeOfAKind(ArrayList<Card> hand) {
        // Check for three of a kind

        return (checkOfAKind(hand, 3) != -1);
    }

    public boolean checkTwoPairs(ArrayList<Card> hand) {
        // Check for two pairs

        int pairPos = checkOfAKind(hand, 2);

        if (pairPos < 2) {
            for (int i = pairPos + 2; i < hand.size() - 1; i++) {
                Card thisCard = hand.get(i);
                Card next = hand.get(i + 1);

                if (thisCard.getValue() == next.getValue()) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkFullHouse(ArrayList<Card> hand) {
        // Check for a full house

        int threeStart = checkOfAKind(hand, 3);

        if (threeStart == 0) {
            Card last = hand.get(hand.size() - 1);
            Card secondLast = hand.get(hand.size() - 2);

            if (last.getValue() == secondLast.getValue()) {
                return true;
            }
        } else if (threeStart == 2) {
            Card first = hand.get(0);
            Card second = hand.get(1);

            if (first.getValue() == second.getValue()) {
                return true;
            }
        }

        return false;
    }
}
