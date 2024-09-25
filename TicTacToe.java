import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X'; // Player 1 starts
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        System.out.println("Current board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkForWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameEnded = false;

        while (!gameEnded) {
            printBoard();
            System.out.println("Player " + (currentPlayer == 'X' ? "1 (X)" : "2 (O)") + ", enter your move (row and column) [e.g., 1 2 3]: ");
            int row = scanner.nextInt(); // No conversion needed, directly use the input
            int col = scanner.nextInt(); // No conversion needed, directly use the input

            // Adjust for 1-based index
            if (row >= 1 && row <= 3 && col >= 1 && col <= 3) {
                int rowIndex = row - 1; // Convert to 0-based index
                int colIndex = col - 1; // Convert to 0-based index

                if (board[rowIndex][colIndex] == '-') {
                    board[rowIndex][colIndex] = currentPlayer;

                    if (checkForWin()) {
                        printBoard();
                        System.out.println("Player " + (currentPlayer == 'X' ? "1" : "2") + " wins!");
                        gameEnded = true;
                    } else if (isBoardFull()) {
                        printBoard();
                        System.out.println("The game is a draw!");
                        gameEnded = true;
                    } else {
                        changePlayer();
                    }
                } else {
                    System.out.println("This move is not valid. Try again.");
                }
            } else {
                System.out.println("Please enter a valid move (1-3 for both row and column).");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.playGame();
    }
}