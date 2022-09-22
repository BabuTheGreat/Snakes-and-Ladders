package snakesladder;

import java.util.Random;
import java.util.Scanner;

/* *Belal Abu-Thuraia 40209178
 * February 7th 2022
 * @author Belal Abu-Thuraia
 */
public class LadderandSnake {
	//Game board characteristics and constants (size, snakes, and ladders)
	int row=10;
	int col=10;
	int num_of_s=8;
	int num_of_l=9;
	int [][] board;
	int [][] snakes;
	int [][] ladders;
	int players;
	int []player;
	Scanner key=new Scanner(System.in);
	
	/**Constructor to initialize board, snakes, and ladders 
	 * 
	 */
	public LadderandSnake()
	{	
	System.out.println("Created by: BelalsGaming\n");
	System.out.println("\n|----------Welcome to Snakes&Ladders-----------|\n");	
	board=new int[row][col];
	//Loop specifically for top row of board since it has an integer with 3 digits
	for(int top=100; top>90;top--)
	{
		if (top<100&&top>91) 
		{
			System.out.print(top+"   ");
			board[9][top-92]=top-1;
		}
		else
		{
			System.out.print(top+"  ");
			board[9][9]=100;
		}
			
	}
	System.out.println();
	System.out.println();
	//Loop so each row can correspond and look like the actual snakes and ladders board
	for(int r = row-2;r>0;r--)
	{
		if(r%2==0)
		{
			for(int c = 0; c<col; c++) {
				board[r][c]=(r)*row+(c+1);
				System.out.print(board[r][c]+"   ");
				}
		}
		else 
		{
			for(int c = col-1; c>=0; c--) {
				board[r][c]=(r)*row+(c+1);
				System.out.print(board[r][c]+"   ");
				}
		}

		System.out.println();
		System.out.println();
	}
	//Loop specifically for bottom row of board since it has single digit integers
	for(int o=1;o<11;o++)
	{
		System.out.print(o+"    ");
		board[0][o-1]=o;
	}
	System.out.println();
	System.out.println("|---------------Hope you enjoy!!---------------|");
	
	
	Snakes();
	Ladders();
	
		
	}

	
	/**Parameterized constructor to set the amount of players for game
	 * 
	 * @param p 
	 */
	public LadderandSnake(int p)
	{

		players=p;
		//Prompting use to enter number of players between 2-4
		System.out.print("\n\nEnter the # of players for your game – Number must be between 2 and 4 inclusively: ");
		int counter=0;
		players=key.nextInt();
		//Loop if for user does not enter value between 2 and 4. Limit of 4 attempts
		while(players<2||players>4||counter>3)
		{
			System.out.println("Bad Attempt "+(counter+1) +" - Invalid # of players. Please enter a # between 2 and 4 inclusively:");
			players=key.nextInt();
			counter++;
			if (counter==2) 
			{
				System.out.println("Bad Attempt "+(counter+1)+" - Invalid # of players. Please enter a # between 2 and 4 inclusively. This is your last attempt:");
				players=key.nextInt();
				counter++;
			}
			
			if(counter>3)
			{
				System.out.println("Bad Attempt 4! You have exhausted all your chances. Program will terminate! ");
				System.exit(0);
			}
		}
		//Setting size of player array in accordance to number of players user has enters
		player=new int[players];
		System.out.println("\nGame is Played by "+players+ " players;");
		System.out.println();
		playersorder();
		
	
	}
	/**Method to organize the players in 
	 * descending order according to each player's 
	 * diceroll. And for duplicates to re-roll and 
	 * fight for the higher rank
	 */
	public void playersorder()
	{	//Array to store each player's roll
		int []roll = new int [players];
		//Variables used for sorting code
		int max=0;
		int keyStart = 0;
		int keyEnd = 0;
		int keyEndHold = -1;
		int keyValue;
		int temp;
		System.out.println("Now deciding which player will start playing; ");
		System.out.println();
		//Filling players array with their player number, and making another array for their respective first rolls
		for (int i=0; i<player.length;i++)
		{
			player[i]=i+1;
			roll[i]=flipDice();
			System.out.println("Player " + player[i] + " got a dice value of " + roll[i] + "\n");
		}
		// Sorting players depending what their first rolls were in descending order
		for (int i=0; i<players-1;i++)
		{
			max=roll[i];
			for(int j=i;j<players;j++)
			{
				if(roll[j]>max)
				{
					temp=roll[j];
					roll[j]=roll[i];
					roll[i]=temp;
					
					temp=player[j];
					player[j]=player[i];
					player[i]=temp;
				}
				max=roll[i];
			}
		}
		//Treat ties as sub arrays, make them re-roll and sort them 
		while(keyStart<players)
		{
			keyValue=roll[keyStart];
			
			//Determining if there is a tie, what is the value (keyValue) and where does the tie begin and end (is a two-way tie, or a three way tie)
			for(int i=keyStart; i<players;i++)
			{
				if(roll[i]==keyValue)
				{
					keyEnd= i;
				}
			}
			//If no tie was recorded
			if((keyStart==keyEnd||(keyStart==keyEndHold)))
			{
				keyStart++;
			}
			//If there is a tie then re-roll
			else
			{
				System.out.print("A tie was achieved between Player "+player[keyStart]);
				for (int i = keyStart+1; i<keyEnd+1; i++)
				{
					System.out.print(" and Player " + player[i]);
				}
				System.out.println(". Attempting to break the tie\n");
				
				//re-roll to battle the tie out
				for(int i= keyStart; i<keyEnd+1; i ++)
				{
					roll[i]=flipDice();
					System.out.println("Player " + player[i] + " got a dice value of " + roll[i]);
					System.out.println();
				}
				for (int i=keyStart; i<keyEnd;i++)
				{
	                max = roll[i];
	                
	                for(int j = i + 1; j < keyEnd + 1; j++) {
	                    if(roll[j] > max) {
	                        temp = roll[j];
	                        roll[j] = roll[i];
	                        roll[i] = temp;
	                        
	                        temp = player[j];
	                        player[j] = player[i];
	                        player[i] = temp;
	                        
	                        max = roll[i];
	                    }
	                }
	            }
				keyEndHold=keyEnd;
			}
		}
		System.out.print("Reached final decision on order of playing: ");
		for(int i=0;i<players;i++)
		{
			System.out.print("Player " + player[i] + "  ");
		}
		System.out.println("\n");
		
	}
	/**Method to return the array of players
	 * @return player array
	 */
	
	public int[] getPlayers()
	{
		return player;
	}

	/**
	 * Method for dice roll
	 * @return dice int
	 */
	public int flipDice()
	{
		Random dice = new Random();
		return dice.nextInt(6)+1;
	}
	/**
	 * Method play to run engine of game
	 * @param current	Which player's turn it is
	 * @param dice		Random(1-6) the player rolls
	 * @param pos		The position the player is currently on
	 * @return 			Boolean value for loop in main method
	 */
	public boolean play(int current,int dice,int[]pos)
	{
		Snakes();
		Ladders();
		//Array for position of each player 
		pos[current-1]+=dice;
		boolean turn=true;
		//When player wins=game over
		if (pos[current-1] == 100)
		{
			turn=false; 
		}
		//When player goes over 100th tile
		else if(pos[current-1]>100)
		{
			int old=pos[current-1]-dice;
			int dif= dice-(100-old);
			pos[current-1]=100-dif;
			turn=true;
		}
		//Loop to compare player's current position with all the heads of the snakes on the board
		for(int i=0;i<num_of_s;i++)
		{
			if(snakes[i][0]==pos[current-1])
			{
				System.out.print("Oh no Player " +current+ "! You landed on " + snakes[i][0]+" and got bit by a snake! Go down to ")
;				pos[current-1]=snakes[i][1];
				System.out.println(pos[current-1]);
				System.out.println();
				turn=true;
			}
		}
		//Loop to compare player's current position with every ladder on the board
		for (int i=0;i<num_of_l;i++)
		{
			if(ladders[i][0]==pos[current-1])
			{
				System.out.print("Good job Player " + current + "! You landed on " + ladders[i][0] +" found a ladder! Climb to ");
				pos[current-1]=ladders[i][1];
				System.out.println(pos[current-1]);
				System.out.println();
				if (pos[current-1]==100)
				{
					turn=false;
				}
				else
				{
					turn=true;
				}
			}
		}
		
		
		System.out.print("Player "+current+ " got a dice value of "+ dice+". Now in square "+ pos[current-1] + "\n");
		return turn;


	}
	
	/**Method to return random numb value (diceroll)
	 * 
	 * @return diceroll
	 */
	public int takeTurn()
	{
		int turn=flipDice();
		return turn;
	}
	
	
	
	/**Making method to know where the snakes are and its size
	 * 
	 */
	public void Snakes()
	{
		snakes=new int [num_of_s][2];
		
		snakes[0][0]=16;
		snakes[0][1]=6;
		snakes[1][0]=48;
		snakes[1][1]=30;
		snakes[2][0]=64;
		snakes[2][1]=60;
		snakes[3][0]=79;
		snakes[3][1]=19;
		snakes[4][0]=93;
		snakes[4][1]=68;
		snakes[5][0]=95;
		snakes[5][1]=24;
		snakes[6][0]=97;
		snakes[6][1]=76;
		snakes[7][0]=98;
		snakes[7][1]=78;
				
	}

	/**
	 * Making method to know where the ladders are and its dimensions
	 */
	public void Ladders()
	{
		ladders=new int [num_of_l][2];
		
		ladders[0][0]=1;
		ladders[0][1]=38;
		ladders[1][0]=4;
		ladders[1][1]=14;
		ladders[2][0]=9;
		ladders[2][1]=31;
		ladders[3][0]=21;
		ladders[3][1]=42;
		ladders[4][0]=28;
		ladders[4][1]=84;
		ladders[5][0]=36;
		ladders[5][1]=44;
		ladders[6][0]=51;
		ladders[6][1]=67;
		ladders[7][0]=71;
		ladders[7][1]=91;
		ladders[8][0]=80;
		ladders[8][1]=100;
	}
}
	