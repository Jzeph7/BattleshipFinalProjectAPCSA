import java.util.Scanner;


public class Main 
{
   public static void main(String[] args)
   {
    System.out.println("Battleship Final Project");
    System.out.println("Map is currently empty");

    // creates map
    Battleship.createMap();

    // deploy player's ship
    Battleship.placePlayerShip();

    // deploy opponet's ship
    Battleship.placeOppShip();

    // playing game do-while loop
    do
    {
        Battleship.battle();
    }
    while(BattleShip.playerShips != 0 && BattleShip.computerShips != 0);




   }

}