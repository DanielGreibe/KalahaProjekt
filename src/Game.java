import java.util.Scanner;

public class Game{
    static Player Player1;
    static Player Player2;
    static Player CurrentPlayer;
    static Board board;

    static void startGame(){
        boolean hasWon = false;
        Player1 = new Player("Daniel" , 1);
        Player2 = new Player("Mathias" , 2);
        board = new Board();
        Scanner scanner = new Scanner(System.in);
        CurrentPlayer = Player1;
        while (!hasWon){
            System.out.println("It is " + CurrentPlayer.name + "'s turn");

            String index = scanner.next();
            switch(index){
                case "1":
                    if(CurrentPlayer == Player1){
                        move(5);
                    } else {
                        move(12);
                    }
                    break;
                case "2":
                    if(CurrentPlayer == Player1){
                        move(4);
                    } else {
                        move(11);
                    }
                    break;
                case "3":
                    if(CurrentPlayer == Player1){
                        move(3);
                    } else {
                        move(10);
                    }
                    break;
                case "4":
                    if(CurrentPlayer == Player1){
                        move(2);
                    } else {
                        move(9);
                    }
                    break;
                case "5":
                    if(CurrentPlayer == Player1){
                        move(1);
                    } else {
                        move(8);
                    }
                    break;
                case "6":
                    if(CurrentPlayer == Player1){
                        move(0);
                    } else {
                        move(7);
                    }
                    break;
                case "reset":
                    board.resetGame();
                    break;
                case "break":
                    hasWon = true;
                    break;
                default:
                    System.out.println("Forkert input, prøv igen");
                    break;
            }

            if(board.hasWon()){
                hasWon = true;
                board.printBoard();
            } else {
                board.printBoard();
            }
        }
    }
    static void move(int index){
        boolean LegalMove = board.move(index, CurrentPlayer);
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