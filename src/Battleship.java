import java.util.Scanner;

public class Battleship
{
    // instance variables
    protected static int numRow = 10;
    protected static int numCol = 10;
    protected static int numPlayerShips;
    protected static int numOppShips;
    protected static String[][] board = new String[numRow][numCol];
    protected static int[][] misses = new int[numRow][numCol];


    // constructor
    /*
    public Battleship(int row, int col)
    {
        this.numRow = row;
        this.numCol = col;
    }
    */
    
    // creates the board
    public static void createMap()
    {
        // Top part of the map
        System.out.print("~");
        for(int i = 0; i < numCol; i++)
        {
            System.out.print(i);
        }
        System.out.println();

        // Middle part of the map
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; i < board[i].length; j++)
            {
                board[i][j] = "~"; // water sign marker
                if(j == 0)
                    System.out.print(i + "|" + board[i][j]);
                else if(j == board[i].length -1)
                    System.out.print(board[i][j] + "|" + i);
                else
                    System.out.print(board[i][j]);
            }
            System.out.println();
        }

        // Bottom part of the map
        System.out.print("~");
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
        for(int i = 1; i <= Battleship.numPlayerShips;)
        {
            System.out.print("Enter X coordinate for your " + i + " ship: ");
            int x = input.nextInt();
            System.out.print("Enter your Y coordinate for your " + i + " ship: ");
            int y = input.nextInt();

            if((x >= 0 && x < numRow) && (y >= 0 && y < numCol) && (board[x][y] == "~"))
            {
                board[x][y] = "X"; // Player ship marker
                i++;
            }
            else if((x >= 0 && x < numRow) && (y >= 0 && y < numCol) && (board[x][y] == "X"))
            {
                System.out.println("You can't place two or more ships on the same coordinate.");
            }
            else if((x < 0 || x >= numRow) || (y < 0 || y >= numCol))
            {
                System.out.println("You can't place ships outside the map.");
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

            if((x >= 0 && x < numRow) && (y >= 0 && y < numCol) && (board[x][y] == "~"))
            {
                board[x][y] = "O"; // Computer ship marker
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
                if(board[x][y] == "O")
                {
                    System.out.println("BOOM! You sunk their ship!");
                    board[x][y] = "!"; // Hit
                    --Battleship.numOppShips;
                }
                else if(board[x][y] == "X")
                {
                    System.out.println("You sunk your OWN ship!");
                    board[x][y] = "*"; // Sunk own ship marker
                    --Battleship.numPlayerShips;
                    ++Battleship.numOppShips;
                }
                else if(board[x][y] == "~")
                {
                    System.out.println("You missed.");
                    board[x][y] = "-";
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
                if(board[x][y] == "X")
                {
                    System.out.println("BOOM! The opponent sunk one of your ships!");
                    board[x][y] = "*";
                    --Battleship.numPlayerShips;
                    ++Battleship.numOppShips;
                }
                else if(board[x][y] == "O")
                {
                    System.out.println("The oppoent has sunk one");
                    board[x][y] = "!";
                }
                else if(board[x][y] == "~")
                {
                    System.out.println("Opponent missed.");
                    
                    if(Battleship.misses[x][y] != 1)
                    {
                        misses[x][y] = 1;
                    }
                    
                }
            }
        }while(())

    }

    // end game
    public static void gameOver()
    {

    }

    // display board
    public static void displayMap()
    {

    }


    
    

    
}