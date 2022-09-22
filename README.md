# Snakes-and-Ladders
This is a small project I worked on implementing the classic snakes and ladders game board through Java. 

**The board**

 The first thing I worked on for this project is creating a board, 10x10. I decided to implement it through 2D arrays. Printing the board, however, was a bit tricky as I had to make sure the "tiles" go from left to right, on the first row, then right to left on the next row. 
 
 <img width="370" alt="Screen Shot 2022-09-22 at 5 42 32 PM" src="https://user-images.githubusercontent.com/99438576/191856673-665a7ae7-e438-497a-b8ea-9e7b201d296d.png">
 
 Moreover, I had to then add the snakes and the ladders on to the board. As most know, if you reach the head of the snake, you slide down to its tail. And if you reach a ladder, you climb up. Since the tiles were implemented through 2D arrays, I made sure every snake and ladder was applied to the board properly through conditional statements. 
 
 **The game**
 
 The game begins by asking how many players are playing (2 to 4). The user inputs a number within the range, then the entire game plays out by itself. Before the players start rolling, they must decide the order of players. Therefore, they must each roll a dice to decide who goes first, second, and so on. The higher the number, the better. If a tie occurs, the players which got the same die value must roll again to fight for the position they were tied for. This required an efficient sorting algorithm, which took time to completely implement without any errors. 
 
<img width="634" alt="Screen Shot 2022-09-22 at 6 09 34 PM" src="https://user-images.githubusercontent.com/99438576/191860250-62a60d56-b687-4942-befb-3bc75bbd026b.png">


 Once the order is set, the players must battle past the snakes and use the ladders to their advantage to reach the 100th tile. To win the game, you must exactly reach the 100th tile, if the player rolls a value which adds up to greater than a 100, then he must move back accordingly. For example, if player 1 is on tile 96 and rolls a 5, he must go 4 tiles up to a hundred, then minus 1, ending up in tile 99. Once a player reached the 100th tile, the game terminates, congratulating the player who has won.

<img width="384" alt="Screen Shot 2022-09-22 at 6 02 20 PM" src="https://user-images.githubusercontent.com/99438576/191859386-cb87b01d-9d5a-486e-b06d-f0acf25a05d1.png">
