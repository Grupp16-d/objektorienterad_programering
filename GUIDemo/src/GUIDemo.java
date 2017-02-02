import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIDemo implements Runnable /*, ActionListener*/ {
    private int count = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUIDemo());
    }

    private JLabel labelCount;
    private JButton buttonCount, buttonReset;
    private JCheckBox decreaseCheckBox;

    // We can implement one ActionListener per button,
    // one for buttonCount, ...
    private class ButtonCountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (decreaseCheckBox.isSelected()) {
                count--;
            } else {
                count++;
            }
            labelCount.setText(Integer.toString(count));
        }

    }

    // ... and one for buttonReset, ...
    private class ButtonResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            count = 0;
            labelCount.setText("0");
        }
    }

    // ... or just one for both of them.
    // Here we let the class itself implement the interface
    // so ", ActionListener" needs to be added to its declaration line.
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == buttonCount) {
//			count++;
//			labelCount.setText(Integer.toString(count));
//		} else if (e.getSource() == buttonReset) {
//			count = 0;
//			labelCount.setText("0");
//		}
//	}


    @Override
    public void run() {
        JFrame frame = new JFrame("GUIDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        labelCount = new JLabel("0");

        buttonCount = new JButton("Count");
        buttonCount.addActionListener(
                new ButtonCountListener());
//		buttonCount.addActionListener(this);

        buttonReset = new JButton("Reset");
        buttonReset.addActionListener(
                new ButtonResetListener());
        buttonReset.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label1 = new JLabel("label1");
        JLabel label2 = new JLabel("label2");
        panel.add(label1);
        panel.add(label2);

        decreaseCheckBox = new JCheckBox("Decrease");

        frame.add(labelCount);
        frame.add(buttonCount);
        frame.add(buttonReset);
        frame.add(decreaseCheckBox);
        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }

}