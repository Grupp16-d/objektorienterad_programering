import javax.swing.*;
import java.io.File;

public class Memory {
    File folder = new File("CardImages");
    File[] pictures = folder.listFiles();
    Icon[] imageIcon = new Icon[pictures.length];

    public Memory(){
        for(int i = 0; i < pictures.length; i++){
            imageIcon[i] = new ImageIcon(pictures[i].getPath());
        }
    }

    public void newGame(int n){
        Icon[] randomArray = new Icon[imageIcon.length];
        System.arraycopy(imageIcon, 0, randomArray, 0, randomArray.length);
        Tools.randomOrder(randomArray);
        Object[] cards = new Icon[n];

        for(int i = 0; i < n/2; i++){
            for(int j = 0; j < n; j = j + 2){
                cards[j] = new Card(randomArray[i]);
                cards[j+1] = new Card(randomArray[i]);
            }
        }

        Tools.randomOrder(cards);
    }
    @Override
	public void actionPerformed(ActionEvent e) {
	if (e.getSource() == buttonCount) {
		count++;
		labelCount.setText(Integer.toString(count));
		} else if (e.getSource() == buttonReset) {
            count = 0;
			labelCount.setText("0");
		}
	}

    public static void main(String[] args){
        final Memory memory = new Memory();
        JFrame frame = new JFrame("Memory");
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton newgame = new JButton("New game");
        newgame.addActionListener(
                new NewGameListener());
        JButton exit = new JButton("Exit game");
        newgame.addActionListener(
                new ExitListener());
        frame.add(newgame);
        frame.add(exit);


    }

}
