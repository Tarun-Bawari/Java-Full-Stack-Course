import java.util.Scanner;

class Guesser
{
	int guessNum;
	public int guessNumber(boolean error) {
		Scanner sc = new Scanner(System.in);
		if(!error) {
			System.out.println("Guesser kindly guess the number");
		}
		guessNum=sc.nextInt();
		return guessNum;	
	}	
}

class Player
{
	int pguessNum;
	public int guessNumber(int playerNumber, boolean error) {
		Scanner sc = new Scanner(System.in);
		if(!error) {
			System.out.println("Player"+ playerNumber + " kindly guess the number");
		}
		pguessNum=sc.nextInt();
		return pguessNum;
	}
}

class Umpire
{
	int numFromGuesser;
	int numFromPlayer;
	int noOfPlayers;
	int [][] players;
	int playersGuessedCorrect;
	int playersLeft;
	boolean gameEnd = false;
	String gameName;
	
	/** This method is used to get the no of Player who wanted to play the Guesser game **/
	public void getPlayer() {
		System.out.println("<-- Hello Aliens, Welcome to the Guesser Game! -->");
		System.out.println("<-- Guesser Game is Starting -->");
		System.out.println("How many players wanted to participate in the Game:-");
		Scanner sc = new Scanner(System.in);
		boolean errorOccured = false;
		do {
			noOfPlayers = sc.nextInt();
			if(noOfPlayers<1 || noOfPlayers>16) {
				System.out.println("Please Enter value from range 1 to 16");
				errorOccured = true;
			}else {
				errorOccured = false;
			}
		} while(errorOccured);
	}
	
	/** This method is used to play the Guesser game until it is finished **/
	public void gameStart() {
		while(playersLeft>0 && playersLeft<=16) {
			roundList();
		}
		System.out.println("<-- Guesser Game Ends -->");
		System.out.println("<-- Thanks for playing the Guesser Game, Hope you like it! -->");
	}
	
	/** This method is used to select which round we have to play **/
	public void roundList() {
		if(playersLeft<=16 && playersLeft>8) {
			System.out.println("<-- Guesser Game Round of " + String.valueOf(playersLeft) +  " is Starting -->");
			gameName = "<-- Round of 16 Results -->";
		}else if(playersLeft<=8 && playersLeft>4) {
			System.out.println("<-- Guesser Game Quarter Finale is Starting -->");
			gameName = "<-- Quarter Finale Results -->";
		}else if(playersLeft<=4 && playersLeft>2) {
			System.out.println("<-- Guesser Game Semi Finale is Starting -->");
			gameName = "<-- Semi Finale Results -->";
		}else if(playersLeft == 2) {
			System.out.println("<-- Guesser Game Finale is Starting -->");
			gameName = "<-- Finale Results -->";
		}else {
			gameName = "<-- Results -->";
		}
		collectNumFromGuesser();
		collectNumFromPlayer();
		compare();
	}
	
	/** This method is used to create a 2D array of size [noOfPlayers][4] and initialize it with some values **/
	/** 1 column represent the Player No. e.g. for Player1, column value is 1 **/
	/** 2 column represent the No. guessed by the Player e.g. if Player guessed 4, column value is 4. Default value is 0 **/
	/** 3 column represent if player is in the game or out of the game. If player is in the game, column value is 1. If player 
	    is out of the game, column value is 0. Default value is 1 because initially all players are in the game **/ 
	/** 4 column represent the Bonus Life provided to each Player. Each player has one bonus life that's why I am setting default
	    value as 1. If Player used his Lifeline, column value reduced to 0.**/
	public void collectNumOfPlayer() {
		getPlayer();
		players = new int[noOfPlayers][4];
		for(int i=0; i<noOfPlayers; i++) {
			players[i][0]=i+1;
			players[i][2]=1;   // 1 -> in the game, 0 -> out of game
			players[i][3]=1;   // 1 -> 1 LifeLine, 0 -> No LifeLine 
		}
		playersLeft = noOfPlayers;
		gameStart();
	}
	
	/** This method is used to collect Number from the Guesser **/
	public void collectNumFromGuesser() {
		Guesser g=new Guesser();
		boolean errorOccured = false;
		do {
			numFromGuesser = g.guessNumber(errorOccured);
			if(numFromGuesser<1 || numFromGuesser>50) {
				System.out.println("Please Enter value from range 1 to 50");
				errorOccured = true;
			}else {
				errorOccured = false;
			}
		} while(errorOccured);
	}
	
	/** This method is used to collect Number from the Player **/
	public void collectNumFromPlayer() {
		Player p = new Player();
		for(int i=0; i<noOfPlayers; i++) {
			if(players[i][2]==1) {                                 //player is in the game
				boolean errorOccured = false;
				do {
					numFromPlayer = p.guessNumber(i+1,errorOccured);
					if(numFromPlayer<1 || numFromPlayer>50) {
						System.out.println("Please Enter value from range 1 to 50");
						errorOccured = true;
					}else {
						players[i][1]=numFromPlayer;
						errorOccured = false;
					}
				} while(errorOccured);
			}
		}
	}
	
	/** This method is used to find out the results of the Guesser Game **/
	public void compare() {
		String result="Player ";
		playersGuessedCorrect = 0;
		for(int i=0; i<noOfPlayers; i++) {
			if(numFromGuesser == players[i][1] && players[i][2] !=0) {
				result+= String.valueOf(i+1) + ",";
				playersGuessedCorrect++;
			}else {
				players[i][2]=0;
			}
		}
		result = result.substring(0,result.length()-1);
		if(playersGuessedCorrect == playersLeft && noOfPlayers != 1) {
			gameEnd = true;
			playersLeft = 0;
			result = "Game tied! " + result + " won the game";
		}else if(playersGuessedCorrect>0 && noOfPlayers == 1) {
			gameEnd = true;
			playersLeft = 0;
			result+= " won the Guesser Game!";
		}else if(playersGuessedCorrect>0) {
			result += " won the current Round!";
		}
		System.out.println(gameName);
		if(playersGuessedCorrect == 0) {
			System.out.println("No Player won the current Round!");
		}else if(playersGuessedCorrect>0) {
			System.out.println(result);
		}
		if(!gameEnd) {
			getLifeLine();
		}
	}
	
	/** This method will provide Lifeline to the Player **/
	public void getLifeLine() {
		String eliminatedPlayers = "Player ";
		String remainingPlayers = "Player ";
		int removedPlayers = 0;
		for(int i=0; i<noOfPlayers; i++) {
			if(players[i][2]==1) {
				remainingPlayers += String.valueOf(i+1) + ",";
				continue;
			}
			if(players[i][2]==0) {
				if(players[i][3]==1) {
					remainingPlayers += String.valueOf(i+1) + ",";
					System.out.println("Lifeline provided to Player" + String.valueOf(i+1) + ", Lifeline Remained is 0!");
					players[i][2]=1;
					players[i][3]=0;
				}else {
					removedPlayers++;
					eliminatedPlayers += String.valueOf(i+1) + ",";
				}
			}
		}
		playersLeft = noOfPlayers-removedPlayers;
		remainingPlayers = remainingPlayers.substring(0,remainingPlayers.length()-1);
		eliminatedPlayers = eliminatedPlayers.substring(0,eliminatedPlayers.length()-1);
		if(playersLeft>0 && playersLeft != noOfPlayers) {
			System.out.println(eliminatedPlayers + " eliminated!");
		}
		if(playersLeft==1 && noOfPlayers>1) {
			playersLeft = 0;
			System.out.println(remainingPlayers + " won the Guesser Game!");
		}else if(playersLeft>0) {
			System.out.println(remainingPlayers + " go to the next Round!");
		}else if(playersLeft==0) {
			System.out.println("Game lost! No player guessed correctly");
		}
	}
}

public class Launchgame {
	public static void main(String[] args) {
		Umpire u = new Umpire();
		u.collectNumOfPlayer();
	}
}