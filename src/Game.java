import java.util.Scanner;

public class Game{
    static Player Player1;
    static Player Player2;
    static Player CurrentPlayer;
    static Board board;

    static void startGame(){
        boolean hasWon = false;
        Player1 = new Player("Human1" , 1);
        Player2 = new Player("Human2" , 2);
        AI ai = new AI();
        board = new Board();
        Scanner scanner = new Scanner(System.in);
        CurrentPlayer = Player2;


        while (!hasWon){
            System.out.println("It is " + CurrentPlayer.name + "'s turn");
            int index;
            if(CurrentPlayer.playerNumber == 1){
               // index = ai.move(board.getKalahaBoard());
                index = scanner.nextInt();
                System.out.println("Value of Index in Game is" + index);
            }else{
                System.out.println("Indtast felt at rykke fra");
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
                        System.out.println("Forkert input, prøv igen");
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
        if (LegalMove)
        {
            board.move(action, currentPlayer, board.getKalahaBoard());
            if (!board.getHasExtraTurn())
            {
                if (currentPlayer == Player1)
                {
                    CurrentPlayer = Player2;
                }
                else {
                    CurrentPlayer = Player1;
                }
            }
            board.setHasExtraTurn(false);
        }
    }
    public Player Player(int[] boardState)
    {
        if(boardState[14] == 1)
        {
            return Player1;
        }
        else
        {
            return Player2;
        }
    }
    public int[] Actions(int[] boardState)
    {
        int[] Actions = new int[6];
        for(int i= 0; i < boardState.length; i++)
        {
            if (board.isLegalMove(i , Player(boardState)))
            {
                Actions[i] = i;
            }
            else
            {
                Actions[i] = -1;
            }
        }
        return Actions;
    }
    public int[] Result(int[] boardState, int action)
    {
        return board.move(action, Player(boardState), boardState);
    }
    public static boolean TerminalTest(int[] boardState)
    {
        return boardState[6]+boardState[13] == 72;
    }
    public int Utility(int[] boardState, Player player)
    {
        if (player.playerNumber == 1)
        {
            return (boardState[13]-boardState[6])*10;
        }
        else
        {
            return (boardState[6] - boardState[13])*10;
        }
    }
}