import javax.swing.*;

public class Card extends JButton{

    public enum Status {
        HIDDEN, VISIBLE, MISSING
    }

    private Status cardStatus;
    private Icon cardIcon;

    public Card(Icon x){
        this.cardStatus = Status.MISSING;
        this.cardIcon = x;
    }

    public Card(Icon x, Status s){
        this.cardStatus = s;
        this.cardIcon = x;
    }

    public void setStatus(Status s){
        this.cardStatus = s;
    }

    public Status getStatus(){
        return this.cardStatus;
    }

    public boolean sameIcon(Card b){
        boolean comp = this.cardIcon == b.cardIcon;
        return comp;
    }
}
