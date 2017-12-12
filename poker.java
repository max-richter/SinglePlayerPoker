import java.util.Scanner;


public class Program10 
{
	public static void main(String[]args)
	{
		Scanner stdIn = new Scanner(System.in);
		
		// Initialize Deck & Hand
		int[] deck;
		deck = new int[36];
		int[] hand;
		hand = new int[4];
		
		// Forces us into the loop with a true condition
		String contGame = "y";
		
		// Initializes and declares betting amount and chips
		int startingChips = 100;
		int chips = startingChips;
		int bet = 0;
		
		// keeps track of wins and losses
		int wins = 0;
		int losses = 0;
		
		// amount of chips won for each determination
		int quad = 6545;
		int trip = 51;
		int straight = 38;
		int twoPair = 22;
		int pair = 1;
		
		
		// displays welcome screen at the beginning of the game
		welcome();

		do
		{
			// Sets the game up
			initDeck(deck);
			shuffleDeck(deck, 100);
			dealHand(deck, hand);
			bet = setBet(bet, chips, stdIn);
			displayHand(hand);

			// Checks if the hand meets any of the rules
			if(isQuad(hand))
			{
				System.out.println();
				System.out.println("Congrats: You got a 4 of a kind and have won $" + bet);
				chips += (quad + bet);
				System.out.println("You currently have " + chips + " chips left.");
				++wins;
				
			
				
			}
			else if(isTrip(hand))
			{
				System.out.println();
				System.out.println("Congrats: You got a 3 of a kind and have won $" + bet);
				chips += (trip + bet);
				System.out.println("You currently have " + chips + " chips left.");
				++wins;
				

			}
			else if(isStraight(hand))
			{
				System.out.println();
				System.out.println("Congrats: You got a Straight and have won $" + bet);
				chips += (straight + bet);
				System.out.println("You currently have " + chips + " chips left.");
				++wins;
				

			}
			else if(is2Pair(hand))
			{
				System.out.println();
				System.out.println("Congrats: You got a Two Pair and have won $" + bet);
				chips += (twoPair + bet);
				System.out.println("You currently have " + chips + " chips left.");
				++wins;
				
			}
			else if(isPair(hand))
			{
				System.out.println();
				System.out.println("Congrats: You got a Pair and have won $" + bet);
				chips += (pair + bet);
				System.out.println("You currently have " + chips + " chips left");
				++wins;
				
			}
			else
			{
				System.out.println();
				System.out.println("Sorry: you got Bubkiss and have lost $" + bet);
				chips -= bet;
				System.out.println("You currently have " + chips + " chips left.");
				++losses;
				
			}
			// Ask the player if they want to play again
			contGame=continueGame(contGame, stdIn, chips);
			
			// If the user wants to end the game, the summary of the game is printed
			if(contGame.equalsIgnoreCase("n") || chips ==0)
			{
				// Prints the scores of the game
				endGameTotal(chips, wins, losses, startingChips);
				
				return;
			}
		} while((contGame.equalsIgnoreCase("y") || contGame.equalsIgnoreCase("n")) && chips>0 );


	}
	public static void initDeck(int[] deck)
	{
		int n = 0;
		for(int i = 0; i < deck.length; ++i)
		{
			deck[i] = n + 1;
			++n;
			if(n == 9)
			{	
				n = 0;
			}
		}

	}
	public static void shuffleDeck(int[] deck, int n)
	{ 
		for(int i = 0; i < n; ++i)
		{
			deck[(int)(Math.random() * (deck.length - 1) + 1)] = deck[(int)(Math.random() * (deck.length - 1) + 1)];
		}
	}
	public static void dealHand(int [] deck, int[] hand)
	{ 
		for(int i = 0; i < 4; ++i)
		{
			hand[i] = deck[i];
			sortHand(hand);
		}

	}
	public static void displayHand(int[] hand)
	{ 
		System.out.println("---------------------------\n");
		System.out.print("     Shuffling cards...");
		System.out.print("\n" + "\n");
		System.out.print("Let the cards fall where they may..." + "\n");
		for(int i = 0; i < 4; ++i)
		{

			System.out.print(hand[i]+" ");
		}
		System.out.println("\n");
	}
	public static boolean isQuad(int[] hand)
	{
		if(hand[0]==hand[1] && hand[2]==hand[3] && hand[0]==hand[3])
		{ 
			return true;
		}
		return false;

	}
	public static boolean isTrip(int[] hand)
	{
		if((hand[1]==hand[0] && hand[1]==hand[2]) || (hand[1]==hand[2] && hand[1]==hand[3]))
		{ 
			return true;
		}
		return false;

	}
	public static boolean isStraight(int[] hand)
	{
		if(hand[0] - 1 == hand[1] && hand[1] - 1 == hand[2] && hand[2] - 1 == hand[3])
		{        
			return true;
		}
		return false;

	}
	public static boolean is2Pair(int[] hand)
	{
		if(hand[0] == hand[1] && hand[2]==hand[3])
		{  
			return true;
		}
		return false;

	}
	public static boolean isPair(int[] hand)
	{
		if(hand[0] == hand[1] || hand[1]==hand[2] || hand[2]==hand[3])
		{  
			return true;
		}
		return false;

	}
	public static void sortHand(int[] hand)	
	{
		for (int i = 0; i < hand.length; ++i)
		{
			int maxLoc = i;
			for (int j = i+1; j < hand.length; ++j)
				if (hand[j] > hand[maxLoc])
					maxLoc = j;
			int tmp = hand[i];
			hand[i] = hand[maxLoc];
			hand[maxLoc] = tmp;
		}
	}
	public static void endGameTotal(int chips, int wins, int losses, int startAmt)
	{ 
		int netWins = chips - startAmt;
		System.out.println("   Thanks for playing");
		System.out.println("-------------------------");
		System.out.println("Hands played:\t " + (wins + losses));
		System.out.println("Wins:   \t " + wins);
		System.out.println("Losses: \t " + losses);
		System.out.println("Net wins/losses: \t$" + netWins+ "\n");

		if(wins>losses)
		{
			System.out.println();
			System.out.print("Good game!");
		}
		else
		{
			System.out.println();
			System.out.print("Sorry, better luck next time.");
		}
	}
	
	public static int setBet(int bet, int chips, Scanner stdIn)
	{	

		System.out.println("You have " + chips + " chips.\n");
		do
		{
			System.out.print("Place your bet [1, 100] : ");
			bet = stdIn.nextInt();
			System.out.println();
		} while(bet < 1 || bet > chips);
		
		return bet;
	}
	public static String continueGame(String contGame, Scanner stdIn, int chips)
	{ 
		do
		{
			System.out.println();
			System.out.print("Do you want to play again? [y,n] : ");
			contGame=stdIn.next();
			System.out.println();
		} while((!contGame.equalsIgnoreCase("y") && !contGame.equalsIgnoreCase("n")) && chips > 0);

		return contGame;
	}

	public static void welcome()
	{
		System.out.print("Welcome to 4 Card Poker" + "\n");
		System.out.print("    Your initial bank roll is $100.00" + "\n");
		System.out.print("+++++++++++++++++++++++++++++++++++++++++" + "\n" + "\n");
	}

}
