import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

// Domino class representing a single domino with two sides
class Domino {
    private int left;
    private int right;

    public Domino(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "[" + left + "|" + right + "]";
    }
}

// DominoGame class responsible for managing the collection of dominoes
class DominoGame {
    private ArrayList<Domino> dominoes;

    public DominoGame() {
        dominoes = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                dominoes.add(new Domino(i, j));
            }
        }
        Collections.shuffle(dominoes);
    }

    public ArrayList<Domino> getDominoes() {
        return dominoes;
    }

    public Domino drawDomino() {
        return !dominoes.isEmpty() ? dominoes.remove(0) : null;
    }
}

// DominoesGUI class responsible for the graphical user interface
public class DominoesGUI extends JFrame {
    private DominoGame game;
    private JPanel dominoPanel;
    private JTextArea displayArea;

    public DominoesGUI() {
        game = new DominoGame();
        displayArea = new JTextArea(5, 30);
        displayArea.setEditable(false);
        JButton drawButton = new JButton("Draw Domino");

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Domino drawnDomino = game.drawDomino();
                if (drawnDomino != null) {
                    displayDomino(drawnDomino);
                    displayArea.append("Drawn Domino: " + drawnDomino + "\n");
                } else {
                    displayArea.append("No more dominoes to draw!\n");
                }
            }
        });

        dominoPanel = new JPanel();
        dominoPanel.setLayout(new GridLayout(0, 5)); // Adjust the number of columns as needed

        setLayout(new BorderLayout());
        add(new JScrollPane(displayArea), BorderLayout.SOUTH);
        add(dominoPanel, BorderLayout.CENTER);
        add(drawButton, BorderLayout.NORTH);

        setTitle("Dominoes Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void displayDomino(Domino domino) {
        JPanel dominoBlock = new JPanel();
        dominoBlock.setPreferredSize(new Dimension(60, 120));
        dominoBlock.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        dominoBlock.setBackground(Color.LIGHT_GRAY);
        dominoBlock.setLayout(new GridLayout(2, 1));

        JLabel leftLabel = new JLabel(String.valueOf(domino.getLeft()), SwingConstants.CENTER);
        JLabel rightLabel = new JLabel(String.valueOf(domino.getRight()), SwingConstants.CENTER);

        leftLabel.setFont(new Font("Arial", Font.BOLD, 24));
        rightLabel.setFont(new Font("Arial", Font.BOLD, 24));

        dominoBlock.add(leftLabel);
        dominoBlock.add(rightLabel);
        dominoPanel.add(dominoBlock);
        dominoPanel.revalidate();
        dominoPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DominoesGUI::new);
    }
}