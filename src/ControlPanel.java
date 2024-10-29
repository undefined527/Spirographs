import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ControlPanel extends JPanel implements ActionListener
{
    Settings currentSettings;

    ControlPanel(int[] size, Spirograph spirograph)
    {
        this.setPreferredSize(new Dimension(size[0], size[1]));
        this.setFocusable(true);

        JPanel handsPanel = new JPanel();
        handsPanel.setLayout(new BoxLayout(handsPanel, BoxLayout.Y_AXIS));
        
        JButton addNewHandButton = new JButton("Add new hand");
        handsPanel.add(addNewHandButton);

        this.currentSettings = spirograph.currentSettings;

        for (int i = 0; i < this.currentSettings.hands.length; i++)
        {
            System.out.println(i);
            handsPanel.add(new HandsControlPanel(this.currentSettings.hands[i]));
        }

        JScrollPane handsScrollPane = new JScrollPane(handsPanel);
        handsScrollPane.setPreferredSize(new Dimension(size[0] - 10, size[1] / 4));
        this.add(handsScrollPane);
    }

    void updatePanel()
    {
        this.revalidate();
        this.repaint();
    }

    class HandsControlPanel extends JPanel
    {
        static FocusListener focusListener = new FocusListener() 
        {
            @Override
            public void focusGained(FocusEvent e)
            {

            }
            
            @Override
            public void focusLost(FocusEvent e)
            {
                System.out.println("lost");
            }
        };

        HandsControlPanel(Hand hand)
        {
            JLabel lengthLabel = new JLabel("Length:");
            this.add(lengthLabel);

            JTextArea lengthTextArea = new JTextArea();
            lengthTextArea.setPreferredSize(new Dimension(40, 20));
            lengthTextArea.setText(Double.toString(hand.length));
            this.add(lengthTextArea);

            JLabel rotationSpeedLabel = new JLabel("Rotation speed:");
            this.add(rotationSpeedLabel);

            JTextArea rotationSpeedTextArea = new JTextArea();
            rotationSpeedTextArea.setPreferredSize(new Dimension(40, 20));
            rotationSpeedTextArea.setText(Double.toString(hand.rotationSpeed));
            rotationSpeedTextArea.addFocusListener(focusListener);
            this.add(rotationSpeedTextArea);

            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(e -> {});
            this.add(deleteButton);
        }

        HandsControlPanel()
        {
            JLabel lengthLabel = new JLabel("Length:");
            this.add(lengthLabel);

            JTextArea lengthTextArea = new JTextArea();
            lengthTextArea.setText("0");
            this.add(lengthLabel);

            JLabel rotationSpeedLabel = new JLabel("Rotation speed:");
            this.add(rotationSpeedLabel);

            JTextArea rotationSpeedTextArea = new JTextArea();
            lengthTextArea.setText("0");
            this.add(rotationSpeedTextArea);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        System.out.println(e);
    }
}
