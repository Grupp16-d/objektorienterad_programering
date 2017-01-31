import javax.swing.*;

public class Card extends JButton{

    public enum Status {
        HIDDEN, VISIBLE, MISSING
    }

    public Status status;
    public Icon picture;

    public Card(Icon x){
        this.status = Status.MISSING;
        this.picture = x;
    }

    public Card(Icon x, Status s){
        this.status = s;
        this.picture = x;
    }

    public void setStatus(Status s){
        this.status = s;
    }

    public Status getStatus(){
        return status;
    }

    public boolean sameIcon(Card b){
        
    }
}
