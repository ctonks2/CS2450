import java.util.Scanner;
import java.util.Random;

/**
 * 
 */

/**
 * @author Chantel Tonks Requirements: 1. Open in console 2. Know the word, can
 *         hardcode 3.Number of attempts :6 4. Display will have: none guessed
 *         words is underscore _ each guessed letter is printed all occurances
 *         of the letter are represented 5. Number of lives is displayed 6. User
 *         can guess letter 7. If letter is not part, reduce lives by 1 8. If
 *         word is guessed player wins, if lives = 0 player loses 9. Complete
 *         word prints at the end
 *
 */
public class hangman {

	/**
	 * @param args **TODO**
	 */

	// needed for 3.
	public static int wrong = 6;
	private static boolean gameActive = true;

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Random random = new Random();
		// Fields
		String word[] = { "utah", "goats", "ball" }; // array of words for random hang man
		gamePrompt(); // starts game with welcome
		int nextGame = input.nextInt();

		if (nextGame != 0) {

			while (gameActive) {

				int wordNumber = random.nextInt(word.length);
				int maxAttempt = 6; // total tries regardless of word
				char hiddenWord[] = word[wordNumber].toCharArray(); // separates word into individual letters to guess
				char gameSlot[] = new char[hiddenWord.length]; // "_ _ _ _" for each letter
				String gameWord = word[wordNumber];
				for (int i = 0; i < gameSlot.length; i++) // assigns _ for each letter not guessed
				{
					gameSlot[i] = '_';
				}

				boolean foundWord = false;
				int attempts = 0;

				System.out.println("Word:");
				while (!foundWord && attempts <= maxAttempt) {

					printGame(gameSlot);

					System.out.println("Enter in guess: ");
					char letter = input.next().charAt(0);
					attempts++; // incrementing attempts

					if (letter == '-') {
						foundWord = true;
						gameActive = false;
					} else {
						for (int i = 0; i < hiddenWord.length; i++) {
							if (hiddenWord[i] == letter) {
								gameSlot[i] = letter;
							}
						}
					}
					if (isWordFound(gameSlot)) {
						foundWord = true;
						System.out.println("You Won! The secret word was....\n " + gameWord);
					}

				}

				if (!foundWord) {
					System.out.println("Out of tries. The secret word was.... \n " + gameWord);
					System.out.println();
				}
				// System.out.println("Keep playing? Press 1 for next game, press 0 to end");
				// String gameActive = input.nextLine();
				gameActive = false;
			} // while statement ends
		}
		System.out.println("Play again? If so.... \n");
		gamePrompt(); // starts game with welcome
		nextGame = input.nextInt();
		System.out.println("Thanks for playing!");

	} // end of main

	private static void gamePrompt() {
		System.out.println("This is Hangman! \n" + "You have six guesses to find out the mystery word."
				+ "Press 1 to play OR 0 to quit");
	}

	public static void printGame(char array[]) // empty dashes for start
	{
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static boolean isWordFound(char[] array) {
		boolean checkGame = true;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == '_') {
				checkGame = false;
			}
		}
		return checkGame;
	}

}
