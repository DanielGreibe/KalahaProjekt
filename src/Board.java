public class Board{
    int[] KalahaBoard;
    Boolean hasExtraTurn;

    public void setHasExtraTurn(boolean value, int[] boardState){
        hasExtraTurn = value;
        if (value)
        {
            boardState[14] = 1;
        }
        else
        {
            boardState[14] = 0;
        }
    }

    public boolean getHasExtraTurn(){
        return hasExtraTurn;
    }

    public int[] getKalahaBoard(){
        return KalahaBoard;
    }

    public Board(){
        setupGame();
    }

    public int[] move(int action, Player player, int[] boardState){
        Boolean legalMove = isLegalMove(action, player);
        int currentIndex = 0;
        if (player.playerNumber == 2)
        {
            currentIndex = 13 - action;
        }
        else if (player.playerNumber == 1)
        {
            currentIndex = 6 - action;
        }
        int Balls = boardState[currentIndex];
        boardState[currentIndex] = 0;
        if(!legalMove)
        {
            return boardState;
        }
        while(Balls != 0)
        {
            currentIndex = (currentIndex - 1);
            //Loops the board
            if(currentIndex == -1)
            {
                currentIndex = 13;
            }
            //Checks that the index is not in the opponents Kalaha
            if(player.playerNumber == 1 && currentIndex != 6 || player.playerNumber == 2 && currentIndex != 13){
                boardState[currentIndex]++;
                Balls--;
            }
            //Placing last ball in own or other side to maybe get a huge bonus
            if(Balls == 0 && boardState[currentIndex] == 1){
                if(player.playerNumber == 1 && currentIndex < 6){

                    boardState[13] = boardState[13] + boardState[currentIndex] + boardState[12 - currentIndex];
                    boardState[12 - currentIndex] = 0;
                    boardState[currentIndex] = 0;
                } else if(player.playerNumber == 2 && currentIndex >= 7 && currentIndex <= 12){
                    boardState[6] = boardState[6] + boardState[currentIndex] + boardState[12 - currentIndex];
                    boardState[12 - currentIndex] = 0;
                    boardState[currentIndex] = 0;
                }
            }
            //Placing ball in your own kalaha for an extra turn
            if(Balls == 0 && currentIndex == 6 || Balls == 0 && currentIndex == 13)
            {

                setHasExtraTurn(true, boardState);
            }
            else
            {
                setHasExtraTurn(false, boardState);
            }

            if (hasPlayerWon(player))
            {
                CleanupPostGame(player);
            }

        }
        return boardState;
    }

    public void printBoard(){
        System.out.print("[" + KalahaBoard[0] + "] \t");
        System.out.print("[" + KalahaBoard[1] + "] \t");
        System.out.print("[" + KalahaBoard[2] + "] \t");
        System.out.print("[" + KalahaBoard[3] + "] \t");
        System.out.print("[" + KalahaBoard[4] + "] \t");
        System.out.print("[" + KalahaBoard[5] + "] \t");
        System.out.print("\n[" + KalahaBoard[13] + "] \t \t \t \t \t \t \t \t \t[" + KalahaBoard[6] + "] \n");
        System.out.print("[" + KalahaBoard[12] + "] \t");
        System.out.print("[" + KalahaBoard[11] + "] \t");
        System.out.print("[" + KalahaBoard[10] + "] \t");
        System.out.print("[" + KalahaBoard[9] + "] \t");
        System.out.print("[" + KalahaBoard[8] + "] \t");
        System.out.print("[" + KalahaBoard[7] + "] \n");
    }

    public void setupGame(){
        KalahaBoard = new int[15];
        hasExtraTurn = false;

       /* for(int i = 0; i < KalahaBoard.length; i++){
            if(i <= 5 || 7 <= i && i <= 12){
                KalahaBoard[i] = 0;
            } else{
                KalahaBoard[i] = 0;
            }
        }*/
        KalahaBoard[0] = 1;
        KalahaBoard[1] = 2;
        KalahaBoard[7] = 1;
        KalahaBoard[8] = 1;
        KalahaBoard[9] = 0;
        KalahaBoard[10] = 0;
        KalahaBoard[11] = 0;
        KalahaBoard[12] = 1;

        //printBoard();
    }

    public boolean isLegalMove(int action, Player player){
        if (player.playerNumber == 2)
        {
            action = 13 - action;
        }
        else if (player.playerNumber == 1)
        {
            action = 6 - action;
        }
        //Try to move from a value out of range.
        if(action < 0 || action > 13){
            return false;
        }
        //Try to move from either of the two Kalaha's
        if(action == 6 || action == 13){
            return false;
        }
        //Player 1 moving from the wrong side
        if(player.playerNumber == 1 && action > 6){
            return false;
        }
        //Player 2 moving from the wrong side
        else if(player.playerNumber == 2 && action < 6){
            return false;
        }
        //It is a legal move
        return true;
    }
    public boolean hasPlayerWon(Player player)
    {
        if (player.playerNumber == 1)
        {
            for(int i = 0; i < 6; i++)
            {
                if (KalahaBoard[i] != 0)
                {
                    return false;
                }
            }
            return true;
        }
        else
        {
            for(int i = 7; i < 13; i++)
            {
                if (KalahaBoard[i] != 0)
                {
                    return false;
                }
            }
            return true;
        }
    }
    public void CleanupPostGame(Player player)
    {
       if (player.playerNumber == 1)
       {
          for(int i = 0; i < 13; i++)
          {
              if (i != 6)
              {
                  KalahaBoard[13] += KalahaBoard[i];
                  KalahaBoard[i] = 0;
              }
          }
       }
       else if (player.playerNumber == 2)
       {
           for(int i = 0; i < 13; i++)
           {
               if (i != 6)
               {
                   KalahaBoard[6] += KalahaBoard[i];
                   KalahaBoard[i] = 0;
               }
           }
       }
    }
}
