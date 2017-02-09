import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;

public class Memory implements Runnable {

    private File folder = new File("CardImages");
    private File[] pictures = folder.listFiles();
    private Icon[] imageIcon = new Icon[pictures.length];

    private JFrame frame = new JFrame("Memory");

    private JLabel lblScore1 = new JLabel("0");
    private JLabel lblScore2 = new JLabel("0");
    private JPanel players = new JPanel();
    private JPanel panePlayer1 = new JPanel();
    private JPanel panePlayer2 = new JPanel();
    private JLabel lblPlayer1 = new JLabel("Player 1");
    private JLabel lblPlayer2 = new JLabel("Player 2");

    private JMenuItem menuSound = new JMenuItem("Sound (Enabled)");

    private Timer timer;

    private int rows = 4;
    private int columns = 4;
    private int timerValue = 1500;
    private int score1 = 0;
    private int score2 = 0;
    private int width = 400;
    private int height = 200;

    private boolean player1turn = true;
    private boolean cardTurned = false;
    private boolean soundEnable = true;

    private Card card1;
    private Card card2;

    //Creates icons of the images in the picture array
    private Memory() {
        for (int i = 0; i < pictures.length; i++) {
            imageIcon[i] = new ImageIcon(pictures[i].getPath());
        }
    }

    private void newGame(int n, int m, int t) {
        int tiles = n * m;
        JButton[] cards = new JButton[tiles];
        Icon[] randomArray = new Icon[imageIcon.length];

        width = m * 100 + 120;
        height = n * 100 + 60;
        score1 = 0;
        score2 = 0;
        player1turn = true;

        timer = new Timer((t), new TimerListener());

        frame.getContentPane().removeAll();

        lblScore1.setText("Score: " + score1);
        lblScore2.setText("Score: " + score2);
        frame.setSize(width, height);
        players.setPreferredSize(new Dimension(100, height - 65));
        panePlayer1.setPreferredSize(new Dimension(100, (height - 65) / 2));
        panePlayer2.setPreferredSize(new Dimension(100, (height - 65) / 2));
        panePlayer1.setBackground(Color.yellow);
        panePlayer2.setBackground(Color.white);

        players.add(panePlayer1);
        players.add(panePlayer2);
        panePlayer1.add(lblPlayer1);
        panePlayer1.add(lblScore1);
        panePlayer2.add(lblPlayer2);
        panePlayer2.add(lblScore2);

        frame.add(players, BorderLayout.WEST);

        System.arraycopy(imageIcon, 0, randomArray, 0, randomArray.length);
        Tools.randomOrder(randomArray);

        for (int i = 0; i < tiles; i = i + 2) {
            cards[i] = new Card(randomArray[i / 2], Card.Status.HIDDEN);
            cards[i + 1] = new Card(randomArray[i / 2], Card.Status.HIDDEN);
        }

        Tools.randomOrder(cards);

        JPanel paneCards = new JPanel();
        for (JButton card : cards) {
            card.setPreferredSize(new Dimension(90, 90));
            card.addActionListener(new CardListener());
            paneCards.add(card);
        }
        frame.add(paneCards, BorderLayout.EAST);
        paneCards.setPreferredSize(new Dimension(width - 100, height - 65));
        frame.revalidate();
    }

    private void checkPlayer() {
        if (player1turn) {
            score1++;
            lblScore1.setText("Score: " + score1);
        } else {
            score2++;
            lblScore2.setText("Score: " + score2);
        }
    }

    private void switchPlayer() {
        if (player1turn) {
            panePlayer1.setBackground(Color.white);
            panePlayer2.setBackground(Color.yellow);
            player1turn = false;
        } else {
            panePlayer1.setBackground(Color.yellow);
            panePlayer2.setBackground(Color.white);
            player1turn = true;
        }
    }

    private void endGame() {
        String winner;
        if (score1 > score2) {
            winner = "Player 1";
        } else if (score1 == score2) {
            winner = "No one, it's a tie!";
        } else {
            winner = "Player 2";
        }

        Object[] options = {"Exit game", "Start new game"};

        int n = JOptionPane.showOptionDialog(frame, "The winner is: " + winner, "Game finished",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[0]);
        switch (n) {
            case 0:
                System.exit(0);
                break;
            case 1:
                newGame(rows, columns, timerValue);
                break;
        }
    }

    private void playSound(String sound) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    private void sizeDialog() {
        boolean rowsTest = true;
        boolean columnsTest = true;
        boolean rcTest = true;

        while (rcTest) {
            while (rowsTest) {
                try {
                    rows = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many rows do you want?"));
                    rowsTest = false;
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(frame, "Not a valid number");
                }
            }
            while (columnsTest) {
                try {
                    columns = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many columns do you want?"));
                    columnsTest = false;
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(frame, "Not a valid number");
                }
            }
            if ((rows * columns) / 2 > imageIcon.length || rows * columns < 4) {
                JOptionPane.showMessageDialog(frame, "Too many or too few tiles");
                rowsTest = true;
                columnsTest = true;
            } else if ((rows * columns) % 2 != 0) {
                JOptionPane.showMessageDialog(frame, "You need an even number of tiles");
                rowsTest = true;
                columnsTest = true;
            } else {
                rcTest = false;
            }
        }
    }

    private void timerDialog() {
        boolean timerTest1 = true;
        boolean timerTest2 = true;

        while (timerTest1) {
            while (timerTest2) {
                try {
                    timerValue = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many milliseconds would you like the delay to be? (5 seconds maximum"));
                    timerTest2 = false;
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(frame, "Not a valid number");
                }
            }
            if (timerValue < 0) {
                JOptionPane.showMessageDialog(frame, "wat?!");
                timerTest2 = true;
            } else if (timerValue > 5000) {
                JOptionPane.showMessageDialog(frame, "That is way too long, you silly goose");
                timerTest2 = true;
            } else {
                timerTest1 = false;
            }
        }
    }

    private void soundDialog() {
        if (soundEnable) {
            menuSound.setText("Sound (Disabled)");
        } else {
            menuSound.setText("Sound (Enabled)");
        }
        soundEnable = !soundEnable;
    }

    public class CardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!cardTurned) {
                card1 = (Card) e.getSource();
                if (card1.getStatus() == Card.Status.HIDDEN) {
                    card1.setStatus(Card.Status.VISIBLE);
                    cardTurned = true;
                }
            } else {
                card2 = (Card) e.getSource();
                if (card2.getStatus() == Card.Status.HIDDEN) {
                    card2.setStatus(Card.Status.VISIBLE);
                    cardTurned = false;
                    frame.setEnabled(false);
                    if (card2.sameIcon(card1)) {
                        if (soundEnable) {
                            playSound("Sounds/Yes.wav");
                        }
                    } else {
                        if (soundEnable) {
                            playSound("Sounds/No.wav");
                        }
                    }
                    timer.start();
                }
            }
        }
    }

    public class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (card2.sameIcon(card1)) {
                card1.setStatus(Card.Status.MISSING);
                card2.setStatus(Card.Status.MISSING);
                checkPlayer();
                if (score1 + score2 == (rows * columns) / 2) {
                    timer.stop();
                    frame.setEnabled(true);
                    endGame();
                }
            } else {
                card1.setStatus(Card.Status.HIDDEN);
                card2.setStatus(Card.Status.HIDDEN);
                switchPlayer();
            }
            timer.stop();
            frame.setEnabled(true);
        }
    }

    private void menu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Game");
        JMenu menuSettings = new JMenu("Settings");
        menuBar.add(menuGame);
        menuBar.add(menuSettings);

        JMenuItem menuNewgame = new JMenuItem("New game");
        menuNewgame.addActionListener((ActionEvent event) -> newGame(rows, columns, timerValue));

        JMenuItem menuExit = new JMenuItem("Exit");
        menuExit.addActionListener((ActionEvent event) -> System.exit(0));

        menuGame.add(menuNewgame);
        menuGame.add(menuExit);

        JMenuItem menuSize = new JMenuItem("Size");
        menuSize.addActionListener((ActionEvent event) -> sizeDialog());
        menuSettings.add(menuSize);

        JMenuItem menuTimer = new JMenuItem("Timer");
        menuTimer.addActionListener((ActionEvent event) -> timerDialog());
        menuSettings.add(menuTimer);

        menuSound.addActionListener((ActionEvent event) -> soundDialog());
        menuSettings.add(menuSound);

        frame.setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Memory());
    }


    @Override
    public void run() {
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(width, height);
        frame.setResizable(false);
        menu();
    }
}

