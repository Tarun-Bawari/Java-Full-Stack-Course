import java.util.Scanner;

class Guesser
{
	int guessNum;
	public int guessNumber(boolean error)
	{
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
	public int guessNumber(int playerNumber, boolean error)
	{
		Scanner sc = new Scanner(System.in);
		if(!error) {
			System.out.println("Player "+ playerNumber + " kindly guess the number");
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
	
	
	public void collectNumFromGuesser()
	{
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
	
	
	public void collectNumFromPlayer()
	{
		System.out.println("How many players wanted to participate in the Game:-");
		Scanner sc = new Scanner(System.in);
		noOfPlayers = sc.nextInt();
		players = new int[noOfPlayers][2];
		Player p = new Player();
		for(int i=0; i<noOfPlayers; i++) {
			boolean errorOccured = false;
			do {
				numFromPlayer = p.guessNumber(i+1,errorOccured);
				if(numFromPlayer<1 || numFromPlayer>50) {
					System.out.println("Please Enter value from range 1 to 50");
					errorOccured = true;
				}else {
					players[i][0]=i+1;
					players[i][1]=numFromPlayer;
					errorOccured = false;
				}
			} while(errorOccured);
		}
	}
	
	void compare()
	{
		String result="";
		int count = 0;
		for(int i=0; i<noOfPlayers; i++) {
			if(numFromGuesser == players[i][1]) {
				result+= "Player"+ String.valueOf(i+1) + ",";
				count++;
			}
		}
		if(count == 0) {
			result = "Game lost! No player guessed correctly";
		}else if(count == noOfPlayers) {
			result = "Game tied! All players guessed correctly";
		}else {
			result = result.substring(0,result.length()-1);
			result += " won the game!";
		}
		System.out.println(result);
	}
}

public class Launchgame {
	public static void main(String[] args) {
		Umpire u=new Umpire();
		u.collectNumFromGuesser();
		u.collectNumFromPlayer();
		u.compare();
	}
}