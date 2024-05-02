import java.util.Scanner;
public class Connect4{
    
   public static void main(String[] args){

        char[][] board = new char[6][7];
        char color = 'Y';
        boolean gameFinished = false;
        initializeBoard(board);
        Scanner scanner = new Scanner(System.in);
        while (!gameFinished){
            int userInput = scanner.nextInt() - 1;
            placeChecker(userInput, board, color);
            printBoard(board);
            gameFinished = checkWinner(board, color);
            if (color == 'Y')
            color = 'R';
            else
            color = 'Y';

        }
        scanner.close();
   }
   
   public static void initializeBoard(char[][] board){

    // initialize the array to have the 'W' char for white
    for (int i = 0; i < board.length; i++){
        for (int j = 0; j < board[0].length; j++){
            board[i][j] = 'W';
        }
    }
   }

   public static boolean placeChecker(int column, char[][] board, char color){
        int row = 5; // 0 indexing means the 6th row is 5

        // start from the bottom row and move up until an empty spot is found
        while (board[row][column] != 'W'){
            if (row == 0)
            return false;
            row -= 1;
        }
        board[row][column] = color;
        return true;
   }

   public static void printBoard(char[][] board) {
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
            System.out.print(board[i][j] + " ");  // Print each cell followed by a space
        }
        System.out.println();  // Move to the next line after each row is printed
    }
    System.out.println();
}

    public static boolean checkWinner(char[][] board, char color){

        // look for 4 across
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[0].length - 3; col++){
                if (board[row][col] == color &&
                    board[row][col+1] == color &&
                    board[row][col+2] == color &&
                    board[row][col+3] == color)
                    return true;
            }
        }

        // look for 4 vertical
        for (int row = 0; row < board.length-3; row++){
            for (int col = 0; col < board[0].length; col++){
                if (board[row][col] == color &&
                    board[row+1][col] == color &&
                    board[row+2][col] == color &&
                    board[row+3][col] == color)
                    return true;
            }
        }

        // check for diagonal up
        for (int row = 3; row < board.length; row++){
            for (int col = 0; col < board[0].length - 3; col++){
                if (board[row][col] == color &&
                    board[row-1][col+1] == color &&
                    board[row-2][col+2] == color &&
                    board[row-3][col+3] == color)
                    return true;
            }
        }

        // check for diagonal down
        for (int row = 0; row < board.length-3; row++){
            for (int col = 0; col < board[0].length - 3; col++){
                if (board[row][col] == color &&
                    board[row+1][col+1] == color &&
                    board[row+2][col+2] == color &&
                    board[row+3][col+3] == color)
                    return true;
            }
        }

        return false;
    }
}

