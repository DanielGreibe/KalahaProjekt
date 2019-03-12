import java.util.Scanner;

public class Game{

    static void startGame(){
        Player Player1 = new Player("Daniel" , 1);
        Player Player2 = new Player("Mathias" , 2);
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        Player CurrentPlayer = Player1;
        while ( true ){
            System.out.println("It is " + CurrentPlayer.name + "'s turn");
            int index = scanner.nextInt();
            if ( index == -1 ){
                break;
            } else{
               boolean LegalMove = board.move(index, CurrentPlayer);
                board.printBoard();
                if (LegalMove && !board.getHasExtraTurn()) {
                    if (CurrentPlayer.equals(Player1)) {
                        CurrentPlayer = Player2;
                    }
                    else {
                        CurrentPlayer = Player1;
                    }
                }
                board.setHasExtraTurn(false);
            }
        }
    }
}
