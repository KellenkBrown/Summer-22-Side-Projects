import java.util.*;
public class Blackjack {

	private String[] cards = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
	private ArrayList<String> cardPool = new ArrayList<String>();
	private ArrayList<String> playerHand = new ArrayList<String>();
	private ArrayList<String> cpuHand = new ArrayList<String>();
	
	public Blackjack() {
		this.generateCardPool(2);
	}
	
	public void dealToPlayer() {
		String cardDelt = this.dealCard();
		playerHand.add(cardDelt);
	}
	
	public void dealToCPU() {
		String cardDelt = this.dealCard();
		cpuHand.add(cardDelt);
	}
	
	public void printPlayerHand() {
		for (String card : playerHand) {
			this.printCard(card);
		}
	}
	
	public void printCpuHand() {
		for (String card : cpuHand) {
			this.printCard(card);
		}
	}
	
	public ArrayList<String> getPlayerHand() {
		return playerHand;
	}
	
	public ArrayList<String> getCpuHand() {
		return cpuHand;
	}
	
	public void generateCardPool(int numDecks) { //Adds each card String to ArrayList of proper size based on deck count
		ArrayList<String> cardPool = new ArrayList<String>();
		int poolSize = numDecks * 13;
		int currentDeck = 0;
		//currentDeck increments by 1 every time i = 13, signifying the start of a new deck
		for (int i = 0; i < poolSize; i++) {
			currentDeck = i % 13 == 0 && i != 0 ? currentDeck += 1 : currentDeck;
			cardPool.add(cards[i - (currentDeck * 13)]);
		}
		this.cardPool = cardPool;
	}
	
	public ArrayList<String> getCardPool() { //Returns the current card pool of cards that haven't been delt yet. (Only used for debugging)
		return cardPool;
	}
	
	public String dealCard() {
		int randomNum = (int)(Math.random() * cardPool.size());
		String cardDelt = cardPool.get(randomNum);
		cardPool.remove(randomNum);
		return cardDelt;
	}
	
	public void printCard(String cardValue) {
		int cardNum = -1;
		char cardDisplay = 'L';
		boolean facedownCard = false;
		
		switch (cardValue) {
		case "Two":
			cardNum = 2;
			break;
		case "Three":
			cardNum = 3;
			break;
		case "Four":
			cardNum = 4;
			break;
		case "Five":
			cardNum = 5;
			break;
		case "Six":
			cardNum = 6;
			break;
		case "Seven":
			cardNum = 7;
			break;
		case "Eight":
			cardNum = 8;
			break;
		case "Nine":
			cardNum = 9;
			break;
		case "Ten":
			cardNum = 10;
			break;
		case "Jack":
			cardDisplay = 'J';
			break;
		case "Queen":
			cardDisplay = 'Q';
			break;
		case "King":
			cardDisplay = 'K';
			break;
		case "Ace":
			cardDisplay = 'A';
			break;
		default: facedownCard = true; break;
		}
		
		for (int i = 0; i < 5; i++) {
			if ((cardNum != 10 || cardDisplay != 'L') && !facedownCard){
			for (int j = 0; j < 3; j++) {
				if (i == 0 || i == 4) {
				System.out.print("-");
				}
				else if (i != 0 && i != 5 && (j == 0 || j == 2)) {
				System.out.print("|");
				}
				else if (i == 2 && j == 1) {
					if (cardDisplay == 'L') {
						System.out.print(cardNum);
					}
					else {
						System.out.print(cardDisplay);
					}
				}
				else {
					System.out.print(" ");
				}
			}
			}
			else if (cardNum == 10) {
				for (int k = 0; k < 4; k++) {
					if (i == 0 || i == 4) {
						System.out.print("-");
					}
					else if (i != 0 && i != 5 && (k == 0 || k == 3)) {
						System.out.print("|");
					}
					else if (i == 2 && k == 1) {
						System.out.print(1);
					}
					else if (i == 2 && k == 2) {
						System.out.print(0);
					}
					else {
						System.out.print(" ");
					}
				}
			}
			else if (facedownCard) {
				for (int j = 0; j < 3; j++) {
					if (i == 0 || i == 4) {
						System.out.print("-");
					}
					else if (i != 0 && i != 4 && (j == 0 || j == 2)) {
						System.out.print("|");
					}
					else {
						System.out.print(" ");
					}
				}
			}
			System.out.println();
			}
		}
	
	/*public void printCard() { // This overloaded method is to print an empty card for the CPU's facedown card
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 3; j++) {
				if (i == 0 || i == 4) {
					System.out.print("-");
				}
				else if (i != 0 && i != 4 && (j == 0 || j == 2)) {
					System.out.print("|");
				}
				else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}*/
	
	public int getCardNumValue(String card) {
		int numValue;
		switch (card) {
		case "Two": numValue = 2; break;
		case "Three": numValue = 3; break;
		case "Four": numValue = 4; break;
		case "Five": numValue = 5; break;
		case "Six": numValue = 6; break;
		case "Seven": numValue = 7; break;
		case "Eight": numValue = 8; break;
		case "Nine": numValue = 9; break;
		case "Ten": 
		case "Jack": 
		case "Queen":
		case "King": numValue = 10; break;
		case "Ace": numValue = 11; break;
		default: numValue = 1; //Will be used as Ace low
		}
		return numValue;
	}
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to Blackjack");
		
		Blackjack newBlackjack = new Blackjack();	
	
		newBlackjack.dealToPlayer(); //These four deals is for the opening hand in blackjack
		newBlackjack.dealToCPU();
		newBlackjack.dealToPlayer();
		newBlackjack.dealToCPU();
		
		int userTotal = 0;
		int cpuTotal = 0;
		
		boolean splitAvailable = newBlackjack.getPlayerHand().get(0) == newBlackjack.getPlayerHand().get(1) ? true : false; //Gives the user "Split" input option if first two cards are the same 
		boolean userHasNextTurn = true;
		boolean userStands = false;
		boolean lastCardDelt = false;
		
		while (!lastCardDelt) {
			
		if (userStands && cpuTotal < 17) { //deals a card to the cpu if the user decides to stand and it's total is less than 17 
			newBlackjack.dealToCPU();
		}
			
		userTotal = 0; //resets player hand total
		cpuTotal = 0; //resets cpu hand total
			
		for (String userCard : newBlackjack.getPlayerHand()) { //calculates player hand total
				userTotal += newBlackjack.getCardNumValue(userCard);
		}
		for (String cpuCard: newBlackjack.getCpuHand()) { //calculates cpu hand total
			cpuTotal += newBlackjack.getCardNumValue(cpuCard);
		}
		
		if (userStands && cpuTotal >= 17) { //Keeps dealing to cpu until they hit 17 if the user stands
			lastCardDelt = true;
		}
		else if (userTotal >= 21) {
			lastCardDelt = true;
		}
			
		System.out.println("\nYour hand: (" + userTotal + ")");
		newBlackjack.printPlayerHand();
		
		// Prints either the CPU's full hand or hand with a facedown card
		if (userTotal < 21 && !userStands) { System.out.println("\nCPU's hand:");
		newBlackjack.printCard(newBlackjack.getCpuHand().get(0));
		newBlackjack.printCard("Facedown card");
		}
		else if (userTotal >= 21 || lastCardDelt) {
			System.out.println("\nCPU's hand:");
			newBlackjack.printCpuHand();
			userHasNextTurn = false;
		}
		
		
		
		if (userHasNextTurn) { //Prints a option menu to user if they still have another turn
		System.out.println("\nOption:\n1. Hit\n2. Stand");
		if (splitAvailable) System.out.println("3. Split");
		System.out.print("Choose:");
		int userChoice = scan.nextInt();
	
		if (userChoice == 1) {
			newBlackjack.dealToPlayer();
			
		}
		else if (userChoice == 2) {
			userStands = true;
		}
		
		}
		
		
		}
		
	
		
		
		
		if (userTotal > 21) {
			System.out.println("You busted. You lose.");
		}
		else if (userTotal <= 21 && userTotal > cpuTotal) {
			System.out.println("Your score: " + userTotal + " Dealer score: " + cpuTotal + " You win." );
		}
		else if (userTotal <= 21 && cpuTotal > userTotal) {
			System.out.println("Your score: " + userTotal + " Dealer score: " + cpuTotal + " You lose." );
		}
		
		
	}

}
