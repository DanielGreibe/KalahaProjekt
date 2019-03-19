public class Board{
    int[] KalahaBoard;
    Boolean hasExtraTurn;

    public void setHasExtraTurn(boolean value){
        hasExtraTurn = value;
    }
    public boolean getHasExtraTurn(){
        return hasExtraTurn;
    }
    public int[] getKalahaBoard(){ return KalahaBoard; }
    public Board(){
        setupGame();
    }

    public boolean move(int initialIndex, Player player){
        if (initialIndex < 0 || initialIndex > 13){
            return false;
        }
        System.out.println("\nThe player moves from " + initialIndex);
        int currentIndex = initialIndex;
        int Balls = KalahaBoard[currentIndex];

        if ( currentIndex == 6 || currentIndex == 13){
            System.out.println("You can't move from either of the two kalaha's");
            return false;
        }
        if (player.playerNumber == 1 && currentIndex > 6){
            System.out.println("Wrong side");
            return false;
        }
        else if (player.playerNumber == 2 && currentIndex < 6){
            System.out.println("Wrong side");
            return false;
        }
        KalahaBoard[currentIndex] = 0;
        while ( Balls != 0 ) {
            currentIndex = (currentIndex - 1);
            if (currentIndex == -1){
                currentIndex = 13;
            }
            if (initialIndex < 6 && currentIndex != 6 || initialIndex > 6 && currentIndex != 13) {
                KalahaBoard[currentIndex]++;
                Balls--;
            }
            //Placing last ball in own or other side to maybe get a huge bonus
            if (Balls == 0 && KalahaBoard[currentIndex] == 1 && currentIndex != 6 && currentIndex != 13){
                if (currentIndex > 6 && currentIndex < 13 && initialIndex > 6){
                    KalahaBoard[6] = KalahaBoard[6] + KalahaBoard[currentIndex] + KalahaBoard[12 - currentIndex];
                    KalahaBoard[12 - currentIndex] = 0;
                    KalahaBoard[currentIndex] = 0;
                }
                else if (currentIndex <= 5 && initialIndex <= 5){
                    KalahaBoard[13] = KalahaBoard[13] + KalahaBoard[currentIndex] + KalahaBoard[12 - currentIndex];
                    KalahaBoard[12 - currentIndex] = 0;
                    KalahaBoard[currentIndex] = 0;
                }
            }
            //Placing ball in your own kalaha for an extra turn
            if (Balls == 0 && currentIndex == 6 || Balls == 0 && currentIndex == 13){
                System.out.println("You get an extra turn");
                hasExtraTurn = true;
            }
        }
        return true;
    }

    public int[] move(int initialIndex, int playerNumber, int[] boardState){
        if (initialIndex < 0 || initialIndex > 13){
            return boardState;
        }
        System.out.println("\nThe player moves from " + initialIndex);
        int currentIndex = initialIndex;
        int Balls = boardState[currentIndex];

        if ( currentIndex == 6 || currentIndex == 13){
            System.out.println("You can't move from either of the two kalaha's");
            return boardState;
        }
        if (playerNumber == 1 && currentIndex > 6){
            System.out.println("Wrong side");
            return boardState;
        }
        else if (playerNumber == 2 && currentIndex < 6){
            System.out.println("Wrong side");
            return boardState;
        }
        boardState[currentIndex] = 0;
        while ( Balls != 0 ) {
            currentIndex = (currentIndex - 1);
            if (currentIndex == -1){
                currentIndex = 13;
            }
            if (initialIndex < 6 && currentIndex != 6 || initialIndex > 6 && currentIndex != 13) {
                boardState[currentIndex]++;
                Balls--;
            }
            //Placing last ball in own or other side to maybe get a huge bonus
            if (Balls == 0 && boardState[currentIndex] == 1 && currentIndex != 6 && currentIndex != 13){
                if (currentIndex > 6 && currentIndex < 13 && initialIndex > 6){
                    boardState[6] = boardState[6] + boardState[currentIndex] + boardState[12 - currentIndex];
                    boardState[12 - currentIndex] = 0;
                    boardState[currentIndex] = 0;
                }
                else if (currentIndex <= 5 && initialIndex <= 5){
                    boardState[13] = boardState[13] + boardState[currentIndex] + boardState[12 - currentIndex];
                    boardState[12 - currentIndex] = 0;
                    boardState[currentIndex] = 0;
                }
            }
            //Placing ball in your own kalaha for an extra turn
            if (Balls == 0 && currentIndex == 6 || Balls == 0 && currentIndex == 13){
                System.out.println("You get an extra turn");
                hasExtraTurn = true;
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
        KalahaBoard = new int[14];
        hasExtraTurn = false;

        for ( int i = 0; i < KalahaBoard.length; i++){
            if (i <= 5 || 7 <= i && i <= 12){
                KalahaBoard[i] = 6;
            } else {
                KalahaBoard[i] = 0;
            }
        }
        printBoard();
    }

    public void resetGame(){
        setupGame();
    }

    public boolean hasWon(){
        boolean doWeHaveAWinner;
        int player1 = 0;
        int player2 = 0;
        for(int i = 0; i <= 5; i++){
            player1 += KalahaBoard[i];
        }
        for(int i = 7; i <= 12; i++){
            player2 += KalahaBoard[i];
        }
        if(player1 == 0){
            for(int i = 7; i <= 12; i++){
                KalahaBoard[i] = 0;
            }
            KalahaBoard[13] += player2;
            System.out.println("\nSpillet er slut ");
            doWeHaveAWinner = true;
        } else if(player2 == 0){
            for(int i = 0; i <= 5; i++){
                KalahaBoard[i] = 0;
            }
            KalahaBoard[6] += player1;
            System.out.println("\nSpillet er slut");
            doWeHaveAWinner = true;
        } else {
            doWeHaveAWinner = false;
        }
        if(doWeHaveAWinner) {
            if(KalahaBoard[6] < KalahaBoard[13]) {
                System.out.println("Spiller 1 har vindet");
            } else if(KalahaBoard[6] > KalahaBoard[13]) {
                System.out.println("Spiller 2 har vundet");
            } else {
                System.out.println("Spillet er uafgjort");
            }
        }
        return doWeHaveAWinner;
    }
}
