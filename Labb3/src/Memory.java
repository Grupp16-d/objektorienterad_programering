import javax.swing.*;
import java.io.File;
import java.io.ObjectInput;

public class Memory {
    File folder = new File("CardImages");
    File[] pictures = folder.listFiles();
    Object[] imageIcon = new Object[pictures.length];

    public Memory(){
        for(int i = 0; i < pictures.length; i++){
            imageIcon[i] = new ImageIcon(pictures[i].getPath());
        }
    }

}
