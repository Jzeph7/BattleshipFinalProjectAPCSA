import java.util.Scanner;

public class Battleship
{
    // instance variables
    private static int numRow = 10;
    private static int numCol = 10;
    private static int numPlayerShips;
    private static int numOppShips;
    private static String[][] board = new String[numRow][numCol];
    private static int[][] misses = new int[numRow][numCol];


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
        System.out.print(" ");
        for(int i = 0; i < numCol; i++)
        {
            System.out.print(i);
        }
        System.out.println();

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; i < board[i].length; j++)
            {
                board[i][j] = "~";
                if(j == 0)
                    System.out.print(i + "x" + board[i][j]);
                 else if(j == board[i].length -1)
                    System.out.print()   


            }
        }


    }

    // places player ships
    public static void placePlayerShip()
    {

    }

    // places opponents ships
    public static void placeOppShip()
    {
        
    }

    // battle method
    public static void battle()
    {
        
    }

    
    

    
}