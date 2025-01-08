import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class LudoGame {

    private static final int BOARD_SIZE = 15;
    private static final int PLAYER_COUNT = 4;
    private static final Color[] PLAYER_COLORS = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

    private JFrame frame;
    private JPanel boardPanel;
    private JButton[][] board;
    private JLabel statusLabel;
    private JButton rollDiceButton;
    private Random random;

    private int[] playerPositions;
    private int currentPlayer;

    public LudoGame() {
        frame = new JFrame("Ludo Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        board = new JButton[BOARD_SIZE][BOARD_SIZE];
        
        initializeBoard();

        statusLabel = new JLabel("Player 1's turn (RED)", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));

        rollDiceButton = new JButton("Roll Dice");
        rollDiceButton.addActionListener(new DiceRollListener());

        random = new Random();
        playerPositions = new int[PLAYER_COUNT];
        currentPlayer = 0;

        frame.setLayout(new BorderLayout());
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.NORTH);
        frame.add(rollDiceButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new JButton();
                board[i][j].setBackground(Color.WHITE);
                board[i][j].setEnabled(false);
                boardPanel.add(board[i][j]);
            }
        }
        placePlayers();
    }

    private void placePlayers() {
        for (int i = 0; i < PLAYER_COUNT; i++) {
            int position = playerPositions[i];
            int x = position / BOARD_SIZE;
            int y = position % BOARD_SIZE;
            board[x][y].setBackground(PLAYER_COLORS[i]);
        }
    }

    private void movePlayer(int player, int steps) {
        int oldPosition = playerPositions[player];
        int oldX = oldPosition / BOARD_SIZE;
        int oldY = oldPosition % BOARD_SIZE;
        board[oldX][oldY].setBackground(Color.WHITE);

        playerPositions[player] = (playerPositions[player] + steps) % (BOARD_SIZE * BOARD_SIZE);
        int newPosition = playerPositions[player];
        int newX = newPosition / BOARD_SIZE;
        int newY = newPosition % BOARD_SIZE;
        board[newX][newY].setBackground(PLAYER_COLORS[player]);
    }

    private class DiceRollListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int diceRoll = random.nextInt(6) + 1;
            statusLabel.setText("Player " + (currentPlayer + 1) + " rolled a " + diceRoll);
            movePlayer(currentPlayer, diceRoll);

            currentPlayer = (currentPlayer + 1) % PLAYER_COUNT;
            statusLabel.setText("Player " + (currentPlayer + 1) + "'s turn (" + getPlayerColorName(currentPlayer) + ")");
        }

        private String getPlayerColorName(int player) {
            switch (player) {
                case 0: return "RED";
                case 1: return "GREEN";
                case 2: return "BLUE";
                case 3: return "YELLOW";
                default: return "UNKNOWN";
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LudoGame::new);
    }
}
