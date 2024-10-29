import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Spirograph extends JPanel implements ActionListener
{
    static final Color BACKGROUND_COLOR = Color.BLACK;
    static final int DELAY = 5;
    static final float HAND_WIDTH = 4.0f;
    static final float LINE_WIDTH = 3.0f; // the width of the line created in the "line" mode
    static final Settings DEFAULT_SETTINGS = App.presetSettings[3];

    boolean running;
    Timer timer;
    BufferedImage spirograph;
    double[] previousLastPosition;
    Settings currentSettings;

    Spirograph(int[] size)
    {
        this.setBackground(BACKGROUND_COLOR);
        this.setFocusable(true);
        this.addKeyListener(new InputKeyAdapter());
        this.spirograph = new BufferedImage(size[0], size[1], BufferedImage.TYPE_INT_RGB);
        this.setPreferredSize(new Dimension(size[0], size[1]));
        this.currentSettings = DEFAULT_SETTINGS;

        start();
    }

    void start()
    {
        applySettings(DEFAULT_SETTINGS);

        running = true;

        timer = new Timer(DELAY, this);
        timer.start();
    }

    void applySettings(Settings settings)
    {
        this.spirograph.flush();
        
        currentSettings = settings;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(this.spirograph, 0, 0, this);
        
        draw((Graphics2D)g);
    }

    void draw(Graphics2D g)
    {
        if (running)
        {
            double[] lastPosition = new double[] {this.getSize().width / 2, this.getSize().height / 2}; 

            g.setStroke(new BasicStroke(HAND_WIDTH));
            g.setColor(Color.RED);
            
            for (int i = 0; i < currentSettings.hands.length; i++)
            {
                Hand hand = currentSettings.hands[i];
                double[] relativeEndPosition = new double[] {Math.cos(hand.orientation) * hand.length, Math.sin(hand.orientation) * hand.length};
                
                if (currentSettings.showHand)
                {
                    g.drawLine((int)lastPosition[0], (int)lastPosition[1], (int)(lastPosition[0] + relativeEndPosition[0]), (int)(lastPosition[1] + relativeEndPosition[1]));
                }
    
                lastPosition = new double[] {lastPosition[0] + relativeEndPosition[0], lastPosition[1] + relativeEndPosition[1]};
            }

            switch (currentSettings.drawMode) 
            {
                case Settings.DrawMode.LINE:
                    if (previousLastPosition != null)
                    {
                        drawLine(previousLastPosition, lastPosition);
                    }
                case Settings.DrawMode.POINT:
                    drawPoint(lastPosition);
                default:
                    break;
            }

            previousLastPosition = lastPosition;
        }
    }

    void drawPoint(double[] point)
    {
        Graphics2D g2d = this.spirograph.createGraphics();

        g2d.fillOval((int)point[0], (int)point[1], 2, 2);

        g2d.dispose();
    }

    void drawLine(double[] pointOne, double[] pointTwo)
    {
        Graphics2D g2d = this.spirograph.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setStroke(new BasicStroke(LINE_WIDTH));

        g2d.drawLine((int)pointOne[0], (int)pointOne[1], (int)pointTwo[0], (int)pointTwo[1]);

        g2d.dispose();
    }

    void updateHands()
    {
        for (int i = 0; i < currentSettings.hands.length; i++)
            {
                Hand hand = currentSettings.hands[i];
                
                hand.orientation += (hand.rotationSpeed * DELAY / 1000 ) % (2 * Math.PI);
            }
    }

    static void saveImage(BufferedImage image)
    {
        File outputFile = new File("F:\\Code\\Data\\output_image.png");

        try 
        {
            ImageIO.write(image, "PNG", outputFile);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }        
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (running)
        {
            updateHands();
        }

        repaint();
    }

    class InputKeyAdapter extends KeyAdapter 
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            if (e.getKeyCode() == KeyEvent.VK_N)
            {
                saveImage(spirograph);
            }
        }
    } 
}