import java.util.Scanner;

public class Battleship
{
    // instance variables
    public static int numRow = 10;
    public static int numCol = 10;
    public static int numPlayerShips;
    public static int numOppShips;
    public static String[][] playerBoard = new String[numRow][numCol];
    public static String[][] oppBoard = new String[numRow][numCol];

    public static int[][] misses = new int[numRow][numCol];


    // creates the player board
    public static void createPlayerMap()
    {
        // Top part of map (x-axis)
        System.out.print("  ");
        for(int i = 0; i < numCol; i++)
                System.out.print(i);
        System.out.println();

        // Middle part of map (empty spaces and y-axis)
        for(int i = 0; i < playerBoard.length; i++) {
            for (int j = 0; j < playerBoard[i].length; j++) {
                playerBoard[i][j] = " ";
                if (j == 0)
                    System.out.print(i + "|" + playerBoard[i][j]);
                else if (j == playerBoard[i].length - 1)
                    System.out.print(playerBoard[i][j] + "|" + i);
                else
                    System.out.print(playerBoard[i][j]);
            }
            System.out.println();
        }

        // Bottom part of map (x-axis)
        System.out.print("  ");
        for(int i = 0; i < numCol; i++)
        {
            System.out.print(i);
        }
        System.out.println();
        
    
    }


    public static void createOppMap()
    {
        // First section of Ocean Map
        /*System.out.print("  ");
        for(int i = 0; i < numCol; i++)
                System.out.print(i);
        System.out.println();
        */
        //Middle section of Ocean Map
        for(int i = 0; i < oppBoard.length; i++) 
        {
            for (int j = 0; j < oppBoard[i].length; j++) 
            {
                oppBoard[i][j] = " ";
                /*if (j == 0)
                    System.out.print(i + "|" + oppBoard[i][j]);
                else if (j == oppBoard[i].length - 1)
                    System.out.print(oppBoard[i][j] + "|" + i);
                else
                    System.out.print(oppBoard[i][j]);
                */
            }
            //System.out.println();
        }

        //Last section of Ocean Map
        /*System.out.print("  ");
        for(int i = 0; i < numCol; i++)
        {
            System.out.print(i);
        }
        System.out.println();
        */
    
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

            if((x >= 0 && x < numRow) && (y >= 0 && y < numCol) && (playerBoard[x][y].equals(" ")))
            {
                playerBoard[y][x] = "X"; // Player ship marker
                i++;
            }
            else if((x >= 0 && x < numRow) && (y >= 0 && y < numCol) && playerBoard[x][y].equals("X"))
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
        System.out.println("\nOpponent is placing ships");

        //Placing five ships for oppoenet 
        Battleship.numOppShips = 5;
        for(int i = 1; i <= Battleship.numOppShips; )
        {
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);

            if((x >= 0 && x < numRow) && (y >= 0 && y < numCol) && (!playerBoard[y][x].equals("X")) && (oppBoard[y][x].equals(" ")))
            {
                oppBoard[y][x] = "O"; // Computer ship marker
                System.out.println("Opponent ship " + i + " placed.");
                i++;
            }
        }
        //displayMap();
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
        System.out.println("\nYour Turn to Guess");
        int x = -1;
        int y = -1;
        do
        {
            // getting player x and y guesses
            Scanner input = new Scanner(System.in);
            System.out.println("Enter X coordinate: ");
            x = input.nextInt();
            System.out.println("Enter Y coordinate: ");
            y = input.nextInt();

            if((x >= 0 && x < numRow) && (y >= 0 && y < numCol)) // In map guess
            {
                // player hits opponent's ship
                if(oppBoard[y][x].equals("O"))
                {
                    System.out.println("BOOM! You sunk their ship!");
                    oppBoard[y][x] = "!"; // Hit
                    playerBoard[y][x] = "!"; // Hit
                    --Battleship.numOppShips;
                }

                // player hits their own ship
                else if(playerBoard[y][x].equals("X"))
                {
                    System.out.println("You sunk your OWN ship!");
                    playerBoard[y][x] = "*"; // Sunk own ship marker
                    --Battleship.numPlayerShips;
                }

                // player misses
                else if(oppBoard[y][x].equals(" "))
                {
                    System.out.println("You missed.");
                    playerBoard[y][x] = "-";
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
        System.out.println("\nOpponent's Turn to Guess");
        // Guess co-ordinates
        int x = -1;
        int y = -1;
        do
        {
            // x and y coordinate randomizer
            x = (int)(Math.random() * 10);
            y = (int)(Math.random() * 10);

            if((x >= 0 && x < numRow) && (y >= 0 && y < numCol)) // valid guess
            {
                // opponent hits player board
                if(playerBoard[y][x].equals("X"))
                {
                    System.out.println("BOOM! The opponent sunk one of your ships!");
                    playerBoard[y][x] = "*";
                    --Battleship.numPlayerShips;
                }

                // opponent hits their own board
                else if(oppBoard[y][x].equals("O"))
                {
                    System.out.println("The opponent has sunk their own ship.");
                    playerBoard[y][x] = "!";
                    oppBoard[y][x] = "!";
                    --Battleship.numOppShips;
                }

                // opponent misses 
                else if(playerBoard[y][x].equals(" ") || playerBoard[y][x].equals("-"))
                {
                    System.out.println("Opponent missed.");

                    oppBoard[y][x] = "-";
                    
                    if(Battleship.misses[y][x] != 1)
                    {
                        misses[y][x] = 1;
                    }
                    
                }
            }
        }while((x < 0 || x >= numRow) || (y < 0 || y >= numCol));

    }

    // end game method
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

    // display player board method
    public static void displayMap()
    {
        System.out.println();

        // Top part of map (x-axis)
        System.out.print("  ");
        for(int i = 0; i < numCol; i++)
        {
            System.out.print(i);
        }
        System.out.println();

        // Middle part of map (empty spaces & y-axis)
        for(int x = 0; x < playerBoard.length; x++)
        {
            System.out.print(x + "|");

            for(int y = 0; y < playerBoard[x].length; y++)
            {
                System.out.print(playerBoard[x][y]);
            }
            System.out.println("|" + x);
        }

        // Bottom part of map (x-axis)
        System.out.print("  ");
        for(int i = 0; i < numCol; i++)
        {
            System.out.print(i);
        }
        System.out.println();

    }

    // testing to see if opponent board is correct
    public static void displayOppMap()
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
        for(int y = 0; y < oppBoard.length; y++)
        {
            System.out.print(y + "|");

            for(int x = 0; x < oppBoard[y].length; x++)
            {
                System.out.print(oppBoard[y][x]);
            }
            System.out.println("|" + y);
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