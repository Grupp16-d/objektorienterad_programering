import javax.swing.*;
import java.awt.*;

public class Card extends JButton {

    private Status cardStatus;
    private Icon cardIcon;

    public enum Status {
        HIDDEN, VISIBLE, MISSING
    }

    public Card(Icon x) {
        this.setBorderPainted(false);
        this.setOpaque(true);
        this.setBackground(Color.white);
        this.cardStatus = Status.MISSING;
        this.cardIcon = x;
        this.setIcon(null);
    }

    public Card(Icon x, Status s) {
        this.setBorderPainted(false);
        this.setOpaque(true);
        this.cardStatus = s;
        this.cardIcon = x;
        switch (s) {
            case MISSING:
                this.setBackground(Color.white);
                this.setIcon(null);
                break;
            case HIDDEN:
                this.setBackground(Color.blue);
                this.setIcon(null);
                break;
            case VISIBLE:
                this.setBackground(Color.blue);
                this.setIcon(x);
                break;
        }

    }

    public boolean sameIcon(Card c) {
        boolean comp = this.cardIcon == c.cardIcon;
        return comp;
    }

    public void setStatus(Status s) {
        this.cardStatus = s;
        switch (s) {
            case MISSING:
                this.setBackground(Color.white);
                this.setIcon(null);
                break;
            case HIDDEN:
                this.setBackground(Color.blue);
                this.setIcon(null);
                break;
            case VISIBLE:
                this.setBackground(Color.blue);
                this.setIcon(this.cardIcon);
                break;
        }
    }

    public Status getStatus() {
        return this.cardStatus;
    }
}
