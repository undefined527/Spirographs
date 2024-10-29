import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Frame extends JFrame 
{
    static final int WIDTH = 1050; 
    static final int HEIGHT = 700; 

    Frame()
    {
        this.setTitle("Spirograph");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        Spirograph spirograph = new Spirograph(new int[] {WIDTH, HEIGHT});

        //this.add(new ControlPanel(new int[] {350, HEIGHT}, spirograph), BorderLayout.WEST);
        this.add(spirograph, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
