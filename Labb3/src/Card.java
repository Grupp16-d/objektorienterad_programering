import javax.swing.*;
import java.awt.*;

//class for cards
public class Card extends JButton {

    //variables for card status and card icons
    private Status cardStatus;
    private Icon cardIcon;

    //The 3 values card status can have
    public enum Status {
        HIDDEN, VISIBLE, MISSING
    }

    //
    public Card(Icon x) {
        this.setBorderPainted(false);
        this.setOpaque(true);
        this.setBackground(Color.white);
        this.cardStatus = Status.MISSING;
        this.cardIcon = x;
        this.setIcon(null);
    }

    //create a card with a icon with a status depending on the status it sets the background or if it is visible
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

    //check if too card have the same icon
    public boolean sameIcon(Card c) {
        boolean comp = this.cardIcon == c.cardIcon;
        return comp;
    }

    //change status on a existing card and change background and icon accordingly
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

    //return status of the selected card
    public Status getStatus() {
        return this.cardStatus;
    }
}
