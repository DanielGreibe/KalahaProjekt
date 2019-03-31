import java.util.List;

import static java.lang.Integer.max;
import static java.lang.Math.min;

public class AI {
    Board boardClass = new Board();
    int[] board = new int[14];
    int totalNumOfBalls =  6;
    Node init;

    public int move(int[] initBoard){
        int action = 0;
        int val = 0;
        setHeap(initBoard);
        val = alphaBeta(init, 2000, 1000000, -1000000, true, initBoard);
        List<Node<Integer>> children = init.getChildren();
        for(int i = 0; i < children.size(); i++){
            if (children.get(i).getData() == val){
                action = children.get(i).getAction();
            }
        }
        return action;
    }


    public int alphaBeta(Node node, int depth, int alpha,int beta, boolean maxPlayer, int[] currentBoard){
        int val;
        int action = 0;
        //setBoard(currentBoard);
        if(depth == 0 || currentBoard[6]+currentBoard[13] == totalNumOfBalls){
            return evaluate(currentBoard);
        }

        MakeChildren(node, currentBoard, maxPlayer);
        List<Node<Integer>> children = node.getChildren();

        if(maxPlayer){
            val = -1000000;
            for(int i = 0; i < children.size(); i++){
                val = max(val, alphaBeta(children.get(i), depth -1, alpha, beta, false, children.get(i).getCurrentBoard()));
                if (children.get(i).getCurrentBoard()[14] == 1)
                {
                    children.get(i).setData(max(val, alphaBeta(children.get(i), depth -1, alpha, beta, false, children.get(i).getCurrentBoard())));
                }
                alpha = max(alpha, val);
                if(alpha >= beta){
                    break;
                }
                node.setData(val);
                return val;
            }
        } else{
            val = 1000000;
            for(int i = 0; i < children.size(); i++){
                val = min(val, alphaBeta(children.get(i), depth -1, alpha, beta, true, children.get(i).getCurrentBoard()));
                beta = min(beta, val);
                if(alpha >= beta){
                    break;
                }
                node.setData(val);
                return val;
            }
        }
        return val;
    }

    private void MakeChildren(Node parent, int[] currentBoard, boolean maxPlayer)
    {

        int[] legalActions = Actions(currentBoard, maxPlayer);
       // System.out.println("Length of currentBoard" + currentBoard.length);
        for(int i = 0; i < legalActions.length; i++)
        {
            int[] originalBoard = new int[15];
            for(int j = 0; j < currentBoard.length; j++)
            {
                originalBoard[j] = currentBoard[j];
            }
            if (legalActions[i] != -1) {
                Node child = new Node<Integer>(parent, boardClass.move(i+1, Game.Player1, originalBoard), i+1);
                System.out.println("Value at child at i = " + i + " is " + child.getCurrentBoard()[14]);
                child.setData(evaluate(child.getCurrentBoard()));
                parent.addChild(child);
            }
        }

    }

    public void setHeap(int[] initBoard){
        int eval = (initBoard[6]-initBoard[13])*10;
        init = new Node(initBoard);
        init.setData(eval);
    }

    public int evaluate(int [] currentBoard){
        return (currentBoard[13] - currentBoard[6])*10;
    }


    public void setBoard(int[] currentBoard){
        for(int i = 0; i < board.length; i++){
            board[i] = currentBoard[i];
        }
    }
    public static int Utility(int[] boardState, Player player)
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

    public int[] Actions(int[] boardState, boolean maxPlayer) {
        int[] Actions = new int[6];
        for (int i = 0; i < 6; i++) {
            if (maxPlayer) {
                if (boardClass.isLegalMove(i + 1, Game.Player1) && boardState[5 - i] != 0) {
                    Actions[i] = i + 1;
                } else {
                    Actions[i] = -1;
                }
            } else {
                if (boardClass.isLegalMove(i + 1, Game.Player2) && boardState[12 - i] != 0) {
                    Actions[i] = i + 1;
                } else {
                    Actions[i] = -1;
                }
            }
        }
        return Actions;
    }

    public Player Player(int[] boardState)
    {
        if(boardState[14] == 1)
        {
            return Game.Player1;
        }
        else
        {
            return Game.Player2;
        }
    }
    /*public void printBoard(int[] boardState)
    {
        System.out.print("[" + boardState[0] + "] \t");
        System.out.print("[" + boardState[1] + "] \t");
        System.out.print("[" + boardState[2] + "] \t");
        System.out.print("[" + boardState[3] + "] \t");
        System.out.print("[" + boardState[4] + "] \t");
        System.out.print("[" + boardState[5] + "] \t");
        System.out.print("\n[" + boardState[13] + "] \t \t \t \t \t \t \t \t \t[" + boardState[6] + "] \n");
        System.out.print("[" + boardState[12] + "] \t");
        System.out.print("[" + boardState[11] + "] \t");
        System.out.print("[" + boardState[10] + "] \t");
        System.out.print("[" + boardState[9] + "] \t");
        System.out.print("[" + boardState[8] + "] \t");
        System.out.print("[" + boardState[7] + "] \n");
    }*/
}
