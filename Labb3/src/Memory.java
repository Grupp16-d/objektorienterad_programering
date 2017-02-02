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

}
