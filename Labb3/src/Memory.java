import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class Memory implements Runnable {
    File folder = new File("CardImages");
    File[] pictures = folder.listFiles();
    Icon[] imageIcon = new Icon[pictures.length];
    JFrame frame = new JFrame("Memory");

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
        JButton[] cards = new JButton[n];

        for(int i = 0; i < tiles/2; i++){
            for(int j = 0; j < tiles; j = j + 2){
                cards[j] = new Card(randomArray[i]);
                cards[j+1] = new Card(randomArray[i]);
            }
        }

        Tools.randomOrder(cards);

        for(int i = 0; i < cards.length; i++){
            frame.add(cards[i]);
        }
    }

    public class NewGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean rowsTest = false;
            boolean columnsTest = false;
            boolean inputTest = false;
            int rows = 0;
            int columns = 0;
            while(inputTest){
                while(rowsTest = false){
                    try{
                        rows = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many rows do you want?"));
                        rowsTest = true;
                    }
                    catch(NumberFormatException error){
                        JOptionPane.showMessageDialog(frame, "Not a valid number");
                    }
                }
                while(columnsTest = false){
                    try{
                        columns = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many columns do you want?"));
                        columnsTest = true;
                    }
                    catch(NumberFormatException error){
                        JOptionPane.showMessageDialog(frame, "Not a valid number");
                    }
                }
                if(rows * columns > imageIcon.length){
                    JOptionPane.showMessageDialog(frame, "Too many tiles");
                }else{
                    inputTest = true;
                }
            }
            newGame(rows, columns);
        }
    }


    public class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Memory());
    }

    @Override
    public void run(){
        final Memory memory = new Memory();
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label1 = new JLabel("label1");
        JLabel label2 = new JLabel("label2");
        panel.add(label1);
        panel.add(label2);

        JButton newgame = new JButton("New game");
        newgame.addActionListener(
                new NewGameListener());
        JButton exit = new JButton("Exit game");
        newgame.addActionListener(
                new ExitListener());
        frame.add(exit);
        frame.add(newgame);


    }
}
