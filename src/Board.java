public class Board{
    int[] KalahaBoard;
    Boolean hasExtraTurn;

    public void setHasExtraTurn(boolean value)
    {
        hasExtraTurn = value;
    }
    public boolean getHasExtraTurn()
    {
        return hasExtraTurn;
    }
    public Board(){
        KalahaBoard = new int[14];
        hasExtraTurn = false;
        setInitialState();
        printBoard();
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
    public void setInitialState(){
        for ( int i = 0; i < KalahaBoard.length; i++){
            if (i <= 5 || 7 <= i && i <= 12){
                KalahaBoard[i] = 6;
            }else{
                KalahaBoard[i] = 0;
            }
        }
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
        if (player.playerNumber == 1 && currentIndex > 6 && currentIndex < 13){
            System.out.println("Wrong side");
            return false;
        }
        else if (player.playerNumber == 2 && currentIndex < 6){
            System.out.println("Wrong side");
            return false;
        }
        KalahaBoard[currentIndex] = 0;
        while ( Balls != 0 ){
            currentIndex = (currentIndex + 1) % 14;
                KalahaBoard[currentIndex] ++;
            Balls--;
            if (Balls == 0 && KalahaBoard[currentIndex] == 1 && currentIndex != 6 && currentIndex != 13){
                if (currentIndex >= 7 && currentIndex <= 12 && initialIndex >= 7 && initialIndex <= 12){
                    KalahaBoard[13] = KalahaBoard[13] + KalahaBoard[currentIndex] + KalahaBoard[12 - currentIndex];
                    KalahaBoard[12 - currentIndex] = 0;
                    KalahaBoard[currentIndex] = 0;
                }
                else if (currentIndex <= 5 && initialIndex <= 5){
                    KalahaBoard[6] = KalahaBoard[6] + KalahaBoard[currentIndex] + KalahaBoard[12 - currentIndex];
                    KalahaBoard[12 - currentIndex] = 0;
                    KalahaBoard[currentIndex] = 0;
                }

            }
            if (Balls == 0 && currentIndex == 13 && initialIndex >= 7 && initialIndex <= 12 || Balls == 0 && currentIndex == 6 && initialIndex <= 5){
                System.out.println("You get an extra turn");
                hasExtraTurn = true;
            }
        }
        return true;
    }
}
