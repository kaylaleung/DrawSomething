package drawsomething;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class DrawSomething extends JFrame implements ActionListener, MouseListener {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private String menuItemSelected;
    private Color sampleColor = Color.BLUE;
    private int x = 0;
    private int y = 0;
    
    private class DrawPanel extends JPanel 
    {
        
        public void paintComponent(Graphics g) 
        {
            super.paintComponent(g);
            
            setBackground(Color.white);
            g.setColor(sampleColor);
            
            if("Line".equals(menuItemSelected))
            {
                g.drawLine(x, y, x+200, y+200);
            }
            else if("Rectangle".equals(menuItemSelected))
            {
                g.drawRect(x, y, 200, 200);
                g.fillRect(x, y, 200, 200);
            }
            else if("Circle".equals(menuItemSelected))
            {
                g.drawOval(x, y, 200, 200);
                g.fillOval(x, y, 200, 200 );
            }

            else if("Clear".equals(menuItemSelected))
            {
                g.clearRect(0,0, WIDTH, HEIGHT);
            }
                    
        }
    }

    public static void main(String[] args) 
    {
        DrawSomething gui = new DrawSomething();
        gui.setVisible(true);
    }
    
    public DrawSomething() 
    {
        super("Whiteboard");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        setLayout(new BorderLayout());
        
        DrawPanel p = new DrawPanel();
        add(p);
        
        p.addMouseListener(this);
 
        JMenu fileMenu = new JMenu("File");
        
        JMenuItem lineMenuItem = new JMenuItem("Line");
        lineMenuItem.addActionListener(this);
        fileMenu.add(lineMenuItem);
        
        JMenuItem rectangleMenuItem = new JMenuItem("Rectangle");
        rectangleMenuItem.addActionListener(this);
        fileMenu.add(rectangleMenuItem);
        
        JMenuItem circleMenuItem = new JMenuItem("Circle");
        circleMenuItem.addActionListener(this);
        fileMenu.add(circleMenuItem);

        JMenuItem colorMenuItem = new JMenuItem("Color");
        colorMenuItem.addActionListener(this);
        fileMenu.add(colorMenuItem);

        JMenuItem clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.addActionListener(this);
        fileMenu.add(clearMenuItem);
        
        JMenuBar bar = new JMenuBar();
        bar.add(fileMenu);
        setJMenuBar(bar);   
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String buttonString = e.getActionCommand();
    
            if("Color".equals(buttonString))
            {
                System.out.println("Color Change");
                sampleColor = JColorChooser.showDialog(this, "JColorChooser", sampleColor);
            }
            else
            {
                menuItemSelected = buttonString;
            }
        repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        int xcoor = e.getX();
        int ycoor = e.getY();
        this.x = e.getX();
        this.y = e.getY();
        System.out.println("(" + xcoor+","+ycoor+")");
        repaint();
    }   
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseExited(MouseEvent e){}
    @Override
    public void mouseReleased(MouseEvent e){}
    
}
