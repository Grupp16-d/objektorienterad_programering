import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;

public class Memory implements Runnable {
    File folder = new File("CardImages");
    File[] pictures = folder.listFiles();
    Icon[] imageIcon = new Icon[pictures.length];
    JFrame frame = new JFrame("Memory");

    Timer timer;

    int rows = 4;
    int columns = 4;
    int timerValue = 1500;


    private int score1 = 0;
    private int score2 = 0;

    JLabel lblScore1 = new JLabel("0");
    JLabel lblScore2 = new JLabel("0");

    private int width = 400;
    private int height = 200;

    boolean player1turn = true;
    boolean cardTurned = false;

    Card card1;
    Card card2;

    JPanel players = new JPanel();
    JPanel panePlayer1 = new JPanel();
    JLabel lblPlayer1 = new JLabel("Player 1");
    JPanel panePlayer2 = new JPanel();
    JLabel lblPlayer2 = new JLabel("Player 2");







    public Memory() {
        for (int i = 0; i < pictures.length; i++) {
            imageIcon[i] = new ImageIcon(pictures[i].getPath());
        }
    }

    private void checkPlayer() {
        if (player1turn) {
            score1++;
            lblScore1.setText("Score: " + score1);
        }else{
            score2++;
            lblScore2.setText("Score: " + score2);
        }
    }

    private void switchPlayer() {
        if (player1turn) {
            player1turn = false;
            panePlayer1.setBackground(Color.white);
            panePlayer2.setBackground(Color.yellow);
        } else {
            player1turn = true;
            panePlayer1.setBackground(Color.yellow);
            panePlayer2.setBackground(Color.white);
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
                dialogOptions();
                break;
        }
    }

    public void playSound(String sound) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }


    private void newGame(int n, int m, int t) {
        int tiles = n * m;
        Icon[] randomArray = new Icon[imageIcon.length];
        System.arraycopy(imageIcon, 0, randomArray, 0, randomArray.length);
        Tools.randomOrder(randomArray);
        JButton[] cards = new JButton[tiles];

        for (int i = 0; i < tiles; i = i + 2) {
            cards[i] = new Card(randomArray[i / 2], Card.Status.HIDDEN);
            cards[i + 1] = new Card(randomArray[i / 2], Card.Status.HIDDEN);
        }

        Tools.randomOrder(cards);

        player1turn = true;

        timer = new Timer((t), new TimerListener());

        score1 = 0;
        score2 = 0;
        lblScore1.setText("Score: " + score1);
        lblScore2.setText("Score: " + score2);


        width = m * 100 + 120;
        height = n * 100 + 60;
        frame.setSize(width, height);

        frame.getContentPane().removeAll();

        JPanel paneButtons = new JPanel();
        paneButtons.setPreferredSize(new Dimension(width, 65));

        JButton startgame = new JButton("New game");
        paneButtons.add(startgame);
        startgame.addActionListener(new StartGameListener());
        JButton exit = new JButton("Exit game");
        paneButtons.add(exit);
        exit.addActionListener(new ExitListener());

        frame.add(paneButtons, BorderLayout.PAGE_END);

        players.setPreferredSize(new Dimension(100, height - 65));
        panePlayer1.setPreferredSize(new Dimension(100, (height - 65) / 2));
        panePlayer2.setPreferredSize(new Dimension(100, (height - 65) / 2));

        players.add(panePlayer1);
        players.add(panePlayer2);
        panePlayer1.add(lblPlayer1);
        panePlayer1.add(lblScore1);
        panePlayer2.add(lblPlayer2);
        panePlayer2.add(lblScore2);

        panePlayer1.setBackground(Color.yellow);
        panePlayer2.setBackground(Color.white);

        frame.add(players, BorderLayout.WEST);

        JPanel paneCards = new JPanel();
        for (JButton card : cards) {
            card.setPreferredSize(new Dimension(90, 90));
            card.addActionListener(new CardListener());
            paneCards.add(card);
            card.setVisible(true);
        }
        frame.add(paneCards, BorderLayout.EAST);
        paneCards.setPreferredSize(new Dimension(width - 100, height - 65));

        paneCards.setVisible(true);
        frame.revalidate();
    }

    private void dialogOptions() {
        boolean rowsTest = true;
        boolean columnsTest = true;
        boolean inputTest = true;
        boolean timerTest1 = true;
        boolean timerTest2 = true;

        while (inputTest) {
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
                inputTest = false;
            }
        }
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
            }else if (timerValue > 5000){
                JOptionPane.showMessageDialog(frame, "That is way too long, you silly goose");
                timerTest2 = true;
            }else{
                timerTest1 = false;
            }
        }
        newGame(rows, columns, timerValue);
    }


    public class StartGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dialogOptions();
        }
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
                }else{
                    playSound("yes.wav");
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

    public class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Memory());
    }

    private void menu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Game");
        JMenu menuSettings = new JMenu("Settings");
        menuBar.add(menuGame);
        menuBar.add(menuSettings);
        JMenuItem menuNewgame = new JMenuItem("New game");

        menuNewgame.addActionListener((ActionEvent event) -> {
            newGame(rows, columns, timerValue);
        });
        JMenuItem menuExit = new JMenuItem("Exit");
        menuExit.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
        menuGame.add(menuNewgame);
        menuGame.add(menuExit);

        JMenuItem menuNewgame = new JMenuItem("New game");




        frame.setJMenuBar(menuBar);
    }


    @Override
    public void run() {
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(width, height);
        frame.setResizable(false);
        menu();

        /*JPanel paneButtons = new JPanel();
        paneButtons.setPreferredSize(new Dimension(width, 50));

        JButton startgame = new JButton("New game");
        paneButtons.add(startgame);
        startgame.addActionListener(new StartGameListener());
        JButton exit = new JButton("Exit game");
        paneButtons.add(exit);
        exit.addActionListener(new ExitListener());

        frame.add(paneButtons, BorderLayout.PAGE_END);*/
    }
}

