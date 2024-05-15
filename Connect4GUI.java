import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Connect4GUI extends JFrame {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private char[][] board = new char[ROWS][COLS];
    private char currentColor = 'Y';
    private JButton[] columnButtons = new JButton[COLS];
    private JLabel[][] cellLabels = new JLabel[ROWS][COLS];

    public Connect4GUI() {
        setTitle("Connect 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(ROWS, COLS, 5, 5)); // Add spacing between cells
        boardPanel.setBackground(new Color(0, 102, 204)); // Set background color of the board
        initializeBoard();
        initializeBoardPanel(boardPanel);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1, COLS, 5, 5)); // Add spacing between buttons
        initializeControlPanel(controlPanel);

        add(controlPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = 'W';
                cellLabels[i][j] = new JLabel();
                cellLabels[i][j].setOpaque(true);
                cellLabels[i][j].setBackground(Color.WHITE);
                cellLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Thicker border
                cellLabels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
            }
        }
    }

    private void initializeBoardPanel(JPanel boardPanel) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                boardPanel.add(cellLabels[i][j]);
            }
        }
    }

    private void initializeControlPanel(JPanel controlPanel) {
        for (int i = 0; i < COLS; i++) {
            final int col = i;
            columnButtons[i] = new JButton("Drop");
            columnButtons[i].setFont(new Font("Arial", Font.BOLD, 16)); // Set font size and style
            columnButtons[i].setBackground(new Color(255, 204, 0)); // Set button background color
            columnButtons[i].setForeground(Color.BLACK); // Set button text color
            columnButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    placeChecker(col);
                }
            });
            controlPanel.add(columnButtons[i]);
        }
    }

    private void placeChecker(int column) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][column] == 'W') {
                board[row][column] = currentColor;
                cellLabels[row][column].setBackground(currentColor == 'Y' ? Color.YELLOW : Color.RED);
                if (checkWinner(board, currentColor)) {
                    JOptionPane.showMessageDialog(this, "Player " + (currentColor == 'Y' ? "Yellow" : "Red") + " wins!");
                    resetBoard();
                } else {
                    currentColor = (currentColor == 'Y') ? 'R' : 'Y';
                }
                break;
            }
        }
    }

    private void resetBoard() {
        initializeBoard();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                cellLabels[i][j].setBackground(Color.WHITE);
            }
        }
    }

    private boolean checkWinner(char[][] board, char color) {
        // look for 4 across
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col] == color &&
                    board[row][col + 1] == color &&
                    board[row][col + 2] == color &&
                    board[row][col + 3] == color)
                    return true;
            }
        }

        // look for 4 vertical
        for (int row = 0; row < board.length - 3; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == color &&
                    board[row + 1][col] == color &&
                    board[row + 2][col] == color &&
                    board[row + 3][col] == color)
                    return true;
            }
        }

        // check for diagonal up
        for (int row = 3; row < board.length; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col] == color &&
                    board[row - 1][col + 1] == color &&
                    board[row - 2][col + 2] == color &&
                    board[row - 3][col + 3] == color)
                    return true;
            }
        }

        // check for diagonal down
        for (int row = 0; row < board.length - 3; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col] == color &&
                    board[row + 1][col + 1] == color &&
                    board[row + 2][col + 2] == color &&
                    board[row + 3][col + 3] == color)
                    return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Connect4GUI();
            }
        });
    }
}
