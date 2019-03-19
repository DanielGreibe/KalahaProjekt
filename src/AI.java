public class AI {
    int[] board = new int[14];
    int[][] heap;
    boolean goalState = board[13] < board[6];

    public int alphaBeta(int[][] node, int depth, int alpha,int beta, boolean maxPlayer, int[] currentBoard){
        int val;
        setBoard(currentBoard);
        if(depth == 0 || goalState){
            if(goalState){
                return (board[6] - board[13])*10;
            } else if(depth == 0){
                return evaluate();
            }
        }
        if(maxPlayer){
            val = -1000000;
            list<node> children = node.getChildren;
            for(int i = 0; i < children.size(); i++){
                val = max(val, alphaBeta(children.get(i), depth -1, alpha, beta, false));
                alpha = max(alphs, val);
                if(alpha >= beta){
                    break;
                }
                return val;
            }
        } else{
            val = 1000000;
            list<node> children = node.getChildren;
            for(int i = 0; i < children.size(); i++){
                val = min(val, alphaBeta(children.get(i), depth -1, alpha, beta, true));
                beta = min(beta, val);
                if(alpha >= beta){
                    break;
                }
                return val;
            }
        }
        return val;
    }


    public int evaluate(){

        return 1; // FEJL
    }


    public void setBoard(int[] currentBoard){
        for(int i = 0; i < board.length; i++){
            board[i] = currentBoard[i];
        }
    }
}
