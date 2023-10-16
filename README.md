# Poker-Game
##Description
The Video Poker Game is a Java program that simulates a popular casino game. It allows players to play a variation of poker where they aim to create the best possible poker hand from a set of initial cards. The game follows standard poker rules and provides payouts based on the strength of the player's hand.

The game includes the following key elements:
- Shuffling: The deck of 52 cards is shuffled at the beginning of each game to ensure randomness.
- Initial Deal: The player pays one token to start the game and is dealt five cards.
- Card Exchange: The player can choose to exchange any number of cards from their initial hand.
- Hand Evaluation: The player's hand is evaluated and categorized into poker hand types, such as pairs, straights, flushes, and more.
- Payouts: The player receives a payout based on the strength of their hand. Payouts are predefined, with values corresponding to specific hand types.
- Game Continuation: The game continues as long as the player wants to play and has tokens in their bankroll. If the player runs out of tokens, the game ends.

##Files
- PokerTest.java: Serves as the entry point for playing the Video Poker game. It allows players to start the game with or without specific command-line arguments.
- Player.java: Represents the player in the Video Poker game. It manages the player's hand, bankroll, bets, and winnings.
- Game.java: Contains the core logic of the Video Poker game. It handles deck management, card dealing, player actions, hand evaluation, and payouts.
- Deck.java: Represents the deck of cards used in the Video Poker game. It handles shuffling and dealing of cards.
- Card.java: Represents individual playing cards in the game. It includes suit and value information and supports card comparison.

##How to Use
- Compile the Java source files using a Java compiler (e.g., javac).
- Run the program by executing the PokerTest class.
- Follow the on-screen prompts to play the Video Poker game.

Error Handling
The program includes error handling to ensure that user inputs are validated and that any errors or mismatches in the game logic are reported to the player.

Future Enhancements
- Building a graphical user interface (GUI) for a more user-friendly experience.
- Adding multiplayer functionality to allow multiple players to compete.
- Customizing payout rules to match specific casino regulations.
Enjoy the Game!

If you encounter any issues or have suggestions for improvements, please feel free to provide feedback or open an issue on the GitHub repository.
