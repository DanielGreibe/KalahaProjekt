import java.util.Scanner;

public class Game{
    static Player Player1;
    static Player Player2;
    static Player CurrentPlayer;
    static Board board;
    static int totalNumOfBalls = 72;

    static void startGame(){
        boolean hasWon = false;
        Player1 = new Player("AI" , 1);
        Player2 = new Player("Human" , 2);
        AI ai = new AI();
        board = new Board();
        Scanner scanner = new Scanner(System.in);
        CurrentPlayer = Player1;
        System.out.println("Game start");
        board.printBoard();

        while (!hasWon){
            System.out.println("It is " + CurrentPlayer.name + "'s turn");
            int index;
            if(CurrentPlayer.playerNumber == 1){
               index = ai.move(board.getKalahaBoard());
            }else{
                System.out.println("Choose a move and press enter:");
                index = scanner.nextInt();
            }
                switch(index){
                    case 1:
                        move(1, CurrentPlayer);
                        break;
                    case 2:
                        move(2, CurrentPlayer);
                        break;
                    case 3:
                        move(3, CurrentPlayer);
                        break;
                    case 4:
                        move(4, CurrentPlayer);
                        break;
                    case 5:
                        move(5, CurrentPlayer);
                        break;
                    case 6:
                        move(6, CurrentPlayer);
                        break;
                    default:
                        System.out.println("Wrong input, please try again");
                        break;
            }
            if(TerminalTest(board.getKalahaBoard())){
                hasWon = true;
                board.printBoard();
            } else {
                board.printBoard();
            }
        }
    }
    static void move(int action, Player currentPlayer){
        boolean LegalMove = board.isLegalMove(action, currentPlayer);
        if (LegalMove){
            board.move(action, currentPlayer, board.getKalahaBoard());
            if (!board.getHasExtraTurn()){
                if (currentPlayer == Player1){
                    CurrentPlayer = Player2;
                }
                else {
                    CurrentPlayer = Player1;
                }
            }
            board.setHasExtraTurn(false, board.getKalahaBoard());
        }
    }

    public static boolean TerminalTest(int[] boardState){
        return boardState[6]+boardState[13] == totalNumOfBalls;
    }
}