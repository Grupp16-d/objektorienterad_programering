import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Memory implements Runnable {
    File folder = new File("CardImages");
    File[] pictures = folder.listFiles();
    Icon[] imageIcon = new Icon[pictures.length];
    JFrame frame = new JFrame("Memory");

    Timer timer = new Timer(1500, new TimerListener());

    int rows;
    int columns;

    private int score1 = 0;
    private int score2 = 0;

    JLabel lblScore1 = new JLabel(Integer.toString(score1));
    JLabel lblScore2 = new JLabel(Integer.toString(score2));

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
            lblScore1.setText(Integer.toString(score1));
        }
        if (!player1turn) {
            score2++;
            lblScore2.setText(Integer.toString(score2));
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

    private void endGame(){
        String winner;
        if(score1 > score2){
            winner = "Player 1";
        }else if(score1 == score2){
            winner = "No one, it's a tie!";
        }else{
            winner = "Player 2";
        }

        Object[] options = {"Exit game", "Start new game"};

        int n = JOptionPane.showOptionDialog(frame, "The winner is: " + winner, "Game finished",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[0]);
        switch (n){
            case 0:
                System.exit(0);
            case 1:
                dialogOptions();
                break;
        }
    }



    private void newGame(int n, int m) {
        int tiles = n * m;
        Icon[] randomArray = new Icon[imageIcon.length];
        System.arraycopy(imageIcon, 0, randomArray, 0, randomArray.length);
        Tools.randomOrder(randomArray);
        JButton[] cards = new JButton[tiles];

        for (int i = 0; i < tiles; i = i + 2) {
            cards[i] = new Card(randomArray[i/2], Card.Status.HIDDEN);
            cards[i + 1] = new Card(randomArray[i/2], Card.Status.HIDDEN);
        }

        Tools.randomOrder(cards);

        player1turn = true;

        score1 = 0;
        score2 = 0;
        lblScore1.setText(Integer.toString(score1));
        lblScore2.setText(Integer.toString(score2));


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

        players.setPreferredSize(new Dimension(120, height - 65));
        panePlayer1.setPreferredSize(new Dimension(120, (height - 65) / 2));
        panePlayer2.setPreferredSize(new Dimension(120, (height - 65) / 2));

        players.add(panePlayer1);
        players.add(panePlayer2);
        panePlayer1.add(lblPlayer1);
        panePlayer1.add(lblScore1);
        panePlayer2.add(lblPlayer2);
        panePlayer2.add(lblScore2);

        panePlayer1.setBackground(Color.yellow);
        panePlayer2.setBackground(Color.white);


        frame.add(players, BorderLayout.WEST);

        panePlayer1.setBorder(BorderFactory.createLineBorder(Color.black));
        panePlayer2.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel paneCards = new JPanel();
        for (JButton card : cards) {
            card.setPreferredSize(new Dimension(90, 90));
            card.addActionListener(new CardListener());
            paneCards.add(card);
            card.setVisible(true);
        }
        frame.add(paneCards, BorderLayout.EAST);
        paneCards.setPreferredSize(new Dimension(width - 120, height - 65));

        paneCards.setVisible(true);
        frame.revalidate();
    }

    private void dialogOptions(){
        boolean rowsTest = true;
        boolean columnsTest = true;
        boolean inputTest = true;
        while (inputTest) {
            while (rowsTest) {
                try {
                    rows = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many rows do you want?"));
                    rowsTest = false;
                } catch (NumberFormatException error) {
                    JOptionPane.showMessageDialog(frame, "Not a valid number");
                }
            }
            while (columnsTest) {
                try {
                    columns = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many columns do you want?"));
                    columnsTest = false;
                } catch (NumberFormatException error) {
                    JOptionPane.showMessageDialog(frame, "Not a valid number");
                }
            }
            if ((rows * columns)/2 > imageIcon.length || rows * columns < 4) {
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
        newGame(rows, columns);
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
                if(score1 + score2 == (rows * columns)/2){
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

    public class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Memory());
    }

    @Override
    public void run() {
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(width, height);
        frame.setResizable(false);

        JPanel paneButtons = new JPanel();
        paneButtons.setPreferredSize(new Dimension(width, 50));

        JButton startgame = new JButton("New game");
        paneButtons.add(startgame);
        startgame.addActionListener(new StartGameListener());
        JButton exit = new JButton("Exit game");
        paneButtons.add(exit);
        exit.addActionListener(new ExitListener());

        frame.add(paneButtons, BorderLayout.PAGE_END);
    }
}

