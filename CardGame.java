import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

/**
 * The class <b>CardGame</b> is an implementation of the
 * ``Old Maid'' card game, based on the Python implementation
 * given in ITI1020
 *
 * @author Taha Khaldi
 *
 */

public class CardGame{

	/**
	 * Array used to store the full deck of cards,
	 */
	private static String[] deck;

	/**
	 * The current number of cards in the full deck of cards
	 */
	private static int sizeDeck;

	/**
	 * Array used to store the player's deck of cards
	 */
	private static String[] playerDeck;

	/**
	 * The current number of cards in the player's deck of cards
	 */
	private static int sizePlayerDeck;

	/**
	 * Array used to store the computer's deck of cards
	 */
	private static String[] computerDeck;

	/**
	 * The current number of cards in the computer's deck of cards
	 */
	private static int sizeComputerDeck;


	/**
	 * An instance of java.util.Scanner, which will get input from the
	 * standard input
	 */
	private static Scanner sc;

	/**
	 * An instance of java.util.Random, to generate random numbers
	 */
	private static Random generator;

	/** 
	 * Constructor of the class. Creates the full deck of cards
	 */

	public  CardGame(){
		sc = new Scanner(System.in);
		generator = new Random();

		String[] suits = {"\u2660", "\u2661", "\u2662", "\u2663"};
		String[] ranks = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		sizeDeck = suits.length * ranks.length - 1;
		deck = new String[sizeDeck];
		int index = 0;
		for(int i =0 ; i < ranks.length; i++){
			for(int j =0 ; j < suits.length; j++){
				if(!(ranks[i]=="Q" && suits[j]=="\u2663")){
					deck[index++]= ranks[i] + " of " + suits[j];
				}
			}
		}
	}

	/** 
	 * Waits for user input
	 */
	private static void waitForUserInput(){
		sc.nextLine();
	}

	/**
	 *  Deals the cards, taking sizeDeck cards out of deck, and deals them
	 *  into playerDeck and computerDeck, starting with playerDeck
	 */
	private static void dealCards(){
		playerDeck = new String[sizeDeck];
		computerDeck = new String[sizeDeck];

		String item;

		while (sizeDeck > 1) {
			item = deck[sizeDeck - 1];
			sizeDeck = ArrayStringsTools.removeItemByIndex(deck, sizeDeck, sizeDeck - 1);
			sizePlayerDeck = ArrayStringsTools.appendItem(playerDeck, sizePlayerDeck, item);

			item = deck[sizeDeck - 1];
			sizeDeck = ArrayStringsTools.removeItemByIndex(deck, sizeDeck, sizeDeck - 1);
			sizeComputerDeck = ArrayStringsTools.appendItem(computerDeck, sizeComputerDeck, item);
		}

		// deal the last remaining card
		item = deck[sizeDeck - 1];
		sizeDeck = ArrayStringsTools.removeItemByIndex(deck, sizeDeck, sizeDeck - 1);
		sizePlayerDeck = ArrayStringsTools.appendItem(playerDeck, sizePlayerDeck, item);
	}

	/**
	 *  Removes all the pairs of cards from the array deckOfCards, that currently
	 * contains currentSize cards. deckOfCards is unsorted. It should also not
	 * be sorted once the method terminates. 
	 *
	 *   @param deckOfCards the array of Strings representing the deck of cards
	 *   @param currentSize the number of strings in the arrayOfStrings,
	 *			stored from arrayOfStrings[0] to arrayOfStrings[currentSize-1] 
	 *   @return the number of cards in deckOfCards once the pair are removed
	 */
	private static int removePairs(String[] deckOfCards, int currentSize){
		String[] noPairs = new String[deckOfCards.length];
		int noPairsSize = 0;

		ArrayStringsTools.sortArray(deckOfCards, currentSize);

		int i = 0;
		while (i < currentSize - 1) {
			String card1 = deckOfCards[i];
			String card2 = deckOfCards[i + 1];
			if (card1.charAt(0) == card2.charAt(0) && card1.charAt(1) == card2.charAt(1)) { // if 10s need to compare first two chars
				i++;
			} else if (card1.charAt(0) == card2.charAt(0)) { // if not 10, it is enough to compare first chars
				i++;
			} else {
				noPairsSize = ArrayStringsTools.appendItem(noPairs, noPairsSize, deckOfCards[i]);
			}
			i++;
		}

		if (i == currentSize - 1) {
			noPairsSize = ArrayStringsTools.appendItem(noPairs, noPairsSize, deckOfCards[i]);
		}

		ArrayStringsTools.shuffleArray(noPairs, noPairsSize);

                System.arraycopy(noPairs, 0, deckOfCards, 0, noPairs.length );

		return noPairsSize;
	}

	/**
	 *  Get a valid index of a card to be removed from computerDeck.
	 *	Note: this method does not check that the input is indeed an integer and
	 *	will crash if something else is provided.
	 *  @return the valid input.
	 */
	private static int getValidInput(){
		System.out.print("I have " + sizeComputerDeck + " cards. If 1 stands for my first card and ");
		System.out.println(sizeComputerDeck + " for my last card, which of my cards would you like?");
		System.out.println("Give me an integer between 1 and " + sizeComputerDeck + ": ");
		int position = sc.nextInt();
		waitForUserInput();

		while (!(position >= 1 && position <= sizeComputerDeck)) {
			System.out.println("Invalid number. Please enter integer between 1 and " + sizeComputerDeck + ": ");
			position = sc.nextInt();
			waitForUserInput();
		}
		
		return position;
	}


	/**
	 *  The actual game, as per the Python implementation
	 */
	public static void playGame(){
		ArrayStringsTools.shuffleArray(deck, sizeDeck);
		dealCards();
		System.out.println("Hello. My name is Robot and I am the dealer.");
		System.out.println("Welcome to my card game!");
		System.out.println("Your current deck of cards is:");
	        System.out.println("");
		ArrayStringsTools.printArray(playerDeck, sizePlayerDeck);
	        System.out.println("");
		System.out.println("Do not worry. I cannot see the order of your cards");

		System.out.println("Now discard all the pairs from your deck. I will do the same.");
		sizeComputerDeck = removePairs(computerDeck, sizeComputerDeck);
		sizePlayerDeck = removePairs(playerDeck, sizePlayerDeck);

		int roundParity = 0;
		while (sizeComputerDeck > 0 && sizePlayerDeck > 0) {
			if (roundParity == 0) {
				System.out.println("***********************************************************");
				System.out.println("Your turn.");
				System.out.println("\nYour current deck of cards is:");
				System.out.println("");

				ArrayStringsTools.printArray(playerDeck, sizePlayerDeck);

				System.out.println("");
				int card_position = getValidInput();
				String item = computerDeck[card_position - 1];
				sizeComputerDeck = ArrayStringsTools.removeItemByIndex(computerDeck, sizeComputerDeck, card_position - 1); //this is valid since cards are unique

				// handled the four endings of ordinals in english
				String[] englishOrdinalsEnd = {"st", "nd", "rd", "th"};
				int ordIndex;
				if (card_position > 3) {
					ordIndex = 3;
				} else {
					ordIndex = card_position - 1;
				}

				System.out.println("You asked for my " + card_position + englishOrdinalsEnd[ordIndex] + " card.");

				System.out.println("Here it is. It is " + item);

				sizePlayerDeck = ArrayStringsTools.appendItem(playerDeck, sizePlayerDeck, item);
				System.out.println("\nWith " + item + " added, your current deck of cards is:");
				System.out.println("");
				ArrayStringsTools.printArray(playerDeck, sizePlayerDeck);
				System.out.println("");
				System.out.println("And after discarding pairs and shuffling, your deck is:");
				sizePlayerDeck = removePairs(playerDeck, sizePlayerDeck);
				System.out.println("");
				ArrayStringsTools.printArray(playerDeck, sizePlayerDeck);
				System.out.println("");
				roundParity = 1;
                                System.out.println("Press Enter to Continue.");
	                        waitForUserInput();
			} else {
				System.out.println("***********************************************************");
				System.out.println("My turn.\n");

				int cardIndex = generator.nextInt(sizePlayerDeck);
				String item = playerDeck[cardIndex];
				sizePlayerDeck = ArrayStringsTools.removeItemByIndex(playerDeck, sizePlayerDeck, cardIndex);
				sizeComputerDeck = ArrayStringsTools.appendItem(computerDeck, sizeComputerDeck, item);
				sizeComputerDeck = removePairs(computerDeck, sizeComputerDeck);

				// handled the four endings of ordinals in english
				String[] englishOrdinalsEnd = {"st", "nd", "rd", "th"};
				int ordIndex;
				if (cardIndex > 2) {
					ordIndex = 3;
				} else {
					ordIndex = cardIndex;
				}

				System.out.println("I took your " + (cardIndex + 1) + englishOrdinalsEnd[ordIndex] + " card.");

				roundParity = 0;
                                System.out.println("Press Enter to Continue.");
	                        waitForUserInput();
			}
		}

		if (sizeComputerDeck == 0) {
			System.out.println("***********************************************************");
			System.out.println("Ups. I do not have any more cards");
			System.out.println("You lost! I, Robot, win");
		} else {
			System.out.println("***********************************************************");
			System.out.println("Ups. You do not have any more cards");
			System.out.println("Congratulations! You, Human, win");
		}
	}


	/**
	 * The main method of this program. Creates the game object
	 * and calls the playGame method on it.
	 * @param args ignored
	 */    


	public static void main(String[] args){
                System.out.println("Beginning of Run of the Old Maid Card Game");
                System.out.println("");
		CardGame game = new CardGame();		
		game.playGame();
                System.out.println("");

	}
}