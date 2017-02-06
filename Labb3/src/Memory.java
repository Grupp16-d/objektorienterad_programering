import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Memory implements Runnable {
    File folder = new File("CardImages");
    File[] pictures = folder.listFiles();
    Icon[] imageIcon = new Icon[pictures.length];
    JFrame frame = new JFrame("Memory");
    JLabel score1 = new JLabel("0");
    JLabel score2 = new JLabel("0");

    private int width = 1000;
    private int height = 800;


    public Memory(){
        for(int i = 0; i < pictures.length; i++){
            imageIcon[i] = new ImageIcon(pictures[i].getPath());
        }
    }

    public void newGame(int n, int m){
        int tiles = n * m;
        Icon[] randomArray = new Icon[imageIcon.length];
        System.arraycopy(imageIcon, 0, randomArray, 0, randomArray.length);
        Tools.randomOrder(randomArray);
        JButton[] cards = new JButton[tiles];

        for(int i = 0; i < tiles/2; i++){
            for(int j = 0; j < tiles; j = j + 2){
                cards[j] = new Card(randomArray[i], Card.Status.HIDDEN);
                cards[j+1] = new Card(randomArray[i], Card.Status.HIDDEN);
            }
        }
        Tools.randomOrder(cards);

        JPanel panePlayer1 = new JPanel();
        JLabel lblPlayer1 = new JLabel("Player 1");
        JPanel panePlayer2 = new JPanel();
        JLabel lblPlayer2 = new JLabel("Player 2");

        panePlayer1.add(lblPlayer1);
        panePlayer1.add(score1);
        panePlayer2.add(lblPlayer2);
        panePlayer2.add(score2);
        frame.add(panePlayer1);
        frame.add(panePlayer2);

        panePlayer1.setVisible(true);
        panePlayer2.setVisible(true);
        lblPlayer1.setVisible(true);
        lblPlayer2.setVisible(true);

        JPanel paneCards = new JPanel();
        for(JButton card : cards){
            card.setPreferredSize(new Dimension(70, 70));
            card.addActionListener(new CardListener());
            paneCards.add(card);
            card.setVisible(true);
        }
        frame.add(paneCards);
        paneCards.setVisible(true);
        frame.revalidate();
    }

    public class StartGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean rowsTest = true;
            boolean columnsTest = true;
            boolean inputTest = true;
            int rows = 0;
            int columns = 0;
            while(inputTest){
                while(rowsTest){
                    try{
                        rows = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many rows do you want?"));
                        rowsTest = false;
                    }
                    catch(NumberFormatException error){
                        JOptionPane.showMessageDialog(frame, "Not a valid number");
                    }
                }
                while(columnsTest){
                    try{
                        columns = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many columns do you want?"));
                        columnsTest = false;
                    }
                    catch(NumberFormatException error){
                        JOptionPane.showMessageDialog(frame, "Not a valid number");
                    }
                }
                if(rows * columns > imageIcon.length || rows * columns < 4){
                    JOptionPane.showMessageDialog(frame, "Too many or too few tiles");
                    rowsTest = true;
                    columnsTest = true;
                }else if((rows * columns) % 2 != 0){
                    JOptionPane.showMessageDialog(frame, "You need an even number of tiles");
                    rowsTest = true;
                    columnsTest = true;
                }else{
                    inputTest = false;
                }
            }
            newGame(rows, columns);
        }
    }

    public class CardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }


    public class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Memory());
    }

    @Override
    public void run(){
        final Memory memory = new Memory();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(width, height);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JButton startgame = new JButton("New game");
        frame.add(startgame);
        startgame.addActionListener(new StartGameListener());
        JButton exit = new JButton("Exit game");
        frame.add(exit);
        exit.addActionListener(new ExitListener());



    }
}
