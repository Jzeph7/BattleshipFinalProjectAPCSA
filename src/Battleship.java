import java.util.*;

public class Battleship
{
    // instance variables
    public static int numRow = 10;
    public static int numCol = 10;
    public static int numPlayerShips;
    public static int numOppShips;
    public static String[][] board = new String[numRow][numCol];
    public static int[][] misses = new int[numRow][numCol];


    // creates the board
    public static void createMap()
    {
        //First section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCol; i++)
                System.out.print(i);
        System.out.println();

        //Middle section of Ocean Map
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = " ";
                if (j == 0)
                    System.out.print(i + "|" + board[i][j]);
                else if (j == board[i].length - 1)
                    System.out.print(board[i][j] + "|" + i);
                else
                    System.out.print(board[i][j]);
            }
            System.out.println();
        }

        //Last section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCol; i++)
        {
            System.out.print(i);
        }
        System.out.println();
        
    
    }

    // places player ships
    public static void placePlayerShip()
    {
        Scanner input = new Scanner(System.in);

        System.out.println("\nPlace your ships:");
        
        // Placing five ships for player
        Battleship.numPlayerShips = 5;
        for(int i = 1; i <= Battleship.numPlayerShips; )
        {
            System.out.print("Enter X coordinate for your " + i + " ship: ");
            int x = input.nextInt();
            System.out.print("Enter your Y coordinate for your " + i + " ship: ");
            int y = input.nextInt();
            System.out.println();

            if((x >= 0 && x < numRow) && (y >= 0 && y < numCol) && (board[x][y] == " "))
            {
                board[y][x] = "X"; // Player ship marker
                i++;
            }
            else if((x >= 0 && x < numRow) && (y >= 0 && y < numCol) && board[x][y] == "X")
            {
                System.out.println("You can't place two or more ships on the same coordinate.");
                continue;
            }
            else if((x < 0 || x >= numRow) || (y < 0 || y >= numCol))
            {
                System.out.println("You can't place ships outside the map.");
                continue;
            }
        }
        displayMap();

    }

    // places opponents ships
    public static void placeOppShip()
    {
        System.out.println("\nComputer is placing ships");

        //Placing five ships for oppoenet 
        Battleship.numOppShips = 5;
        for(int i = 1; i <= Battleship.numOppShips; )
        {
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);

            if((x >= 0 && x < numRow) && (y >= 0 && y < numCol) && (board[x][y] == " "))
            {
                board[y][x] = "O"; // Computer ship marker
                System.out.println(i + ". ship placed");
                i++;
            }
        }
        displayMap();
    }

    // battle method
    public static void battle()
    {
        playerTurn();
        oppTurn();

        displayMap();

        System.out.println("Your ships: " + Battleship.numPlayerShips + " | Computer ships: " + Battleship.numOppShips);
        System.out.println();
    }

    // player turn to guess
    public static void playerTurn()
    {
        System.out.println("\nYour Turn");
        int x = -1;
        int y = -1;
        do
        {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter X coordinate: ");
            x = input.nextInt();
            System.out.println("Enter Y coordinate: ");
            y = input.nextInt();

            if((x >= 0 && x < numRow) && (y >= 0 && y < numCol)) // In map guess
            {
                if(board[y][x] == "O")
                {
                    System.out.println("BOOM! You sunk their ship!");
                    board[y][x] = "!"; // Hit
                    --Battleship.numOppShips;
                }
                else if(board[y][x] == "X")
                {
                    System.out.println("You sunk your OWN ship!");
                    board[y][x] = "*"; // Sunk own ship marker
                    --Battleship.numPlayerShips;
                    ++Battleship.numOppShips;
                }
                else if(board[y][x] == " ")
                {
                    System.out.println("You missed.");
                    board[y][x] = "-";
                }
            }
            else if((x < 0 || x >= numRow) || (y < 0 || y >= numCol)) // outside map
            {
                System.out.println("You can't place ships outside the map.");
            }
            


        }while((x < 0 || x >= numRow) || (y < 0 || y >= numCol)); // keep asking until valid guess

        
    }

    // computer turn to guess
    public static void oppTurn()
    {
        System.out.println("\nOpponent's Turn");
        // Guess co-ordinates
        int x = -1;
        int y = -1;
        do
        {
            x = (int)(Math.random() * 10);
            y = (int)(Math.random() * 10);

            if((x >= 0 && x < numRow) && (y >= 0 && y < numCol)) // valid guess
            {
                if(board[y][x] == "X")
                {
                    System.out.println("BOOM! The opponent sunk one of your ships!");
                    board[y][x] = "*";
                    --Battleship.numPlayerShips;
                    ++Battleship.numOppShips;
                }
                else if(board[y][x] == "O")
                {
                    System.out.println("The oppoent has sunk one");
                    board[y][x] = "!";
                }
                else if(board[y][x] == " ")
                {
                    System.out.println("Opponent missed.");
                    
                    if(Battleship.misses[y][x] != 1)
                    {
                        misses[y][x] = 1;
                    }
                    
                }
            }
        }while((x < 0 || x >= numRow) || (y < 0 || y >= numCol));

    }

    // end game
    public static void gameOver()
    {
        System.out.println("Your ships: " + Battleship.numPlayerShips + " | Opponent ships: " + Battleship.numOppShips);
        if(Battleship.numPlayerShips > 0 && Battleship.numOppShips <= 0)
        {
            System.out.println("Congrats! You won the battle!");
        }
        else
        {
            System.out.println("Sorry, you lost the battle.");
        }
    }

    // display board
    public static void displayMap()
    {
        System.out.println();

        // First section of map
        System.out.print("  ");
        for(int i = 0; i < numCol; i++)
        {
            System.out.print(i);
        }
        System.out.println();

        // Middle section of map
        for(int x = 0; x < board.length; x++)
        {
            System.out.print(x + "|");

            for(int y = 0; y < board[x].length; y++)
            {
                System.out.print(board[x][y]);
            }
            System.out.println("|" + x);
        }

        // Last section of map
        System.out.print("  ");
        for(int i = 0; i < numCol; i++)
        {
            System.out.print(i);
        }
        System.out.println();

    }


    
     
}