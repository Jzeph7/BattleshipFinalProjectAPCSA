public class Main 
{
   public static void main(String[] args)
   {
    System.out.println("Battleship Final Project");
    System.out.println("Key: \nX = Player Ship\nO = Opponent Ship\n- = Missed Guess\n! = Opponent Hit\n* = Player Hit");
    System.out.println("Map is currently empty\n");

    // creates player map
    Battleship.createPlayerMap();

    // creates opp map
    Battleship.createOppMap();

    // deploy player's ship
    Battleship.placePlayerShip();

    // deploy opponet's ship
    Battleship.placeOppShip();

    // playing game do-while loop
    do
    {
        Battleship.battle();
    }
    while(Battleship.numPlayerShips != 0 && Battleship.numOppShips != 0);

    BattleShip.gameOver();


   }

}
