import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Money_Animation extends JPanel implements ActionListener {
    final int Width = 600;
    final int height = 700;
    Image money;
    Timer timer;
    int xvelocity = 3;
    int yvelocity = 8;
    int x = 3;
    int y;
    int ch = 0;

    Money_Animation(int del, int loc, String Money){
        ch = loc;
        y = del;
        setBounds((int)(11.7*10),(int)(35.5*10),(14*10),(10*10));
        setBackground(Color.BLACK);
        setOpaque(false);
        money = new ImageIcon(Money).getImage();
        timer = new Timer(2, this);
        timer.start();
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(money, x, y,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(ch == 1){
            if(y >= -67){
                y = y - yvelocity;
            }
            repaint();
        }

        if(ch == 2){
            if(y <= 110){
                y = y + yvelocity;
            }
            repaint();
        }
    }
}
