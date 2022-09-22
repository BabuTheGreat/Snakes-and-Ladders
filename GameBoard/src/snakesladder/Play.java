package snakesladder;

public class Play {
	//Driver Method
		public static void main(String[] args) {
			//Initializing board
			LadderandSnake board = new LadderandSnake();
			//Initializing amount of players and their rank
			LadderandSnake pl = new LadderandSnake(0);
			
			
			
			//Boolean to determine when game is still playing or not
			boolean game=true;
			//Which player turn(idx) it is
			int playeridx=0;
			//Array to store each players' positions
			int[]positions=new int[pl.players];
			pl.Snakes();
			pl.Ladders();
			int []ply=pl.getPlayers();
			
			//Playing the game through while loop
			while(game)
			{	
				System.out.println();
				int currplayer=ply[playeridx];
				
				int roll=pl.takeTurn();
				
				game=pl.play(currplayer,roll,positions);
				
				//When a player reaches 100th tile exactly
				if(game==false)
				{
		
					System.out.println("\nCONGRATULATIONS PLAYER " + currplayer + "!!\n\nYOU HAVE WON THE GAME!!\n\nHOPE YOU ENJOYED!!");
					System.exit(0);
				}
				
				//Set up for next player
				playeridx++;
				
				//Going back to first player
				if(playeridx==pl.players)
				{
					playeridx=0;
					System.out.println();
				}
				
			}
		
			
			

			
		
			
			

		}

	}
