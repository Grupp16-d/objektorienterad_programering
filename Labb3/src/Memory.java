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

    //Starts a new game with the current settings
    private void newGame() {
        int tiles = rows * columns;
        JButton[] cards = new JButton[tiles];
        Icon[] randomArray = new Icon[imageIcon.length];

        //Calculates the new width and height of the main windows according to the number of cards
        width = columns * 100 + 120;
        height = rows * 100 + 60;
        score1 = 0;
        score2 = 0;
        player1turn = true;

        //Creates a timer with the selected timerValue
        timer = new Timer((timerValue), new TimerListener());

        //Sets the correct settings of the labels and panels
        lblScore1.setText("Score: " + score1);
        lblScore2.setText("Score: " + score2);
        frame.setSize(width, height);
        players.setPreferredSize(new Dimension(100, height - 65));
        panePlayer1.setPreferredSize(new Dimension(100, (height - 65) / 2));
        panePlayer2.setPreferredSize(new Dimension(100, (height - 65) / 2));
        panePlayer1.setBackground(Color.yellow);
        panePlayer2.setBackground(Color.white);

        //Removes the old game
        frame.getContentPane().removeAll();

        //Adds the player panels to the main player panel
        panePlayer1.add(lblPlayer1);
        panePlayer1.add(lblScore1);
        panePlayer2.add(lblPlayer2);
        panePlayer2.add(lblScore2);
        players.add(panePlayer1);
        players.add(panePlayer2);

        //Adds the main player panel on the left side of the frame
        frame.add(players, BorderLayout.WEST);

        //Creates a copy of the array with images and randomises the copy
        System.arraycopy(imageIcon, 0, randomArray, 0, randomArray.length);
        Tools.randomOrder(randomArray);

        //Adds the first tiles/2 cards twice into an array of cards and then randomises the array
        for (int i = 0; i < tiles; i = i + 2) {
            cards[i] = new Card(randomArray[i / 2], Card.Status.HIDDEN);
            cards[i + 1] = new Card(randomArray[i / 2], Card.Status.HIDDEN);
        }
        Tools.randomOrder(cards);

        //Creates a panel for the cards and adds each card to the panel with an actionListener
        JPanel paneCards = new JPanel();
        for (JButton card : cards) {
            card.setPreferredSize(new Dimension(90, 90));
            card.addActionListener(new CardListener());
            paneCards.add(card);
        }

        //Adds the card panel to the frame and revalidate the frame.
        frame.add(paneCards, BorderLayout.EAST);
        paneCards.setPreferredSize(new Dimension(width - 100, height - 65));
        frame.revalidate();
    }

    //Checks which players turn it is and gives that player +1 to score and updates the label
    private void checkPlayer() {
        if (player1turn) {
            score1++;
            lblScore1.setText("Score: " + score1);
        } else {
            score2++;
            lblScore2.setText("Score: " + score2);
        }
    }

    //Switches the active player and changes color accordingly
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
        //Check who the winner
        String winner;
        if (score1 > score2) {
            winner = "Player 1";
        } else if (score1 == score2) {
            winner = "No one, it's a tie!";
        } else {
            winner = "Player 2";
        }

        Object[] options = {"Exit game", "Start new game"};

        //Gives a dialog popup with the option to start a new game with the newGame method or exit the game
        int n = JOptionPane.showOptionDialog(frame, "The winner is: " + winner, "Game finished",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[0]);
        switch (n) {
            case 0:
                System.exit(0);
                break;
            case 1:
                newGame();
                break;
        }
    }

    //Plays a sound file at the given path
    private void playSound(String sound) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error playing " + sound);
        }
    }

    //A series of dialog inputs to change size
    private void sizeDialog() {
        //boolean to determine whether or not the input is correct.
        boolean rowsTest = true;
        boolean columnsTest = true;
        boolean rcTest = true;

        //Loop that continues as long as there has not been a correct combination of rows and columns have been entered
        while (rcTest) {
            //Loop that continues as long as a valid int has not been enter in the rows input
            while (rowsTest) {
                try {
                    rows = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many rows do you want?"));
                    rowsTest = false;
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(frame, "Not a valid number");
                }
            }
            //Same as above but for columns
            while (columnsTest) {
                try {
                    columns = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many columns do you want?"));
                    columnsTest = false;
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(frame, "Not a valid number");
                }
            }
            /*Checks if the number of tiles of possible with the amount of pictures and general memory logic
            If the number of tiles is incorrect, the rows and column tests continue. if not the main loop boolean is
            set to false, which ends the loop*/
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
        //boolean to determine whether or not the input is correct.
        boolean timerTest1 = true;
        boolean timerTest2 = true;

        /*Same logic as the sizeDialog, a second loop that checks if the try catch goes through, and then a main loop
        that checks if the value is correct*/
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

    //Changes the text of the menuItem for sound and changes the soundEnabled value
    private void soundDialog() {
        if (soundEnable) {
            menuSound.setText("Sound (Disabled)");
            soundEnable = false;

        } else {
            menuSound.setText("Sound (Enabled)");
            soundEnable = true;
        }
    }

    //Listener for the card
    public class CardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            /*If cardTurned is not true then it is flipped and cardTurned is set to true. If cardTurned is true the second
            card is flipped and the two icons are compared to determine with sound should play. After that the timer for
            the delay is started and the frame is disabled*/
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
                            playSound("yes.wav");
                        }
                    } else {
                        if (soundEnable) {
                            playSound("no.wav");
                        }
                    }
                    timer.start();
                }
            }
        }
    }

    //Listener for the timer, which runs after the check of the second card
    public class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            /*The icons are check again, if they're the same the cards are removed and the player is given one point.
            If the maximum amount of points have been given the game is ended. If the icons are not the same the cards
            are hidden again and the players are switched. The timer is then stopped and the frame enabled*/
            if (card2.sameIcon(card1)) {
                card1.setStatus(Card.Status.MISSING);
                card2.setStatus(Card.Status.MISSING);
                checkPlayer();
                if (score1 + score2 == (rows * columns) / 2) {
                    timer.stop();
                    frame.setEnabled(true);
                    endGame();
                    return;
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

    //A Method that adds the menuBar to the frame
    private void menu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Game");
        JMenu menuSettings = new JMenu("Settings");
        menuBar.add(menuGame);
        menuBar.add(menuSettings);

        JMenuItem menuNewgame = new JMenuItem("New game");
        menuNewgame.addActionListener((ActionEvent event) -> newGame());

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

