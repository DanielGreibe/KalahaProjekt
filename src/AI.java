import java.util.List;

import static java.lang.Integer.max;
import static java.lang.Math.min;

public class AI {
    Board boardClass = new Board();
    int[] board = new int[14];
    int[][] heap;
    boolean goalState = board[6] > 32 && board[6]+board[13]==72;
    Node init;

    public int move(int[] initBoard){
        setHeap(initBoard);
        int moveIndex = alphaBeta(init, 5, 1000000, -1000000, true, initBoard);


        return 5;
    }


    public int alphaBeta(Node node, int depth, int alpha,int beta, boolean maxPlayer, int[] currentBoard){
        int val;
        setBoard(currentBoard);
        MakeChildren(node, currentBoard);

        if(depth == 0 || goalState){
            return evaluate(currentBoard);
        }
        if(maxPlayer){
            val = -1000000;
            List<Node<Integer>> children = node.getChildren();
            for(int i = 0; i < children.size(); i++){
                val = max(val, alphaBeta(children.get(i), depth -1, alpha, beta, false, currentBoard));
                alpha = max(alpha, val);
                if(alpha >= beta){
                    break;
                }
                return val;
            }
        } else{
            val = 1000000;
            List<Node<Integer>> children = node.getChildren();
            for(int i = 0; i < children.size(); i++){
                val = min(val, alphaBeta(children.get(i), depth -1, alpha, beta, true,  currentBoard));
                beta = min(beta, val);
                if(alpha >= beta){
                    break;
                }
                return val;
            }
        }
        return val;
    }

    private void MakeChildren(Node node, int[] currentBoard) {
        for(int i = 0; i < 6; i++)
        {
            if (boardClass.isLegal(i, 2, currentBoard))
            {
                Node child = new Node<Integer>(node , boardClass.move(i, 2 ,currentBoard));
                child.setData(evaluate(boardClass.move(i, 2 ,currentBoard)));
                node.addChild(child);
            }

        }
    }

   /* public int[] newBoard(){

    }*/

    public void setHeap(int[] initBoard){
        int eval = (initBoard[6]-initBoard[13])*10;
        init = new Node(initBoard);
        init.setData(eval);
    }

    public int evaluate(int [] currentBoard){
        return (currentBoard[6] - currentBoard[13])*10;
    }


    public void setBoard(int[] currentBoard){
        for(int i = 0; i < board.length; i++){
            board[i] = currentBoard[i];
        }
    }
}
